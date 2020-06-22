package it.csi.idf.idfapi.business.be.service;

import org.opengis.geometry.Geometry;

import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

public interface SIGMATER {
	
	PlainParticelleCatastali getParticelleCatastali(PlainParticelleCatastali particelleCatastali);
	Geometry getGeometryFromParticelleCatastali(PlainParticelleCatastali particelleCatastali);
}
