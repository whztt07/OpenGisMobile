package code.google.com;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ParcelasIconListView extends ListActivity {
	   private ArrayList<Local> m_locals = null;
	    private IconListViewAdapter m_adapter;
	    private String dni;
	    private ArrayList objetosCompletos;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        Bundle extra = getIntent().getExtras();
			dni = extra.getString("dni");
	        
	        /*
	         * Al crear la clase se inicializa el ListView que muestra los aperos
	         */          
	        
	        m_locals = new ArrayList<Local>();
	        this.m_adapter = new IconListViewAdapter(this, R.layout.iconrow, m_locals);
	        setListAdapter(this.m_adapter);
	        
	        inicializarLocales();

	    }
	    
	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	        /*Local local = (Local) l.getItemAtPosition(position);        
	       
	        Toast.makeText(this, local.getLocalName(), 
	          		Toast.LENGTH_LONG).show();     */
	    	
	    	
	    	final ParcelasDatos parcelaSeleccionada = (ParcelasDatos) objetosCompletos.get(position);

	    	
	        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ParcelasIconListView.this);
	        dialogBuilder.setMessage(getString(R.string.LotsConfig));
	        dialogBuilder.setCancelable(true).setTitle(getString(R.string.Lots));
	        dialogBuilder.setPositiveButton(getString(R.string.editLot),new DialogInterface.OnClickListener() { 
	            public void onClick(DialogInterface dialog, int arg1) {
	            	
	            	
	            	if(parcelaSeleccionada.getDNIPropietario().toLowerCase().equals(dni.toLowerCase())){
	    	    		
	    	    		
	    	    		
	    	    		Intent vEdicionParcela = new Intent(ParcelasIconListView.this,infoParcelasActivity.class);
	    	    		vEdicionParcela.putExtra("idParcela",parcelaSeleccionada.getIdparcela());
	    	    		vEdicionParcela.putExtra("aliasParcela",parcelaSeleccionada.getAlias());
	    	    		vEdicionParcela.putExtra("provinciaParcela",parcelaSeleccionada.getProvincia());
	    	    		vEdicionParcela.putExtra("poblacionParcela",parcelaSeleccionada.getPoblacion());
	    	    		vEdicionParcela.putExtra("poligonoParcela",parcelaSeleccionada.getPoligono());
	    	    		vEdicionParcela.putExtra("numeroParcela",parcelaSeleccionada.getNumero());
	    	    		vEdicionParcela.putExtra("dni",dni);
	    	    		startActivity(vEdicionParcela);
	    	    		
	    	    		
	    	    		
	    	    		
	    	    	}else{
	    	    		
	    	    		
	    	    		
	    	    		alertaMensaje(getString(R.string.notEditableLot),getString(R.string.msgAviso));
	    	    		
	    	    	}
	            	
	            	
	            	
	            } 
	        }); 
	        
	        dialogBuilder.setNegativeButton(getString(R.string.deleteLot),new DialogInterface.OnClickListener() { 
	            public void onClick(DialogInterface dialog, int arg1) { 
	                
	            	//Aqu’ preguntamos si desea eliminar la parcela
	            	
					AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ParcelasIconListView.this);
			        dialogBuilder.setMessage(getString(R.string.msgDeleteLot));
			        dialogBuilder.setCancelable(true).setTitle(getString(R.string.Lots));
			        dialogBuilder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() { 
			            public void onClick(DialogInterface dialog, int arg1) {
			            	
			            	//Eliminamos la parcela de la tabla parcela_usuario
			            	
			            	
			            	String url = "http://79.108.245.167/OpenGisMobile/BorrarParcelaWebService.php?dni="+dni+"&idparcela="+parcelaSeleccionada.getIdparcela()+"";
			            
			            	
			            	boolean finalizado = AccesoWebService.InsertarEnWebService(url);
			            	
			            	
			            	if(finalizado){
			            		
			            		
			            		Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.deletedLot), Toast.LENGTH_SHORT);
			            		toast.show();
			            		
			            		Intent vMisParcelas = new Intent(ParcelasIconListView.this,ParcelasIconListView.class);
			            		vMisParcelas.putExtra("dni",dni);
			            		startActivity(vMisParcelas);
			            		
			            	}else{
			            		
			            		
			            		// No se ha podido insertar
			            		
			            	}
			            	
			            } 
			        }); 
			        
			        dialogBuilder.setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener() { 
			            public void onClick(DialogInterface dialog, int arg1) { 
		
			            	// No hacemos nada
			            	
			            } 
			        }); 
			        dialogBuilder.create().show();
	            	
	            	
	            	
	            } 
	        }); 
	        dialogBuilder.create().show();
	    	
	    	
	    	
	    	

	    }
	    
	    
	    /*
	     * Inicializacion del mapa
	     */
	    
	    private void inicializarLocales(){
	    	
	    	try {
	    	
	    	///////////DE AQUI//////////////
	    	m_locals = new ArrayList<Local>();
	    		
			String url = "http://79.108.245.167/OpenGisMobile/MisParcelasWebService.php?dni="+dni+"";
			
			String data = AccesoWebService.recogerDatosWebService(url);
			
			final Object[] listaParcelas = AccesoWebService.convertirDatosJSONParcelas(data);
			
			objetosCompletos = new ArrayList();
			
			for(int i=0;i<listaParcelas.length;i++){
				
				
				ParcelasDatos parcela = (ParcelasDatos) listaParcelas[i];
				
				// En caso de que ese apero del usuario estŽ activo, lo a–adiremos
				
				if(parcela.getActivo().equals("1")){

					String productoMostrar = parcela.getAlias();
					objetosCompletos.add(parcela);
					
					Local loc = new Local();
					loc.setLocalName(parcela.getAlias());
					loc.setLocalMedida("ID de parcela: " + parcela.getIdparcela());
					loc.setLocalImage(R.drawable.ic_launcher);
					
					m_locals.add(loc);
				}
				
			}
	    	
	    	//////////A AQUI///////////////
	    	
	            /*m_locals = new ArrayList<Local>();

	            Local o1 = new Local();
	            o1.setLocalName("Apero2");
	            o1.setLocalMedida("5,7m");
	            o1.setLocalImage(R.drawable.ic_launcher);
	           

	            m_locals.add(o1);*/
       
	            Log.i("Parcelas aÃ±adidos ", ""+ m_locals.size());
	            
	          } catch (Exception e) {
	            Log.e("BACKGROUND_PROC", e.getMessage());
	          }
	          
	    	
	    	
	          if(m_locals != null && m_locals.size() > 0){
	              for(int i=0;i<m_locals.size();i++)
	              m_adapter.add(m_locals.get(i));
	          }

	          m_adapter.notifyDataSetChanged();       	
	    	
	    }  
    

    public class IconListViewAdapter extends ArrayAdapter<Local> {

    	        private ArrayList<Local> items;

    	        public IconListViewAdapter(Context context, int textViewResourceId, ArrayList<Local> items) {
    	                super(context, textViewResourceId, items);
    	                this.items = items;
    	        }
    	        @Override
    	        public View getView(int position, View convertView, ViewGroup parent) {
    	                View v = convertView;
    	                if (v == null) {
    	                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	                    v = vi.inflate(R.layout.iconrow, null);
    	                }
    	                Local o = items.get(position);
    	                if (o != null) {
    	                	
    	                	//poblamos la lista de elementos
    	                	
    	                        TextView tt = (TextView) v.findViewById(R.id.row_toptext);
    	                        TextView tta = (TextView) v.findViewById(R.id.row_bottomtext);
    	                        ImageView im = (ImageView) v.findViewById(R.id.icon);
    
    	                        
    	                        if (im!= null) {
    	                        	im.setImageResource(o.getLocalImage());
    	                        }
    	                        if (tta!= null) {
    	                        	tta.setText(o.getLocalMedida());
    	                        }    
    	                        if (tt != null) {             
    	                            tt.setText(o.getLocalName());                             
    	                        }    	                    	                        
    	                }
    	                return v;
    	        }
    	}
    
    
    @Override
    public void onBackPressed() {
    
    	
    	Intent vPrincipal = new Intent(ParcelasIconListView.this,principalActivity.class);
    	vPrincipal.putExtra("dni",dni);
    	startActivity(vPrincipal);
    	
    	
    return;
    }
    	

    public void alertaMensaje(String cadena,String titulo) {
        
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
        }
    
    
}