package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;

public class DatiStazionaliTwoMapper implements RowMapper<AreaDISaggioDataStazionaliTwo> {

	@Override
	public AreaDISaggioDataStazionaliTwo mapRow(ResultSet rs, int arg1) throws SQLException {

		AreaDISaggioDataStazionaliTwo areaDiSaggio = new AreaDISaggioDataStazionaliTwo();

		areaDiSaggio.setCodiceADS(rs.getString("codice_ads"));
		areaDiSaggio.setDestinazione(rs.getInt("fk_destinazione"));
		areaDiSaggio.setTipoIntervento(rs.getInt("fk_tipo_intervento"));
		areaDiSaggio.setTipoIntervento(rs.getInt("fk_tipo_intervento"));
		areaDiSaggio.setPriorita(rs.getInt("fk_priorita"));
		areaDiSaggio.setDanno(rs.getInt("fk_danno"));
		
		areaDiSaggio.setCodEsbosco(rs.getString("cod_esbosco"));
		areaDiSaggio.setDistEsboscoFuoriPista(rs.getInt("dist_esbosco_fuori_pista"));
		areaDiSaggio.setDistEsboscoSuPista(rs.getInt("dist_esbosco_su_pista"));		
		areaDiSaggio.setMinDistanzaPlanimetrica(rs.getInt("min_distanza_planimetrica"));
		areaDiSaggio.setDanniPerc(rs.getInt("danni_perc"));
		areaDiSaggio.setNrPianteMorte(rs.getInt("nr_piante_morte"));
		areaDiSaggio.setPercDefogliazione(rs.getInt("perc_defogliazione"));
		areaDiSaggio.setPercIngiallimento(rs.getInt("perc_ingiallimento"));
		areaDiSaggio.setFlgPascolamento(rs.getByte("flg_pascolamento"));
		
		return areaDiSaggio;
	}

}
