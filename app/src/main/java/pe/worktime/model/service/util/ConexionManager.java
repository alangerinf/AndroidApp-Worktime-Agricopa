package pe.worktime.model.service.util;

//import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class ConexionManager {
   
    private static String ContentLanguage = "es-ES";
    private static String ContentType = "text/xml; charset=utf-8";
    private static URI uri = null;
    private static HttpPost post = null;
    
    private ConexionManager() {
    }

    public static synchronized InputStream getHttpResponseSoapAccionPOST(String URL, String data, String SoapAccion) throws IOException, Exception {
        System.out.println(URL);
        System.out.println(data);
        InputStream response = null;
        try {
            uri = new URI(URL);
            post = new HttpPost(uri);
            post.setHeader("SOAPAction", SoapAccion);
            post.setHeader("SOAPAction", SoapAccion);
            post.setHeader("SOAPAction", SoapAccion);
            post.setHeader("Content-Language", ContentLanguage);
            post.setHeader("Content-Type", ContentType);
            post.setEntity(new StringEntity(data));
            HttpClient client = new DefaultHttpClient();
            HttpResponse resp = client.execute(post);
            response = resp.getEntity().getContent();
            //---------------------------------------------
        /*
        	BufferedReader in = new BufferedReader(new InputStreamReader(response));
        	String inputLine;
        	while ((inputLine = in.readLine()) != null)
        	    System.out.println(inputLine);
        	in.close();*/
            //--------------------------------------------
            System.out.println("Result: ");
        } catch (ClientProtocolException e) {
            Log.e("CM", "HTTP protocol error", e);
        } catch (IOException e) {
            Log.e("CM", "Communication error", e);
        }
        return response;
    }
}