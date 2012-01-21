package code.google.com;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class misAperosListActivity extends ListActivity {
	
	ArrayAdapter adaptador;
	final String[] items = new String[] {"Hola","Adi—s"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		adaptador = new ArrayAdapter<String>
        (this,R.layout.listaaperos,items);
		
		setListAdapter(adaptador);
		
		}
	
	

}
