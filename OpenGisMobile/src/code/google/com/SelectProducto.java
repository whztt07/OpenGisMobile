package code.google.com;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectProducto extends ListActivity {
	   private ArrayList<Local> m_locals = null;
	    private IconListViewAdapter m_adapter;
	    private String dni;
	    private ArrayList objetosCompletos;
	    private String selTarea;
	    private String selApero,selnTarea;
    	private String dosis = "";
    	private int posicionObjeto;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        
	        TextView tit = (TextView) findViewById(R.id.tvTitulo);
	        tit.setText(R.string.selectProduct);
			
	        Bundle extra = getIntent().getExtras();
			dni = extra.getString("dni");
			selTarea = extra.getString("idTarea");
			selnTarea = extra.getString("nombreTarea");
			selApero = extra.getString("idApero");
			
	        
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
	    	

	    	posicionObjeto = position;
	    	
	    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	    	alert.setTitle(R.string.dosis);
	    	alert.setMessage(R.string.insertDose);
	    	final EditText input = new EditText(this);
	    	input.setInputType(InputType.TYPE_CLASS_NUMBER);
	    	alert.setView(input);

	    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    		
		    	ProductosDatos ProductoSeleccionado = (ProductosDatos) objetosCompletos.get(posicionObjeto);
		    	Bundle extras = getIntent().getExtras();
	    		
		    	dosis = input.getText().toString();
	    	  
		    	if(dosis.equals("")){
		    		
		    		
		    	}else{
		    	
			    	Intent selPro = new Intent(SelectProducto.this,SelectParcela.class);
			    	selPro.putExtra("idTarea",extras.getString("idTarea"));
			    	selPro.putExtra("nombreTarea",extras.getString("nombreTarea"));
			    	selPro.putExtra("dni", extras.getString("dni"));
			    	selPro.putExtra("idApero", extras.getString("idApero"));
			    	selPro.putExtra("tamApero", extras.getString("tamApero"));
			    	selPro.putExtra("nombreApero", extras.getString("nombreApero"));
			    	selPro.putExtra("idProducto", ProductoSeleccionado.getIdprod());
			    	selPro.putExtra("nombreProducto", ProductoSeleccionado.getNombre());
			    	selPro.putExtra("dosisProducto",dosis);
			    	startActivity(selPro);
	    	  
	    	  
		    	}
			    	
	    	  }
	    	});

	    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    	  public void onClick(DialogInterface dialog, int whichButton) {
	    	    // Canceled.
	    	  }
	    	});

	    	alert.show();
	    	

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
				
				if(producto.getActivo().equals("0")&&producto.getNomtarea().trim().equals(selnTarea.trim())){
				

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
    	



    
}