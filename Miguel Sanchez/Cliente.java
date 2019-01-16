
public class Cliente {

	private String id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String modeloCoche;

	public Cliente(String id, String nombre, String apellidos, String direccion, String modeloCoche) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.modeloCoche = modeloCoche;
	}

	public Cliente() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getModeloCoche() {
		return modeloCoche;
	}

	public void setModeloCoche(String modeloCoche) {
		this.modeloCoche = modeloCoche;
	}
}
