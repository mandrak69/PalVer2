package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDatiTecniciDAO;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.mapper.TipoInterventoDatiTecniciMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoInterventoDatiTecniciDAOImpl implements TipoInterventoDatiTecniciDAO {
	
	private final TipoInterventoDatiTecniciMapper tipoInterventoDatiTecniciMapper =  new TipoInterventoDatiTecniciMapper();

	@Override
	public TipoInterventoDatiTecnici findTipoIntervDatiTehnici( Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, stato.descr_stato_intervento, data_presa_in_carico");
		sql.append(", interSel.annata_silvana, ARRAY_AGG(parFor.idgeo_pl_particella_forest) AS idgeo_pl_particella_forest");
		sql.append(", evento.id_evento, fk_governo");
		sql.append(", flg_piedilista, interv.descrizione_intervento, interv.localita, interv.superficie_interessata");
		sql.append(", fk_tipo_intervento, interv.fascia_altimetrica, fk_finalita_taglio, fk_dest_legname");
		sql.append(" FROM idf_t_interv_selvicolturale interSel");
		sql.append(" INNER JOIN idf_d_stato_intervento stato ON interSel.fk_stato_intervento = stato.id_stato_intervento");
		sql.append(" INNER JOIN idf_t_intervento interv ON interSel.id_intervento = interv.id_intervento");
		sql.append(" LEFT JOIN idf_r_partfor_intervento parInterv ON interSel.id_intervento = parInterv.id_intervento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFor ON parInterv.idgeo_pl_particella_forest = parFor.idgeo_pl_particella_forest");
		sql.append(" LEFT JOIN idf_r_intervselv_evento selEvento ON interSel.id_intervento = selEvento.id_intervento");
		sql.append(" LEFT JOIN idf_t_evento evento ON selEvento.id_evento = evento.id_evento");
		sql.append(" WHERE interv.id_intervento = ?");
		sql.append(" GROUP BY flg_conforme_deroga, stato.descr_stato_intervento, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", interSel.annata_silvana, evento.id_evento");
		sql.append(", fk_governo, flg_piedilista, interv.descrizione_intervento, interv.localita");
		sql.append(", interv.superficie_interessata, fk_tipo_intervento, interv.fascia_altimetrica");
		sql.append(", fk_finalita_taglio, fk_dest_legname");
				
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), tipoInterventoDatiTecniciMapper, idIntervento);
	}

	
	
	
}