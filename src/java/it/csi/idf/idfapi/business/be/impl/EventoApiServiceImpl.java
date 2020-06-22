package it.csi.idf.idfapi.business.be.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.EventoApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperEventoDAO;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.PlainPrimaPfaEvento;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.EventoExcelEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class EventoApiServiceImpl extends SpringSupportedResource implements EventoApi {

	static final Logger logger = Logger.getLogger(EventoApiServiceImpl.class);
	
	@Autowired
	private WrapperEventoDAO wrapperEventoDAO;

	EventoDAO eventoDao = null;

	public EventoDAO getEventoDao() {
		return eventoDao;
	}

	@Autowired
	public void setEventoDao(EventoDAO eventoDao) {
		this.eventoDao = eventoDao;
	}

	@Override
	public Response getAllEventi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<EventoDTO> eventi = getEventoDao().findAllEventi();

		return Response.ok(eventi).build();
	}

	@Override
	public Response getEventoByID(Integer idEvento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		EventoDTO evento;

		try {
			evento = getEventoDao().findEventoById(idEvento);
			return Response.ok(evento).build();

		} catch (RecordNotFoundException e) {
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		}

	}

	@Override
	public Response saveEvento(EventoDTO body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(eventoDao.createEvento(body)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response generateExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<EventoPiano> list;

		List<EventoExcelEnum> columns = Arrays.asList(EventoExcelEnum.values());

		list = eventoDao.findEventiPianoDTOByIdGeoPlPfa(excel.getIdGeoPfaEventi());

		// Create a Sheet
		Sheet sheet = hwb.createSheet("Eventi excel download");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);
		
		CellStyle textCellStyle = hwb.createCellStyle();
		textCellStyle.setDataFormat((short)5);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 1; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (list != null) {
			for (EventoPiano eventi : list) {
				Row row = sheet.createRow(rowNum++);
				
				row.createCell(0).setCellValue("");
				row.createCell(1).setCellValue(eventi.getProgressivoEventoPfa());
				row.createCell(2).setCellValue(eventi.getNomeBreve());
				row.createCell(3).setCellValue(Date.valueOf(eventi.getDataEvento()));
				row.createCell(4).setCellValue(Arrays.toString(eventi.getIdgeoPlParticelaForest()));
				row.createCell(5).setCellValue(eventi.getTipoEvento());
				row.createCell(6).setCellValue(eventi.getDescrEvento());
				row.createCell(7).setCellValue(eventi.getLocalita());
				row.createCell(8).setCellValue(eventi.getSupInteressataHa() == null ? 0 : eventi.getSupInteressataHa().doubleValue());
				
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);
		String filename = "eventi_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	@Override
	public Response getEventiByPlPfa(int idGeoPlPfa, int page, int limit, String sort, HttpServletRequest httpRequest) {
		try {
			return Response.ok(getEventoDao().findEventiPianoDTOByIdGeoPlPfa(idGeoPlPfa, page, limit, sort)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response savePrimaPfaEvento(PlainPrimaPfaEvento body, int idGeoPlPfa, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperEventoDAO.savePrimaPfaEvento(body, idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getSecondoPfaEvento(int idEvento, int idGeoPlPfa, PlainSecondoPfaEvento body, HttpServletRequest httpRequest) {
		try {
			return Response.ok(getEventoDao().findSecondoPfaEventoById(idEvento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSecondoPfaEvento(int idEvento, int idGeoPlPfa, PlainSecondoPfaEvento body, HttpServletRequest httpRequest) {
		try {
			wrapperEventoDAO.saveSecondoPfaEvento(body, idEvento);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getProgressiviNomiBreve(int idGeoPlPfa, HttpServletRequest httpRequest) {
		try {
			return Response.ok(getEventoDao().findProgressiviNomeBreve(idGeoPlPfa)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}