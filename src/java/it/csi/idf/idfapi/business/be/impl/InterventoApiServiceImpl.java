package it.csi.idf.idfapi.business.be.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import it.csi.idf.idfapi.business.be.InterventoApi;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.InterventoExcelEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class InterventoApiServiceImpl extends SpringSupportedResource implements InterventoApi {

	static final Logger logger = Logger.getLogger(InterventoApiServiceImpl.class);
	
	@Autowired
	private InterventoDAO interventoDao;
	
	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;

	@Override
	public Response getInterventi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
		return Response.ok(interventoDao.findAllInterventi()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getInterventoByID(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(interventoDao.findInterventoPianoByID(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveIntervento(TipoInterventoDatiTecnici body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(interventoDao.createIntervento(body)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateIntervento(Intervento body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		//TODO See if this is needed 
		return null;
	}

	@Override
	public Response generateExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<InterventoPiano> list;
		
		List<InterventoExcelEnum> columns = Arrays.asList(InterventoExcelEnum.values());
		
		list = interventoDao.findInterventiPianiByIdGeoPlPfa(excel.getIdGeoPfaInterventi());
		
		// Create a Sheet
		Sheet sheet = hwb.createSheet("Interventi excel");
		
		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();
		
		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		
		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);
		
		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);
		
		// Create cells
		for (int i = 0; i < columns.size(); i++) {
		 Cell cell = headerRow.createCell(i);
		 cell.setCellValue(columns.get(i).getValue());
		 cell.setCellStyle(headerCellStyle);
		 headerRow.setRowStyle(headerCellStyle);
				}
		
		int rowNum = 1;
		if (list != null) {
			for (InterventoPiano interventi : list) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(interventi.getPfaDenominazione());
				row.createCell(1).setCellValue(interventi.getNrProgressivoInterv());
				row.createCell(2).setCellValue(interventi.getAnnataSilvana());
				row.createCell(3).setCellValue(Arrays.toString(interventi.getnParticelaForestale()));
				row.createCell(4)
						.setCellValue(interventi.getDataInizio() == null ? "" : interventi.getDataInizio().toString());
				row.createCell(5)
						.setCellValue(interventi.getDataFine() == null ? "" : interventi.getDataFine().toString());
				row.createCell(6).setCellValue(interventi.getDescrizione());
				row.createCell(7).setCellValue(interventi.getLocalita());
				row.createCell(8).setCellValue(interventi.getSuperficieInteressata());
				row.createCell(9).setCellValue(interventi.getM3Prelevati());
				row.createCell(10).setCellValue(interventi.getDescrStatoIntervento());
				row.createCell(11).setCellValue(interventi.getComunicazioneDiTaglio());
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
		String filename = "Interventi_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		
		return response.build();
	}

	@Override
	public Response getInterventiPianiByPlPfa(int idGeoPlPfa, int page, int limit, String sort, HttpServletRequest httpRequest) {
		try {
			return Response.ok(interventoDao.findInterventiPianiByIdGeoPlPfa(idGeoPlPfa, page, limit, sort)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response savePrimaPfaIntervento(PfaLottoLocalizza body, int idGeoPlPfa,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.saveLocalizzaLotto(body, userSessionDAO.getUser(httpRequest), idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getNumberOfNextStep(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getNumberOfNextStep(idIntervento)).build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
		
	}
}