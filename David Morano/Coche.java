
public class Coche {

	private String matricula;
	private String marca;
	private String color;
	private int caballos;
	private float precio;
	private boolean vendido;

	public Coche(String matricula, String marca, String color, int caballos, float precio, boolean vendido) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.color = color;
		this.caballos = caballos;
		this.precio = precio;
		this.vendido = vendido;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCaballos() {
		return caballos;
	}

	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
