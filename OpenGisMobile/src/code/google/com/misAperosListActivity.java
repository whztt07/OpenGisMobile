package code.google.com;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class misAperosListActivity extends ListActivity {
	
	ArrayAdapter adaptador;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle extra = getIntent().getExtras();
		
		String dni = extra.getString("dni");
		
		
		try{
			
			String url = "http://79.108.245.167/OpenGisMobile/MisAperosWebService.php?dni="+dni+"";
			
			String data = AccesoWebService.recogerDatosWebService(url);
			
			Object[] listaAperos = AccesoWebService.convertirDatosJSONAperos(data);
			
			ArrayList items = new ArrayList();
			
			for(int i=0;i<listaAperos.length;i++){
				
				
				AperosDatos apero = (AperosDatos) listaAperos[i];

				String aperoMostrar = apero.getIdApero() + " - " + apero.getNombreApero();
				
				items.add(aperoMostrar);
				
			}
			
			
			adaptador = new ArrayAdapter<String>(this,R.layout.listaaperos,items);
			
			setListAdapter(adaptador);
			
		
		}catch(Exception e2){
			
			
			
			
		}
		
		
		}
	
	
    public void alertaMensaje(String cadena,String titulo) {
        
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
        }
    

	

}
