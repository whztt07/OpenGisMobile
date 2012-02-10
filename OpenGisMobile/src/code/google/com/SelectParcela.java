package code.google.com;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectParcela extends ListActivity {
	   private ArrayList<Local> m_locals = null;
	    private IconListViewAdapter m_adapter;
	    private String dni,selTarea, selApero,selPro,selDosis;
	    private ArrayList objetosCompletos;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        
	        TextView tit = (TextView) findViewById(R.id.tvTitulo);
	        tit.setText(R.string.selectLot);
	        
	        Bundle extra = getIntent().getExtras();
			dni = extra.getString("dni");
			selTarea = extra.getString("idTarea");
			selApero = extra.getString("idApero");
			selPro = extra.getString("idProducto");
			selDosis = extra.getString("dosisProducto");
	        
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
	    	
	    	if((Integer.parseInt(selTarea))==4||Integer.parseInt(selTarea)==5){
	    		ParcelasDatos ParcelaSeleccionada = (ParcelasDatos) objetosCompletos.get(position);

	    		
	    		
	    		String url2 = "https://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC?Provincia=&Municipio=&SRS=EPSG:4326&RC="+
	    	    		 ParcelaSeleccionada.getProvincia()+ParcelaSeleccionada.getPoblacion()+"A"+ParcelaSeleccionada.getPoligono()+ParcelaSeleccionada.getNumero()+"";
	    		
	    		
	    		try{
	    		
	    		W3CSigPac latitudes = new W3CSigPac(url2);
	    		String posX = latitudes.getAuxx();
	    		String posY = latitudes.getAuxy();
	    		
	    		Toast info2 = Toast.makeText(getApplicationContext(),posX + " " + posY , Toast.LENGTH_LONG);
	    		info2.show();
	    		
	    		Intent i = new Intent(SelectParcela.this,VisorDeMapa.class);
	    		i.putExtra("latitud",posX);
	    		i.putExtra("longitud",posY);
	    		startActivity(i);
	    		
	    		
	    		}catch(Exception e2){
	    			
	    			
		    		Toast info2 = Toast.makeText(getApplicationContext(),"El Registro Catastral no es correcto. Contacte con el administrador de la base de datos", Toast.LENGTH_LONG);
		    		info2.show();
	    			
	    			
	    		}
	    		

	    		
	    	}else{
	    		ParcelasDatos ParcelaSeleccionada = (ParcelasDatos) objetosCompletos.get(position);
	    		Toast info = Toast.makeText(getApplicationContext(),
	    		"Tarea:"+selTarea+" Apero:"+selApero+" Parcela:"+ParcelaSeleccionada.getIdparcela(), Toast.LENGTH_LONG);
	    		info.show();
	    		
	    		String url2 = "https://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC?Provincia=&Municipio=&SRS=EPSG:4326&RC="+
	    	    		 ParcelaSeleccionada.getProvincia()+ParcelaSeleccionada.getPoblacion()+"A"+ParcelaSeleccionada.getPoligono()+ParcelaSeleccionada.getNumero()+"";
	    		
	    		
	    		try{
	    		
	    		W3CSigPac latitudes = new W3CSigPac(url2);
	    		String posX = latitudes.getAuxx();
	    		String posY = latitudes.getAuxy();
	    		
	    		
	    		Intent i = new Intent(SelectParcela.this,VisorDeMapa.class);
	    		i.putExtra("latitud",posY);
	    		i.putExtra("longitud",posX);
	    		startActivity(i);
	    		
	    		
	    		}catch(Exception e2){
	    			
	    			
		    		Toast info2 = Toast.makeText(getApplicationContext(),"El Registro Catastral no es correcto. Contacte con el administrador de la base de datos", Toast.LENGTH_LONG);
		    		info2.show();
	    			
	    			
	    		}
	    		
	    		
	    	}
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
    


    public void alertaMensaje(String cadena,String titulo) {
        
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
        }
    
    
}