package code.google.com;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class ItemsOverlay extends Overlay {
	
	private Double latitud;
	private Double longitud;
	
	public ItemsOverlay(Double latitud,Double longitud){
		
		this.latitud = latitud;
		this.longitud = longitud;
		
	}

    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
        super.draw(canvas, mapView, shadow);
        

        GeoPoint point = new GeoPoint((int)latitud.intValue(), (int)longitud.intValue());
        
        Point myScreenCoords = new Point() ;
        mapView.getProjection().toPixels(point, myScreenCoords);
        
		Paint p = new Paint();
		p.setColor(Color.BLUE);

        Bitmap markerImage = BitmapFactory.decodeResource(mapView.getResources(),R.drawable.apero);

        // Draw it, centered around the given coordinates
        canvas.drawBitmap(markerImage,myScreenCoords.x - markerImage.getWidth() / 2,
            myScreenCoords.y - markerImage.getHeight(), p);
			
            return true;
    }
    
    
    
    
    
}


