package code.google.com;

import android.app.Activity;
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
        
        EditText txtDNI = (EditText) findViewById(R.id.txtDNI); 
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
				
				
				
				// Para finalizar dejamos los botones al igual que antes
			
				cmdModificar.setEnabled(true);
				cmdGuardar.setEnabled(false);
				
				txtNombre.setEnabled(false);
				txtApellidos.setEnabled(false);
				txtEmail.setEnabled(false);
				txtTelefono.setEnabled(false);
				
			}
        	
        	
        });
        
	}

}
