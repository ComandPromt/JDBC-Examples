
public class Usuario {

	private String users;
	private String pass;
	private String nombre;
	private String correo;

	public Usuario(String users, String pass, String nombre, String correo) {
		super();
		this.users = users;
		this.pass = pass;
		this.nombre = nombre;
		this.correo = correo;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Usuarios [users=" + users + ", pass=" + pass + ", nombre=" + nombre + ", correo=" + correo + "]";
	}

}
