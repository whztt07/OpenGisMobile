package code.google.com;

import android.app.Activity;
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
        
        final Bundle extras = getIntent().getExtras();
        
        // Recogemos e instanciamos los TextView que van a rellenarse con los parametros pasados. Adem‡s recogemos en esta secci—n los
        // botones que vamos a necesitar.
        
        final TextView txtDNI = (TextView) findViewById(R.id.tvIdData);
        final TextView txtNombre = (TextView) findViewById(R.id.tvNameData);
        final TextView txtApellidos = (TextView) findViewById(R.id.tvSurnameData);
        
        Button cmdMisDatos = (Button) findViewById(R.id.btnConfigUserData);
        
        // Rellenamos los TextView con los parametros guardados en extras
        
        txtDNI.setText(extras.getString("dni"));
        txtNombre.setText(extras.getString("nombre"));
        txtApellidos.setText(extras.getString("apellidos"));
        
        
        
        // Acciones de bot—n
        
        
        
        // Acci—n al presionar el bot—n "Mis Datos".
        
        cmdMisDatos.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				Intent vMisDatos = new Intent(principalActivity.this,misDatosActivity.class);
				
				vMisDatos.putExtra("dni",txtDNI.getText());
				vMisDatos.putExtra("nombre",txtNombre.getText());
				vMisDatos.putExtra("apellidos",txtApellidos.getText());
				vMisDatos.putExtra("email",extras.getString("email"));
				vMisDatos.putExtra("telefono",extras.getString("telefono"));
				vMisDatos.putExtra("direccion",extras.getString("direccion"));
				vMisDatos.putExtra("poblacion",extras.getString("poblacion"));
				vMisDatos.putExtra("provincia",extras.getString("provincia"));
				vMisDatos.putExtra("cp",extras.getString("cp"));
				vMisDatos.putExtra("fec_nac",extras.getString("fec_nac"));
				
				startActivity(vMisDatos);
				
			}
        
        
        });
        
        
	}

}
