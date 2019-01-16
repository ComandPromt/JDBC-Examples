import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestCoche {

	public void conectarBD() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Error al conectar con la base de datos: " + e);
			}

			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			boolean valid = connection.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			if (valid) {
				int opcion = 0;

				do {
					System.out.println("Menú de Opciones");
					System.out.println("1 - Insertar Coche");
					System.out.println("2 - Modificar Coche");
					System.out.println("3 - Eliminar Coche");
					System.out.println("4 - Listar todos los Coches");
					System.out.println("5 - Comprar coche");
					System.out.println("6 - Salir");

					Scanner s = new Scanner(System.in);
					opcion = s.nextInt();

					switch (opcion) {
					case 1:
						System.out.println("Introduce la marca del coche");
						String marca = s.next();
						System.out.println("Introduce el modelo del coche");
						String modelo = s.next();
						System.out.println("Introduce el precio del coche");
						Double precio = s.nextDouble();
						insertar(connection, marca, modelo, precio);
						break;
					case 2:
						System.out.println("Introduce la marca del coche");
						String marca2 = s.next();
						System.out.println("Introduce el modelo del coche");
						String modelo2 = s.next();
						System.out.println("Introduce el precio del coche");
						Double precio2 = s.nextDouble();
						update(connection, marca2, modelo2, precio2);
						break;
					case 3:
						System.out.println("Introduce el modelo del coche");
						String modelo3 = s.next();
						delete(connection, modelo3);
						break;
					case 4:
						select(connection);
						break;
					case 5:
						System.out.println("Introduce la id del cliente");
						String id = s.next();
						System.out.println("Introduce el nombre del cliente");
						String nombre = s.next();
						System.out.println("Introduce los apellidos del cliente");
						String apellidos = s.next();
						System.out.println("Introduce la dirección del cliente");
						String direccion = s.next();
						System.out.println("Introduce el modelo del coche que quieres comprar");
						String modeloCoche = s.next();
						insertarCliente(connection, id, nombre, apellidos, direccion, modeloCoche);
						break;
					case 6:
						break;
					default:
						break;
					}
				} while (opcion != 6);

			}
		} catch (java.sql.SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	private static java.sql.Timestamp getFecha() {
		java.util.Date hoy = new java.util.Date();
		return new java.sql.Timestamp(hoy.getTime());
	}

	public void insertar(Connection connection, String marca, String modelo, Double precio) throws SQLException {
		String sql = "INSERT INTO coche (marca, modelo, fechaMatriculacion, precio) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);

		Coche coche = new Coche();

		coche.setMarca(marca);
		coche.setModelo(modelo);
		coche.setPrecio(precio);

		statement.setString(1, coche.getMarca());
		statement.setString(2, coche.getModelo());
		statement.setTimestamp(3, getFecha());
		statement.setDouble(4, coche.getPrecio());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche insertado!");
		}
	}

	public void insertarCliente(Connection connection, String id, String nombre, String apellidos, String direccion,
			String modeloCoche) throws SQLException {
		String sql = "INSERT INTO cliente (id, nombre, apellidos, direccion, modeloCoche) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);

		Cliente cliente = new Cliente();

		cliente.setId(id);
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setDireccion(direccion);
		cliente.setModeloCoche(modeloCoche);

		statement.setString(1, cliente.getId());
		statement.setString(2, cliente.getNombre());
		statement.setString(3, cliente.getApellidos());
		statement.setString(4, cliente.getDireccion());
		statement.setString(5, cliente.getModeloCoche());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche insertado!");
		}
	}

	public void update(Connection connection, String marca, String modelo, Double precio) throws SQLException {
		String sql = "UPDATE coche SET marca=?, fechaMatriculacion=?, precio=? WHERE modelo=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		Coche coche = new Coche();

		coche.setMarca(marca);
		coche.setModelo(modelo);
		coche.setPrecio(precio);

		statement.setString(1, coche.getMarca());
		statement.setString(2, coche.getModelo());
		statement.setTimestamp(3, getFecha());
		statement.setDouble(4, coche.getPrecio());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche modificado!");
		}
	}

	public void delete(Connection connection, String modelo) throws SQLException {
		String sql = "DELETE FROM coche WHERE modelo=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		Coche coche = new Coche();

		coche.setModelo(modelo);

		statement.setString(1, coche.getModelo());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche eliminado!");
		}
	}

	public void select(Connection connection) throws SQLException {
		String sql = "SELECT * FROM coche";
		String sql2 = "SELECT * FROM cliente";

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		Statement statement2 = connection.createStatement();
		ResultSet result2 = statement2.executeQuery(sql2);

		Coche coche = new Coche();
		Cliente cliente = new Cliente();
		int contador = 0;

		System.out.println("Lista de Coches");
		while (result.next()) {
			coche.setMarca(result.getString("marca")); // Se puede coger el campo por numero
			coche.setModelo(result.getString("modelo")); // Se puede coger el campo por numero
			coche.setFechaMatriculacion(result.getTimestamp("fechaMatriculacion"));
			coche.setPrecio(result.getDouble("precio")); // Se puede coger el campo por numero
			contador++;
			System.out.println("--" + contador + " " + coche.getMarca() + " " + coche.getModelo() + " "
					+ coche.getFechaMatriculacion() + " " + coche.getPrecio());
		}

		System.out.println("Lista de Clientes");
		while (result2.next()) {
			cliente.setId(result2.getString("id")); // Se puede coger el campo por numero
			cliente.setNombre(result2.getString("nombre")); // Se puede coger el campo por numero
			cliente.setApellidos(result2.getString("apellidos"));
			cliente.setDireccion(result2.getString("direccion")); // Se puede coger el campo por numero
			cliente.setModeloCoche(result2.getString("modeloCoche")); // Se puede coger el campo por numero
			contador++;
			System.out.println("--" + contador + " " + cliente.getId() + " " + cliente.getNombre() + " "
					+ cliente.getApellidos() + " " + cliente.getDireccion() + " " + cliente.getModeloCoche());
		}
	}

	public static void main(String[] args) {
		TestCoche javaMySQLBasic = new TestCoche();
		javaMySQLBasic.conectarBD();
	}
}
