package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class AperoNuevo extends Activity {
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearapero);
        
        Bundle extras = getIntent().getExtras();
        
        EditText txtId = (EditText) findViewById(R.id.txtIDAperoNuevo);
        
        
        txtId.setText(extras.getString("idNueva"));
	}

}
