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

public class infoProductosActivity extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoproductos);
        
        Bundle extras = getIntent().getExtras();
        
        
        final EditText txtIDProducto = (EditText) findViewById(R.id.txtIDProducto);
        final EditText txtNombreProducto = (EditText) findViewById(R.id.txtNombreProducto);
        final EditText txtDosisProducto = (EditText) findViewById(R.id.txtDosisProducto);
        final EditText txtDescripcionProducto = (EditText) findViewById(R.id.txtDescripcionProducto);
        
        final Button cmdModificarProducto = (Button) findViewById(R.id.cmdModificarProducto);
        final Button cmdEliminarProducto = (Button) findViewById(R.id.cmdBorrarProducto);
        final Button cmdGuardarProducto = (Button) findViewById(R.id.cmdGuardarProducto);
        
        
        txtIDProducto.setText(extras.getString("idProducto"));
        txtNombreProducto.setText(extras.getString("nombreProducto"));
        txtDosisProducto.setText(extras.getString("dosisProducto"));
        txtDescripcionProducto.setText(extras.getString("descripcionProducto"));
        final String dniUser = extras.getString("dniUsuario");
        
        
        //Acciones de bot—n
        
        cmdModificarProducto.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				
				
				cmdModificarProducto.setEnabled(false);
				cmdEliminarProducto.setEnabled(false);
				cmdGuardarProducto.setEnabled(true);
				
				txtNombreProducto.setEnabled(true);
				txtDosisProducto.setEnabled(true);
				txtDescripcionProducto.setEnabled(true);
				
			}
        	
        	
        	
        	
        });
        
        
        cmdEliminarProducto.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
			
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(infoProductosActivity.this);
		        dialogBuilder.setMessage(getString(R.string.msgDeleteProduct));
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Products));
		        dialogBuilder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						String idProducto = txtIDProducto.getText().toString();
						
						String direccionWebService = "http://79.108.245.167/OpenGisMobile/BorrarProductoWebService.php?idproducto="+idProducto+"";
						
						boolean finalizado = AccesoWebService.InsertarEnWebService(direccionWebService);
						
						if(finalizado){
							
		            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.deletedProduct), Toast.LENGTH_SHORT);
		            		toast.show();
		            		
		            	
		            		
		            		Intent vMisAperos = new Intent(infoProductosActivity.this, ProductosIconListView.class);
		            		vMisAperos.putExtra("dni",dniUser);
		            		startActivity(vMisAperos);
							
							
						}else{
							
							
							// No se ha podido eliminar el producto seleccionado
							
							
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
