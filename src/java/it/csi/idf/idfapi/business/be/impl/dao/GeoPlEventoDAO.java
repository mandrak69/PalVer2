package it.csi.idf.idfapi.business.be.impl.dao;

public interface GeoPlEventoDAO {
	
	void insertGeoPlEvento(int fkEvento, Object geometria);

	void deleteGeoPlEvento(int idEvento);
}