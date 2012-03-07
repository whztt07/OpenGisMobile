package code.google.com;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import code.google.com.tratadoImagenes.RecorridoEspiral;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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

public class VisorDeMapa extends MapActivity implements LocationListener{
	
	private MapController mapController;
	private MapView mapview;
	private Double latitud;
	private Double longitud;
	private String referenciaCatastral;
	private Location puntoViejo;
	private int veces =0;
	
	private int posicionXEmpezar;
	private int posicionYEmpezar;
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapa);
	    
	    final Button botonAjustar = (Button) findViewById(R.id.botonAjustar);
	    final Button botonEmpezar = (Button) findViewById(R.id.botonEmpezarRecorrido);
	    final Button botonFinalizar = (Button) findViewById(R.id.botonFinalizarTarea);

	    botonEmpezar.setEnabled(false);
	    botonFinalizar.setVisibility(View.INVISIBLE); // Ponemos el bot—n en invisible
	    
	    botonAjustar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				botonEmpezar.setEnabled(true);
				
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
							
					        int primeraX = latitudCatastro.intValue() - 147;
							int segundaX = latitudCatastro.intValue() + 147;
							
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
				
				
				alertaMensaje(getString(R.string.ajustarParcela),getString(R.string.step2));
				
				
			}
	    	
	    	
	    	
	    	
	    	
	    });
	    
	    
	    botonEmpezar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				botonAjustar.setEnabled(false);
				botonEmpezar.setEnabled(false);
				botonFinalizar.setVisibility(View.VISIBLE);
				mapview.setEnabled(false);
				
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
					        
					        RecorridoEspiral rc = new RecorridoEspiral(mutableBitmap);
				
					        
					        Bitmap nuevaImagen = rc.recorridoEspiral(posicionXEmpezar,posicionYEmpezar);
					        
					        Toast tt = Toast.makeText(getApplicationContext(),posicionXEmpezar + "-" + posicionYEmpezar,Toast.LENGTH_LONG);
					        tt.show();
					        
					        
					        ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,"",nuevaImagen);
					        mapview.getOverlays().add(imagenParcela);
					       
		  
					        
				        }catch(Exception e2){
				        	
				        	System.out.println(e2);
				        	System.out.println("EmpiezoX en: " + posicionXEmpezar);
				        	System.out.println("EmpiezoY en: " + posicionYEmpezar);
				        	
				        }
					 
				        
					
					
				}
				
				
				if(zoom == 20){
					
					 try{
				    	   
						 	Bundle extras = getIntent().getExtras();
						 
				   			Double latitudCatastro = Double.valueOf(extras.getString("posXCatastro"));
				   			Double longitudCatastro = Double.valueOf(extras.getString("posYCatastro"));
				        
							WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
							Display display = wm.getDefaultDisplay();
							
							
							
					        int primeraX = latitudCatastro.intValue() - 148;
							int segundaX = latitudCatastro.intValue() + 148;
							
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
						       
					        RecorridoEspiral rc = new RecorridoEspiral(mutableBitmap);
					        

						       
					        Bitmap nuevaImagen = rc.recorridoEspiral(posicionXEmpezar,posicionYEmpezar);
					        
					        Toast tt = Toast.makeText(getApplicationContext(),posicionXEmpezar + "-" + posicionYEmpezar,Toast.LENGTH_LONG);
					        tt.show();
						      
					        
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
        mapController.setZoom(20); // CAMBIAR A 19!!!!
        
        mapview.getOverlays();
          
        alertaMensaje(getString(R.string.mensajeAjustarZoom),getString(R.string.step1));
 
	    LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1, this);
	      
 
	}
		
	
	public void onLocationChanged(Location location) {
		
		if(veces==0){
			
			puntoViejo = location;
			veces = 1;
			
		}
		
		updateLocation(location,puntoViejo);
		this.puntoViejo = location;
		
	}
	 


	public void onProviderDisabled(String provider) {
		
	    Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    startActivity(intent);
	    
	}
	 
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
		
	}
	 
	public void onProviderEnabled(String provider) {
		
		
	}

	
	
	
	public void alertaMensaje(String mensaje,String titulo){
		
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(VisorDeMapa.this);
        dialogBuilder.setMessage(mensaje);
        dialogBuilder.setCancelable(true).setTitle(titulo); 
        dialogBuilder.create().show();
		
		
	}
	
    // Declaramos la nueva clase interna para dibujar el OverLay de nuestra posici—n
    
    class MiPosicion extends Overlay { 
    	
		private GeoPoint point;
		private Location pointAnterior;
		private float puntoX;
		private float puntoY;

		/* El constructor recibe el punto donde se dibujar‡ el marker */
		public MiPosicion(GeoPoint point,Location pointAnterior) {
		  super();
		  this.point = point;
		  this.pointAnterior = pointAnterior;
		  System.out.println("El punto anterior es: "+pointAnterior);

		}
    	
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		    super.draw(canvas, mapView, shadow);
		    
		   //se traduce el punto geo localizado a un punto en la pantalla
		   Point scrnPoint = new Point();
		   mapView.getProjection().toPixels(this.point, scrnPoint);
		   
		   GeoPoint puntoViejo = new GeoPoint((int) (pointAnterior.getLatitude() * 1E6), (int) (pointAnterior.getLongitude() * 1E6));
		   
		   	Point scrnPoint2 = new Point();
		   	mapView.getProjection().toPixels(puntoViejo,scrnPoint2);
		   
		   	Paint p = new Paint();
		    p.setColor(Color.RED);
		    p.setStrokeWidth(11);
		    
		    canvas.drawLine(scrnPoint.x,scrnPoint.y,scrnPoint2.x,scrnPoint2.y,p);
				   
		   	


		   return true;
		}
		
		
		
    }

	
    protected void updateLocation(Location location,Location puntoViejo){
    	

		
		MapView mapView = (MapView) findViewById(R.id.mapview);
		MapController mapController = mapView.getController();
		
		GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
		Point scrnPoint = new Point();
		mapView.getProjection().toPixels(point, scrnPoint);
		
		this.posicionXEmpezar = scrnPoint.x;
		this.posicionYEmpezar = scrnPoint.y;
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		MiPosicion linea = new MiPosicion(point,puntoViejo);
		mapOverlays.add(linea);
		

		mapView.invalidate();
		
	}
    
    
   

}
