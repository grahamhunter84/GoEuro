package GoEuro;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;


public class GoEuroWebService {

    Client client = null;

    /**
     * Constructor
     * @throws URISyntaxException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
   public GoEuroWebService() 
     throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException
    {
    	//Initialize the client upfront as well as the SSL context.
    	//As the certificate is self-signed, need create a new trustmanager to accept it.
    	SSLContext ssl_context = getSSLContext();
    	client = ClientBuilder.newBuilder().sslContext(ssl_context).build();  
    	
    	//Register jackson to map the JSON to the object.
    	JacksonFeature jackson = new JacksonFeature();
    	client.register(jackson);
    }

    /**
     * This calls the web service to return the geographic information on a location
     * @param name
     * @return
     * ResultsWrapper, contains the list of location objects mapped from the JSON response.
     * @throws Exception
     */
    public ResultsWrapper callGetLocationInformation(String name) throws Exception
    {
       
        final WebTarget documentTarget = client.target(URI_NAME).path(name);
        
        final ResultsWrapper location_result = documentTarget.request(MediaType.APPLICATION_JSON)
                .get(ResultsWrapper.class); 
                
        return location_result;
    }

    /**
     * Create the new SSLContext with a TrustManager that accepts the web service's certificate.
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private SSLContext getSSLContext() throws NoSuchAlgorithmException,
	    KeyManagementException {
	final SSLContext sslContext = SSLContext.getInstance(SSLContextType);
	
	// set up a TrustManager that trusts everything
	sslContext.init(null, new TrustManager[] 
			{ new X509TrustManager() 
				{
			
				    public X509Certificate[] getAcceptedIssuers() {
				        return null;
				    }
				
				
				    public void checkClientTrusted(X509Certificate[] certs,
				            String authType) {
				    }
				
				 
				    public void checkServerTrusted(X509Certificate[] certs,
				            String authType) {
				    }
				} 
			}, new SecureRandom());
	return sslContext;
	}
    
    private static final String URI_NAME =  "https://api.goeuro.de/api/v1/suggest/position/en/name/";
    private static final String SSLContextType	= "SSL";
}
