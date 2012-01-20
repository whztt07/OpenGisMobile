package code.google.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class AccesoWebService {
	
	
	// Este método recoge los datos según la URL donde está alojado el WebService
	// Devuelve un String en formato JSON.
	
	static String recogerDatosWebService(String url) {
		String response = "";
		
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet method = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(method);
			InputStream is = httpResponse.getEntity().getContent();
			response = convertStreamToString(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	
	private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
}
	
	
	
	// Este mŽtodo devuelve true en caso de realizar la inserci—n correctamente.
	
	
	static boolean InsertarEnWebService(String url) {
		
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet method = new HttpGet(url);
			httpClient.execute(method);
			return true;

		} catch (Exception e) {
			
			return false;
		}

	}

}
