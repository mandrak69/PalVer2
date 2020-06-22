package it.csi.idf.idfapi.business.be.service.helper;

import java.io.IOException;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.util.Constants;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;


public class ApiManagerServiceHelper {

	/**
	 * Logger.
	 */
	protected static final Logger LOGGER = Logger
			.getLogger(Constants.COMPONENT_NAME + ".service");
	
	private OauthHelper oauthHelper;

	private String idfTokenUrl;
	
	public ApiManagerServiceHelper(String idfTokenUrl){
		this.idfTokenUrl = idfTokenUrl;
	}

	public final OauthHelper getOauthHelper() throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getOauthHelper] BEGIN");
	
		// TODO reperire info configurazioni da DB
		//IdfCnfParamApimgrDto config =

    	
		if (this.oauthHelper == null) {
			this.oauthHelper = new OauthHelper(
					this.idfTokenUrl,
					//"https://tst-api-ent.ecosis.csi.it/api/token",
					//config.getConsumerKey(),
					"_kPhXq8tFmuNWn0NAc2kXFHZXr4a",
					//config.getConsumerSecret(), 10000);
					"3LOJ57emkDnlnWm9kbfiBct1yQUa", 10000);
		}
		
		LOGGER.debug("[ApiManagerServiceHelper::getOauthHelper] END");
		return this.oauthHelper;
		

	}

}
