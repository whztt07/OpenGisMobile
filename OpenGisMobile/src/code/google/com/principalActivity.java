package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class principalActivity extends Activity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        Bundle extras = getIntent().getExtras();
        
        TextView txtDNI = (TextView) findViewById(R.id.tvIdData);
        TextView txtNombre = (TextView) findViewById(R.id.tvNameData);
        TextView txtApellidos = (TextView) findViewById(R.id.tvSurnameData);
        
        txtDNI.setText(extras.getString("dni"));
        txtNombre.setText(extras.getString("nombre"));
        txtApellidos.setText(extras.getString("apellidos"));
        
        
	}

}
