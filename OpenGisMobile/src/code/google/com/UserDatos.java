package code.google.com;

public class UserDatos {
	
	private String dni;
	private String nombre;
	private String apellidos;
	
	
	public UserDatos(String dni,String nombre,String apellidos){
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		
		
	}
	
	
	public String getDNI(){
		
		return this.dni;
		
	}
	
	public String getNombre(){
		
		return this.nombre;
		
	}
	
	public String getApellidos(){
		
		return this.apellidos;
		
	}

}
