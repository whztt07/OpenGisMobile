package code.google.com;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;

public class ConfigTabActivity extends TabActivity{
	private TabHost mTabHost;
	private String dni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configtabhost);
		final Bundle extras = getIntent().getExtras();
		this.dni = extras.getString("dni");
		
		//Crea el TabHost
		mTabHost = getTabHost();
		//Crea el TabSpec
		TabHost.TabSpec spec;
		//Crea el intent
		Intent intent;
		
		/** 
		 * En esta parte del OnCreate definimos las Pestañas y sus respectivos intents
		 */
		
		//Define la tab abierta por defecto
		mTabHost.setCurrentTab(0);
		
		//Tab Start
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,principalActivity.class);
			intent.putExtra("dni",dni);	
				//Define la tab
			spec=mTabHost.newTabSpec("start").setIndicator(getString(R.string.start) ,getResources().getDrawable(R.drawable.home)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
		
		//Tab Aperos
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,AperosIconListView.class);
			intent.putExtra("dni",dni);	
				//Define la tab
			spec=mTabHost.newTabSpec("aperos").setIndicator(getString(R.string.Tools) ,getResources().getDrawable(R.drawable.apero)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
			
		//Tab Productos
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,ProductosIconListView.class);
			intent.putExtra("dni",dni);
				//Define la tab
			spec=mTabHost.newTabSpec("productos").setIndicator(getString(R.string.Products) ,getResources().getDrawable(R.drawable.producto)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
			
		//Tab Parcelas
			//Marca donde debe dirigirse la tab
			intent = new Intent(this,ParcelasIconListView.class);
			intent.putExtra("dni",dni);
				//Define la tab
			spec=mTabHost.newTabSpec("parcelas").setIndicator(getString(R.string.Lots) ,getResources().getDrawable(R.drawable.lots)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
			
		//Tab Datos User
				//Marca donde debe dirigirse la tab
			intent = new Intent(this,misDatosActivity.class);
			intent.putExtra("dni",dni);	
				//Define la tab
			spec=mTabHost.newTabSpec("user").setIndicator(getString(R.string.user) ,getResources().getDrawable(R.drawable.usuario)).setContent(intent);
				//Añade la tab
			mTabHost.addTab(spec);
	}
	

    }
