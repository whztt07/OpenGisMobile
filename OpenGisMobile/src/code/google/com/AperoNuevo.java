package code.google.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AperoNuevo extends Activity {
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearapero);
        
        final Bundle extras = getIntent().getExtras();
        
        final EditText txtId = (EditText) findViewById(R.id.txtIDAperoNuevo);
        final EditText txtNombre = (EditText) findViewById(R.id.txtNombreAperoNuevo);
        final EditText txtTamanyo = (EditText) findViewById(R.id.txtTamanyoAperoNuevo);
        final EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcionAperoNuevo);
        final Spinner cbTareas = (Spinner) findViewById(R.id.cbTareasApero);
        final Button bGuardar = (Button) findViewById(R.id.cmdGuardarAperoNuevo);
       
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.arrayTareasID, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbTareas.setAdapter(adapter);
        
        txtId.setText(extras.getString("idNueva"));
        
        
        bGuardar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {

				
				String tarea = cbTareas.getSelectedItem().toString();
				
				tarea = tarea.substring(0,1);

				String url = "http://79.108.245.167/OpenGisMobile/CrearAperoWebService.php?id="+txtId.getText()+"&nom="+txtNombre.getText()+"&tam="+txtTamanyo.getText()+"&tarea="+tarea+"&desc="+txtDescripcion.getText()+"&dni="+extras.getString("dni")+"";

				url = url.replaceAll(" ","%20");
				
				boolean finalizado = AccesoWebService.InsertarEnWebService(url);
				
				if(finalizado){
					
					Toast tt = Toast.makeText(getApplicationContext(),getString(R.string.addToolOK),Toast.LENGTH_SHORT);
					tt.show();
					
					Intent vMisAperos = new Intent(AperoNuevo.this,AperosIconListView.class);
					vMisAperos.putExtra("dni",extras.getString("dni"));
					startActivity(vMisAperos);
					
					
				}else{
					
					// No se ha conseguido
					
				}
				
			}
        	
        	
        	
        });
	}

}
