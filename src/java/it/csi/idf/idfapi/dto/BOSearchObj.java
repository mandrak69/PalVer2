package it.csi.idf.idfapi.dto;
// DM
public class BOSearchObj {

	private String tipoIstanza;
	private String numIstanza;
	private String statoIstanza;
	private String annoIstanza;
	private String dataPresDa;
	private String dataPresA;
	private String prov;
	private String comune;
	private String sezione;
	private String foglio;
	private String particella;
	private String pf;
	private String pg;
	private String prof;
	private String aapp;
	private String rn2k;
	private String popSeme;
	private String vincIdro;
	private Boolean compNec;
	private Boolean compO1;
	private Boolean compO2;
	private Boolean compO3;
	private Boolean compO4;
	private Boolean compO5;
	private String govForm;
	private String catFor;
	private String ubicazione;
	private String tipTrasf;
	private String calcEconDa;
	private String calcEconA;
	private String page;
	private String limit;
	private String sort;
	
	
	
	@Override
	public String toString() {
		StringBuilder sql = new StringBuilder();
		String sep = " WHERE ";
		if (tipoIstanza != null) {
			sql.append("tipoIstanza=");
			
			sep = " AND ";
		}
		if (numIstanza != null) {
			sql.append(sep).append(" ifo.nr_istanza_forestale = ? ");
			sep = " AND ";
		}
		if (statoIstanza != null) {
			sql.append(sep).append(" ifo.fk_stato_istanza = ? ");
			sep = " AND ";
		}
		if (annoIstanza != null) {
			sql.append(sep).append(" TO_CHAR(ifo.data_inserimento, 'YYYY') = ? ");
			
			sep=" AND ";
		}
		if (dataPresDa != null) {
			sql.append(sep).append(" ifo.data_invio >= ? ");
			
			sep=" AND ";
		}
		if (dataPresA != null) {
			sql.append(sep).append(" ifo.data_invio <= ? ");
			
			sep=" AND ";
		}
		if (prov != null) {
			sql.append(sep).append(" cmn.istat_prov = ?");
			
			sep=" AND ";
		}
		if (comune != null) {
			sql.append(sep).append(" cmn.fk_comune = ?");
			
			sep=" AND ";
		}
		if (sezione != null) {
			sql.append(sep).append(" ppc.sezione = ? ");
			
			sep=" AND ";
		}
		if (foglio != null) {
			sql.append(sep).append(" ppc.foglio = ?");
			
			sep=" AND ";
		}
		if (particella != null) {
			sql.append(sep).append(" ppc.particella = ? ");
			
			sep=" AND ";
		}
		
		String ssogetoBeg = " s.id_soggetto IN( ";
		String ssogetoEnd = " ";
		if (pf != null) {
			sql.append(ssogetoBeg);
			sql.append(" ? ").append(sep);
			ssogetoBeg=" ";
			sep=" , ";
			 ssogetoEnd = " ) ";
		}
		if (pg != null) {
			sql.append(ssogetoBeg);
			sql.append(" ? ").append(sep);
			ssogetoBeg="";
			sep=" , ";
			 ssogetoEnd = " ) ";
		}
		if (prof != null) {
			sql.append(ssogetoBeg);
			sql.append(" ? ").append(sep);
			ssogetoBeg=" ";
			sep=" , ";
			 ssogetoEnd = " ) ";
		}
		sql.append(ssogetoEnd);
		sep=" AND ";
		
		if (aapp != null) {
			//builder.append("aapp=");
			
			sep=" AND ";
		}
		if (rn2k != null) {
			//builder.append("rn2k=");
			
			sep=" AND ";
		}
		if (popSeme != null) {
			//builder.append("popSeme=");
			
			sep=" AND ";
		}
		if (vincIdro != null) {
			sql.append(sep).append(" it.flg_vinc_idro = ? ");
			
			sep=" AND ";
		}
		if (compNec != null) {
			//builder.append("compNec=");
			
			sep=" AND ";
		}
		if (compO1 != null) {
			//builder.append("compO1=");
			
			sep=" AND ";
		}
		if (compO2 != null) {
			//builder.append("compO2=");
			
			sep=" AND ";
		}
		if (compO3 != null) {
			//builder.append("compO3=");
			
			sep=" AND ";
		}
		if (compO4 != null) {
			//builder.append("compO4=");
			
			sep=" AND ";
		}
		if (compO5 != null) {
			//builder.append("compO5=");
			
			sep=" AND ";
		}
		if (govForm != null) {
			//builder.append("govForm=");
			
			sep=" AND ";
		}
		if (catFor != null) {
			//builder.append("catFor=");
			
			sep=" AND ";
		}
		if (ubicazione != null) {
			//builder.append("ubicazione=");
			
			sep=" AND ";
		}
		if (tipTrasf != null) {
			//builder.append("tipTrasf=");
			
			sep=" AND ";
		}
		if (calcEconDa != null) {
			//builder.append("calcEconDa=");
			
			sep=" AND ";
		}
		if (calcEconA != null) {
			//builder.append("calcEconA=");
			
			sep=" AND ";
		}
		if (page != null) {
			//builder.append("page=");
			
			sep=" AND ";
		}
		if (limit != null) {
			//builder.append("limit=");
			
			sep=" AND ";
		}
		if (sort != null) {
			//builder.append("sort=");
			sep=" AND ";
		}
		//builder.append("]");
		return sql.toString();
	}
	
	
	public String getTipoIstanza() {
		return tipoIstanza;
	}
	public void setTipoIstanza(String tipoIstanza) {
		this.tipoIstanza = tipoIstanza;
	}
	public String getNumIstanza() {
		return numIstanza;
	}
	public void setNumIstanza(String numIstanza) {
		this.numIstanza = numIstanza;
	}
	public String getStatoIstanza() {
		return statoIstanza;
	}
	public void setStatoIstanza(String statoIstanza) {
		this.statoIstanza = statoIstanza;
	}
	public String getAnnoIstanza() {
		return annoIstanza;
	}
	public void setAnnoIstanza(String annoIstanza) {
		this.annoIstanza = annoIstanza;
	}
	public String getDataPresDa() {
		return dataPresDa;
	}
	public void setDataPresDa(String dataPresDa) {
		this.dataPresDa = dataPresDa;
	}
	public String getDataPresA() {
		return dataPresA;
	}
	public void setDataPresA(String dataPresA) {
		this.dataPresA = dataPresA;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public String getFoglio() {
		return foglio;
	}
	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public String getPf() {
		return pf;
	}
	public void setPf(String pf) {
		this.pf = pf;
	}
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getAapp() {
		return aapp;
	}
	public void setAapp(String aapp) {
		this.aapp = aapp;
	}
	public String getRn2k() {
		return rn2k;
	}
	public void setRn2k(String rn2k) {
		this.rn2k = rn2k;
	}
	public String getPopSeme() {
		return popSeme;
	}
	public void setPopSeme(String popSeme) {
		this.popSeme = popSeme;
	}
	public String getVincIdro() {
		return vincIdro;
	}
	public void setVincIdro(String vincIdro) {
		this.vincIdro = vincIdro;
	}
	
	public String getGovForm() {
		return govForm;
	}
	public void setGovForm(String govForm) {
		this.govForm = govForm;
	}
	public String getCatFor() {
		return catFor;
	}
	public void setCatFor(String catFor) {
		this.catFor = catFor;
	}
	public String getUbicazione() {
		return ubicazione;
	}
	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}
	public String getTipTrasf() {
		return tipTrasf;
	}
	public void setTipTrasf(String tipTrasf) {
		this.tipTrasf = tipTrasf;
	}
	public String getCalcEconDa() {
		return calcEconDa;
	}
	public void setCalcEconDa(String calcEconDa) {
		this.calcEconDa = calcEconDa;
	}
	public String getCalcEconA() {
		return calcEconA;
	}
	public void setCalcEconA(String calcEconA) {
		this.calcEconA = calcEconA;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
public StringBuilder buildSqlHead() {
	StringBuilder sql = new StringBuilder();
	sql.append("SELECT distinct ifo.nr_istanza_forestale,i.id_intervento,ifo.data_invio, ifo.id_istanza_intervento, ");
	sql.append(" TO_CHAR(ifo.data_inserimento, 'YYYY') as anno_istanza , ");
	sql.append(
			" ti.id_tipo_istanza, ti.descr_dett_tipoistanza,sti.id_stato_istanza, sti.descr_stato_istanza, ");
	sql.append(" s.id_soggetto, s.codice_fiscale, s.partita_iva, s.nome, s.cognome, s.denominazione, ");
	sql.append(" aapp.id_intervento as intervento_aapp, rn.id_intervento as intervento_rn, pop.id_intervento as intervento_pop, ");
	sql.append(" it.flg_vinc_idro, it.flg_compensazione, it.compenzazione_euro,cmn.id_comune, cmn.istat_comune, cmn.istat_prov, cmn.denominazione_comune ");
	sql.append(" FROM idf_t_istanza_forest ifo  ");
	sql.append(" JOIN idf_t_intervento i ON ifo.id_istanza_intervento = i.id_intervento  ");
	sql.append(" JOIN idf_t_interv_trasformazione it ON i.id_intervento = it.id_intervento  ");

	sql.append(
			" JOIN (SELECT idfr.id_intervento,idfr.id_soggetto,idfr.fk_config_utente,codice_fiscale,partita_iva, nome, cognome, denominazione ,ifts.fk_comune ");
	sql.append(" FROM idf_r_soggetto_intervento idfr ");
	sql.append(" LEFT JOIN idf_t_soggetto ifts using (id_soggetto)  ) s ON i.id_intervento = s.id_intervento  ");
	//  treba korigovati upit  bitno je da nadje jedan zapis . u rezultatu on setuje samo da ima necega ovde
	
	sql.append(" JOIN idf_r_intervento_aapp aapp ON i.id_intervento = aapp.id_intervento  ");
	sql.append(" JOIN idf_r_intervento_rn_2000 rn ON i.id_intervento = rn.id_intervento  ");
	sql.append(" JOIN idf_r_intervento_pop_seme pop ON i.id_intervento = pop.id_intervento ");
	sql.append(" JOIN idf_r_intervento_catfor cat ON i.id_intervento = cat.id_intervento  ");
	sql.append(" JOIN idf_r_paramtrasf_trasformazion pt ON it.id_intervento = pt.id_intervento ");
	sql.append(" JOIN idf_d_tipo_istanza ti ON ifo.fk_tipo_istanza = ti.id_tipo_istanza ");
	sql.append(" JOIN idf_d_stato_istanza sti ON ifo.fk_stato_istanza = sti.id_stato_istanza ");

	sql.append(
			" JOIN ( SELECT inCom.id_intervento,inCom.idgeo_pl_prop_catasto, inCom.data_inserimento, inCom.data_aggiornamento, ");
	sql.append(
			" inCom.fk_config_utente,inCom.fk_comune ,igpc.Id_comune,igpc.istat_comune, igpc.istat_prov, igpc.denominazione_comune ");
	sql.append(
			" FROM (SELECT  i.id_intervento,idfr.idgeo_pl_prop_catasto, idfr.data_inserimento, idfr.data_aggiornamento,  ");
	sql.append(" idfr.fk_config_utente,idfpl.fk_comune FROM  idf_t_intervento i ");
	sql.append(" LEFT JOIN idf_r_propcatasto_intervento idfr using (id_intervento)  ");
	sql.append(" LEFT JOIN idf_geo_pl_prop_catasto idfpl using (idgeo_pl_prop_catasto)) inCom ");
	sql.append(
			" left join idf_geo_pl_comune igpc on inCom.fk_comune=igpc.id_comune ) cmn on cmn.id_intervento=i.id_intervento ");
return sql;
}
}
