package it.csi.idf.idfapi.business.be.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import it.csi.idf.idfapi.business.be.impl.dao.DichiarazioneSummaryDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.service.FileGenerator;
import it.csi.idf.idfapi.dto.DichPropCatasto;
import it.csi.idf.idfapi.dto.GeneratedFileBean;
import it.csi.idf.idfapi.dto.GeneratedFileParameters;
import it.csi.idf.idfapi.dto.IstanzaCompensazione;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class FileGeneratorImpl extends SpringSupportedResource implements FileGenerator {
	
	private static final String DICHIARAZIONE_FIRMA_DIGITALE = "firmato digitalmente";
	private static final String EXPORT_PATH = "c:/tmp/pal";
	private static final String JASPER_RESOURCES_CLASSPATH = "classpath:resources/jasperReports/dichiarazione/";
	private static final String NIKE_CHECKBOX_PIC_NAME = "NikeCheckbox.png";
	private static final String BOSCO_SUBREPORT_FILE_NAME = "BoscoSubreport";
	private static final String JASPER_EXTENSION = ".jasper";
	private static final String EXPORT_EXTENSION_PDF = ".pdf";
	private static final String SLASH = "/";
	private static final String UNDERSCORE = "_";
	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String DICHIARAZIONE_FIRMA_NO_SIGNATURE = "Dichiarazione presentata per via telematica secondo l'art. 65, comma 1, lettera b) del D.Lgs 82/2005 e s.m.i";
	
	private static final String DICHIARAZIONE_FIRMA_AUTOGRAFA = " ";

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DichiarazioneSummaryDAO dichiarazioneSummaryDAO;
	
	@Autowired
	private PropCatastoDAO propCatastoDAO;
	
	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;
	
	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedFileBean generateDichiarazione(TipoAllegatoEnum tipoAllegato, int idIntervento) throws JRException, IOException {
		
		if(tipoAllegato.getValue() < 1 || tipoAllegato.getValue() > 3) {
			throw new IllegalArgumentException("Method : generateDichiarazione does not support provided TipoAllegatoEnum tipoAllegato argument");
		}
		
		GeneratedFileParameters genFileParams = dichiarazioneSummaryDAO.getSummary(idIntervento);
		genFileParams.setTipoAllegato(tipoAllegato);
		genFileParams.setPropCatasti(propCatastoDAO.getDichPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento)));
		genFileParams.setIstanzeCompensazione(istanzaCompensazioneDAO.getAllByFkIntervento(idIntervento));

		Map<String, Object> param = mapParameters(genFileParams);
		param.put("BOSCO_SUBREPORT", compileJasperSubReport(BOSCO_SUBREPORT_FILE_NAME));
		
		File generated = new File(EXPORT_PATH,
				genFileParams.getTipoAllegato().toString().
				concat(UNDERSCORE.concat(TIMESTAMP_FORMAT.format(new Date()))
								.concat(EXPORT_EXTENSION_PDF)));
								
        JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReport(
				compileJasperReport(genFileParams.getTipoAllegato()), param,
				new JRBeanCollectionDataSource(Lists.newArrayList(param))), generated.getPath());								
							
		GeneratedFileBean gfb = new GeneratedFileBean();
		gfb.setNome("DICHIARAZIONE");
		
		return gfb;
	}
	
	private Map<String, Object> mapParameters(GeneratedFileParameters genFileParams) throws IOException {
		Map<String, Object> param = new HashMap<>();
		param.put("nikeImg", resourceLoader
				.getResource(JASPER_RESOURCES_CLASSPATH.concat(NIKE_CHECKBOX_PIC_NAME)).getInputStream());
		
		param.put("richCognome",genFileParams.getRichCognome());
		param.put("richNome",genFileParams.getRichNome());
		
		if(genFileParams.getTipoTitolarita().equals(TipoTitolaritaEnum.RG)) {
			param.put("richRagSociale",genFileParams.getRichRagSociale());
			param.put("richPartitaIva",genFileParams.getRichPartitaIva());			
		}
		param.put("richCodFiscale",genFileParams.getRichCodiceFiscale());
		param.put("richIndirizzo",genFileParams.getRichIndirizzo());
		param.put("richCivico",genFileParams.getRichCivico());
		param.put("richProv",genFileParams.getRichProvincia());
		param.put("richCap",genFileParams.getRichCap());
		param.put("richComune",genFileParams.getRichComune());
		param.put("richTel",genFileParams.getRichTelefonico());
		
		String emailPec;
		if(genFileParams.getRichEmail() != null && genFileParams.getRichPec() != null) {
			emailPec =  genFileParams.getRichEmail().concat(SLASH).concat(genFileParams.getRichPec());
		} else if(genFileParams.getRichEmail() != null) {
			emailPec = genFileParams.getRichEmail();
		} else {
			emailPec = genFileParams.getRichPec();
		}
		param.put("richEmailPec", emailPec);
		
		String istanzeCompensazione = "(con istanze di taglio n. .................... del ..................);";

		if(genFileParams.getFlgCompensazione().equals(SuperficieCompensationEnum.N)) {
			param.put("flgNonNecessaria", true);			
			param.put("flgArt7a", genFileParams.isFlgArt7a());			
			param.put("flgArt7b", genFileParams.isFlgArt7b());			
			param.put("flgArt7c", genFileParams.isFlgArt7c());			
			param.put("flgArt7d", genFileParams.isFlgArt7d());			
			param.put("flgArt7dBis", genFileParams.isFlgArt7dBis());			
		} else if(genFileParams.getFlgCompensazione().equals(SuperficieCompensationEnum.M)) {
			param.put("flgNecessaria", true);
			param.put("isDichCompMonetaria", true);
		} else if(genFileParams.getFlgCompensazione().equals(SuperficieCompensationEnum.F)) {
			param.put("flgNecessaria", true);
			param.put("isDichCompFisica", true);
			istanzeCompensazione = getFormatedIstanzi(genFileParams.getIstanzeCompensazione());
		}
		
		param.put("istanzeCompensazione", istanzeCompensazione);
		param.put("isFormaGoverno1", genFileParams.isFormaGovernoFlg1());
		param.put("isFormaGoverno2", genFileParams.isFormaGovernoFlg2());
		param.put("isCategForest1", genFileParams.isCategForestFlg1());
		param.put("isCategForest2", genFileParams.isCategForestFlg2());
		param.put("isCategForest3", genFileParams.isCategForestFlg3());
		param.put("isUbicazione1", genFileParams.isUbicazioneFlg1());
		param.put("isUbicazione2", genFileParams.isUbicazioneFlg2());
		param.put("isUbicazione3", genFileParams.isUbicazioneFlg3());
		param.put("isDestVinc1", genFileParams.isDestVincFlg1());
		param.put("isDestVinc2", genFileParams.isDestVincFlg2());
		param.put("isDestVinc3", genFileParams.isDestVincFlg3());
		param.put("isTipologia1", genFileParams.isTipologiaFlg1());
		param.put("isTipologia2", genFileParams.isTipologiaFlg2());
		param.put("isTipologia3", genFileParams.isTipologiaFlg3());
		
		param.put("profCognome", genFileParams.getProfCognome());
		param.put("profNome", genFileParams.getProfNome());
		param.put("profCodiceFiscale", genFileParams.getProfCodiceFiscale());
		param.put("profProvinciaOrdine", genFileParams.getProfProvinciaOrdine());
		param.put("profNIscrizione", genFileParams.getProfNIscrizione());
		param.put("profTelefonico", genFileParams.getProfTelefonico());
		param.put("profPec", genFileParams.getProfPec());
		param.put("compenzazioneEuro", genFileParams.getCompenzazioneEuro());
		
		param.put("isDichProprietario", genFileParams.isDichProprietario());
		param.put("isDichNonProprietario", !genFileParams.isDichProprietario());
		param.put("isDichDissenso", genFileParams.isDichDissenso());
		param.put("isDichAutPaesaggistica", genFileParams.isDichAutPaesaggistica());
		param.put("dichDataPaesaggistica", genFileParams.getDichDataPaesaggistica());
		param.put("dichNrPaesaggistica", genFileParams.getDichNrPaesaggistica());
		param.put("dichEntePaesaggistica", genFileParams.getDichEntePaesaggistica());
		param.put("isDichAutVidro", genFileParams.isDichAutVidro());
		param.put("dichDataVidro", genFileParams.getDichDataVidro());
		param.put("dichNrVidro", genFileParams.getDichNrVidro());
		param.put("dichEnteVidro", genFileParams.getDichEnteVidro());
		param.put("isDichAutIncidenza", genFileParams.isDichAutIncidenza());
		param.put("dichDataIncidenza", genFileParams.getDichDataIncidenza());
		param.put("dichNrIncidenza", genFileParams.getDichNrIncidenza());
		param.put("dichEnteIncidenza", genFileParams.getDichEnteIncidenza());
		param.put("isDichAltriPareri",
				genFileParams.getDichAltriPareri() != null && !genFileParams.getDichAltriPareri().trim().equals(""));
		param.put("dichAltriPareri", genFileParams.getDichAltriPareri());
		
		if(genFileParams.getTipoAllegato().equals(TipoAllegatoEnum.DICHIARAZIONE)) {
			param.put("dataLuogo", LocalDate.now());
			param.put("dichiaranteFirma", DICHIARAZIONE_FIRMA_NO_SIGNATURE);
		} else if(genFileParams.getTipoAllegato().equals(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE)) {
			param.put("dataLuogo", LocalDate.now());
			param.put("dichiaranteFirma", DICHIARAZIONE_FIRMA_DIGITALE);
		}else if(genFileParams.getTipoAllegato().equals(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA)) {
			param.put("dataLuogo", LocalDate.now());
			param.put("dichiaranteFirma", DICHIARAZIONE_FIRMA_AUTOGRAFA);
		}
		
		
		
		
		param.putAll(getBoscoSubreportResources(genFileParams.getPropCatasti()));
		param.put("superficieInteressata", genFileParams.getSuperficieInteressata() == null ? null
				: genFileParams.getSuperficieInteressata().toString());
		
		param.put("regionaleSoggetto", ""); //TODO fill with proper value
		
		return param;
	}

	private JasperReport compileJasperReport(TipoAllegatoEnum allegato) throws JRException, IOException {

		Resource res = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat(allegato.toString()).concat(JASPER_EXTENSION));
		return (JasperReport)JRLoader.loadObject(res.getInputStream());
	}
	
	private JasperReport compileJasperSubReport(String subreportName) throws JRException, IOException {
		Resource res = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat(subreportName).concat(JASPER_EXTENSION));
		return (JasperReport)JRLoader.loadObject(res.getInputStream());
	}
	
	private Map<String, Object> getBoscoSubreportResources(List<DichPropCatasto> propCatasti) {

		List<HashMap<Object, Object>> elencoBosco = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		result.put("elencoDatiBosco", elencoBosco);

		for(DichPropCatasto catasto : propCatasti) {
			HashMap<Object, Object> catastoProp = new HashMap<>();
			catastoProp.put("comune", catasto.getDenominazioneComune());
			catastoProp.put("provincia", catasto.getDenominazioneProvincia());
			catastoProp.put("sezione", catasto.getSezione());
			catastoProp.put("foglio", catasto.getFoglio() == null ? null : catasto.getFoglio().toString());
			catastoProp.put("particela", catasto.getParticella());
			catastoProp.put("supCatastale", catasto.getSupCatastaleMq() == null ? null : catasto.getSupCatastaleMq().toString());
			catastoProp.put("supTrasformazione", catasto.getSupCartograficaHa() == null ? null : catasto.getSupCartograficaHa().toString());
			
			elencoBosco.add(catastoProp);
		}
		return result;
	}
	
	private String getFormatedIstanzi(List<IstanzaCompensazione> istanze) {
		StringBuilder response = new StringBuilder();
		response.append("(con istanze di taglio");
		
		for(int i = 0; i < istanze.size(); i++) {
			response.append(" n. ");			
			response.append(istanze.get(i).getNumIstanzaCompensazione());			
			response.append(" ");			
			if(istanze.get(i).getDataAutorizzazione() != null) {
				response.append("del ");
				response.append(istanze.get(i).getDataAutorizzazione().toString());					
			} else if(istanze.get(i).getDataPresentazione() != null) {
				response.append("del ");
				response.append(istanze.get(i).getDataPresentazione().toString());	
			}
			if(i != (istanze.size() - 1)) {
				response.append(",");	
			}
		}
		response.append(");");

		return response.toString();
	}
}