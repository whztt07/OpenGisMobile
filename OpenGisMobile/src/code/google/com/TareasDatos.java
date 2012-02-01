package code.google.com;

public class TareasDatos {

	private String idtarea;
	private String nombre;

	public TareasDatos(String idtarea,String nombre){
		
		this.setIdtarea(idtarea);
		this.setNombre(nombre);
		
	
	}

	public String getIdtarea() {
		return idtarea;
	}

	public void setIdtarea(String idtarea) {
		this.idtarea = idtarea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
