package code.google.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class infoAperosActivity extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoaperos);
        
        Bundle extras = getIntent().getExtras();
        
        final EditText txtID = (EditText) findViewById(R.id.txtIDApero);
        final EditText txtNombreApero = (EditText) findViewById(R.id.txtNombreApero);
        final EditText txtTamanyo = (EditText) findViewById(R.id.txtTamanyoApero);
        final EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcionApero);
        
        
        final Button cmdModificar = (Button) findViewById(R.id.cmdModificarApero);
        final Button cmdBorrar = (Button) findViewById(R.id.cmdBorrarApero);
        final Button cmdGuardar = (Button) findViewById(R.id.cmdGuardarApero);
        
        txtID.setText(extras.getString("idApero"));
        txtNombreApero.setText(extras.getString("nombreApero"));
        txtTamanyo.setText(extras.getString("tama–oApero"));
        txtDescripcion.setText(extras.getString("descripcionApero"));
        
        
        // Acciones de bot—n!
        
        
        cmdModificar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
			
				cmdBorrar.setEnabled(false);
				cmdModificar.setEnabled(false);
				cmdGuardar.setEnabled(true);
				
				txtNombreApero.setEnabled(true);
				txtTamanyo.setEnabled(true);
				txtDescripcion.setEnabled(true);
				
				
			}
        	
        });
        
        
        cmdBorrar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				
				
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(infoAperosActivity.this);
		        dialogBuilder.setMessage(getString(R.string.msgDeleteTool));
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Tools));
		        dialogBuilder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
		            	//En caso afirmativo borramos el Apero con ese ID.
		            	
		            	String direccionWebService = "http://79.108.245.167/OpenGisMobile/BorrarAperoWebService.php?idapero="+txtID.getText();
		            	
		            	
		            	boolean estado = AccesoWebService.InsertarEnWebService(direccionWebService);
		            	
		            	if(estado){
		            		
		            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.deletedTool), Toast.LENGTH_SHORT);
		            		toast.show();
		            		
		            		Intent vMisAperos = new Intent(infoAperosActivity.this, misAperosListActivity.class);
		            		startActivity(vMisAperos);
		            		
		            	}else{
		            		
		            		// No se ha podido eliminar
		            		
		            		
		            	}
		            	
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();
				
				
			}
        	
        	
        	
        });
        
        
        
        
        
        
	}
	
}
