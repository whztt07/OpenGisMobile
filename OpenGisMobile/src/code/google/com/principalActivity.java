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
        
        // Recogemos e instanciamos los TextView que van a rellenarse con los parametros pasados. Adem‡s recogemos en esta secci—n los
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
        	
	//Creamos el menœ de configuracion
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.config_menu, menu);
        return true;
    }
    
    // Acciones del menœ de configuraci—n
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.config_apero:
                
		        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(principalActivity.this);
		        dialogBuilder.setMessage(getString(R.string.ToolsConfig));
		        dialogBuilder.setCancelable(true).setTitle(getString(R.string.Tools));
		        dialogBuilder.setPositiveButton(getString(R.string.showTools),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisAperos = new Intent(principalActivity.this,AperosIconListView.class);
						vMisAperos.putExtra("dni",dni);
						startActivity(vMisAperos);
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.createTools),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	//Aqu’ la ventana del nuevo Apero
		            	
		            	try{
		            		
			            	
		            	String url = "http://79.108.245.167/OpenGisMobile/MaxIdAperoWebService.php";
		            	
		            	String data = AccesoWebService.recogerDatosWebService(url);
		            	
		            	Object[] obj = AccesoWebService.convertirDatosJSONAperoMaximo(data);
		            	
		            	AperosDatos apero = (AperosDatos) obj[0];
		            	
		            	String idNueva = Integer.parseInt(apero.getIdApero()) + 1 + "";
		            	
		            	Intent vCrearApero = new Intent(principalActivity.this,AperoNuevo.class);
		            	vCrearApero.putExtra("idNueva",idNueva);
		            	vCrearApero.putExtra("dni",dni);
		            	startActivity(vCrearApero);
		            	
		            	}catch(Exception e2){
		            		
		            		
		            	}
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();
            	
            	
            	
                return true;
            case R.id.config_Producto:
                
            	dialogBuilder = new AlertDialog.Builder(principalActivity.this);
		        dialogBuilder.setMessage(getString(R.string.ProductsConfig));
		        dialogBuilder.setCancelable(true).setTitle(getString(R.string.Products));
		        dialogBuilder.setPositiveButton(getString(R.string.showProducts),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisProductos = new Intent(principalActivity.this,ProductosIconListView.class);
						vMisProductos.putExtra("dni",dni);
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
		            	vCrearProducto.putExtra("dni",dni);
		            	startActivity(vCrearProducto);
		            	
		            	}catch(Exception e2){
		            		
		            		
		            		
		            	}
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();

            	
            	
            	
                return true;
            case R.id.config_parcela:
                
            	dialogBuilder = new AlertDialog.Builder(principalActivity.this);
		        dialogBuilder.setMessage(getString(R.string.LotsConfig));
		        dialogBuilder.setCancelable(true).setTitle(getString(R.string.Lots));
		        dialogBuilder.setPositiveButton(getString(R.string.showLots),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) {
		            	
		            	
						
						Intent vMisParcelas = new Intent(principalActivity.this,ParcelasIconListView.class);
						vMisParcelas.putExtra("dni",dni);
						startActivity(vMisParcelas);
		            	
		            } 
		        }); 
		        
		        dialogBuilder.setNegativeButton(getString(R.string.createLots),new DialogInterface.OnClickListener() { 
		            public void onClick(DialogInterface dialog, int arg1) { 
		                
		            	
		            	Intent vTodasLasParcelas = new Intent(principalActivity.this,TodasParcelasIconListView.class);
		            	vTodasLasParcelas.putExtra("dni",dni);
		            	startActivity(vTodasLasParcelas);
		            	
		            } 
		        }); 
		        dialogBuilder.create().show();
            	
            	
            	
                return true;
            case R.id.config_datos:
                
				
            	String direccionWebService = "http://79.108.245.167/OpenGisMobile/MostrarDatosWebService.php?dni="+dni;

				
				
				String data = AccesoWebService.recogerDatosWebService(direccionWebService);
			
				try{
					
			
				// En este momento cogemos dichos datos en formato JSON y los pasamos a string, el cual almacenamos en un array.
					
				
				 Object[] resultado = AccesoWebService.convertirDatosJSONUser(data);
				
				 UserDatos user = (UserDatos) resultado[0];

				String nombre = user.getNombre();
				String apellidos = user.getApellidos();
				String email = user.getEmail();
				String telefono = user.getTelefono();
            	
            	
				Intent vMisDatos = new Intent(principalActivity.this,misDatosActivity.class);
				vMisDatos.putExtra("dni",dni);
				vMisDatos.putExtra("nombre",nombre);
				vMisDatos.putExtra("apellidos",apellidos);
				vMisDatos.putExtra("email",email);
				vMisDatos.putExtra("telefono",telefono);
				startActivity(vMisDatos);
            	
				}catch(Exception e2){
					
					
				}
            	
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
	

