package code.google.com;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class misAperosListActivity extends ListActivity {
	
	ArrayAdapter adaptador;
	
	Bundle extra = getIntent().getExtras();
	
	String dni = extra.getString("dni");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String url = "http://79.108.245.167/OpenGisMobile/MisAperosWebService.php?dni="+dni+"";
		
		String data = AccesoWebService.recogerDatosWebService(url);
	
		final String[] items = new String[] {"Hola","Adi—s"};
		
		adaptador = new ArrayAdapter<String>
        (this,R.layout.listaaperos,items);
		
		setListAdapter(adaptador);
		
		}
	
	

}
