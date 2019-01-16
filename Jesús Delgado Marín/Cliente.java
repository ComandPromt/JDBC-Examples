
public class Cliente {

	private String DNI;
	private String Nombre;
	private String domicilio;

	public Cliente(String dNI, String nombre, String domicilio) {
		super();
		DNI = dNI;
		Nombre = nombre;
		this.domicilio = domicilio;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
