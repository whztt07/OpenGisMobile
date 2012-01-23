package code.google.com;

public class ProductosDatos {

	private String idprod = "";
	private String nombre = "";
	private String descripcion = "";
	private String nomtarea = "";
	private String dosis = "";
	private String activo = "";
	private String dni = "";

	public ProductosDatos(String idprod, String nombre, String descripcion,
			String nomtarea, String dosis, String dni, String activo) {
		
		
		
		this.setIdprod(idprod);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setNomtarea(nomtarea);
		this.setDosis(dosis);
		this.setActivo(activo);
		this.setDNI(dni);
		
	
	}

	public String getIdprod() {
		return idprod;
	}

	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNomtarea() {
		return nomtarea;
	}

	public void setNomtarea(String nomtarea) {
		this.nomtarea = nomtarea;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDNI() {
		return dni;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}


}
