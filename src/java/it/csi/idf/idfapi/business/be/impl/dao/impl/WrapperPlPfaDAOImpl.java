package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AreaProtettaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlPfaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PopsemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RN2000DAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.AreaProtetta;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.dto.GeoPlPfaExcelDTO;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.mapper.RicadenzaHolderMapper;
import it.csi.idf.idfapi.util.EventoExcelEnum;
import it.csi.idf.idfapi.util.InterventoExcelEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.PfaExcelEnums;
import it.csi.idf.idfapi.util.PfaExcelNonPublicEnums;

@Component
public class WrapperPlPfaDAOImpl implements WrapperPlPfaDAO {

	static final Logger logger = Logger.getLogger(WrapperPlPfaDAOImpl.class);

	@Autowired
	private GeoPlPfaDAO geoPlPfaDAO;

	@Autowired
	private PopsemeDAO popsemeDAO;

	@Autowired
	private AreaProtettaDAO areaProtettaDAO;

	@Autowired
	private RN2000DAO rn2000DAO;

	@Autowired
	private EventoDAO eventoDAO;

	@Autowired
	private InterventoDAO interventoDAO;

	@Override
	public PaginatedList<GeoPfaSearch> getPublicPianiForestaliSearch(Map<String, String> queryParams) {
		return geoPlPfaDAO.searchPianiForestali(queryParams);
	}

	@Override
	public GeoPfaSearchDettaglio getPublicPfaSearchByID(Integer idGeoPlPfa) {
		GeoPfaSearchDettaglio geoPfaSearchDettaglio = geoPlPfaDAO.pianoForestaleSearchDettaglio(idGeoPlPfa);

		geoPfaSearchDettaglio
				.setRicadenzeAree(RicadenzaHolderMapper.areeToRicadenza(areaProtettaDAO.getByIdgeoPlPfa(idGeoPlPfa)));
		geoPfaSearchDettaglio
				.setRicadenzeRn2000(RicadenzaHolderMapper.rnsToRicadenza(rn2000DAO.getByIdgeoPlPfa(idGeoPlPfa)));
		geoPfaSearchDettaglio.setRicadenzePopseme(
				RicadenzaHolderMapper.popsemesToRicadenza(popsemeDAO.getFatPopsemeByIdgeoPlPfa(idGeoPlPfa)));

		return geoPfaSearchDettaglio;
	}

	@Override
	public byte[] generateExcel(ExcelDTO excel) {
		HSSFWorkbook hwb = new HSSFWorkbook();
		List<GeoPfaSearchDettaglio> pfaList = new ArrayList<>();

		// List<PfaExcelEnums> columns = Arrays.asList(PfaExcelEnums.values());

		for (GeoPlPfaExcelDTO idPfa : excel.getPfaExcelDto()) {
			Integer idGeoPfa = idPfa.getIdGeoPfa();
			pfaList.add(geoPlPfaDAO.pianoForestaleSearchDettaglio(idGeoPfa));
		}
		// Create sheet
		Sheet pfaSheet = hwb.createSheet("PFA");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = pfaSheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells

		boolean flag = excel.isFlag();
		if (flag) {
			List<PfaExcelEnums> publicPFAColumns = Arrays.asList(PfaExcelEnums.values());

			createHeaderCellsForPublicPFA(publicPFAColumns, headerRow, headerCellStyle);

			fillSheetForPublicPfa(pfaList, pfaSheet);

			resizeColums(publicPFAColumns, pfaSheet);

		} else {

			Sheet eventoSheet = hwb.createSheet("Eventi");
			Sheet interventoSheet = hwb.createSheet("Interventi");

			List<PfaExcelNonPublicEnums> columnsForPFASheet = Arrays.asList(PfaExcelNonPublicEnums.values());
			List<EventoExcelEnum> columnsForEventsSheet = Arrays.asList(EventoExcelEnum.values());
			List<InterventoExcelEnum> columnsForInterventiSheet = Arrays.asList(InterventoExcelEnum.values());

			List<EventoPiano> eventoList = new ArrayList<EventoPiano>();
			List<InterventoPiano> interventoList = new ArrayList<InterventoPiano>();

			for (GeoPlPfaExcelDTO idPfa : excel.getPfaExcelDto()) {

				Integer idGeoPfa = idPfa.getIdGeoPfa();
				eventoList.addAll(eventoDAO.findEventiPianoDTOByIdGeoPlPfa(idGeoPfa));
				interventoList.addAll(interventoDAO.findInterventiPianiByIdGeoPlPfa(idGeoPfa));
			}

			// List<EventoPiano> eventoList = eventoDAO.findEventiPianoDTOByIdGeoPlPfa();

			createHeaderCellsForPFA(columnsForPFASheet, headerRow, headerCellStyle);
			fillSheetForPfa(pfaList, pfaSheet);
			resizeColums(columnsForPFASheet, pfaSheet);

			// creating and populating evento sheet
			Row headerRowForEventSheet = eventoSheet.createRow(0);
			headerRowForEventSheet.setHeightInPoints(24);

			createHeaderCellsForEventi(columnsForEventsSheet, headerRowForEventSheet, headerCellStyle);
			fillSheetForEventi(eventoList, eventoSheet);
			resizeColums(columnsForEventsSheet, eventoSheet);

			// creating and populating intervento sheet
			Row headerRowForInterventiSheet = interventoSheet.createRow(0);
			headerRowForInterventiSheet.setHeightInPoints(24);

			createHeaderCellsForInterventi(columnsForInterventiSheet, headerRowForInterventiSheet, headerCellStyle);
			fillSheetForInterventi(interventoList, interventoSheet);
			resizeColums(columnsForInterventiSheet, interventoSheet);

		}

		ByteArrayOutputStream bArray = new ByteArrayOutputStream();

		try {
			hwb.write(bArray);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return bArray.toByteArray();
	}
	

	
	private static void resizeColums(List list, Sheet sheet) {
		for (int i = 0; i < list.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private static void createHeaderCellsForEventi(List<EventoExcelEnum> columnsForEvents, Row headerRowforEventSheet,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columnsForEvents.size(); i++) {
			Cell cell = headerRowforEventSheet.createCell(i);
			cell.setCellValue(columnsForEvents.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRowforEventSheet.setRowStyle(headerCellStyle);
		}
	}

	private static void createHeaderCellsForInterventi(List<InterventoExcelEnum> columns,
			Row headerRowforInterventiSheet, CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRowforInterventiSheet.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRowforInterventiSheet.setRowStyle(headerCellStyle);
		}
	}

	public static void createHeaderCellsForPublicPFA(List<PfaExcelEnums> columns, Row headerRow,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}
	}

	public static void createHeaderCellsForPFA(List<PfaExcelNonPublicEnums> columns, Row headerRow,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}
	}

