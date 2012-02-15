package code.google.com;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        txtUser.setText("00000000a");
        txtPass.setText("a");
        
        final TextView txtRecoveryPass = (TextView) findViewById(R.id.txtRecoveryPass);

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
					
				
				 Object[] resultado = AccesoWebService.convertirDatosJSONUser(data);
				
				 //En caso de que el resultado sea null no ser‡ un usuario valido.

				 if(resultado[0]!=null){
				 
					 UserDatos user = (UserDatos) resultado[0];

					 String dni = user.getDNI();
				
					/*Intent vPrincipal = new Intent(loginActivity.this, principalActivity.class);
					vPrincipal.putExtra("dni",dni);	
					startActivity(vPrincipal);*/
					 
					Intent vPrincipal = new Intent(loginActivity.this, ConfigTabActivity.class);
					vPrincipal.putExtra("dni",dni);	
					startActivity(vPrincipal);
					
					
				}else{ // En caso de que no se encuentre el usuario se mostrar‡ un mensaje informativo
					
					alertaMensaje(getString(R.string.incorrectuser),getString(R.string.msgError));
					
					
				}
				
				
				
				}catch(Exception e2){
					
					alertaMensaje(getString(R.string.errorInQuery),getString(R.string.msgError));
					
				}
				
			}
        	
        	
        	
        });
        
        
        txtRecoveryPass.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
			
				
				if(txtUser.getText() == null || txtUser.getText().equals("")){
					
					
					//Meter mensaje de error, no hay usuario introducido
					
					
					
				}else{
					
					
					String direccionWebService = "http://79.108.245.167/OpenGisMobile/RecuperarPassWebService.php?dni="+txtUser.getText()+"";

					
					// Gracias al metodo recogerDatosWebService recogemos en un string los datos del servicio web realizado en formato JSON
					
					String data = AccesoWebService.recogerDatosWebService(direccionWebService);
				
					try{
						
				
					// En este momento cogemos dichos datos en formato JSON y los pasamos a string, el cual almacenamos en un array.
						
					
					 Object[] resultado = AccesoWebService.convertirDatosJSONUser(data);
					
					 //En caso de que el resultado sea null no ser‡ un usuario valido.

					 if(resultado[0]!=null){
					 
						 UserDatos user = (UserDatos) resultado[0];

						 String dni = user.getDNI();
						 String nombre = user.getNombre();
						 String apellidos = user.getApellidos();
						 String email = user.getEmail();
						 String pass = user.getPass();
	
						 
					
					
					
					
				}
				
				
			}catch(Exception e2){
				
				
				alertaMensaje("ERROR","ERROR");
				
				
			}
        	
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
    

    

    
    
    
    
    
}