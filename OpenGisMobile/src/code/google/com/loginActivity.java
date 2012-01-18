package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        
        Button cmdBorrar = (Button) findViewById(R.id.cmdBorrar);
        Button cmdEnviar = (Button) findViewById(R.id.cmdEnviar);    
        final EditText txtUser = (EditText) findViewById(R.id.txtUser);
        final EditText txtPass = (EditText) findViewById(R.id.txtPass);

        cmdBorrar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				txtUser.setText("");
				txtPass.setText("");
				
				
				
			}
		});
        
        
        
    }
}