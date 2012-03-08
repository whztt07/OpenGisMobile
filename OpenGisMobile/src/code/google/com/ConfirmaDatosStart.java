package code.google.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmaDatosStart extends Activity {
	private Button confWork,confApero,confProduct,confLot,confirm;
	private EditText tbWork,tbTool,tbToolWidth,tbProduct,tbProductDosis,tbLot;
    private String dni,selTarea, selApero,selPro,selDosis;
    private String selParcela,nombreParcela,provParcela,poliParcela,poblParcela,numParcela;
    private Bundle extra;
	private String nombreTarea;
	private String nombreApero;
	private String tamApero;
	private String nomPro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.confirmdata);
		
		//Recoje los extras
        extra = getIntent().getExtras();
		dni = extra.getString("dni");
		selTarea = extra.getString("idTarea");
		nombreTarea = extra.getString("nombreTarea");
		selApero = extra.getString("idApero");
		tamApero = extra.getString("tamApero");
		nombreApero = extra.getString("nombreApero");

		selParcela = extra.getString("idParcela");
		nombreParcela = extra.getString("nombreParcela");
		provParcela = extra.getString("provParcela");
		poliParcela = extra.getString("poliParcela");
		poblParcela = extra.getString("poblParcela");
		numParcela = extra.getString("numParcela");
		
		
		//Inicializa los Textbox
		tbWork = (EditText) findViewById(R.id.tbWork);
		tbTool = (EditText) findViewById(R.id.tbTools);
		tbToolWidth = (EditText) findViewById(R.id.tbTamTools);
		tbProduct = (EditText) findViewById(R.id.tbProducts);
		tbProductDosis = (EditText) findViewById(R.id.tbProductDosis);
		tbLot = (EditText) findViewById(R.id.tbLot);
		
		//Inicializa los botones
		confWork = (Button) findViewById(R.id.btnConfigWorks);
		confApero = (Button) findViewById(R.id.btnConfigTools);
		confProduct = (Button) findViewById(R.id.btnConfigProducts);
		confLot = (Button) findViewById(R.id.btnConfigLots);
		confirm = (Button) findViewById(R.id.button1);
		
		//Coloca Datos en los textBox
		tbWork.setText(nombreTarea);
		tbTool.setText(nombreApero);
		tbToolWidth.setText(tamApero+"cm");
		tbLot.setText(nombreParcela);
		
		//Asigna los datos de Producto segun la tarea
		if((Integer.parseInt(selTarea))==4||Integer.parseInt(selTarea)==5){
    		//Recoge los extras del producto
    		selPro = extra.getString("idProducto");
    		selDosis = extra.getString("dosisProducto");
    		nomPro = extra.getString("nombreProducto");
    		
    		//Coloca los datos del producto en el textbox
    		tbProduct.setText(nomPro);
    		tbProductDosis.setText(selDosis);
		}else{
    		
    		//Coloca los datos del producto en el textbox
    		tbProduct.setText("Sin producto");
    		tbProductDosis.setText("Sin producto");
    		confProduct.setEnabled(false);
		}
		
		
		
		
		
		/**
		 * Onclick Boton Confirmar
		 */
		
		confirm.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

		    	if((Integer.parseInt(selTarea))==4||Integer.parseInt(selTarea)==5){		    		

		    		
		    		String referenciaCatastral = provParcela+poblParcela+"A"+poliParcela+numParcela;
		    		
		    		String url2 = "https://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC?Provincia=&Municipio=&SRS=EPSG:4326&RC="+referenciaCatastral+"";
		    		
		    		String urlCatastro = "https://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC?Provincia=&Municipio=&SRS=EPSG:23030&RC="+referenciaCatastral+"";
		    		
		    		try{
		    		
		    		W3CSigPac latitudes = new W3CSigPac(url2);
		    		String posX = latitudes.getAuxx();
		    		String posY = latitudes.getAuxy();
		    		
		    		W3CSigPac coordenadasSigPac = new W3CSigPac(urlCatastro);
		    		String posXCatastro = coordenadasSigPac.getAuxx();
		    		String posYCatastro = coordenadasSigPac.getAuxy();
		   
		    		
		    		Intent i = new Intent(ConfirmaDatosStart.this,VisorDeMapa.class);
		    		i.putExtra("latitud",posX);
		    		i.putExtra("longitud",posY);
		    		i.putExtra("posXCatastro",posXCatastro);
		    		i.putExtra("posYCatastro",posYCatastro);
		    		i.putExtra("dni",dni);
		    		i.putExtra("idTarea",selTarea);
		    		i.putExtra("idApero",selApero);
		    		i.putExtra("idProducto",selPro);
		    		i.putExtra("dosis",selDosis);
		    		i.putExtra("selparcela", selParcela);
		    		i.putExtra("referenciaCatastral",referenciaCatastral);
		    		startActivity(i);
		    		
		    		
		    		}catch(Exception e2){
		    			
		    			
			    		Toast info2 = Toast.makeText(getApplicationContext(),getString(R.string.catastroError), Toast.LENGTH_LONG);
			    		info2.show();
		    			
		    			
		    		}
		    		

		    		
		    	}else{
		    		
		    		Toast info = Toast.makeText(getApplicationContext(),
		    		"Tarea:"+selTarea+" Apero:"+selApero+" Parcela:"+selParcela, Toast.LENGTH_LONG);
		    		info.show();
		    		
		    		String url2 = "https://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC?Provincia=&Municipio=&SRS=EPSG:4326&RC="+
		    				provParcela+poblParcela+"A"+poliParcela+numParcela+"";
		    		
		    		
		    		try{
		    		
		    		W3CSigPac latitudes = new W3CSigPac(url2);
		    		String posX = latitudes.getAuxx();
		    		String posY = latitudes.getAuxy();
		    		
		    		
		    		selPro = "";
		    		selDosis = "";
		    		
		    		Intent i = new Intent(ConfirmaDatosStart.this,VisorDeMapa.class);
		    		i.putExtra("latitud",posX);
		    		i.putExtra("longitud",posY);
		    		i.putExtra("dni",dni);
		    		i.putExtra("idTarea",selTarea);
		    		i.putExtra("idApero",selApero);
		    		i.putExtra("idProducto",selPro);
		    		i.putExtra("dosis",selDosis);
		    		i.putExtra("selparcela",selParcela);
		    		startActivity(i);
		    		
		    		
		    		}catch(Exception e2){
		    			
		    			
			    		Toast info2 = Toast.makeText(getApplicationContext(),getString(R.string.catastroError), Toast.LENGTH_LONG);
			    		info2.show();
		    			
		    			
		    		}
		    		

		    		
		    		
		    	}
			}
		}); 
	
		/**
		 * Onclick Resto de botones
		 */
		
		//Boton Volver a Trabajos
		confWork.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent vWork = new Intent(ConfirmaDatosStart.this,SelectTrabajo.class);
				vWork.putExtra("dni",extra.getString("dni"));
				startActivity(vWork);
			}
		});
		
		//Boton Volver a Aperos
		confApero.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent vWork = new Intent(ConfirmaDatosStart.this,SelectApero.class);
				vWork.putExtra("dni",extra.getString("dni"));
				vWork.putExtra("idTarea",extra.getString("idTarea"));
				vWork.putExtra("nombreTarea",extra.getString("nombreTarea"));
				
				startActivity(vWork);
			}
		});
		
		//Boton Volver a Productos
		confProduct.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent vWork = new Intent(ConfirmaDatosStart.this,SelectProducto.class);
				vWork.putExtra("dni",extra.getString("dni"));
				vWork.putExtra("idTarea",extra.getString("idTarea"));
				vWork.putExtra("nombreTarea",extra.getString("nombreTarea"));
				vWork.putExtra("idApero", extra.getString("idApero"));
				vWork.putExtra("nombreApero", extra.getString("nombreApero"));
				vWork.putExtra("tamApero", extra.getString("tamApero"));
				startActivity(vWork);
			}
		});
		
		//Boton Volver a Parcelas
		confLot.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if((Integer.parseInt(selTarea))==4||Integer.parseInt(selTarea)==5){
					Intent vWork = new Intent(ConfirmaDatosStart.this,SelectParcela.class);
					
					vWork.putExtra("idTarea",extra.getString("idTarea"));
					vWork.putExtra("nombreTarea",extra.getString("nombreTarea"));
					vWork.putExtra("dni", extra.getString("dni"));
					vWork.putExtra("idApero", extra.getString("idApero"));
					vWork.putExtra("tamApero", extra.getString("tamApero"));
					vWork.putExtra("nombreApero", extra.getString("nombreApero"));
					vWork.putExtra("idProducto", extra.getString("idProducto"));
					vWork.putExtra("nombreProducto", extra.getString("nombreProducto"));
					vWork.putExtra("dosisProducto", extra.getString("dosisProducto"));
					startActivity(vWork);
				}else{
					Intent vWork = new Intent(ConfirmaDatosStart.this,SelectParcela.class);
					vWork.putExtra("idTarea",extra.getString("idTarea"));
					vWork.putExtra("nombreTarea",extra.getString("nombreTarea"));
					vWork.putExtra("dni", extra.getString("dni"));
					vWork.putExtra("idApero", extra.getString("idApero"));
					vWork.putExtra("tamApero", extra.getString("tamApero"));
					vWork.putExtra("nombreApero", extra.getString("nombreApero"));
					startActivity(vWork);
				}

			}
		});
		
	}
}
