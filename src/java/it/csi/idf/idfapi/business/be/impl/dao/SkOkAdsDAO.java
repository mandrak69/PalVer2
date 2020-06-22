package it.csi.idf.idfapi.business.be.impl.dao;

public interface SkOkAdsDAO {
		
	void insertFlgSez1(String codiceAds, int stepNum);
		
	void updateStepFinished(Long codiceAds, int stepNumber);
	
	boolean isStepDone(Long codiceAds, int stepNumber);
		
	int getLastStepDone(Long codiceAds);
	
	String getWhereClause();
	
	public boolean skOdsExists(Long codiceAds);
}
