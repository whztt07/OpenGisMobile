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
		            		
		            	
		            		
		            		Intent vMisProductos = new Intent(infoProductosActivity.this, ProductosIconListView.class);
		            		vMisProductos.putExtra("dni",dniUser);
		            		startActivity(vMisProductos);
							
							
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
        
        
        cmdGuardarProducto.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
			
				
				String idProducto = txtIDProducto.getText().toString();
				String nombre = txtNombreProducto.getText().toString();
				String dosis = txtDosisProducto.getText().toString();
				String descripcion = txtDescripcionProducto.getText().toString();
				
				String direccionWebService = "http://79.108.245.167/OpenGisMobile/ModificarProductoWebService.php?idproducto="+idProducto+"&nombre="+nombre+"&dosis="+dosis+"&descripcion="+descripcion+"";
				
				direccionWebService = direccionWebService.replace(" ","%20");
				
				
				boolean acceso = AccesoWebService.InsertarEnWebService(direccionWebService);
				
				if(acceso){
					
            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.modifyProductOK), Toast.LENGTH_SHORT);
            		toast.show();
            		
            		Intent vMisProductos = new Intent(infoProductosActivity.this, ProductosIconListView.class);
            		vMisProductos.putExtra("dni",dniUser);
            		startActivity(vMisProductos);
					
					
				}else{
					
            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.msgError), Toast.LENGTH_SHORT);
            		toast.show();
            		
            		Intent vMisProductos = new Intent(infoProductosActivity.this, ProductosIconListView.class);
            		vMisProductos.putExtra("dni",dniUser);
            		startActivity(vMisProductos);
					
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
