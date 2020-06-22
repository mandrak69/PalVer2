package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.ws.rs.core.Context;

import org.apache.axis.handlers.BasicHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.JacksonConfig;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.dto.FatSoggetto;
import it.csi.idf.idfapi.dto.SoggettoResource;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.mapper.FatSoggettoMapper;
import it.csi.idf.idfapi.mapper.SoggettoResourceMapper;
import it.csi.idf.idfapi.mapper.TSoggettoMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.DtoMapper;
import it.csi.idf.idfapi.util.SoggettoTypeEnum;

@Component
public class SoggettoDAOImpl implements SoggettoDAO {
	
	private  ObjectMapper mapperr=new ObjectMapper();
	
	private final TSoggettoMapper soggettoMapper = new TSoggettoMapper();
	private final SoggettoResourceMapper soggettoResourceMapper = new SoggettoResourceMapper();
	private final FatSoggettoMapper fatSoggettoMapper = new FatSoggettoMapper();
	
	@Override
	public TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException {
		String sql = "SELECT * FROM idf_t_soggetto s where s.id_soggetto=?";
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper,idSoggetto);	
		if (soggettos.isEmpty()) {
			throw new RecordNotFoundException();
		} else {
			return soggettos.get(0);
		}
	}
	
	@Override
	public TSoggetto findSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException {
		//DtoMapper<TSoggetto> qmapeer = new DtoMapper<TSoggetto>(null);
		String sql = "SELECT * FROM idf_t_soggetto s where s.codice_fiscale= ?";
		  List<Map<String, Object>> soggetto1 = DBUtil.jdbcTemplate.queryForList(sql,codiceFiscale);
		
System.out.println(soggetto1.toString());
//TSoggetto dd = objectMapper.convertValue(soggetto1.get(0), TSoggetto.class);
		  List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper,codiceFiscale);	
			if (soggettos.size()>1) {
				throw new RecordNotUniqueException();
			} else {
				System.out.println(soggettos.get(0).toString());
				return soggettos.get(0);
			}
		}
	@Override
	public List<TSoggetto> findByPartialCodiceFiscale(String codiceFiscale) {
		String sql = "SELECT * FROM idf_t_soggetto s where s.codice_fiscale LIKE '" + codiceFiscale +  "%' ";
		
		return DBUtil.jdbcTemplate.query(sql, soggettoMapper);
	}

	@Override
	public int createSoggetto(TSoggetto soggetto) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_soggetto(");
		sql.append("fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, indirizzo, ");
		sql.append("nr_telefonico, e_mail, pec, n_iscrizione_ordine, istat_prov_iscrizione_ordine, istat_prov_competenza_terr, ");
		sql.append("flg_settore_regionale, data_inserimento, fk_config_utente, civico, cap)");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		List<Object> parameters = new ArrayList<>();
        parameters.add(soggetto.getFkComune());
        parameters.add(soggetto.getNome());
        parameters.add(soggetto.getCognome());
        parameters.add(soggetto.getCodiceFiscale());
        parameters.add(soggetto.getPartitaIva());
        parameters.add(soggetto.getDenominazione());
        parameters.add(soggetto.getIndirizzo());
        parameters.add(soggetto.getNrTelefonico());
        parameters.add(soggetto.geteMail());
        parameters.add(soggetto.getPec());
        parameters.add(soggetto.getnIscrizioneOrdine());
        parameters.add(soggetto.getIstatProvIscrizioneOrdine());
        parameters.add(soggetto.getIstatProvCompetenzaTerr());
        parameters.add(soggetto.getFlgSettoreRegionale());
        parameters.add(Date.valueOf(LocalDate.now()));
        parameters.add(soggetto.getFkConfigUtente());
        parameters.add(soggetto.getCivico());
        parameters.add(soggetto.getCap());
	
		return AIKeyHolderUtil.updateWithGenKey("id_soggetto", sql.toString(), parameters);
	}
	
	@Override
	public int createSoggettoFisica(TSoggetto soggetto) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_soggetto(");
		sql.append("fk_comune, nome, cognome, codice_fiscale, indirizzo, nr_telefonico, e_mail, ");
		sql.append("flg_settore_regionale, data_inserimento, fk_config_utente, civico, cap)");
		sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		List<Object> parameters = new ArrayList<>();
        parameters.add(soggetto.getFkComune());
        parameters.add(soggetto.getNome());
        parameters.add(soggetto.getCognome());
        parameters.add(soggetto.getCodiceFiscale());
        parameters.add(soggetto.getIndirizzo());
        parameters.add(soggetto.getNrTelefonico());
        parameters.add(soggetto.geteMail());
        parameters.add(soggetto.getFlgSettoreRegionale());
        parameters.add(Date.valueOf(LocalDate.now()));
        parameters.add(soggetto.getFkConfigUtente());
        parameters.add(soggetto.getCivico());
        parameters.add(soggetto.getCap());
	
		return AIKeyHolderUtil.updateWithGenKey("id_soggetto", sql.toString(), parameters);
	}
	
	@Override
	public int updateSoggetto(TSoggetto soggetto) {

		List<Object> parameters = new ArrayList<>();

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_t_soggetto SET");
		
		update.append(" flg_settore_regionale = ?");
		parameters.add(soggetto.getFlgSettoreRegionale()); //NOT NULL constraint

		if (soggetto.getFkComune() != null) {
			update.append(", fk_comune = ?");
			parameters.add(soggetto.getFkComune());
		}
		if (soggetto.getNome() != null) {
			update.append(", nome = ?");
			parameters.add(soggetto.getNome());
		}
		if (soggetto.getCognome() != null) {
			update.append(", cognome = ?");
			parameters.add(soggetto.getCognome());
		}
		if (soggetto.getCodiceFiscale() != null) {
			update.append(", codice_fiscale = ?");
			parameters.add(soggetto.getCodiceFiscale());
		}
		if (soggetto.getPartitaIva() != null) {
			update.append(", partita_iva = ?");
			parameters.add(soggetto.getPartitaIva());
		}
		if (soggetto.getDenominazione() != null) {
			update.append(", denominazione = ?");
			parameters.add(soggetto.getDenominazione());
		}
		if (soggetto.getIndirizzo() != null) {
			update.append(", indirizzo = ?");
			parameters.add(soggetto.getIndirizzo());
		}
		if (soggetto.getNrTelefonico() != null) {
			update.append(", nr_telefonico = ?");
			parameters.add(soggetto.getNrTelefonico());
		}
		if (soggetto.geteMail() != null) {
			update.append(", e_mail = ?");
			parameters.add(soggetto.geteMail());
		}
		if (soggetto.getPec() != null) {
			update.append(", pec = ?");
			parameters.add(soggetto.getPec());
		}
		if (soggetto.getnIscrizioneOrdine() != null) {
			update.append(", n_iscrizione_ordine = ?");
			parameters.add(soggetto.getnIscrizioneOrdine());
		}
		if (soggetto.getIstatProvIscrizioneOrdine() != null) {
			update.append(", istat_prov_iscrizione_ordine = ?");
			parameters.add(soggetto.getIstatProvIscrizioneOrdine());
		}
		if (soggetto.getIstatProvCompetenzaTerr() != null) {
			update.append(", istat_prov_competenza_terr = ?");
			parameters.add(soggetto.getIstatProvCompetenzaTerr());
		}
		if (soggetto.getDataAggiornamento() != null) {
			update.append(", data_aggiornamento = ?");
			parameters.add(Date.valueOf(soggetto.getDataAggiornamento()));
		}
		if (soggetto.getFkConfigUtente() != null) {
			update.append(", fk_config_utente = ?");
			parameters.add(soggetto.getFkConfigUtente());
		}
		if (soggetto.getCivico() != null) {
			update.append(", civico = ?");
			parameters.add(soggetto.getCivico());
		}
		if (soggetto.getCap() != null) {
			update.append(", cap = ?");
			parameters.add(soggetto.getCap());
		}

		update.append(" where id_soggetto = ?");
        parameters.add(soggetto.getIdSoggetto());

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	public boolean validation(TSoggetto soggetto) {
		return soggetto.getCodiceFiscale() != null && soggetto.getNome() != null && soggetto.getCognome() != null
				&& soggetto.getNrTelefonico() != null && soggetto.geteMail() != null;
	}

	@Override
	public void updateFkConfigUtente(int idSoggetto, int configUtenteId) {
		String sql = "Update idf_t_soggetto SET fk_config_utente = ? WHERE id_soggetto = ?";
		DBUtil.jdbcTemplate.update(sql, configUtenteId, idSoggetto);
	}

	@Override
	public void updateSoggettoProfess(TSoggetto soggettoProfess) {
		StringBuilder update=new StringBuilder();
		update.append("UPDATE idf_t_soggetto SET");
		update.append(" nome = ?");
		update.append(", cognome = ?");
		update.append(", codice_fiscale = ?");
		update.append(", partita_iva = ?");
		update.append(", nr_telefonico = ?");
		update.append(", e_mail = ?");
		update.append(", pec = ?");
		update.append(", data_aggiornamento = ?");

		update.append(" where id_soggetto = ?");
	
		List<Object> parameters = new ArrayList<>();
        parameters.add(soggettoProfess.getNome());
        parameters.add(soggettoProfess.getCognome());
        parameters.add(soggettoProfess.getCodiceFiscale());
        parameters.add(soggettoProfess.getPartitaIva());
        parameters.add(soggettoProfess.getNrTelefonico());
        parameters.add(soggettoProfess.geteMail());
        parameters.add(soggettoProfess.getPec());
        parameters.add(Date.valueOf(LocalDate.now()));
        parameters.add(soggettoProfess.getIdSoggetto());
	
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public List<SoggettoResource> getPersFisicaForBOSearch() {
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT id_soggetto, codice_fiscale, partita_iva, nome, cognome, denominazione");
		sql.append(" FROM idf_t_soggetto WHERE id_soggetto IN(");
		sql.append("SELECT DISTINCT(id_soggetto) FROM idf_r_soggetto_intervento si");
		sql.append(" JOIN idf_cnf_config_utente cu ON si.fk_config_utente = cu.id_config_utente");
		sql.append(" AND si.id_soggetto = cu.fk_soggetto");
		sql.append(") ORDER BY codice_fiscale");
	
		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getPersGiuridicaForBOSearch() {
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT id_soggetto, codice_fiscale, partita_iva, nome, cognome, denominazione");
		sql.append(" FROM idf_t_soggetto WHERE id_soggetto IN(");
		sql.append("SELECT DISTINCT(id_soggetto) FROM idf_r_soggetto_intervento si");
		sql.append(" WHERE id_soggetto IN(SELECT DISTINCT(id_soggetto_pg) FROM idf_r_pf_pg)");
		sql.append(") ORDER BY partita_iva");
	
		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getProfessForBOSearch() {
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT id_soggetto, codice_fiscale, partita_iva, nome, cognome, denominazione");
		sql.append(" FROM idf_t_soggetto WHERE id_soggetto IN(");
		sql.append("SELECT DISTINCT(id_soggetto) FROM idf_r_soggetto_intervento si");
		sql.append(" WHERE id_tipo_soggetto = ");
		sql.append(SoggettoTypeEnum.TECNICO_FORESTALE.getValue());
		sql.append(") ORDER BY codice_fiscale");
	
		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper);
	}

	@Override
	public FatSoggetto findFatSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id_comune, c.denominazione_comune, c.istat_comune, c.istat_prov");
		sql.append(", s.id_soggetto, s.nome, s.cognome, s.codice_fiscale, s.partita_iva, s.denominazione");
		sql.append(", s.id_soggetto, s.indirizzo, s.nr_telefonico, s.e_mail, s.pec, s.n_iscrizione_ordine");
		sql.append(", s.istat_prov_iscrizione_ordine, s.istat_prov_competenza_terr, s.flg_settore_regionale");
		sql.append(", s.data_inserimento, s.data_aggiornamento, s.fk_config_utente, s.civico, s.cap");
		sql.append(" FROM idf_t_soggetto s");
		sql.append(" JOIN idf_geo_pl_comune c ON S.fk_comune = c.id_comune");
		sql.append(" WHERE s.codice_fiscale= ?");
		
		List<FatSoggetto> soggettos= DBUtil.jdbcTemplate.query(sql.toString(), fatSoggettoMapper,codiceFiscale);
		if (soggettos.isEmpty()) {
			return null;
		} else if(soggettos.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return soggettos.get(0);
		}
	}
}
