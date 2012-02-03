package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AperoNuevo extends Activity {
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearapero);
        
        Bundle extras = getIntent().getExtras();
        
        final EditText txtId = (EditText) findViewById(R.id.txtIDAperoNuevo);
        final EditText txtNombre = (EditText) findViewById(R.id.txtNombreAperoNuevo);
        final EditText txtTamanyo = (EditText) findViewById(R.id.txtTamanyoAperoNuevo);
        final EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcionAperoNuevo);
        final Spinner cbTareas = (Spinner) findViewById(R.id.cbTareasApero);
        final Button bGuardar = (Button) findViewById(R.id.cmdGuardarAperoNuevo);
        
        txtId.setText(extras.getString("idNueva"));
	}

}
