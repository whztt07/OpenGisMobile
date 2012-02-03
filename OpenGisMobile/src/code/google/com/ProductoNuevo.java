package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class ProductoNuevo extends Activity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearproducto);
        
        Bundle extras = getIntent().getExtras();
        
        Spinner cbTareas = (Spinner) findViewById(R.id.cbTareas);
        EditText txtId = (EditText) findViewById(R.id.txtIDProductoNuevo);
        
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.arrayTareas, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbTareas.setAdapter(adapter);

        txtId.setText(extras.getString("idNueva"));
        
        
        
	}

}
