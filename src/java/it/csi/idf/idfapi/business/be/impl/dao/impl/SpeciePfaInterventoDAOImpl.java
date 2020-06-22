package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SpeciePfaInterventoDAO;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SpeciePfaInterventoDAOImpl implements SpeciePfaInterventoDAO {

	@Override
	public int createSpeciePfaIntervento(SpeciePfaIntervento speciePfaIntervento, Integer idIntervento) {

		StringBuilder insert = new StringBuilder();
		
		insert.append("INSERT INTO idf_r_specie_pfa_intervento");
		insert.append(" (id_specie");
		insert.append(", id_intervento");
		insert.append(", volume_specie");
		insert.append(", flg_specie_priorita");
		insert.append(", fk_udm)");
		insert.append(" VALUES (?, ?, ?, ?, ?)");
		
        List<Object> parameters = new ArrayList<>();
		
		parameters.add(speciePfaIntervento.getIdSpecie());
		parameters.add(idIntervento);
		parameters.add(speciePfaIntervento.getVolumeSpecia());
		parameters.add(speciePfaIntervento.getFlgSpeciePriorita());
		parameters.add(speciePfaIntervento.getFkUdm());
		
		return DBUtil.jdbcTemplate.update(insert.toString(), parameters.toArray());
	}

	@Override
	public void deleteSpeciePfaIntervento(Integer idSpecie, Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM idf_r_specie_pfa_intervento");
		sql.append(" WHERE id_specie = ? AND id_intervento = ?");
		
		 List<Object> parameters = new ArrayList<>();
		 
		 parameters.add(idSpecie);
		 parameters.add(idIntervento);
		 
		 DBUtil.jdbcTemplate.update(sql.toString(),parameters.toArray());
;
	}

	

}
