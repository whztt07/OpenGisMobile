package code.google.com;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
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

public class SelectApero extends ListActivity {
	   private ArrayList<Local> m_locals = null;
	    private IconListViewAdapter m_adapter;
	    private String dni;
	    private String selTarea;
	    private ArrayList objetosCompletos;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        TextView tit = (TextView) findViewById(R.id.tvTitulo);
	        tit.setText(R.string.selectTool);
	        
	        Bundle extra = getIntent().getExtras();
			dni = extra.getString("dni");
			selTarea = extra.getString("idTarea");
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
	    	if((Integer.parseInt(selTarea))==4||Integer.parseInt(selTarea)==5){
		    	AperosDatos AperoSeleccionado = (AperosDatos) objetosCompletos.get(position);
		    	Bundle extras = getIntent().getExtras();
		    	Intent selAp = new Intent(SelectApero.this,SelectProducto.class);
		    	selAp.putExtra("idTarea",extras.getString("idTarea"));
		    	selAp.putExtra("nombreTarea",extras.getString("nombreTarea"));
		    	selAp.putExtra("dni", extras.getString("dni"));
		    	selAp.putExtra("idApero", AperoSeleccionado.getIdApero());
		    	selAp.putExtra("nombreApero", AperoSeleccionado.getNombreApero());
		    	startActivity(selAp);
	    	}else{
		    	AperosDatos AperoSeleccionado = (AperosDatos) objetosCompletos.get(position);
		    	Bundle extras = getIntent().getExtras();
		    	
		    	Intent selAp = new Intent(SelectApero.this,SelectParcela.class);
		    	selAp.putExtra("idTarea",extras.getString("idTarea"));
		    	selAp.putExtra("nombreTarea",extras.getString("nombreTarea"));
		    	selAp.putExtra("dni", extras.getString("dni"));
		    	selAp.putExtra("idApero", AperoSeleccionado.getIdApero());
		    	selAp.putExtra("nombreApero", AperoSeleccionado.getNombreApero());
		    	startActivity(selAp);
	    	}
	    }
	    
	    /*
	     * Inicializacion del mapa
	     */
	    
	    private void inicializarLocales(){
	    	
	    	try {
	    	
	    	///////////DE AQUI//////////////
	    	m_locals = new ArrayList<Local>();
	    		
			String url = "http://79.108.245.167/OpenGisMobile/MisAperosWebService.php?dni="+dni+"";
			
			String data = AccesoWebService.recogerDatosWebService(url);
			
			final Object[] listaAperos = AccesoWebService.convertirDatosJSONAperos(data);
			
			objetosCompletos = new ArrayList();
			
			for(int i=0;i<listaAperos.length;i++){
				
				
				AperosDatos apero = (AperosDatos) listaAperos[i];
				
				// En caso de que ese apero del usuario estŽ activo
				//y sea para la tarea seleccionada, lo añadiremos
				
				if(apero.getEstadoApero().equals("1")&&apero.getIdTarea().equals(selTarea)){

					String aperoMostrar = apero.getNombreApero();
					objetosCompletos.add(apero);
					
					Local loc = new Local();
					loc.setLocalName(apero.getNombreApero());
					loc.setLocalMedida(apero.getTamanyoApero()+"cm");
					loc.setLocalImage(R.drawable.ic_launcher);
					
					m_locals.add(loc);
				}
				
			}

       
	            Log.i("Aperos aÃ±adidos ", ""+ m_locals.size());
	            
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
    	


    
}