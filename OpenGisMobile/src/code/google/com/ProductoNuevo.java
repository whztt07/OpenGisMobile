package code.google.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class ProductoNuevo extends Activity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearproducto);
        
        final Bundle extras = getIntent().getExtras();
        
        final Spinner cbTareas = (Spinner) findViewById(R.id.cbTareas);
        final EditText txtId = (EditText) findViewById(R.id.txtIDProductoNuevo);
        final EditText txtNombre = (EditText) findViewById(R.id.txtNombreProductoNuevo);
        final EditText txtDosis = (EditText) findViewById(R.id.txtDosisProductoNuevo);
        final EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcionProductoNuevo);
        
        Button bGuardar = (Button) findViewById(R.id.cmdGuardarProductoNuevo);
        
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.arrayTareas, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbTareas.setAdapter(adapter);

        txtId.setText(extras.getString("idNueva"));
        
        bGuardar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				
				
				String url = "http://"+getString(R.string.direccionServidor)+"/OpenGisMobile/CrearProductoWebService.php?id="+txtId.getText()+"&nom="+txtNombre.getText()+"&dosis="+txtDosis.getText()+"&tarea="+cbTareas.getSelectedItem()+"&desc="+txtDescripcion.getText()+"&dni="+extras.getString("dni")+"";

				url = url.replaceAll(" ","%20");
				
				boolean finalizado = AccesoWebService.InsertarEnWebService(url);
				
				if(finalizado){
					
					Toast tt = Toast.makeText(getApplicationContext(),getString(R.string.addProductOK),Toast.LENGTH_SHORT);
					tt.show();
					
					Intent vMisProductos = new Intent(ProductoNuevo.this,ProductosIconListView.class);
					vMisProductos.putExtra("dni",extras.getString("dni"));
					startActivity(vMisProductos);
					
					
				}else{
					
					// No se ha conseguido
					
				}
				
			}
        	
        	
        	
        	
        });
        
	}

}
