package code.google.com;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
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

public class ProductosIconListView extends ListActivity {
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
	    	
	    	ProductosDatos productoSeleccionado = (ProductosDatos) objetosCompletos.get(position);
	    	
	    	Intent vInfoProducto = new Intent(ProductosIconListView.this,infoProductosActivity.class);
	    	vInfoProducto.putExtra("idProducto",productoSeleccionado.getIdprod() );
	    	vInfoProducto.putExtra("nombreProducto",productoSeleccionado.getNombre());
	    	vInfoProducto.putExtra("descripcionProducto",productoSeleccionado.getDescripcion());
	    	vInfoProducto.putExtra("dosisProducto",productoSeleccionado.getDosis());
	    	vInfoProducto.putExtra("dniUsuario",productoSeleccionado.getDNI());
	    	startActivity(vInfoProducto);
	    }
	    
	    /*
	     * Inicializacion del mapa
	     */
	    
	    private void inicializarLocales(){
	    	
	    	try {
	    	
	    	///////////DE AQUI//////////////
	    	m_locals = new ArrayList<Local>();
	    		
			String url = "http://79.108.245.167/OpenGisMobile/MisProductosWebService.php?dni="+dni+"";
			
			String data = AccesoWebService.recogerDatosWebService(url);
			
			final Object[] listaProductos = AccesoWebService.convertirDatosJSONProductos(data);
			
			objetosCompletos = new ArrayList();
			
			for(int i=0;i<listaProductos.length;i++){
				
				
				ProductosDatos producto = (ProductosDatos) listaProductos[i];
				
				// En caso de que ese apero del usuario estŽ activo, lo a–adiremos
				
				if(producto.getActivo().equals("0")){

					String productoMostrar = producto.getNombre();
					objetosCompletos.add(producto);
					
					Local loc = new Local();
					loc.setLocalName(producto.getNombre());
					loc.setLocalMedida(producto.getDescripcion());
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
       
	            Log.i("Productos aÃ±adidos ", ""+ m_locals.size());
	            
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
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	Intent vPrincipal = new Intent(ProductosIconListView.this,principalActivity.class);
        	vPrincipal.putExtra("dni",dni);
        	startActivity(vPrincipal);
        	
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    
}