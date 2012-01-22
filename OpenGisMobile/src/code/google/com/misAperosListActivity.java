package code.google.com;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
			
			final Object[] listaAperos = AccesoWebService.convertirDatosJSONAperos(data);
			
			ArrayList items = new ArrayList();
			final ArrayList objetosCompletos = new ArrayList();
			
			for(int i=0;i<listaAperos.length;i++){
				
				
				AperosDatos apero = (AperosDatos) listaAperos[i];
				
				// En caso de que ese apero del usuario estŽ activo, lo a–adiremos
				
				if(apero.getEstadoApero().equals("1")){

					String aperoMostrar = apero.getNombreApero();
					objetosCompletos.add(apero);
					items.add(aperoMostrar);
				}
				
			}
			
			
			adaptador = new ArrayAdapter<String>(this,R.layout.listaaperos,items);
			
			setListAdapter(adaptador);
			
			ListView lv = getListView();
			lv.setTextFilterEnabled(true);

			// Cuando seleccionemos un objeto de la lista accederemos a la informaci—n de dicho objeto.
			
			  lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) {
			      

			    	// Recogemos la posici—n para ver los datos segœn la lista anterior. (listaAperos)
			    	
			    	AperosDatos aperoSeleccionado = (AperosDatos) objetosCompletos.get(position);
			    	
			    	Intent vInfoApero = new Intent(misAperosListActivity.this,infoAperosActivity.class);
			    	vInfoApero.putExtra("idApero",aperoSeleccionado.getIdApero() );
			    	vInfoApero.putExtra("nombreApero",aperoSeleccionado.getNombreApero());
			    	vInfoApero.putExtra("tama–oApero",aperoSeleccionado.getTamanyoApero());
			    	vInfoApero.putExtra("descripcionApero",aperoSeleccionado.getDescripcionApero());
			    	vInfoApero.putExtra("dniUser",aperoSeleccionado.getDNIUser());
			    	startActivity(vInfoApero);
			    	
			    	
			    	
			    }

			  });
			
			
			
			
			
		
		}catch(Exception e2){
			
			alertaMensaje(getString(R.string.errorInQuery),getString(R.string.msgError));
			
		}
		
		
		}
	
	
    public void alertaMensaje(String cadena,String titulo) {
        
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
        }
    

	

}
