package code.google.com;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class VisorDeMapa extends MapActivity {
	
	private LocationManager locationManager;
	private MapController mapController;
	private MapView mapview;
	private Double latitud;
	private Double longitud;
	private String referenciaCatastral;

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapa);
	    
	    Button botonBloquear = (Button) findViewById(R.id.botonBloquear);
	    Button botonDesbloquear = (Button) findViewById(R.id.botonDesbloquear);
	    Button botonAjustar = (Button) findViewById(R.id.botonAjustar);
	   
	    
	    botonBloquear.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				mapview.setEnabled(false);
				Toast tt = Toast.makeText(getApplicationContext(),"Mapa Bloqueado",Toast.LENGTH_LONG);
				tt.show();
			}
	    	
	    	
	    	
	    	
	    	
	    });
	    
	    botonDesbloquear.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				mapview.setEnabled(true);
				Toast tt = Toast.makeText(getApplicationContext(),"Mapa Desbloqueado",Toast.LENGTH_LONG);
				tt.show();
				
			}
	    	
	    	
	    	
	    	
	    	
	    });
	    
	    
	    botonAjustar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				mapview.getOverlays().clear();
				
				int zoom = mapview.getZoomLevel();

				if(zoom == 19){
					
					 try{
				    	    
						 	Bundle extras = getIntent().getExtras();
						 
				   			Double latitudCatastro = Double.valueOf(extras.getString("posXCatastro"));
				   			Double longitudCatastro = Double.valueOf(extras.getString("posYCatastro"));
				        
							WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
							Display display = wm.getDefaultDisplay();
							
					        int primeraX = latitudCatastro.intValue() - 296;
							int segundaX = latitudCatastro.intValue() + 296;
							
							int primeraY = longitudCatastro.intValue() - 179;
							int segundaY = longitudCatastro.intValue() + 177;
							
							int ancho = display.getWidth();
							int alto = display.getHeight();
							
							
							String urlImagen = "http://ovc.catastro.meh.es/Cartografia/WMS/ServidorWMS.aspx?SERVICE=WMS&SRS=EPSG:23030&REQUEST=GETMAP&bbox="+primeraX+","+primeraY+","+segundaX+","+segundaY+"&width="+ancho+"&height="+alto+"&format=png&transparent=yes&layers=parcela&refcat="+referenciaCatastral+"";
					       		
							
							URL imageUrl = new URL(urlImagen);
					        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
					        conn.connect();
					        Bitmap loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
					        
					        ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,"",loadedImage);
					        mapview.getOverlays().add(imagenParcela);
					        
				        }catch(Exception e2){
				        	
				        	
				        }
				        
					
					
				}
				
				
				
				
				if(zoom == 20){
					
					 try{
				    	   
						 	Bundle extras = getIntent().getExtras();
						 
				   			Double latitudCatastro = Double.valueOf(extras.getString("posXCatastro"));
				   			Double longitudCatastro = Double.valueOf(extras.getString("posYCatastro"));
				        
							WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
							Display display = wm.getDefaultDisplay();
							
					        int primeraX = latitudCatastro.intValue() - 145;
							int segundaX = latitudCatastro.intValue() + 145;
							
							int primeraY = longitudCatastro.intValue() - 79;
							int segundaY = longitudCatastro.intValue() + 100;
							
							int ancho = display.getWidth();
							int alto = display.getHeight();
							
							
							String urlImagen = "http://ovc.catastro.meh.es/Cartografia/WMS/ServidorWMS.aspx?SERVICE=WMS&SRS=EPSG:23030&REQUEST=GETMAP&bbox="+primeraX+","+primeraY+","+segundaX+","+segundaY+"&width="+ancho+"&height="+alto+"&format=png&transparent=yes&layers=parcela&refcat="+referenciaCatastral+"";
					       		
							
							URL imageUrl = new URL(urlImagen);
					        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
					        conn.connect();
					        Bitmap loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
					        
					        ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,"",loadedImage);
					        mapview.getOverlays().add(imagenParcela);
					        
				        }catch(Exception e2){
				        	
				        	
				        }
				        
					
					
				}
				
				
			}
	    	
	    	
	    	
	    	
	    	
	    });
	    
	    
	    
	    
	    Bundle extras = getIntent().getExtras();
	    
	    referenciaCatastral = extras.getString("referenciaCatastral");
	    
		mapview = (MapView) findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(false);
		mapview.setEnabled(true);
		mapController = mapview.getController();
		mapview.setSatellite(true);
		
        
		latitud = Double.valueOf(extras.getString("latitud")).doubleValue() * 1E6;
		longitud = Double.valueOf(extras.getString("longitud")).doubleValue() * 1E6;
		
        GeoPoint point = new GeoPoint((int) (longitud.intValue()),(int) (latitud.intValue()) );
          
        mapController.animateTo(point);
        mapController.setCenter(point);
        mapController.setZoom(19);
        
        
        String mensaje = getString(R.string.youAreIn) + " " + extras.getString("nomParcela");

        // A–adimos el dibujo del tractor
        
        ItemsOverlay miPosicion = new ItemsOverlay(longitud,latitud,mensaje);
        mapview.getOverlays().add(miPosicion);
        
 
               
 
	}
		
		
	
	
	
	public class GeoUpdateHandler implements LocationListener {

		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
			mapController.animateTo(point); //	mapController.setCenter(point);
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	

}
