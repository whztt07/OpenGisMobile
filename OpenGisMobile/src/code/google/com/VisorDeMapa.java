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
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
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
	    
	    final Button botonBloquear = (Button) findViewById(R.id.botonBloquear);
	    final Button botonAjustar = (Button) findViewById(R.id.botonAjustar);
	    final Button botonEmpezar = (Button) findViewById(R.id.botonEmpezarRecorrido);
	    
	    botonBloquear.setText(getString(R.string.lock));
	    botonEmpezar.setEnabled(false);
	    
	    botonBloquear.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				if(botonBloquear.getText().equals(getString(R.string.lock))){
				
					mapview.setEnabled(false);
					botonEmpezar.setEnabled(true);
					botonBloquear.setText(getString(R.string.unlock));
					Toast tt = Toast.makeText(getApplicationContext(),"Mapa Bloqueado",Toast.LENGTH_LONG);
					tt.show();
					
				}else{
					
					
					mapview.setEnabled(true);
					botonBloquear.setText(getString(R.string.lock));
					Toast tt = Toast.makeText(getApplicationContext(),"Mapa Desbloqueado",Toast.LENGTH_LONG);
					tt.show();
					
				}
				
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
				        	
				        	System.out.println(e2);
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
				
				
				alertaMensaje("Ajuste el perimetro a su parcela. Bloquee el mapa y presione Empezar",getString(R.string.step2));
				
				
			}
	    	
	    	
	    	
	    	
	    	
	    });
	    
	    
	    botonEmpezar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				botonBloquear.setEnabled(false);
				botonAjustar.setEnabled(false);
				
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
					        
					        Bitmap mutableBitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
					       
					        Bitmap nuevaImagen = recorridoEspiral(mutableBitmap);
					        
					        ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,"",nuevaImagen);
					        mapview.getOverlays().add(imagenParcela);
					        
				        }catch(Exception e2){
				        	
				        	System.out.println(e2);
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
					        
					        Bitmap mutableBitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
						       
					        Bitmap nuevaImagen = recorridoEspiral(mutableBitmap);
					        
					       ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,"",nuevaImagen);
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
        
        mapview.getOverlays();
        
        

        
        alertaMensaje(getString(R.string.mensajeAjustarZoom),getString(R.string.step1));
 
               
 
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
	
	public void alertaMensaje(String mensaje,String titulo){
		
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(VisorDeMapa.this);
        dialogBuilder.setMessage(mensaje);
        dialogBuilder.setCancelable(true).setTitle(titulo); 
        dialogBuilder.create().show();
		
		
	}
	
	
	public Bitmap recorridoEspiral(Bitmap imagenRecogida){
		
		Canvas canvas = new Canvas(imagenRecogida);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		 for(int i=0;i<imagenRecogida.getWidth();i++){
          	for(int j=0;j<imagenRecogida.getHeight();j++){
          		
          		if(imagenRecogida.getPixel(i,j) == Color.RED || imagenRecogida.getPixel(i,j) == Color.BLUE){
          			canvas.drawPoint(i,j,p);
          			
          		}
          		
          	}
          	
         }
		

		 
		return imagenRecogida;
		
	}
	

}
