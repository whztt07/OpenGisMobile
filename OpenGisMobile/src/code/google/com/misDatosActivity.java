package code.google.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class misDatosActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.misdatos);
        
        final EditText txtDNI = (EditText) findViewById(R.id.txtDNI); 
        final EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        final EditText txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText txtTelefono = (EditText) findViewById(R.id.txtTlf);
        
        final Button cmdModificar = (Button) findViewById(R.id.cmdModificar);
        final Button cmdGuardar = (Button) findViewById(R.id.cmdGuardar);
        
        Bundle extras = getIntent().getExtras();
        
        
        txtDNI.setText(extras.getString("dni"));  
        txtNombre.setText(extras.getString("nombre"));
        txtApellidos.setText(extras.getString("apellidos"));
        txtEmail.setText(extras.getString("email"));
        txtTelefono.setText(extras.getString("telefono"));
        
        
        
        //Acciones de bot—n
        
        
        cmdModificar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
			
				cmdModificar.setEnabled(false);
				cmdGuardar.setEnabled(true);
				
				txtNombre.setEnabled(true);
				txtApellidos.setEnabled(true);
				txtEmail.setEnabled(true);
				txtTelefono.setEnabled(true);

				
				
			}
        	
        	
        });
        
        
        cmdGuardar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				//Aqu’ validaremos los datos!
				
				
				
				//Llamamos al web service que guarda los datos
				
				
				String direccionWebService = "http://79.108.245.167/OpenGisMobile/ModificarMisDatoswebService.php?nombre="+ txtNombre.getText() +"&apellidos="+txtApellidos.getText()+"&email="+txtEmail.getText()+"&telefono="+txtTelefono.getText()+"&dni="+txtDNI.getText()+"";
				
				direccionWebService = direccionWebService.replaceAll(" ","%20"); // Con esto solucionamos los espacios en blanco de la direccion de consulta
				
				
				boolean correcto = AccesoWebService.InsertarEnWebService(direccionWebService);
				
				
				if(correcto==true){
					
					//Mostramos un mensaje indicando que se actualizar‡n los datos y que debe volver a loguearse.
					
			        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(misDatosActivity.this);
			        dialogBuilder.setMessage("Se han modificado sus datos personales.\nEl programa le devolver‡ a la pantalla de Login.");
			        dialogBuilder.setCancelable(false).setTitle("OK");
			        dialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() { 
			            public void onClick(DialogInterface dialog, int arg1) { 
			                
							//Al insertarlo volvemos a la ventana Login, haciendo que el usuario vuelva a loguearse para actualizar sus datos.
							
							
							Intent vLogin = new Intent(misDatosActivity.this, loginActivity.class);
							startActivity(vLogin);
			            	
			            } 
			        }); 
			        dialogBuilder.create().show();
			        
					
					

					
					
				}else{
					
					alertaMensaje("No se ha podido realizar la inserci—n de forma correcta","Error");
					
				}
				
				
			}
        	
        	
        });
        
	}
	
	
	public void alertaMensaje(String mensaje,String titulo){
		
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(misDatosActivity.this);
        dialogBuilder.setMessage(mensaje);
        dialogBuilder.setCancelable(false).setTitle(titulo); 
        dialogBuilder.create().show();
		
		
	}
	

}
