import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class TestCoche {

	private static Timestamp getCurretnTimestamp() {
		java.util.Date hoy = new java.util.Date();
		return new java.sql.Timestamp(hoy.getTime());
	}

	static Timestamp fechaCompra = getCurretnTimestamp();

	private static Coche coche1 = new Coche("Audi", "RS7", "Azul", "0717KLL", 90.000, fechaCompra);

	public static void connectDatabase() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MYSQL: " + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
			boolean valid = connection.isValid(50000);

			Scanner entrada = new Scanner(System.in);
			int numero;
			do {
				System.out.print("Introduzca una opción del menú: \n");
				System.out.print(" 1. Insertar un coche: \n");
				System.out.print(" 2. Actualizar un  coche: \n");
				System.out.print(" 3. Borrar un coche: \n");
				System.out.print(" 4. Mostrar coche: \n");
				numero = entrada.nextInt();
				switch (numero) {
				case 1:
					insertarCoche(connection);
					break;
				case 2:
					actualizarCoche(connection);
					break;
				case 3:
					borrarCoche(connection);
					break;
				case 4:
					selectCoche(connection);
					break;
				default:
					System.out.println("Por favor, introduzca de nuevo los datos");
					break;

				}
			} while (numero != 0);

			System.out.println(valid ? "TEST OK" : "TEST FAIL");

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error" + sqle);
		}
	}

	public static void insertarCoche(Connection conn) throws SQLException {

		String sql = "INSERT INTO coche (marca, modelo, matricula, color, precio, fechaCompra) VALUES (?,?,?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, coche1.getMarca());
		statement.setString(2, coche1.getModelo());
		statement.setString(3, coche1.getColor());
		statement.setString(4, coche1.getMatricula());
		statement.setDouble(5, coche1.getPrecio());
		statement.setTimestamp(6, coche1.getFechaCompra());

		int rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("--Coche insertado!--");
		}

	}

	public static void actualizarCoche(Connection conn) throws SQLException {
		Coche coche2 = new Coche("BMW", "M4", "Negro", "8593GHQ", 70.000, fechaCompra);
		String sql = "UPDATE coche SET marca=?, modelo=?, color=?, precio=?, fechaCompra=? WHERE matricula = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, coche2.getMarca());
		statement.setString(2, coche2.getModelo());
		statement.setString(3, coche2.getColor());
		statement.setString(6, coche1.getMatricula());
		statement.setDouble(4, coche2.getPrecio());
		statement.setTimestamp(5, coche2.getFechaCompra());

		int rowsUpdate = statement.executeUpdate();

		if (rowsUpdate > 0) {
			System.out.println("--Coche Actualizado--!");
		}

	}

	public static void borrarCoche(Connection conn) throws SQLException {

		String sql = "DELETE FROM coche WHERE marca=?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, coche1.getMarca());

		int rowsDeleted = statement.executeUpdate();

		if (rowsDeleted > 0) {
			System.out.println("--Coche Borrado--!");
		}
	}

	public static void selectCoche(Connection conn) throws SQLException {

		String sql = "SELECT * FROM coche";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String marca = result.getString(1);
			String modelo = result.getString(2);
			String color = result.getString("color");
			String matricula = result.getString("matricula");
			Double precio = result.getDouble("precio");
			Timestamp fechaCompra = result.getTimestamp("fechaCompra");
			count++;
			System.out.println(" ---> " + count + " " + marca + " " + modelo + " " + color + " " + matricula + " "
					+ precio + " " + fechaCompra);
		}

	}

	public static void main(String[] args) {
		TestCoche.connectDatabase();

	}

}
