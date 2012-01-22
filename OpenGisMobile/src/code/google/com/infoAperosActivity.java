package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class infoAperosActivity extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoaperos);
        
        Bundle extras = getIntent().getExtras();
        
        EditText txtID = (EditText) findViewById(R.id.txtIDApero);
        EditText txtNombreApero = (EditText) findViewById(R.id.txtNombreApero);
        EditText txtTamanyo = (EditText) findViewById(R.id.txtTamanyoApero);
        EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcionApero);
        
        txtID.setText(extras.getString("idApero"));
        txtNombreApero.setText(extras.getString("nombreApero"));
        txtTamanyo.setText(extras.getString("tama–oApero"));
        txtDescripcion.setText(extras.getString("descripcionApero"));
	}
	
}
