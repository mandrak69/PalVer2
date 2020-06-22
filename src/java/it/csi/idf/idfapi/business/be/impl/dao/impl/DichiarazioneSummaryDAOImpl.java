package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DichiarazioneSummaryDAO;
import it.csi.idf.idfapi.dto.GeneratedFileParameters;
import it.csi.idf.idfapi.mapper.DichiarazioneSummaryMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DichiarazioneSummaryDAOImpl implements DichiarazioneSummaryDAO {
	
	private final DichiarazioneSummaryMapper dichMapper = new DichiarazioneSummaryMapper();

	@Override
	public GeneratedFileParameters getSummary(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT si.id_intervento, si.fk_config_utente, si.fk_tipo_titolarita");
		sql.append(", sog.cognome, sog.nome, sog.denominazione, sog.codice_fiscale, sog.partita_iva");
		sql.append(", sog.indirizzo, sog.civico, sog.cap, sog.nr_telefonico, sog.e_mail, sog.pec");
		sql.append(", cmn.denominazione_comune, prov.denominazione_prov");
		sql.append(", it.flg_compensazione, it.flg_art7_a, it.flg_art7_b, it.flg_art7_c, it.flg_art7_d, it.flg_art7_d_bis, it.compenzazione_euro");
		sql.append(", it.flg_proprieta, it.flg_dissensi");
		sql.append(", it.flg_aut_paesaggistica, it.data_aut_paesaggistica, it.nr_aut_paesaggistica, it.ente_aut_paesaggistica");
		sql.append(", it.flg_aut_vidro, it.data_aut_vidro, it.nr_aut_vidro, it.ente_aut_vidro");
		sql.append(", it.flg_aut_incidenza, it.data_aut_incidenza, it.nr_aut_incidenza, it.ente_aut_incidenza, it.altri_pareri ");
		sql.append(", inter.superficie_interessata");
		sql.append(", array_to_string(array(SELECT id_parametro_trasf FROM idf_r_paramtrasf_trasformazion");
		sql.append(" WHERE id_intervento = ? ORDER BY id_parametro_trasf), ', ') as id_parametro_trasf");
		sql.append(", sgPf.cognome as prof_cognome, sgPf.nome as prof_nome");
		sql.append(", sgPf.codice_fiscale as prof_codice_fiscale, sgPf.istat_prov_iscrizione_ordine as prof_prov_ordine");
		sql.append(", sgPf.n_iscrizione_ordine as prof_iscrizione, sgPf.nr_telefonico as prof_telefonico, sgPf.pec as prof_pec");
		sql.append(" FROM idf_r_soggetto_intervento si");
		sql.append(" JOIN idf_t_soggetto sog ON si.id_soggetto = sog.id_soggetto");
		sql.append(" JOIN idf_geo_pl_comune cmn ON sog.fk_comune = cmn.id_comune");
		sql.append(" JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov");
		sql.append(" JOIN idf_t_interv_trasformazione it ON si.id_intervento = it.id_intervento");
		sql.append(" JOIN idf_t_intervento inter ON si.id_intervento = inter.id_intervento");
		sql.append(" LEFT JOIN idf_t_soggetto sgPf ON inter.fk_soggetto_profess = sgPf.id_soggetto");
		sql.append(" WHERE si.id_intervento = ?");
		sql.append("");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), dichMapper, idIntervento, idIntervento);
	}
}