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
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class VisorDeMapa extends MapActivity {
	
	private LocationManager locationManager;
	private MapController mapController;
	private MapView mapview;

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapa);
	    
	    Bundle extras = getIntent().getExtras();
	    
	    String referenciaCatastral = extras.getString("referenciaCatastral");
	    
		mapview = (MapView) findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapview.setSatellite(true);
		
        
		Double latitud = Double.valueOf(extras.getString("latitud")).doubleValue() * 1E6;
		Double longitud = Double.valueOf(extras.getString("longitud")).doubleValue() * 1E6;
		
        GeoPoint point = new GeoPoint((int) (longitud.intValue()),(int) (latitud.intValue()) );
          
        mapController.animateTo(point);
        mapController.setCenter(point);
        mapController.setZoom(19);
        
        
        String mensaje = getString(R.string.youAreIn) + " " + extras.getString("nomParcela");

        // A�adimos el dibujo del tractor
        
        ItemsOverlay miPosicion = new ItemsOverlay(longitud,latitud,mensaje);
        mapview.getOverlays().add(miPosicion);
        
        // Recogemos la imagen de la parcela
        
       try{
    	   
           
   			Double latitudCatastro = Double.valueOf(extras.getString("posXCatastro"));
   			Double longitudCatastro = Double.valueOf(extras.getString("posYCatastro"));
        
	        int primeraX = latitudCatastro.intValue() - 200;
			int segundaX = latitudCatastro.intValue() + 200;
			
			int primeraY = longitudCatastro.intValue() - 200;
			int segundaY = longitudCatastro.intValue() + 200;
			
			String anchoFoto = "" ;
			
			String urlImagen = "http://ovc.catastro.meh.es/Cartografia/WMS/ServidorWMS.aspx?SERVICE=WMS&SRS=EPSG:23030&REQUEST=GETMAP&bbox="+primeraX+","+primeraY+","+segundaX+","+segundaY+"&width=300&height=300&format=png&transparent=yes&layers=parcela&refcat="+referenciaCatastral+"";
	       		
			
			URL imageUrl = new URL(urlImagen);
	        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
	        conn.connect();
	        Bitmap loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
	        
	        ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,mensaje,loadedImage);
	        mapview.getOverlays().add(imagenParcela);
	        
        }catch(Exception e2){
        	
        	
        }
        
        
 
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
