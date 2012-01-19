package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class misDatosActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.misdatos);
        
        EditText txtDNI = (EditText) findViewById(R.id.txtDNI); 
        
        Bundle extras = getIntent().getExtras();
        
        
        txtDNI.setText(extras.getString("dni"));  
        
        
	}

}
