
public class Coche {
	String marca;
	String modelo;
	String color;
	int numeroDePuertas;
	int cuentaKilometros;
	int velocidad;
	boolean arrancado;

	public Coche(String marca, String modelo, String color, int numeroDePuertas, int cuentaKilometros, int velocidad,
			boolean arrancado) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.numeroDePuertas = numeroDePuertas;
		this.cuentaKilometros = cuentaKilometros;
		this.velocidad = velocidad;
		this.arrancado = arrancado;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumeroDePuertas() {
		return numeroDePuertas;
	}

	public void setNumeroDePuertas(int numeroDePuertas) {
		this.numeroDePuertas = numeroDePuertas;
	}

	public int getCuentaKilometros() {
		return cuentaKilometros;
	}

	public void setCuentaKilometros(int cuentaKilometros) {
		this.cuentaKilometros = cuentaKilometros;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isArrancado() {
		return arrancado;
	}

	public void setArrancado(boolean arrancado) {
		this.arrancado = arrancado;
	}

	void arrancar() {
		arrancado = true;
	}

	void parar() {
		arrancado = false;
	}

	void acelerar() {
		velocidad = velocidad + 1;
	}

	void frenar() {
		velocidad = velocidad - 1;
	}

	void pitar() {
		System.out.println("Piiiiiiiiiiiiiiiiii");
	}

	int consultarCuentaKilometros() {
		return cuentaKilometros;
	}
}