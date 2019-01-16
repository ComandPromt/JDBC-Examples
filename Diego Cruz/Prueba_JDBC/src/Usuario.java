import lombok.Data;

@Data
public class Usuario {

	private String user, pass, nombre, correo;

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", pass=" + pass + ", nombre=" + nombre + ", correo=" + correo + "]";
	}

}
