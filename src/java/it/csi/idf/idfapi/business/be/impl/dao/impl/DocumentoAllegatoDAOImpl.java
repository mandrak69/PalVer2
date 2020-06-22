package it.csi.idf.idfapi.business.be.impl.dao.impl;


import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAllegatoDAO;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexTrasformazioni;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.mapper.DocumentoMapper;
import it.csi.idf.idfapi.mapper.FatDocumentoMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;

@Component
public class DocumentoAllegatoDAOImpl implements DocumentoAllegatoDAO {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private TipoAllegatoDAO tipoAllegatoDAO;
	
	private final DocumentoMapper documentoMapper =  new DocumentoMapper();
	private final FatDocumentoMapper fatDocumentoMapper =  new FatDocumentoMapper();
	
	@Override
	public List<DocumentoAllegato> findAllDocumentiByPfa(Integer idGeoPlPfa) {

		String query = "SELECT * FROM idf_t_documento_allegato doc WHERE doc.idgeo_pl_pfa = ?";
		
		return DBUtil.jdbcTemplate.query(query, documentoMapper, idGeoPlPfa);
	}

	@Override
	public List<DocumentoAllegato> findAllDocumenti() {
		
		String query = "SELECT * FROM idf_t_documento_allegato;";
		
		return DBUtil.jdbcTemplate.query(query, documentoMapper);
	}

	@Override
	public DocumentoAllegato findDocumentoByID(Integer idDocumentoAllegato) {
		
		String query = "SELECT * FROM idf_t_documento_allegato doc WHERE doc.id_documento_allegato = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(query, documentoMapper, idDocumentoAllegato);
	}

	@Override
	public int createDocumentoAllegato(DocumentoAllegato documento) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_documento_allegato(");
		sql.append("  idgeo_pl_pfa, fk_intervento, fk_tipo_allegato");
		sql.append(", nome_allegato, dimensione_allegato_kb, data_inizio_validita");
		sql.append(", data_fine_validita, data_inserimento, data_aggiornamento, fk_config_utente, note");
		sql.append(", uid_index");
		sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(documento.getIdGeoPlPfa());
		parameters.add(documento.getFkIntervento());
		parameters.add(documento.getFkTipoAleggato());
		parameters.add(documento.getNomeAllegato());
		parameters.add(documento.getDimensioneAllegatoKB());
		parameters.add(documento.getDataIniziValidita() == null ? null : Date.valueOf(documento.getDataIniziValidita()));
		parameters.add(documento.getDataFineValidita() == null ? null : Date.valueOf(documento.getDataFineValidita()));
		parameters.add(documento.getDataInserimento() == null ? null : Date.valueOf(documento.getDataInserimento()));
		parameters.add(documento.getDataAggiornamento() == null ? null : Date.valueOf(documento.getDataAggiornamento()));
		parameters.add(documento.getFkConfigUtente());
		parameters.add(documento.getNote());
		parameters.add(documento.getUidIndex());
		
		return AIKeyHolderUtil.updateWithGenKey("id_documento_allegato", sql.toString(), parameters);
	}
	
