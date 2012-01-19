package code.google.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        
        Button cmdBorrar = (Button) findViewById(R.id.cmdBorrar);
        Button cmdEnviar = (Button) findViewById(R.id.cmdEnviar);   
        
        final EditText txtUser = (EditText) findViewById(R.id.txtUser);
        final EditText txtPass = (EditText) findViewById(R.id.txtPass);

        cmdBorrar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				txtUser.setText("");
				txtPass.setText("");
				
				
				
			}
		});
        
        
        
        cmdEnviar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				

				String data = getJSONdata("http://79.108.245.167/OpenGisMobile/webService.php");
				
				
				try{
					
				
				String[] resultado = parseJSONdata(data);
				
				
				
				alertaMensaje(resultado[0],resultado[0]);
				
				}catch(Exception e2){
					
					alertaMensaje("Esto peta","VAYA");
					
				}
				
			}
        	
        	
        	
        });
        
        
        
        
        
        
    }
    
    
    public void alertaMensaje(String cadena,String titulo) {
      
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
        }
    
    
	private String getJSONdata(String url) {
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


	private String[] parseJSONdata(String data) throws JSONException {
		
		String[] usersList = new String[1];
 
		JSONObject jsonObj = new JSONObject(data);
		String strData = jsonObj.getString("users");
		JSONArray jsonArray = new JSONArray(strData);
 
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userObj = jsonArray.getJSONObject(i);
			String userStr = userObj.getString("user");
			JSONObject item = new JSONObject(userStr);
		
			usersList[i] = item.getString("dni");
			
		}
 
		return usersList;
	}
    
    
    
    
    
}