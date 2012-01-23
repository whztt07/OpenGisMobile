package code.google.com;

public class ParcelasDatos {
	
	
	
	private String idparcela;
	private String alias;
	private String provincia;
	private String poblacion;
	private String poligono;
	private String numero;
	private String activo;
	private String partida;
	private String DNIPropietario;

	public ParcelasDatos(String idparcela,String alias,String provincia,String poblacion,String poligono,String numero,String activo,String partida, String dniPropietario){
		
		this.setIdparcela(idparcela);
		this.setAlias(alias);
		this.setProvincia(provincia);
		this.setPoblacion(poblacion);
		this.setPoligono(poligono);
		this.setNumero(numero);
		this.setActivo(activo);
		this.setPartida(partida);
		this.setDNIPropietario(dniPropietario);
		
		
	}

	public String getIdparcela() {
		return idparcela;
	}

	public void setIdparcela(String idparcela) {
		this.idparcela = idparcela;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getPoligono() {
		return poligono;
	}

	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getDNIPropietario() {
		return DNIPropietario;
	}

	public void setDNIPropietario(String dNIPropietario) {
		DNIPropietario = dNIPropietario;
	}

}
