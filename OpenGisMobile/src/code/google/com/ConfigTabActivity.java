package code.google.com;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;

public class ConfigTabActivity extends TabActivity{
	private TabHost mTabHost;
	private String dni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configtabhost);
		final Bundle extras = getIntent().getExtras();
		this.dni = extras.getString("dni");
		
		//Crea el TabHost
		mTabHost = getTabHost();
		//Crea el TabSpec
		TabHost.TabSpec spec;
		//Crea el intent
		Intent intent;
		
		/** 
		 * En esta parte del OnCreate definimos las Pestañas y sus respectivos intents
		 */
		
		//Define la tab abierta por defecto
		mTabHost.setCurrentTab(0);
		
		//Tab Start
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,principalActivity.class);
			intent.putExtra("dni",dni);	
				//Define la tab
			spec=mTabHost.newTabSpec("start").setIndicator(getString(R.string.start) ,getResources().getDrawable(R.drawable.home)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
		
		//Tab Aperos
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,AperosIconListView.class);
			intent.putExtra("dni",dni);	
				//Define la tab
			spec=mTabHost.newTabSpec("aperos").setIndicator(getString(R.string.Tools) ,getResources().getDrawable(R.drawable.apero)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
			
		//Tab Productos
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,ProductosIconListView.class);
			intent.putExtra("dni",dni);
				//Define la tab
			spec=mTabHost.newTabSpec("productos").setIndicator(getString(R.string.Products) ,getResources().getDrawable(R.drawable.producto)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
			
		//Tab Parcelas
			//Marca donde debe dirigirse la tab
			intent = new Intent(this,ParcelasIconListView.class);
			intent.putExtra("dni",dni);
				//Define la tab
			spec=mTabHost.newTabSpec("parcelas").setIndicator(getString(R.string.Lots) ,getResources().getDrawable(R.drawable.lots)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
			
		//Tab Datos User
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,misDatosActivity.class);
			intent.putExtra("dni",dni);	
				//Define la tab
			spec=mTabHost.newTabSpec("user").setIndicator(getString(R.string.user) ,getResources().getDrawable(R.drawable.usuario)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.config_apero:
                
		                
		            	//Aqu’ la ventana del nuevo Apero
		            	
		            	try{
		            		
			            	
		            	String url = "http://79.108.245.167/OpenGisMobile/MaxIdAperoWebService.php";
		            	
		            	String data = AccesoWebService.recogerDatosWebService(url);
		            	
		            	Object[] obj = AccesoWebService.convertirDatosJSONAperoMaximo(data);
		            	
		            	AperosDatos apero = (AperosDatos) obj[0];
		            	
		            	String idNueva = Integer.parseInt(apero.getIdApero()) + 1 + "";
		            	
		            	Intent vCrearApero = new Intent(ConfigTabActivity.this,AperoNuevo.class);
		            	vCrearApero.putExtra("idNueva",idNueva);
		            	vCrearApero.putExtra("dni",dni);
		            	startActivity(vCrearApero);
		            	
		            	}catch(Exception e2){
		            		
		            		
		            	}
		            	
		            

                return true;
           /* case R.id.config_Producto:
                
            	dialogBuilder = new AlertDialog.Builder(ConfigTabActivity.this);
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
            	
            	
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	

}
