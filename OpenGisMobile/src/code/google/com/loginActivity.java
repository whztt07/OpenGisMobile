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
import android.content.Intent;
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
				
				// Al hacer clic en enviar le pasamos la URL con los parametros necesarios (user y pass) para realizar el web service
				
				String direccionWebService = "http://79.108.245.167/OpenGisMobile/LoginwebService.php?dni="+txtUser.getText()+"&pass="+txtPass.getText()+"";

				
				// Gracias al metodo recogerDatosWebService recogemos en un string los datos del servicio web realizado en formato JSON
				
				String data = AccesoWebService.recogerDatosWebService(direccionWebService);
			
				try{
					
			
				// En este momento cogemos dichos datos en formato JSON y los pasamos a string, el cual almacenamos en un array.
					
				
				 Object[] resultado = convertirJSON(data);
				
				 //En caso de que el resultado sea null no ser‡ un usuario valido.

				 if(resultado[0]!=null){
				 
				
					 UserDatos user = (UserDatos) resultado[0];

					 String dni = user.getDNI();
					 String nombre = user.getNombre();
					 String apellidos = user.getApellidos();
					 String email = user.getEmail();
					 String telefono = user.getTelefono();
					 String direccion = user.getPoblacion();
					 String provincia = user.getProvincia();
					 String poblacion = user.getPoblacion();
					 String cp = user.getCP();
					 String fec_nac = user.getFecNac();
				
					Intent vPrincipal = new Intent(loginActivity.this, principalActivity.class);
					vPrincipal.putExtra("dni",dni);
					vPrincipal.putExtra("nombre",nombre);
					vPrincipal.putExtra("apellidos",apellidos);
					vPrincipal.putExtra("email",email);
					vPrincipal.putExtra("telefono",telefono);
					vPrincipal.putExtra("direccion",direccion);
					vPrincipal.putExtra("poblacion",poblacion);
					vPrincipal.putExtra("provincia",provincia);
					vPrincipal.putExtra("cp",cp);
					vPrincipal.putExtra("fec_nac",fec_nac);
					startActivity(vPrincipal);
					
					
				}else{ // En caso de que no se encuentre el usuario se mostrar‡ un mensaje informativo
					
					alertaMensaje("Usuario o contrase–a incorrecto","Login erroneo");
					
					
				}
				
				
				
				}catch(Exception e2){
					
					alertaMensaje("Error al realizar la consulta al servidor","Error");
					
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
    

    

	private Object[] convertirJSON(String data) throws JSONException {
		
		Object[] usersList = new Object[1];
 
		JSONObject jsonObj = new JSONObject(data);
		String strData = jsonObj.getString("users");
		JSONArray jsonArray = new JSONArray(strData);
 
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
    
    
    
    
    
}