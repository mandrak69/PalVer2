package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AreaDiSaggio;

public class AreaDiSaggioDTOMapper implements RowMapper<AreaDiSaggio> {
	@Override
	public AreaDiSaggio mapRow(ResultSet rs, int arg1) throws SQLException {
		String[] arr={"Superficie nota", "Relascopica semplice", "Relascopica completa"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        
		AreaDiSaggio areaDiSaggio = new AreaDiSaggio();
		
		areaDiSaggio.setCodiceADS(rs.getString("codice_ads"));
		areaDiSaggio.setComune(rs.getString("denominazione_comune"));
		areaDiSaggio.setTipologia(rs.getString("descr_tipo_ads"));
		areaDiSaggio.setDescrTipoAds(rs.getString("descr_tipo_ads"));
		areaDiSaggio.setCategoriaForestale(rs.getString("descrizione"));
		areaDiSaggio.setDataRilevamento(rs.getString("data_ril"));
		areaDiSaggio.setAmbitoDiRilevamento(rs.getString("descr_ambito"));

		areaDiSaggio.setDettaglioAmbito(rs.getString("descr_ambito"));
		
		areaDiSaggio.setStatoScheda(new StatoAdsMapper().mapRow(rs, -1));
	
		return areaDiSaggio;
}
}
