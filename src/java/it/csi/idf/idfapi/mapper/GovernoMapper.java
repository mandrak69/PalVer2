package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Governo;

public class GovernoMapper implements RowMapper<Governo>{

	@Override
	public Governo mapRow(ResultSet rs, int arg1) throws SQLException {

		Governo governo = new Governo();
		
		governo.setIdGoverno(rs.getInt("id_governo"));
		governo.setDescrGoverno(rs.getString("descr_governo"));
		governo.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		governo.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return governo;
	}
}