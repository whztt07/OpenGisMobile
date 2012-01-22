package code.google.com;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
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

public class IconListView extends ListActivity {
	   private ArrayList<Local> m_locals = null;
	    private IconListViewAdapter m_adapter;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);        
	        
	        /*
	         * Al crear la clase se inicializa el ListView que muestra los men�s
	         */          
	        
	        m_locals = new ArrayList<Local>();
	        this.m_adapter = new IconListViewAdapter(this, R.layout.iconrow, m_locals);
	        setListAdapter(this.m_adapter);  
	        
	        inicializarLocales();

	    }
	    
	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	        Local local = (Local) l.getItemAtPosition(position);        
	       
	        Toast.makeText(this, local.getLocalName(), 
	          		Toast.LENGTH_LONG).show();      
	    }
	    
	    /*
	     * Inicializacion del mapa
	     */
	    
	    private void inicializarLocales(){
	    	
	    	try {
	            m_locals = new ArrayList<Local>();
	            Local o5 = new Local();
	            o5.setLocalName("Apero 1");
	            o5.setLocalImage(R.drawable.ic_launcher);
	            Local o1 = new Local();
	            o1.setLocalName("Apero 2");
	            o1.setLocalImage(R.drawable.ic_launcher);
	            Local o2 = new Local();
	            o2.setLocalName("Apero 2");
	            o2.setLocalImage(R.drawable.ic_launcher);
	            Local o3 = new Local();
	            o3.setLocalName("Apero 3");
	            o3.setLocalImage(R.drawable.ic_launcher);
	            Local o4 = new Local();
	            o4.setLocalName("Apero 4");
	            o4.setLocalImage(R.drawable.ic_launcher);

	            m_locals.add(o1);
	            m_locals.add(o2);
	            m_locals.add(o3);
	            m_locals.add(o4);
	            m_locals.add(o5);        
	            Log.i("Locales añadidos ", ""+ m_locals.size());
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
    	                        ImageView im = (ImageView) v.findViewById(R.id.icon);
    	                        
    	                        if (im!= null) {
    	                        	im.setImageResource(o.getLocalImage());
    	                        }                        
    	                        if (tt != null) {             
    	                            tt.setText(o.getLocalName());                             
    	                        }    	                    	                        
    	                }
    	                return v;
    	        }
    	}
    	


    
}