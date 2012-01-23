package code.google.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccesoWebService {
	
	
	// Este mÈtodo recoge los datos seg˙n la URL donde est· alojado el WebService
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
	
	
	
	// Este método devuelve true en caso de realizar la inserción correctamente.
	
	
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
	
	
	
	
	
	/**
	 * Este método devuelve un Array de Objetos con todos los objetos encontrados. Después deberemos crear los objetos a partir
	 * de la clase para poder mostrar los datos al usuario.
	 * 
	 * @param data String que se recoge al utilizar el método recogerDatosWebService
	 * @return
	 * @throws JSONException
	 */
	

	static Object[] convertirDatosJSONUser(String data) throws JSONException {
		
		
 
		JSONObject jsonObj = new JSONObject(data);
		String strData = jsonObj.getString("users");
		JSONArray jsonArray = new JSONArray(strData);
 
		Object[] usersList = new Object[jsonArray.length()];
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userObj = jsonArray.getJSONObject(i);
			String userStr = userObj.getString("user");
			JSONObject item = new JSONObject(userStr);
			
			UserDatos usuario = new UserDatos(item.getString("dni"),item.getString("nombre"),item.getString("apellidos"),
					
					item.getString("email"),item.getString("telefono"),item.getString("direccion"), item.getString("poblacion"),
					
					item.getString("provincia"), item.getString("cp"), item.getString("fecha_nacimiento"));
		
			
			usersList[i] = usuario;
			
		}
 
		return usersList;
	}
	
	
	
	
	
	/**
	 * Este método devuelve un Array de Objetos con todos los objetos encontrados. Después deberemos crear los objetos a partir
	 * de la clase para poder mostrar los datos al usuario.
	 * 
	 * @param data String que se recoge al utilizar el método recogerDatosWebService
	 * @return
	 * @throws JSONException
	 */
	

	static Object[] convertirDatosJSONAperos(String data) throws JSONException {
		
		
 
		JSONObject jsonObj = new JSONObject(data);
		String strData = jsonObj.getString("aperos");
		JSONArray jsonArray = new JSONArray(strData);
 
		Object[] aperosList = new Object[jsonArray.length()];
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userObj = jsonArray.getJSONObject(i);
			String userStr = userObj.getString("apero");
			JSONObject item = new JSONObject(userStr);
			
			AperosDatos apero = new AperosDatos(item.getString("idapero"),item.getString("nombre"),item.getString("tamanyo"),item.getString("descripcion"),item.getString("idtarea"),
					
					item.getString("dni_usuario"),item.getString("activo"));
		
			
			aperosList[i] = apero;
			
		}
 
		return aperosList;
	}
	
	
	/**
	 * Este método devuelve un Array de Objetos con todos los objetos encontrados. Después deberemos crear los objetos a partir
	 * de la clase para poder mostrar los datos al usuario.
	 * 
	 * @param data String que se recoge al utilizar el método recogerDatosWebService
	 * @return
	 * @throws JSONException
	 */
	

	static Object[] convertirDatosJSONProductos(String data) throws JSONException {
		
		
 
		JSONObject jsonObj = new JSONObject(data);
		String strData = jsonObj.getString("productos");
		JSONArray jsonArray = new JSONArray(strData);
 
		Object[] productosList = new Object[jsonArray.length()];
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userObj = jsonArray.getJSONObject(i);
			String userStr = userObj.getString("producto");
			JSONObject item = new JSONObject(userStr);
			
			ProductosDatos producto = new ProductosDatos(item.getString("idprod"),item.getString("nombre"),item.getString("descripcion"),item.getString("nomtarea"),item.getString("dosis"),item.getString("dni"),item.getString("activo"));
		
			
			productosList[i] = producto;
			
		}
 
		return productosList;
	}
	
	
	
	/**
	 * Este método devuelve un Array de Objetos con todos los objetos encontrados. Después deberemos crear los objetos a partir
	 * de la clase para poder mostrar los datos al usuario.
	 * 
	 * @param data String que se recoge al utilizar el método recogerDatosWebService
	 * @return
	 * @throws JSONException
	 */
	

	static Object[] convertirDatosJSONParcelas(String data) throws JSONException {
		
		
 
		JSONObject jsonObj = new JSONObject(data);
		String strData = jsonObj.getString("parcelas");
		JSONArray jsonArray = new JSONArray(strData);
 
		Object[] parcelasList = new Object[jsonArray.length()];
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userObj = jsonArray.getJSONObject(i);
			String userStr = userObj.getString("parcela");
			JSONObject item = new JSONObject(userStr);
			
			ParcelasDatos parcela = new ParcelasDatos(item.getString("idparcela"),item.getString("alias"),item.getString("provincia"),item.getString("poblacion"),item.getString("poligono"),item.getString("numero"),item.getString("activo"),item.getString("partida"),item.getString("dni_propietario"));
		
			
			parcelasList[i] = parcela;
			
		}
 
		return parcelasList;
	}

}
