import java.sql.Timestamp;

public class Coche {

	private String marca;
	private String modelo;
	private Timestamp fechaMatriculacion;
	private Double precio;

	public Coche(String marca, String modelo, Timestamp fechaMatriculacion, Double precio) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.fechaMatriculacion = fechaMatriculacion;
		this.precio = precio;
	}

	public Coche() {

	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Timestamp getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	public void setFechaMatriculacion(Timestamp fechaMatriculacion) {
		this.fechaMatriculacion = fechaMatriculacion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
