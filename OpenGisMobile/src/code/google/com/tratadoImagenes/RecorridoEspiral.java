package code.google.com.tratadoImagenes;

import com.google.android.maps.Projection;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RecorridoEspiral {
	
	private Bitmap imagen;
	private boolean empezar = false;
	
	public RecorridoEspiral(Bitmap imagen){
		
		this.imagen = imagen;
		
	}

	
	public Bitmap pintarPerimetro(){
		
		Canvas canvas = new Canvas(this.imagen);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		 for(int i=0;i<imagen.getWidth();i++){
          	for(int j=0;j<imagen.getHeight();j++){
          		
          		if(imagen.getPixel(i,j) == Color.RED || imagen.getPixel(i,j) == Color.BLUE){
          			canvas.drawPoint(i,j,p);
          			
          		}
          		
          	}
          	
         }
		 
		
		return imagen;
		
	}
	
	
	public Bitmap recorridoEspiral(int posicionX,int posicionY){
		
		Bitmap imagenTratar = pintarPerimetro();
		
		
		Canvas canvas = new Canvas(imagenTratar);
		
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		posicionY = posicionY - 50; // Le quitamos el alto del espacio de arriba
		
		System.out.println("Y: "+posicionY);
		
		System.out.println("X: "+posicionX);
		
		canvas.drawCircle(240,240,4,p);
		
		Bitmap imagenTratada = scanner(240,240,imagenTratar);
		

		
		return imagenTratada;
		
	}
	
	public Bitmap getImagen(){
		
		return this.imagen;
	}
	
	
	public Bitmap scanner (int x ,int y,Bitmap img){ //metodo que posiciona el inicio de trabajo al lugar mas cercano ademas gestiona todos los metodos que se usan para trazar la espiral

		Bitmap imagen2 = img;
		
		Canvas canvas = new Canvas(imagen2);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		boolean condicion = false;
		int derecha = 3;
		int izquierda  = 3;
		int arriba  = 3;
		int abajo = 3;
		
		for(;derecha<=100;derecha++){ //comprueba la distancia hasta el azul
			
			
			if(imagen2.getPixel(x+derecha,y) == Color.BLUE){
				break;
			}
		}
		for(;izquierda<=50;izquierda++){//comprueba la distancia hasta el azul
			
			
			if(imagen2.getPixel(x-izquierda,y) == Color.BLUE){
				break;
			}
		}
		for(;arriba<=50;arriba++){//comprueba la distancia hasta el azul
			
			if(imagen2.getPixel(x,y - arriba) == Color.BLUE){
				break;
			}
		}	
		for(;abajo<=50;abajo++){//comprueba la distancia hasta el azul
			

			if(imagen2.getPixel(x,y + abajo) == Color.BLUE){
				break;
			}
		}
		
		int media =(derecha+ arriba + abajo + izquierda)/4; // esto ara que se pare recorrido
		
		System.out.println("derecha"+derecha+" izquierda"+izquierda+" arriba"+arriba+" abajo"+ abajo);
		
		
if(izquierda <=10 && abajo <=10 && arriba <=10 && media <=10 || izquierda <=10 && derecha <=10 && abajo <=10 && media <=10 || izquierda <=10 && arriba <=10 && derecha <=10 && media <=10  || derecha <=10 && arriba <=10 && abajo <=10 && media <=10 ){ // esto no esta comprobado que funcione xq no acaba el recorrido jajajaja
	

	condicion=true;
	

}else{
if(empezar==false){
	if(derecha<izquierda && derecha<abajo && derecha<arriba && condicion ==false ){ //comprueba x donde empieza segun cual esta mas cerca
		
		condicion = true;
		for(int pinto = 0;pinto <=10;pinto++){
			canvas.drawPoint(x-pinto,y,p);
		}
		derechaAbajo(x+derecha-10,y,img);
	}
	if(izquierda<derecha && izquierda<abajo && izquierda<arriba && condicion ==false){//comprueba x donde empieza segun cual esta mas cerca
		System.out.println("empieza izquierdaArriba");
		condicion = true;
		for(int pinto = 0;pinto <=10;pinto++){
			canvas.drawPoint(x+pinto,y,p);
		}
		izquierdaArriba(x-izquierda+10,y,img);
		}
	if(arriba<derecha && arriba<abajo && arriba<izquierda && condicion ==false){//comprueba x donde empieza segun cual esta mas cerca
		System.out.println("empieza arribaDerecha");
		condicion = true;
		for(int pinto = 0;pinto <=10;pinto++){
			canvas.drawPoint(x,y-pinto,p);
		}
		arribaDerecha(x,y-arriba+10,img);
		}
	if(abajo<arriba && abajo<derecha && abajo<izquierda && condicion ==false){//comprueba x donde empieza segun cual esta mas cerca
		System.out.println("empieza abajoIzquierda");
		condicion = true;
		for(int pinto = 0;pinto <=10;pinto++){
			canvas.drawPoint(x,y+pinto,p);
		}
		abajoIzquierda(x,y+abajo-10,img);		
		}
}else{
	for(int control=-2; control<=2;control++ ){
	if (arriba == derecha+control && condicion ==false){ //gestiona los rincones
		condicion = true;
		if(arriba < 25){
		System.out.println("pinta derechaAbajo");
			derechaAbajo(x,y,img);
			//arribaDerecha(x,y);
			arriba=35;
			abajo=35;
			izquierda=35;
			derecha=35;
		}} 
	}
	for(int control=-2; control<=2;control++ ){
	if(abajo==derecha+control && condicion ==false){
		condicion = true;
		if(abajo<25){	
		System.out.println("pinta abajoIzquierda");//gestiona los rincones
			abajoIzquierda(x,y,img);
			arriba=35;
			abajo=35;
			izquierda=35;
			derecha=35;
		}
	} }
	for(int control=-2; control<=2;control++ ){
	if (abajo == izquierda+control && condicion ==false){
		condicion = true;
		if(abajo<25){	
		System.out.println("pinta izquierdaArriba");//gestiona los rincones
			izquierdaArriba(x,y,img);
			
		}
		} }
	for(int control=-2; control<=2;control++ ){
	if (izquierda== arriba+control && condicion ==false){
		condicion = true;
		if(izquierda<25)	
		System.out.println("pinta arribaDerecha");//gestiona los rincones
			arribaDerecha(x,y,img);
			//derechaAbajo(x,y);
			
		}}
	
		
	
		 if(condicion ==false){ //otra condicion
			condicion = true;
			System.out.println("otra condicion en scanner"+"derexa "+derecha+" izquierda:" + izquierda+ " abajo:"+abajo + " arriba:"+arriba); // otra condicion como una eskina que aun no e desarollado
		} }}

	return img;

	}

	public void derechaAbajo(int x, int y,Bitmap img) {
		empezar = true;
		int linea = 0;
		boolean romper = false;
		
		Canvas canvas = new Canvas(img);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		while (romper == false) {
			linea++; //COORDENADA EN Y DEL PIXEL EN EL QUE BUSCAMOS EL PERIMETRO
			for (int derecha = 1; derecha <= 15; derecha++) {
				if (img.getPixel(x+derecha,y+linea) == Color.BLUE) { //SI HEMOS ENCONTRADO EL PERÍMETRO...
					int resultado = 10 - derecha;
					x = x - resultado;
					canvas.drawPoint(x, y + linea,p);
					
					if (img.getPixel(x,y+linea+10) == Color.BLUE) {
						romper = true;
					}
					break;
				}
			}
		}

		
	scanner(x, y + linea,img);
	}

	
	public void abajoIzquierda(int x, int y,Bitmap img) {
		empezar = true;
		int linea = 1;
		boolean romper = false;
		
		Canvas canvas = new Canvas(img);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		p.setStrokeWidth(2);

		while (romper == false) {
			
			for (int abajo = 1; abajo <= 15; abajo++) {

				if (img.getPixel(x-linea,y+abajo) == Color.BLUE) {
					int resultado = 10 - abajo;
					y = y - resultado;
					
					if (img.getPixel(x-linea-10,y) == Color.BLUE) {
						romper = true;
					}canvas.drawPoint(x - linea, y,p);
					//this.repaint();
					break;
				}
			}
			linea++;}

		
		scanner(x - linea, y,img);
	}

	
	public void izquierdaArriba(int x, int y,Bitmap img) {
		empezar = true;
		int linea = 1;
		boolean romper = false;

		Canvas canvas = new Canvas(img);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		

		while (romper == false) {
		//System.out.println(linea);
			linea++;
			for (int izquierda = 1; izquierda <= 15; izquierda++) {
				
				if (img.getPixel(x-izquierda,y-linea) == Color.BLUE) {
					int resultado = 10 - izquierda;
					x = x + resultado;
					canvas.drawPoint(x, y - linea,p);


					if (img.getPixel(x+resultado,y-linea-10) == Color.BLUE) {
						romper = true;
					}
					break;
				}
			}
		}
		//boolean pro = false;
		//if (pro == false){
		scanner(x,y-linea,img);
			//pro=true;
		}
	

	public void arribaDerecha(int x, int y,Bitmap img) {
		empezar = true;

		Canvas canvas = new Canvas(img);
		
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		
		int linea = 0;
		boolean romper = false;
		
		while (romper == false) {
			linea++; //añado 1 a la coordenada X del Pixel a buscar
			for (int arriba = 1; arriba <= 30; arriba++) {//busca hacia arriba del actual pixel, buscando el perímetro

			
				if (img.getPixel(x+linea,y-arriba) == Color.BLUE) {
					int resultado = 10 - arriba;
					y = y + resultado;
					canvas.drawPoint(x + linea, y,p);
					//this.repaint();

					if (img.getPixel(x+linea+10,y) == Color.BLUE) {
						romper = true;
					}
					break;
				}
			}
			}
		scanner(x + linea, y,img);
		}
	


	
}
