package code.google.com;

public class UserDatos {
	
	private String dni = "";
	private String nombre = "";
	private String apellidos = "";
	private String email = "";
	private String telefono = "";
	private String direccion = "";
	private String poblacion = "";
	private String provincia = "";
	private String cp = "";
	private String fec_nac = "";
	
	
	public UserDatos(String dni,String nombre,String apellidos, String email, String telefono, String direccion, String poblacion, String provincia, String cp, String fec_nac){
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.cp = cp;
		this.fec_nac = fec_nac;
		
		
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
	
	
	public String getEmail(){
		
		return this.email;
		
	}
	
	public String getTelefono(){
		
		return this.telefono;
		
	}
	
	public String getDireccion(){
		
		return this.direccion;
		
	}
	
	public String getPoblacion(){
		
		return this.poblacion;
		
	}
	
	public String getProvincia(){
		
		return this.provincia;
		
	}
	
	public String getCP(){
		
		return this.cp;
		
	}
	
	public String getFecNac(){
		
		return this.fec_nac;
		
	}

}
