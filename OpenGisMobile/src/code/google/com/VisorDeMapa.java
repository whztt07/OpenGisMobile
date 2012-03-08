package code.google.com;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
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
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
	private double distanciaRecorrida = 0;
	

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
	    
	    
	    botonFinalizar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View arg0) {
				
				
				// Acci—n que se realizar‡ al presionar finalizar tarea 
				
				Bundle extras = getIntent().getExtras();
				
				
				String dni = extras.getString("dni");
	    		String idTarea = extras.getString("idTarea");
	    		String idApero = extras.getString("idApero");
	    		String idProducto = extras.getString("idProducto");
	    		String dosis = extras.getString("dosis");
	    		String parcela = extras.getString("nomParcela");
	    		String finalizada = "1";
	    		Date fecha = new Date();
	    		
	    		Toast.makeText(getApplicationContext(),dni + " " + idTarea + " " + idApero + " " +idProducto+ " " +dosis+ " " +parcela+ " " +fecha,Toast.LENGTH_LONG).show();
	    		
	    		
				
				
			}
	    	
	    	
	    	
	    	
	    });
	    
	    
	    
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
					        
					        Bitmap imagenRedimensionada = getResizedBitmap(nuevaImagen,1550,2500);

					        mapController.setZoom(20);
					        
					        ImagenOverlay imagenParcela = new ImagenOverlay(longitud,latitud,"",imagenRedimensionada);
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
        mapController.setZoom(19); // CAMBIAR A 19!!!!
        
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
		
		double lat2 = location.getLatitude();
		double lng2 = location.getLongitude();
		
		double lat1 = puntoViejo.getLatitude();
		double lng1 = puntoViejo.getLongitude();
		
		double km = calcularDistancia(lat1,lng1,lat2,lng2);
		
		this.distanciaRecorrida  +=km;
		
		DecimalFormat df = new DecimalFormat("0.000"); 
		
		String distanciaKM = getString(R.string.distanciaRecorrida) + " "+df.format(this.distanciaRecorrida) + " Km ";
		
		
		double velocidad = km / 3600;
		
		String veloKmHora = velocidad + "    ";
		
		veloKmHora =  Html.fromHtml("<b>"+getString(R.string.velocidad)+"</b>") +" "+ veloKmHora.substring(0,4) + " Km/h";
		
		
		TextView textoDistancia = (TextView) findViewById(R.id.textoDistancia);
		TextView textoVelocidad = (TextView) findViewById(R.id.textoVelocidad);
		
		textoVelocidad.setText(veloKmHora);
		textoDistancia.setText(distanciaKM);

		
	}
    
    
    /**
     * 
     * Metodo para redimensionar la im‡gen
     * 
     * 
     * @param bm Le pasamos el Bitmp
     * @param newHeight nuevo alto
     * @param newWidth nuevo ancho
     * @return Devolvemos la im‡gen
     */
    
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

    	int width = bm.getWidth();

    	int height = bm.getHeight();

    	float scaleWidth = ((float) newWidth) / width;

    	float scaleHeight = ((float) newHeight) / height;

    	// create a matrix for the manipulation

    	Matrix matrix = new Matrix();

    	// resize the bit map

    	matrix.postScale(scaleWidth, scaleHeight);

    	// recreate the new Bitmap

    	Bitmap resizedBitmap = Bitmap.createBitmap(bm,310,175, width - 310, height - 175, matrix, false);

    	return resizedBitmap;

    	}
    
    /**
     * MŽtodo para calcular la distancia entre dos puntos
     * 
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return Nos devuelve una distancia entre dos puntos en KM
     */
    
    public double calcularDistancia(double lat1, double lng1,double lat2,double lng2)
    {
    	double pi80 = Math.PI / 180;
    	lat1 *= pi80;
    	lng1 *= pi80;
    	lat2 *= pi80;
    	lng2 *= pi80;
     
    	double r = 6372.797; // mean radius of Earth in km
    	double dlat = lat2 - lat1;
    	double dlng = lng2 - lng1;
    	double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlng / 2) * Math.sin(dlng / 2);
    	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    	double km = r * c;
     
    	return km;
    }
    
   

}
