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
import android.widget.Toast;

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
        
        
        
        //Acciones de boton
        
        
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
				
				//Aquí validaremos los datos!
				
				if(ValidacionDatos.validarTexto(txtNombre.getText()+"")==false){
					
					
					alertaMensaje(getString(R.string.nameInvalid),getString(R.string.msgError));
					
					
				}else{
				
				
					//Llamamos al web service que guarda los datos
					
					
					String direccionWebService = "http://79.108.245.167/OpenGisMobile/ModificarMisDatoswebService.php?nombre="+ txtNombre.getText() +"&apellidos="+txtApellidos.getText()+"&email="+txtEmail.getText()+"&telefono="+txtTelefono.getText()+"&dni="+txtDNI.getText()+"";
					
					direccionWebService = direccionWebService.replaceAll(" ","%20"); // Con esto solucionamos los espacios en blanco de la direccion de consulta
					
					
					boolean correcto = AccesoWebService.InsertarEnWebService(direccionWebService);
					
					
					if(correcto==true){
						
						
						
	            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.msgModifyOK), Toast.LENGTH_SHORT);
	            		toast.show();
								
	            		Intent vLogin = new Intent(misDatosActivity.this, loginActivity.class);
	            		startActivity(vLogin);
	            		

					}else{
						
						alertaMensaje(getString(R.string.updateError),getString(R.string.msgError));
						
					}
					
				}
			}
        	
        	
        });
        
	}
	
	
	public void alertaMensaje(String mensaje,String titulo){
		
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(misDatosActivity.this);
        dialogBuilder.setMessage(mensaje);
        dialogBuilder.setCancelable(true).setTitle(titulo); 
        dialogBuilder.create().show();
		
		
	}
	

}
