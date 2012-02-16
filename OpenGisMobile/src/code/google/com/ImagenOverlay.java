package code.google.com;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Point;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;


public class ImagenOverlay extends Overlay {
	
	private Double latitud;
	private Double longitud;
	private Context contexto;
	private String mensaje;
	private Bitmap imagen;
	
	public ImagenOverlay(Double latitud,Double longitud, String mensaje,Bitmap imagen){
		
		this.latitud = latitud;
		this.longitud = longitud;
		this.mensaje = mensaje;
		this.imagen = imagen;
		
	}

    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
        super.draw(canvas, mapView, shadow);
       
        
        Point myScreenCoords = new Point() ;
        Projection proyeccion = mapView.getProjection();
        GeoPoint point = new GeoPoint((int)latitud.intValue(), (int)longitud.intValue());
        
        Point centro = new Point();
        proyeccion.toPixels(point, centro);
        
		Paint p = new Paint();
		p.setColor(Color.BLUE);

        canvas.drawCircle(centro.x, centro.y, 5, p);
        
        Bitmap markerImage = this.imagen;
        
        markerImage.getPixel(10,10); // PARA RECOGER EL PIXEL!

        canvas.drawBitmap(markerImage,0,0, p);
			
            return true;
    }
    
    @Override
    public boolean onTap(GeoPoint p,MapView mapView){

    	    Context contexto = mapView.getContext();
    	    String msg = this.mensaje;
    	 
    	    Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
    	    toast.show();

	    return true;
    
    }
    
    
    
}

