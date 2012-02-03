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
import android.widget.Toast;

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
        Button cmdMisAperos = (Button) findViewById(R.id.btnConfigTools);
        Button cmdMisProductos = (Button) findViewById(R.id.btnConfigProducts);
        Button cmdMisParcelas = (Button) findViewById(R.id.btnConfigLots);
        Button cmdStart = (Button) findViewById(R.id.btnStart);
        
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
        
        //Cuando presiona el boton comenzar
        
        cmdStart.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {

				
			Intent vTarea = new Intent(principalActivity.this,SelectTrabajo.class);	
			startActivity(vTarea);
			
				
			}
		});
        
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
		        dialogBuilder.setMessage(getString(R.string.ToolsConfig));
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Tools));
		        dialogBuilder.setPositiveButton(getString(R.string.showTools),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisAperos = new Intent(principalActivity.this,AperosIconListView.class);
						vMisAperos.putExtra("dni",txtDNI.getText());
						startActivity(vMisAperos);
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.createTools),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	//Aqu’ la ventana del nuevo Apero
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();

			}
        	
        	
        });
        
        
        
        
        cmdMisProductos.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(principalActivity.this);
		        dialogBuilder.setMessage(getString(R.string.ProductsConfig));
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Products));
		        dialogBuilder.setPositiveButton(getString(R.string.showProducts),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisProductos = new Intent(principalActivity.this,ProductosIconListView.class);
						vMisProductos.putExtra("dni",extras.getString("dni"));
						startActivity(vMisProductos);
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.createProducts),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	//Aqu’ la ventana del nuevo Producto
		            	
		            	//Buscamos la máxima id.
		            	try{
		            		
		            	
		            	String url = "http://79.108.245.167/OpenGisMobile/MaxIdProductoWebService.php";
		            	String data = AccesoWebService.recogerDatosWebService(url);
		            	
		            	Object[] obj = AccesoWebService.convertirDatosJSONProductoMaximo(data);
		            	
		            	ProductosDatos producto = (ProductosDatos) obj[0];
		            	
		            	String idNueva = Integer.parseInt(producto.getIdprod()) + 1 + "";	
		            	Intent vCrearProducto = new Intent(principalActivity.this,ProductoNuevo.class);
		            	vCrearProducto.putExtra("idNueva",idNueva);
		            	startActivity(vCrearProducto);
		            	
		            	}catch(Exception e2){
		            		
		            		
		            		
		            	}
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();

			}
        	
        	
        });
				
        
        
        cmdMisParcelas.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				
				
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(principalActivity.this);
		        dialogBuilder.setMessage(getString(R.string.LotsConfig));
		        dialogBuilder.setCancelable(false).setTitle(getString(R.string.Lots));
		        dialogBuilder.setPositiveButton(getString(R.string.showLots),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisParcelas = new Intent(principalActivity.this,ParcelasIconListView.class);
						vMisParcelas.putExtra("dni",txtDNI.getText());
						startActivity(vMisParcelas);
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.createLots),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	
		            	Intent vTodasLasParcelas = new Intent(principalActivity.this,TodasParcelasIconListView.class);
		            	vTodasLasParcelas.putExtra("dni",txtDNI.getText());
		            	startActivity(vTodasLasParcelas);
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();

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
	