	@Override
	public int createDocumentoAllegato(FatDocumentoAllegato documento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_documento_allegato(");
		sql.append("  idgeo_pl_pfa, fk_intervento, fk_tipo_allegato");
		sql.append(", nome_allegato, dimensione_allegato_kb, data_inizio_validita");
		sql.append(", data_fine_validita, data_inserimento, data_aggiornamento, fk_config_utente, note");
		sql.append(", uid_index");
		sql.append(") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)");
	
		List<Object> parameters = new ArrayList<>();
		parameters.add(documento.getIdGeoPlPfa());
		parameters.add(documento.getFkIntervento());
		parameters.add(documento.getIdTipoAleggato());
		parameters.add(documento.getNomeAllegato());
		parameters.add(documento.getDimensioneAllegatoKB());
		parameters.add(documento.getDataIniziValidita() == null ? null : Date.valueOf(documento.getDataIniziValidita()));
		parameters.add(documento.getDataFineValidita() == null ? null : Date.valueOf(documento.getDataFineValidita()));
		parameters.add(documento.getDataInserimento() == null ? null : Date.valueOf(documento.getDataInserimento()));
		parameters.add(documento.getDataAggiornamento() == null ? null : Date.valueOf(documento.getDataAggiornamento()));
		parameters.add(documento.getFkConfigUtente());
		parameters.add(documento.getNote());
	    parameters.add(documento.getUidIndex());
	    return AIKeyHolderUtil.updateWithGenKey("id_documento_allegato", sql.toString(), parameters);
	}

	@Override
	public List<DocumentoAllegato> findDocumentiByIdIntervento(Integer idIntervento) {
		String query = "SELECT * FROM idf_t_documento_allegato WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.query(query, documentoMapper, idIntervento);
	}

	@Override
	public void deleteDocumentoAllegatoByID(int idDocumentoAllegato) {
		String sql = "DELETE FROM idf_t_documento_allegato WHERE id_documento_allegato = ?";
		DBUtil.jdbcTemplate.update(sql, idDocumentoAllegato);
	}

	@Override
	public List<FatDocumentoAllegato> findDocumentiByIdAndTipos(Integer idIntervento,
			List<TipoAllegatoEnum> tipoAllegati) {
		
		if(tipoAllegati.isEmpty()) {
			throw new IllegalArgumentException();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", da.note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.ud_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE da.fk_intervento = ?");
		sql.append(" AND da.fk_tipo_allegato IN(");
		
		for(int i = 0; i < tipoAllegati.size(); i++) {
			sql.append(tipoAllegati.get(i).getValue());
			if(i != tipoAllegati.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		return DBUtil.jdbcTemplate.query(sql.toString(), fatDocumentoMapper, idIntervento);
	}
	
	@Override
	public FatDocumentoAllegato uploadDocumento(int idIntervento, int tipoDocumento, MultipartFormDataInput form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			

			Map<String, List<InputPart>> uploadForm = form.getFormDataMap();
			InputPart inputPart = uploadForm.get("form").get(0);
			String fileNameWithExtension = getFileNameWithExtension(inputPart.getHeaders());

			byte[] content = inputPart.getBodyAsString().getBytes(StandardCharsets.UTF_8);
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			MetadatiIndexTrasformazioni metadatiIndexTrasf = new MetadatiIndexTrasformazioni();
			metadatiIndexTrasf.setIdIntervento(String.valueOf(idIntervento));
			metadatiIndexTrasf.setIdTipoAllegato(String.valueOf(tipoDocumento));

			String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
			FatDocumentoAllegato documentoAllegato = new FatDocumentoAllegato();
			documentoAllegato.setIdTipoAleggato(tipoDocumento);
			documentoAllegato.setDescrTipoAllegato(descrTipoAllegato);
			documentoAllegato.setDeleted(false);
			documentoAllegato.setNomeAllegato(fileNameWithExtension);
			documentoAllegato.setDimensioneAllegatoKB(Long.valueOf(content.length));
			documentoAllegato.setFkIntervento(idIntervento);
			String fileUID = indexSrvHelper.indexUploadFile(fileNameWithExtension, content, metadatiIndexTrasf);
			
			documentoAllegato.setUidIndex(fileUID);
			
			return documentoAllegato;

		} catch (Exception e) {
			return null;
		}
	}
	
		@Override
		public String deleteDocumentoById(Integer idDocumentoAllegato  )
				 {

			try {

				DocumentoAllegato documentoAllegato = findDocumentoByID(idDocumentoAllegato);
				MetadatiIndexTrasformazioni metadatiIndexTrasf = new MetadatiIndexTrasformazioni();
				metadatiIndexTrasf.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
				metadatiIndexTrasf.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAleggato()));
				String uidIn=documentoAllegato.getUidIndex();
				
				String uidDeleted = deleteDocumentoFromPortal(uidIn,metadatiIndexTrasf);
				
				if (uidDeleted == null) {
					
					return null;
				} else {
					deleteDocumentoAllegatoByID(idDocumentoAllegato);
					
					return uidDeleted;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}
	
		private String getFileNameWithExtension(MultivaluedMap<String, String> header) {

			String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

			for (String filename : contentDisposition) {
				if ((filename.trim().startsWith("filename"))) {

					String[] name = filename.split("=");

					return name[1].trim().replaceAll("\"", "");
				}
			}
			return "unknown";
		}
		
		
		public String deleteDocumentoFromPortal(String uidIndex,MetadatiIndexTrasformazioni metadati  ) {
				
				try {
					IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
					indexSrvHelper.indexGetFolder(metadati);
					indexSrvHelper.indexDeleteFile(uidIndex);
					return uidIndex;
					
				} catch (ServiceException e) {
					e.printStackTrace();
					return null;
				}
				
				
			}
}
