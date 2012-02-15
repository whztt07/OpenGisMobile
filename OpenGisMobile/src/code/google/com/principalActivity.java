package code.google.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class principalActivity extends Activity {
	
	
	private String dni;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        // Recogemos los parametros que hemos guardado en extras desde el login.
        
        final Bundle extras = getIntent().getExtras();
        
        // Recogemos e instanciamos los TextView que van a rellenarse con los parametros pasados. Adem�s recogemos en esta secci�n los
        // botones que vamos a necesitar.
        
        final TextView txtDNI = (TextView) findViewById(R.id.tvIdData);
        

        Button cmdStart = (Button) findViewById(R.id.btnStart);
        
        // Rellenamos los TextView con los parametros guardados en extras
        
        txtDNI.setText(extras.getString("dni"));        
        this.dni = extras.getString("dni");

        
        //Cuando presiona el boton comenzar
        
        cmdStart.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {

				
			Intent vTarea = new Intent(principalActivity.this,SelectTrabajo.class);	
			vTarea.putExtra("dni",extras.getString("dni"));
			startActivity(vTarea);
			
				
			}
		});
        
       
				
			}
        	
	//Creamos el men� de configuracion
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.config_menu, menu);
        return true;
    }

    
    @Override
    public void onBackPressed() {
    
    	
    	Intent vLogin = new Intent(principalActivity.this,loginActivity.class);
    	startActivity(vLogin);
    	
    	
    return;
    }

    public void alertaMensaje(String cadena,String titulo) {
        
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
        }
    

    

        
	}
	

