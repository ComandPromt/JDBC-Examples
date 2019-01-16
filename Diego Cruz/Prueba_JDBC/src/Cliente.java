import lombok.Data;

@Data
public class Cliente {
	int id, serieCoche;
	String nombre, apellidos, direccion;

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", serieCoche=" + serieCoche + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", direccion=" + direccion + "]";
	}
}
