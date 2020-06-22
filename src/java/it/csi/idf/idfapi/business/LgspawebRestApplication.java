package it.csi.idf.idfapi.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.idf.idfapi.business.be.impl.AdsrelSpecieServiceImpl;
import it.csi.idf.idfapi.business.be.impl.AmbitoRilievoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.AssettoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.CategoriaForestaleApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.ComuneApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.DannoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.DelegaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.DestLegnameApiImpl;
import it.csi.idf.idfapi.business.be.impl.DestinazioneApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.DocumentoAllegatoApiServiceImp;
import it.csi.idf.idfapi.business.be.impl.EsboscoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.EsposizioneApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.EventoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.EventoDatiTehniciApiImpl;
import it.csi.idf.idfapi.business.be.impl.FasciaAltimetricaApiImpl;
import it.csi.idf.idfapi.business.be.impl.FinalitaTaglioApiImpl;
import it.csi.idf.idfapi.business.be.impl.GeoPlPfaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.GeoPtAreaDiSaggioApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.GovernoApiImpl;
import it.csi.idf.idfapi.business.be.impl.InterventoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.InterventoInsericiApiServisImpl;
import it.csi.idf.idfapi.business.be.impl.IstanzaForestAPIServiceImpl;
import it.csi.idf.idfapi.business.be.impl.ParametroTrasfApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PlainAdpforHomeApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PlainSezioniApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PlainUtenteApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PrioritaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PropCatastoServiceApiImpl;
import it.csi.idf.idfapi.business.be.impl.ProprietaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.ProvinciaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PublicComuneApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PublicDocumentoAllegatoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PublicGeoPlPfaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.PublicProvinciaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.RelascopicaCompletaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.RelascopicadetailsApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.RicadenzApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.SigmaterApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.SoggettoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.SpecieApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.StadioSviluppoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.StatoAdsApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.StatoInterventoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.StatoIstanzaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TestApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TipoAdsApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TipoCampioneApiImpl;
import it.csi.idf.idfapi.business.be.impl.TipoEventiApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TipoForestaleApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TipoInterventoApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TipoIstanzaApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.TipoStrutturaleApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.UdmSpecieApiImpl;
import it.csi.idf.idfapi.business.be.impl.UsoViabilitaApiImpl;
import it.csi.idf.idfapi.util.SpringInjectorInterceptor;
import it.csi.idf.idfapi.util.SpringSupportedResource;

@ApplicationPath("restfacade/be")
public class LgspawebRestApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();
    public LgspawebRestApplication(){
         //singletons.add(new LgspawclBE());
         singletons.add(new SpringInjectorInterceptor());
         singletons.add(new GeoPlPfaApiServiceImpl());
         singletons.add(new ComuneApiServiceImpl());
         singletons.add(new SpecieApiServiceImpl());
         singletons.add(new SoggettoApiServiceImpl());
         singletons.add(new ProvinciaApiServiceImpl());
         singletons.add(new CategoriaForestaleApiServiceImpl());
         singletons.add(new AmbitoRilievoApiServiceImpl());
         singletons.add(new InterventoApiServiceImpl());
         singletons.add(new DocumentoAllegatoApiServiceImp());
         singletons.add(new PropCatastoServiceApiImpl());
         singletons.add(new EventoApiServiceImpl());
         singletons.add(new TipoEventiApiServiceImpl());
         singletons.add(new FinalitaTaglioApiImpl());
         singletons.add(new DestLegnameApiImpl());
         singletons.add(new FasciaAltimetricaApiImpl());
         singletons.add(new UdmSpecieApiImpl());
         singletons.add(new UsoViabilitaApiImpl());
         singletons.add(new InterventoInsericiApiServisImpl());
         singletons.add(new EventoDatiTehniciApiImpl());
         singletons.add(new GovernoApiImpl());
         
         //STEFAN SERVICES
         singletons.add(new PlainAdpforHomeApiServiceImpl());
         singletons.add(new PlainUtenteApiServiceImpl());
         singletons.add(new PlainSezioniApiServiceImpl());
         singletons.add(new IstanzaForestAPIServiceImpl());
         singletons.add(new StatoIstanzaApiServiceImpl());
         singletons.add(new ParametroTrasfApiServiceImpl());
         singletons.add(new RicadenzApiServiceImpl());
         singletons.add(new TipoIstanzaApiServiceImpl());
         singletons.add(new DelegaApiServiceImpl());
         singletons.add(new PublicProvinciaApiServiceImpl());
         singletons.add(new PublicComuneApiServiceImpl());
         singletons.add(new PublicGeoPlPfaApiServiceImpl());
         singletons.add(new PublicDocumentoAllegatoApiServiceImpl());
         singletons.add(new SigmaterApiServiceImpl());
         singletons.add(new StatoInterventoApiServiceImpl());
         singletons.add(new TestApiServiceImpl());
         //IVANA SERVICES
         singletons.add(new TipoForestaleApiServiceImpl());
         singletons.add(new GeoPtAreaDiSaggioApiServiceImpl());
         singletons.add(new TipoAdsApiServiceImpl());
         singletons.add(new DestinazioneApiServiceImpl());
         singletons.add(new TipoInterventoApiServiceImpl());
         singletons.add(new TipoStrutturaleApiServiceImpl());
         singletons.add(new StadioSviluppoApiServiceImpl());
         singletons.add(new EsposizioneApiServiceImpl());
         singletons.add(new ProprietaApiServiceImpl());
         singletons.add(new EsboscoApiServiceImpl());
         singletons.add(new AssettoApiServiceImpl());
         singletons.add(new AdsrelSpecieServiceImpl());
         singletons.add(new RelascopicadetailsApiServiceImpl());
         singletons.add(new RelascopicaCompletaApiServiceImpl());
         singletons.add(new StatoAdsApiServiceImpl());
         singletons.add(new DannoApiServiceImpl());
         singletons.add(new PrioritaApiServiceImpl());
         singletons.add(new TipoCampioneApiImpl());
         
         for (Object c : singletons) {
 			if (c instanceof SpringSupportedResource) {
 				SpringApplicationContextHelper.registerRestEasyController(c);
 			}
 		}
    }
    @Override
    public Set<Class<?>> getClasses() {
         return empty;
    }
    @Override
    public Set<Object> getSingletons() {
         return singletons;
    }
}
