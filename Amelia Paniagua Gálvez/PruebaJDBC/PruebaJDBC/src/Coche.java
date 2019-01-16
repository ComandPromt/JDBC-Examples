
public class Coche {

	private int id;
	private String marca;
	private boolean aa;
	private double precio;
	private boolean disponible;

	public Coche() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coche(String marca, boolean aa, Integer id, double precio, boolean disponible) {
		super();
		this.marca = marca;
		this.aa = aa;
		this.id = id;
		this.precio = precio;
		this.disponible = disponible;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isAa() {
		return aa;
	}

	public void setAa(boolean aa) {
		this.aa = aa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", aa=" + aa + ", precio=" + precio + ", disponible="
				+ disponible + "]";
	}

	

}
