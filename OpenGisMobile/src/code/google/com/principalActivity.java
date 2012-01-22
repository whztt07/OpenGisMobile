package code.google.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class principalActivity extends Activity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        // Recogemos los parametros que hemos guardado en extras desde el login.
        
        Bundle extras = getIntent().getExtras();
        
        // Recogemos e instanciamos los TextView que van a rellenarse con los parametros pasados. Adem‡s recogemos en esta secci—n los
        // botones que vamos a necesitar.
        
        final TextView txtDNI = (TextView) findViewById(R.id.tvIdData);
        final TextView txtNombre = (TextView) findViewById(R.id.tvNameData);
        final TextView txtApellidos = (TextView) findViewById(R.id.tvSurnameData);
        
        Button cmdMisDatos = (Button) findViewById(R.id.btnConfigUserData);
        Button cmdMisAperos = (Button) findViewById(R.id.btnConfigTools);
        
        // Rellenamos los TextView con los parametros guardados en extras
        
        txtDNI.setText(extras.getString("dni"));
        txtNombre.setText(extras.getString("nombre"));
        txtApellidos.setText(extras.getString("apellidos"));
        
        // Recogemos los parametros restantes
        
        final String email = extras.getString("email");
        final String telefono = extras.getString("telefono");
        final String direccion = extras.getString("direccion");
        final String poblacion = extras.getString("poblacion");
        final String provincia = extras.getString("provincia");
        final String cp= extras.getString("cp");
        final String fec_nac = extras.getString("fec_nac");
        
        // Acciones de boton
        
        
        
        // Acci—n al presionar el bot—n "Mis Datos".
        
        cmdMisDatos.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				Intent vMisDatos = new Intent(principalActivity.this,misDatosActivity.class);
				
				vMisDatos.putExtra("dni",txtDNI.getText());
				vMisDatos.putExtra("nombre",txtNombre.getText());
				vMisDatos.putExtra("apellidos",txtApellidos.getText());
				vMisDatos.putExtra("email",email);
				vMisDatos.putExtra("telefono",telefono);
				vMisDatos.putExtra("direccion",direccion);
				vMisDatos.putExtra("poblacion",poblacion);
				vMisDatos.putExtra("provincia",provincia);
				vMisDatos.putExtra("cp",cp);
				vMisDatos.putExtra("fec_nac",fec_nac);
				
				startActivity(vMisDatos);
				
			}
        
        
        });
        
        
        cmdMisAperos.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
		        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(principalActivity.this);
		        dialogBuilder.setMessage("Gesti—n de Aperos");
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Tools));
		        dialogBuilder.setPositiveButton("Ver mis aperos",new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisAperos = new Intent(principalActivity.this,misAperosListActivity.class);
						vMisAperos.putExtra("dni",txtDNI.getText());
						startActivity(vMisAperos);
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton("Crear Apero",new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	//Aqu’ la ventana del nuevo Apero
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();

			}
        	
        	
        });
        
        
	}

}
