
public class Cliente {

	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String coche;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nombre, String apellido, String direccion, String coche) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.coche = coche;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCoche() {
		return coche;
	}

	public void setCoche(String coche) {
		this.coche = coche;
	}

}
