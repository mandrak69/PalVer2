package it.csi.idf.idfapi.business.be.impl.dao;

public interface GeoLnEventoDAO {

	void insertGeoLnEvento(int fkEvento, Object geometria);

	void deleteGeoLnEvento(int idEvento);
}