	public static void fillSheetForPublicPfa(List<GeoPfaSearchDettaglio> list, Sheet sheet) {
		int rowNum = 1;
		for (GeoPfaSearchDettaglio pfa : list) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(pfa.getDenominazione());
			row.createCell(1).setCellValue(pfa.getDenominazioneProvincie());
			row.createCell(2).setCellValue(pfa.getDenominazioneComuni());
			row.createCell(3).setCellValue(pfa.getDataInizioValidita().toString());
			row.createCell(4).setCellValue(pfa.getDataFineValidita().toString());
		}
	}

	public static void fillSheetForPfa(List<GeoPfaSearchDettaglio> list, Sheet sheet) {
		int rowNum = 1;
		for (GeoPfaSearchDettaglio pfa : list) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(pfa.getDenominazione());
			row.createCell(1).setCellValue(pfa.getDenominazioneProvincie());
			row.createCell(2).setCellValue(pfa.getDenominazioneComuni());
			row.createCell(3).setCellValue(pfa.getDataInizioValidita().toString());
			row.createCell(4).setCellValue(pfa.getDataFineValidita().toString());
			row.createCell(5).setCellValue(pfa.getPropNonForestaleHa().toString());
			row.createCell(6).setCellValue(pfa.getSupPianifNonForestaleHa().toString());
			row.createCell(7).setCellValue(pfa.getProprietaSilvopastHa().toString());
			row.createCell(8).setCellValue(pfa.getProprietaForestaleHa().toString());
			row.createCell(9).setCellValue(pfa.getSuperfBocsGestAttivaHa().toString());
			row.createCell(10).setCellValue(pfa.getSupPianifForestaleHa().toString());
			row.createCell(11).setCellValue(pfa.getSuperfGestNonAttivaMonHa().toString());
			row.createCell(12).setCellValue(pfa.getSuperfGestNonAttivaTotHa().toString());
			row.createCell(13).setCellValue(pfa.getSuperfGestNonAttivaEvlHa().toString());

		}
	}

	public static void fillSheetForEventi(List<EventoPiano> list, Sheet sheet) {
		int rowNum = 1;
		if (list != null) {
			for (EventoPiano eventi : list) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(eventi.getPfaDenominazione());
				row.createCell(1).setCellValue(eventi.getProgressivoEventoPfa());
				row.createCell(2).setCellValue(eventi.getNomeBreve());
				row.createCell(3).setCellValue(eventi.getDataEvento().toString());
				// row.createCell(4).setCellValue(Arrays.toString(eventi.getIdgeoPlParticelaForest()));
				row.createCell(4).setCellValue(Arrays.toString(eventi.getDenominazioneParticella()).substring(1).replace("]", ""));
				row.createCell(5).setCellValue(eventi.getTipoEvento());
				row.createCell(6).setCellValue(eventi.getDescrEvento());
				row.createCell(7).setCellValue(eventi.getLocalita());
				row.createCell(8).setCellValue(
						eventi.getSupInteressataHa() == null ? 0 : eventi.getSupInteressataHa().doubleValue());

			}
		}
	}

	public static void fillSheetForInterventi(List<InterventoPiano> list, Sheet sheet) {

		int rowNum = 1;
		if (list != null) {
			for (InterventoPiano interventi : list) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(interventi.getPfaDenominazione());
				row.createCell(1).setCellValue(interventi.getNrProgressivoInterv());
				row.createCell(2).setCellValue(interventi.getAnnataSilvana());
				row.createCell(3).setCellValue(Arrays.toString(interventi.getDenominazioneParticella()));
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
	}

}