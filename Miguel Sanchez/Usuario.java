public class Usuario {

	private String correo;
	private String nombre;
	private String pass;
	private String user;

	public Usuario(String correo, String nombre, String pass, String user) {
		super();
		this.correo = correo;
		this.nombre = nombre;
		this.pass = pass;
		this.user = user;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Usuario [correo=" + correo + ", nombre=" + nombre + ", pass=" + pass + ", user=" + user + "]";
	}
}
