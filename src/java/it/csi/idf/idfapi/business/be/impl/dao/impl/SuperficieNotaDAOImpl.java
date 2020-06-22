package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import it.csi.idf.idfapi.business.be.impl.dao.SuperficieNotaDAO;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SuperficieNotaDAOImpl implements SuperficieNotaDAO {

	
	String [] datiSuperficieOneColumns = { "idgeo_pt_ads","densita_campionamento", "raggio_mt", "fk_tipo_strutturale_princ", "cod_stadio_sviluppo",
				"nr_ceppaie", "rinnovazione", "rin_specie_prevalente", "perc_copertura_chiome", "fk_tipo_strutturale_second", 
				"combust_p","flg_pascolamento", "cod_esbosco" };
	
	String [] datiSuperficieTwoColumns = {"cod_esbosco" , "dist_esbosco_fuori_pista" , "dist_esbosco_su_pista" , 
			"min_distanza_planimetrica" , "danni_perc" , "nr_piante_morte" , "perc_defogliazione" , "perc_ingiallimento" , "flg_pascolamento"};
	
	String tableName = "idf_t_ads_superficie_nota";
	String pkColumnName = "idgeo_pt_ads";
	
	public void saveSuperficie(AreaDiSaggio areaDiSaggio) {
		if (!superficiaNotaExists(areaDiSaggio)) {
			insertSuperficieDatiStazionaliOne(areaDiSaggio);
		} else {
			updateSuperficieDatiStazionaliOne(areaDiSaggio);
		}
	}
	
	@Override
	public int insertSuperficieDatiStazionaliOne(AreaDiSaggio areaDiSaggio) {
		try {
		return AIKeyHolderUtil.updateWithGenKey("idgeo_pt_ads", DBUtil.createInsertQueryString(tableName, datiSuperficieOneColumns), getParamsForDatiStazionaliOne(areaDiSaggio, false));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public void updateSuperficieDatiStazionaliOne(AreaDiSaggio areaDiSaggio) {				
		DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, datiSuperficieOneColumns, pkColumnName), getParamsForDatiStazionaliOne(areaDiSaggio, true).toArray());
	}
	
	@Override
	public int updateSuperficieDatiStazionaliTwo(AreaDiSaggio areaDiSaggio) {
        return DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, datiSuperficieTwoColumns, pkColumnName), getParamsForDatiStazionaliTwo(areaDiSaggio, true).toArray());
	}
	
	public boolean superficiaNotaExists(AreaDiSaggio areaDiSaggio) {
		String countSQL = DBUtil.createCountByPkQuery(tableName, pkColumnName);		
		return DBUtil.jdbcTemplate.queryForInt(countSQL, Long.parseLong(areaDiSaggio.getCodiceADS())) > 0;
	}

	private List<Object> getParamsForDatiStazionaliOne(AreaDiSaggio areaDiSaggio, boolean updateQuery) {
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
        parameters.add(Double.parseDouble(areaDiSaggio.getDensitaCamp()));
        parameters.add(Integer.parseInt(areaDiSaggio.getRaggioArea()));
        parameters.add(areaDiSaggio.getTipoStrutturale());
        parameters.add(areaDiSaggio.getStadioDiSviluppo());
        parameters.add(Integer.parseInt(areaDiSaggio.getnCepaie()));
        parameters.add(Integer.parseInt(areaDiSaggio.getRinnovazione()));
        parameters.add(areaDiSaggio.getSpeciePrevalenteRinnovazione());
        parameters.add(Integer.parseInt(areaDiSaggio.getCoperturaChiome()));
        parameters.add(87);//mocked not null
        parameters.add(0);//mocked not null
        parameters.add(0);//mocked not null
        parameters.add("GR");//mocked not null
        
        if (updateQuery) {
        	parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
        }
        
        return parameters;
	}
	
	private List<Object> getParamsForDatiStazionaliTwo(AreaDiSaggio areaDiSaggio, boolean updateQuery) {
		List<Object> parameters = new ArrayList<>();
        parameters.add(areaDiSaggio.getEsbosco());
        parameters.add(Integer.parseInt(areaDiSaggio.getDefp()));
        parameters.add(Integer.parseInt(areaDiSaggio.getDesp()));
        parameters.add(Integer.parseInt(areaDiSaggio.getMdp()));
        parameters.add(Integer.parseInt(areaDiSaggio.getIntesitaDanni()));
        parameters.add(Integer.parseInt(areaDiSaggio.getnPianteMorte()));
        parameters.add(Integer.parseInt(areaDiSaggio.getDefogliazione()));
        parameters.add(Integer.parseInt(areaDiSaggio.getIngiallimento()));
        parameters.add(Integer.parseInt(areaDiSaggio.getPascolamento()));
//        parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
        
        if (updateQuery) {
        	parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
        }
        
        return parameters;
		
	}
}
