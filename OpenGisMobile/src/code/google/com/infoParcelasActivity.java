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

public class infoParcelasActivity extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoparcelas);
        
        final EditText txtIdparcela = (EditText) findViewById(R.id.txtIdparcela);
        final EditText txtAliasparcela = (EditText) findViewById(R.id.txtAliasparcela);
        final EditText txtProvinciaparcela = (EditText) findViewById(R.id.txtProvinciaparcela);
        final EditText txtPoblacionparcela = (EditText) findViewById(R.id.txtPoblacionparcela);
        final EditText txtPoligonoparcela = (EditText) findViewById(R.id.txtPoligonoparcela);
        final EditText txtNumeroparcela = (EditText) findViewById(R.id.txtNumeroparcela);
        
        
        final Button cmdModificar = (Button) findViewById(R.id.cmdModificarParcela);
        final Button cmdBorrar = (Button) findViewById(R.id.cmdBorrarParcela);
        final Button cmdGuardar = (Button) findViewById(R.id.cmdGuardarParcela);
        
        
        
        Bundle extras = getIntent().getExtras();
        
        txtIdparcela.setText(extras.getString("idParcela"));
        txtAliasparcela.setText(extras.getString("aliasParcela"));
        txtProvinciaparcela.setText(extras.getString("provinciaParcela"));
        txtPoblacionparcela.setText(extras.getString("poblacionParcela"));
        txtPoligonoparcela.setText(extras.getString("poligonoParcela"));
        txtNumeroparcela.setText(extras.getString("numeroParcela"));
        
        final String dni = extras.getString("dni");
        
        
        // Acciones de bot—n
        
        cmdModificar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				cmdModificar.setEnabled(false);
				cmdBorrar.setEnabled(false);
				cmdGuardar.setEnabled(true);
				
				txtAliasparcela.setEnabled(true);
				txtProvinciaparcela.setEnabled(true);
				txtPoblacionparcela.setEnabled(true);
				txtPoligonoparcela.setEnabled(true);
				txtNumeroparcela.setEnabled(true);
				
			}
        	
        	
        	
        	
        });
        
        
        cmdBorrar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(infoParcelasActivity.this);
		        dialogBuilder.setMessage(getString(R.string.msgDeleteLot));
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Lots));
		        dialogBuilder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	//Eliminamos la parcela de la tabla parcela_usuario
		            	
		            	
		            	String url = "http://79.108.245.167/OpenGisMobile/BorrarParcelaWebService.php?dni="+dni+"&idparcela="+txtIdparcela.getText()+"";
		            
		            	
		            	boolean finalizado = AccesoWebService.InsertarEnWebService(url);
		            	
		            	
		            	if(finalizado){
		            		
		            		
		            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.deletedLot), Toast.LENGTH_SHORT);
		            		toast.show();
		            		
		            		Intent vMisParcelas = new Intent(infoParcelasActivity.this,ParcelasIconListView.class);
		            		vMisParcelas.putExtra("dni",dni);
		            		startActivity(vMisParcelas);
		            		
		            	}else{
		            		
		            		
		            		// No se ha podido insertar
		            		
		            	}
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
	
		            	// No hacemos nada
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();
            	
				
				
				
			}
        	
        	
        	
        	
        });
        	
        	
        
       cmdGuardar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				
				
				String url = "http://79.108.245.167/OpenGisMobile/ModificarParcelaWebService.php?idparcela="+txtIdparcela.getText()+"&alias="+txtAliasparcela.getText()+"&provincia="+txtProvinciaparcela.getText()+"&poblacion="+txtPoblacionparcela.getText()+"&poligono="+txtPoligonoparcela.getText()+"&numero="+txtNumeroparcela.getText()+"";
				
				boolean finalizado = AccesoWebService.InsertarEnWebService(url);
				
				if(finalizado){
					
					
            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.modifiedLotOK), Toast.LENGTH_SHORT);
            		toast.show();
            		
            		Intent vMisParcelas = new Intent(infoParcelasActivity.this,ParcelasIconListView.class);
            		vMisParcelas.putExtra("dni",dni);
            		startActivity(vMisParcelas);
					
					
				}else{
					
					
					
				}
				
			}
    	   
    	   
    	   
    	   
       });
        	
        
        
	}

}
