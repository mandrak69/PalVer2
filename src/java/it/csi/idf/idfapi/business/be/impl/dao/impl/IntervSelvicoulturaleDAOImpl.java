package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.mapper.IntervSelvicolturaleMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class IntervSelvicoulturaleDAOImpl implements IntervSelvicoulturaleDAO {

	private final IntervSelvicolturaleMapper intervSelvicolturaleMapper= new IntervSelvicolturaleMapper();
	
	@Override
	public void insertIntervSelvicolturale(IntervSelvicolturale intervSelvicolturale, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, idgeo_pl_pfa, flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", fk_fascia_altimetrica, annata_silvana, flg_piedilista, fk_tipo_intervento, fk_finalita_taglio");
		sql.append(", fk_dest_legname, nr_piante, volume_ramaglia_m3, stima_massa_retraibile_m3, note_esbosco");
		sql.append(", flg_interv_non_previsto, fk_config_ipla, flg_istanza_taglio, fk_config_utente, fk_governo )");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(intervSelvicolturale.getIdgeoPlPfa());
		parameters.add(intervSelvicolturale.getFlgConformeDeroga());
		parameters.add(intervSelvicolturale.getNrProgressivoInterv());
		parameters.add(intervSelvicolturale.getFkStatoIntervento()); // not null constraint
		parameters.add(intervSelvicolturale.getDataPresaInCarico() == null ? null
				: Date.valueOf(intervSelvicolturale.getDataPresaInCarico()));
		parameters.add(intervSelvicolturale.getFkFasciaAltimetrica()); // not null constraint
		parameters.add(intervSelvicolturale.getAnnataSilvana());
		parameters.add(intervSelvicolturale.getFlgPiedilista()); // not null constraint
		parameters.add(intervSelvicolturale.getFkTipoIntervento()); // not null constraint
		parameters.add(intervSelvicolturale.getFkFinalitaTaglio()); // not null constraint
		parameters.add(intervSelvicolturale.getFkDestLegname()); // not null constraint
		parameters.add(intervSelvicolturale.getNrPiante());
		parameters.add(intervSelvicolturale.getVolumeRamagliaM3());
		parameters.add(intervSelvicolturale.getStimaMassaRetraibileM3());
		parameters.add(intervSelvicolturale.getNoteEsbosco());
		parameters.add(intervSelvicolturale.getFlgIntervNonPrevisto()); // not null constraint
		parameters.add(intervSelvicolturale.getFkConfigIpla()); // not null constraint
		parameters.add(intervSelvicolturale.getFlgIstanzaTaglio()); // not null constraint
		parameters.add(intervSelvicolturale.getFkConfigUtente()); // not null constraint
		parameters.add(intervSelvicolturale.getFkGoverno());
		
		//parameters.add(intervSelvicolturale.getFkTipoForestalePrevalente());
		//parameters.add(intervSelvicolturale.getFlgPiedilista());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateIntervSelvicolturale(IntervSelvicolturale interventoSelvi, Integer idIntervento) {
		
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_t_interv_selvicolturale SET");
		update.append(" nr_piante=?, stima_massa_retraibile_m3=?");
		update.append(",volume_ramaglia_m3=?, note_esbosco=?");
		update.append(",fk_tipo_intervento=?, fk_stato_intervento=?");
		update.append(",fk_finalita_taglio=?, fk_dest_legname=?");
		update.append(",fk_fascia_altimetrica=?, annata_silvana =?");
		update.append(",flg_piedilista=?,fk_governo=?");
		update.append(",flg_conforme_deroga=?, flg_istanza_taglio=?");
		update.append(",data_inserimento=?, flg_istanza_taglio=?");
		
		update.append(" WHERE id_intervento=?");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(interventoSelvi.getNrPiante());
		parameters.add(interventoSelvi.getStimaMassaRetraibileM3());
		parameters.add(interventoSelvi.getVolumeRamagliaM3());
		parameters.add(interventoSelvi.getNoteEsbosco());
		
		parameters.add(interventoSelvi.getFkTipoIntervento());
		parameters.add(interventoSelvi.getFkStatoIntervento());
		parameters.add(interventoSelvi.getFkFinalitaTaglio());
		parameters.add(interventoSelvi.getFkDestLegname());
		parameters.add(interventoSelvi.getFkFasciaAltimetrica());
		
		parameters.add(interventoSelvi.getAnnataSilvana());
		parameters.add(interventoSelvi.getFlgPiedilista());
		parameters.add(interventoSelvi.getFkGoverno());
		parameters.add(interventoSelvi.getFlgConformeDeroga());
		parameters.add(interventoSelvi.getFlgIstanzaTaglio());
		parameters.add(Date.valueOf(LocalDate.now()));
		
		
		
		
		//parameters.add(interventoSelvi.getIdIntervento());
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public void deleteIntervSelvicolturale(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_t_interv_selvicolturale WHERE id_intervento = ? ";
		
		DBUtil.jdbcTemplate.update(sql, idIntervento);
		
	}

	@Override
	public int getProssimoNrProgInterv(int idTipoIntervento) {
		return DBUtil.jdbcTemplate.queryForInt(
				"SELECT COALESCE(MAX(nr_progressivo_interv), 0) FROM idf_t_interv_selvicolturale WHERE fk_tipo_intervento = ?",
				idTipoIntervento) + 1;
	}

	@Override
	public IntervSelvicolturale findInterventoSelvicolturale(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select intselv.id_intervento, fk_tipo_intervento, fk_stato_intervento, idgeo_pl_pfa, fk_tipo_forestale_prevalente");
		sql.append(",fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, flg_interv_non_previsto, fk_config_ipla");
		sql.append(",nr_piante, stima_massa_retraibile_m3, m3_prelevati, volume_ramaglia_m3");
		sql.append(",data_presa_in_carico, annata_silvana, nr_progressivo_interv, flg_istanza_taglio");
		sql.append(", flg_piedilista, flg_conforme_deroga, note_esbosco, intselv.data_inserimento, intselv.data_aggiornamento");
		sql.append(", intselv.fk_config_utente, ripresa_prevista_mc, ripresa_reale_fine_interv_mc, fk_governo");
		sql.append(", intselvuso.fk_uso_viabilita, intselesb.cod_esbosco");
		sql.append(" from IDF_T_INTERV_SELVICOLTURALE intselv");
		sql.append(" LEFT JOIN IDF_R_INTERVENTOSELV_USOVIAB intselvuso using (id_intervento)");
		sql.append(" LEFT JOIN IDF_R_INTERVSELV_ESBOSCO intselesb using (id_intervento)");
		sql.append(" WHERE id_intervento = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),intervSelvicolturaleMapper ,idIntervento);
	}

	
}