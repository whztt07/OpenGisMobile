package code.google.com;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.content.Context;
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
	    
	    
	    
		mapview = (MapView) findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapview.setSatellite(true);
		
        
		Double latitud = Double.valueOf(extras.getString("latitud")).doubleValue() * 1E6;
		Double longitud = Double.valueOf(extras.getString("longitud")).doubleValue() * 1E6;
		
        GeoPoint point = new GeoPoint((int) (longitud.intValue()),(int) (latitud.intValue()) );
        
        // A–adimos el dibujo del tractor
        
        List<Overlay> mapOverlays = mapview.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.apero);
        ItemsOverlay itemizedoverlay = new ItemsOverlay(drawable, this);
        OverlayItem overlayitem = new OverlayItem(point, "Posicion", "Parcela");
        
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
        
        mapController.animateTo(point);
        mapController.setCenter(point);
        mapController.setZoom(18);
        


 
 
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
