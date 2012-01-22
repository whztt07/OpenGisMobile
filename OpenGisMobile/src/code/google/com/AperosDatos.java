package code.google.com;

public class AperosDatos {

	
	private String idApero = "";
	private String nombre = "";
	private String tamanyo = "";
	private String descripcion = "";
	private String activo = "";
	private String idTarea = "";
	
	
	public AperosDatos(String idapero, String nombre, String tamanyo, String descripcion,String idTarea, String activo) {
		
		this.idApero = idapero;
		this.nombre = nombre;
		this.tamanyo = tamanyo;
		this.descripcion = descripcion;
		this.activo = activo;
		this.idTarea  = idTarea;
		
		
	}
	
	
	public String getIdApero(){
		
		return this.idApero;
		
	}

	
	
	public String getNombreApero(){
		
		return this.nombre;
		
	}
	
	public String getTamanyoApero(){
		
		return this.tamanyo;
		
	}
	
	public String getDescripcionApero(){
		
		return this.descripcion;
		
	}
	
	public String getEstadoApero(){
		
		return this.activo;
		
	}


	public String getIdTarea() {
		return idTarea;
	}

	
}
