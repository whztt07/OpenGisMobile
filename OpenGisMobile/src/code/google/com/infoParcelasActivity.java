package code.google.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class infoParcelasActivity extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoparcelas);
        
        final EditText txtIdparcela = (EditText) findViewById(R.id.txtIdparcela);
        final EditText txtAliasparcela = (EditText) findViewById(R.id.txtAliasparcela);
        final EditText txtProvinciaparcela = (EditText) findViewById(R.id.txtProvinciaparcela);
        final EditText txtPoblacionparcela = (EditText) findViewById(R.id.txtPoblacionparcela);
        final EditText txtPoligonoparcela = (EditText) findViewById(R.id.txtPoligonoparcela);
        final EditText txtNumeroparcela = (EditText) findViewById(R.id.txtNumeroparcela);
        
        
        final Button cmdModificar = (Button) findViewById(R.id.cmdModificarParcela);
        final Button cmdBorrar = (Button) findViewById(R.id.cmdBorrarParcela);
        final Button cmdGuardar = (Button) findViewById(R.id.cmdGuardarParcela);
        
        
        
        Bundle extras = getIntent().getExtras();
        
        txtIdparcela.setText(extras.getString("idParcela"));
        txtAliasparcela.setText(extras.getString("aliasParcela"));
        txtProvinciaparcela.setText(extras.getString("provinciaParcela"));
        txtPoblacionparcela.setText(extras.getString("poblacionParcela"));
        txtPoligonoparcela.setText(extras.getString("poligonoParcela"));
        txtNumeroparcela.setText(extras.getString("numeroParcela"));
        
        
        // Acciones de bot—n
        
        cmdModificar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				cmdModificar.setEnabled(false);
				cmdBorrar.setEnabled(false);
				cmdGuardar.setEnabled(true);
				
				txtAliasparcela.setEnabled(true);
				txtProvinciaparcela.setEnabled(true);
				txtPoblacionparcela.setEnabled(true);
				txtPoligonoparcela.setEnabled(true);
				txtNumeroparcela.setEnabled(true);
				
			}
        	
        	
        	
        	
        });
        
	}

}
