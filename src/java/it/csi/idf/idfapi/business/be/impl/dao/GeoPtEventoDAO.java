package it.csi.idf.idfapi.business.be.impl.dao;

public interface GeoPtEventoDAO {

	void insertGeoPtEvento(int fkEvento, Object geometria);

	void deleteGeoPtEvento(int idEvento);
}
