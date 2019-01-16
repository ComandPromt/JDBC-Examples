import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class TestCoche {

	public static Connection conectarBaseDatos() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("No se encontro el Driver MySQL para JDBC.");
		}

		// Connection cn =
		// DriverManager.getConnection("jdbc:mysql://servidor_bd:puerto/nombre_bd",
		// "usuario", "contraseña");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

		boolean valid = cn.isValid(50000);
		System.out.println(valid ? "Conexion establecida" : "Conexion Fallida");

		return cn;

	}

	private static void insertarCoche(Connection conn, String marca, String color) throws SQLException {
		Date date = new Date();

		String sql = "INSERT INTO coches(modelo,color,numerodepuertas,cuentakilometros,velocidad,arrancado,fechaInsertado) values(?,?,?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, marca);
		statement.setString(2, color);
		statement.setInt(3, 4);
		statement.setInt(4, 200);
		statement.setInt(5, 200);
		statement.setInt(6, 1);
		statement.setTimestamp(7, new Timestamp(date.getTime()));

		int rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("Coche insertado");
		}

	}

	private static void mostrarListadoCoche(Connection conn) throws SQLException {
		String sql = "SELECT * FROM coches";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery(sql);

		while (result.next()) {
			Integer id = result.getInt(1);
			String modelo = result.getString(2);
			String color = result.getString(3);
			int numeroDePuertas = result.getInt(4);
			int cuentaKilometros = result.getInt(5);
			int velocidad = result.getInt(6);
			System.out.println("Registros: " + id + " " + modelo + " " + color + " " + numeroDePuertas + " "
					+ cuentaKilometros + " " + velocidad);
		}

	}

	private static void actualizarCoche(Connection conn, int id, String marcaActualizada) throws SQLException {
		String sql = "UPDATE coches SET modelo=? WHERE id=?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, marcaActualizada);
		statement.setInt(2, id);

		int rowsUpdated = statement.executeUpdate();

		if (rowsUpdated > 0) {
			System.out.println("Actualizado.");
		}

	}

	private static void mostrarCoche(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM coches where id=" + id;

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			// statement.setString(1, "Renault");
			System.out.println(statement.toString());
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				String marca = result.getString(2);
				String modelo = result.getString(3);
				String color = result.getString(4);
				int numeroDePuertas = result.getInt(5);
				int cuentaKilometros = result.getInt(6);
				int velocidad = result.getInt(7);
				System.out.println("Registros: " + id + " " + marca + " " + modelo + " " + color + " " + numeroDePuertas
						+ " " + cuentaKilometros + " " + velocidad);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static boolean borrarCoche(Connection conn, int idBorrado) throws SQLException {
		String sql = "DELETE FROM coches where id=?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idBorrado);

		int rowsDeleted = statement.executeUpdate();

		if (rowsDeleted > 0) {
			System.out.println("Borrado.");
			return true;
		}

		return false;

	}

	private static void comprarCoche(Connection conn, Cliente cliente, int idComprar) throws SQLException {

		if (borrarCoche(conn, idComprar)) {
			String sql = "INSERT INTO clientes(dni,nombre,domicilio,idCocheComprado) values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, cliente.getDNI());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getDomicilio());
			statement.setInt(4, idComprar);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Cliente insertado");
			}
		} else {
			System.out.println("El coche no esta en stock");
		}
		;

	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int select = -1;
		//

		Connection conn = null;
		try {
			conn = conectarBaseDatos();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Mientras la opción elegida sea 0, preguntamos al usuario
		while (select != 0) {
			// Try catch para evitar que el programa termine si hay un error
			try {
				System.out.println("Elige opción:\n" + "1.- Insertar\n" + "2.- Mostrar todos\n"
						+ "3.- Mostrar un coche por id\n" + "4.- Actualizar marca coche\n" + "5.- Borrar coche\n"
						+ "6.- Comprar coche\n" + "0.- Salir de programa\n");
				// Recoger una variable por consola
				select = Integer.parseInt(scanner.next());
				switch (select) {
				case 1:
					System.out.println("Insertar la marca:");
					String marca = scanner.next();
					scanner.nextLine();
					System.out.println("Insertar el color:");
					String color = scanner.next();
					insertarCoche(conn, marca, color);
					break;
				case 2:
					mostrarListadoCoche(conn);
					break;
				case 3:
					System.out.println("Introduce id coche");
					int idConsultar = scanner.nextInt();
					mostrarCoche(conn, idConsultar);
					break;
				case 4:
					System.out.println("Introduce el id del coche a actualizar");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Introduce el modelo del coche a actualizar");
					String marcaActualizada = scanner.next();
					actualizarCoche(conn, id, marcaActualizada);
					break;
				case 5:
					System.out.println("Introduce el id del coche a borrar");
					int idBorrado = scanner.nextInt();
					borrarCoche(conn, idBorrado);
					break;
				case 6:
					System.out.println("Introduce los datos del cliente:");
					System.out.println("DNI: ");
					String dni = scanner.next();
					System.out.println("Nombre: ");
					String nombre = scanner.next();
					System.out.println("Domicilio: ");
					scanner.nextLine();
					String domicilio = scanner.nextLine();

					Cliente cliente = new Cliente(dni, nombre, domicilio);

					System.out.print("Id del coche que compra: ");
					int idComprar = scanner.nextInt();

					comprarCoche(conn, cliente, idComprar);
					break;
				case 0:
					System.out.println("Adios!");
					break;
				default:
					System.out.println("Número no reconocido");
					break;
				}

				System.out.println("\n"); // Mostrar un salto de línea en Java

			} catch (Exception e) {
				System.out.println("Uoop! Error!" + e.getMessage());
			}
		}

	}

}
