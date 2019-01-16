
public class Usuario {

	private String user;
	private String nombre;
	private String correo;
	private String pass;

	public Usuario(String user, String nombre, String correo, String pass) {
		this.user = user;
		this.nombre = nombre;
		this.correo = correo;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", nombre=" + nombre + ", correo=" + correo + ", pass=" + pass + "]";
	}

}
