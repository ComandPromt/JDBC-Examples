import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TestCoche {

	public void conectar() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el drive de MYSQL: " + ex);
			}
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			boolean valid = connection.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			// insertarRegistro(connection);
			// actualizarRegistro(connection);
			// borrarRegistro(connection);
			// selectRegistros(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public static void insertarRegistro(Connection conn, String matricula, String marca, String modelo, Double precio)
			throws SQLException {
		String sql = "INSERT INTO coche (matricula, marca, modelo, precio) VALUES (?, ?, ?, ?)";

		Coche coche = new Coche(matricula, marca, modelo, precio);

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, coche.getMatricula());
		statement.setString(2, coche.getMarca());
		statement.setString(3, coche.getModelo());
		statement.setDouble(4, coche.getPrecio());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "Coche Insertado");
		}
	}

	public static void actualizarRegistro(Connection conn, String matricula, String marca, String modelo, Double precio)
			throws SQLException {
		String sql = "UPDATE coche SET marca=?, modelo=?, precio=? WHERE matricula=?";

		Coche coche = new Coche(matricula, marca, modelo, precio);

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, coche.getMarca());
		statement.setString(2, coche.getModelo());
		statement.setDouble(3, coche.getPrecio());
		statement.setString(4, coche.getMatricula());

		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			JOptionPane.showMessageDialog(null, "Coche actualizado!");
		}
	}

	public static void borrarRegistro(Connection conn, String matricula) throws SQLException {
		String sql = "DELETE FROM coche WHERE matricula=?";

		Coche coche = new Coche();
		coche.setMatricula(matricula);

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, coche.getMatricula());

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			JOptionPane.showMessageDialog(null, "Coche borrado!");
		}
	}

	public static void selectRegistros(Connection conn) throws SQLException {
		String sql = "SELECT * FROM coche";

		Coche coche = new Coche();

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		String lista = "";
		while (result.next()) {
			coche.setMatricula(result.getString(1));
			coche.setMarca(result.getString(2));
			coche.setModelo(result.getString(3));
			coche.setPrecio(result.getDouble(4));
			count++;

			lista += "\n" + count + " Matrícula: " + coche.getMatricula() + " \nMarca: " + coche.getMarca()
					+ " \nModelo: " + coche.getModelo() + " \nPrecio: " + coche.getPrecio()
					+ "\n--------------------------";

		}

		JOptionPane.showMessageDialog(null, lista);

	}

	public static void comprar(Connection conn) throws SQLException {

		String sql = "SELECT * FROM coche WHERE matricula NOT IN(SELECT coche FROM cliente)";

		Coche coche = new Coche();

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		String lista = "";
		while (result.next()) {
			coche.setMatricula(result.getString(1));
			coche.setMarca(result.getString(2));
			coche.setModelo(result.getString(3));
			coche.setPrecio(result.getDouble(4));
			count++;

			lista += "\n" + count + " Matrícula: " + coche.getMatricula() + " \nMarca: " + coche.getMarca()
					+ " \nModelo: " + coche.getModelo() + " \nPrecio: " + coche.getPrecio()
					+ "\n--------------------------";

		}

		String matriculaComprar = JOptionPane.showInputDialog(lista);

		String nombreCliente = JOptionPane.showInputDialog("Nombre de cliente");
		String apellidoCliente = JOptionPane.showInputDialog("Apellido de cliente");
		String direccionCliente = JOptionPane.showInputDialog("Dirección del cliente");

		String sql2 = "INSERT INTO cliente (nombre, apellido, direccion, coche) VALUES (?, ?, ?, ?)";

		PreparedStatement statement2 = conn.prepareStatement(sql2);
		statement2.setString(1, nombreCliente);
		statement2.setString(2, apellidoCliente);
		statement2.setString(3, direccionCliente);
		statement2.setString(4, matriculaComprar);

		int rowsUpdated = statement2.executeUpdate();
		if (rowsUpdated > 0) {
			JOptionPane.showMessageDialog(null, "Coche comprado!");
		}

	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Test javaMSQLBasic = new Test();
		javaMSQLBasic.conectar();

		String[] options = { "Insertar", "Actualizar", "Borrar", "Listar", "Comprar" };
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Coche", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
		switch (seleccion) {
		case 0:
			String matricula = JOptionPane.showInputDialog("Matricula");
			String marca = JOptionPane.showInputDialog("Marca");
			String modelo = JOptionPane.showInputDialog("Modelo");
			String precio = JOptionPane.showInputDialog("Precio");

			Double precio1 = Double.parseDouble(precio);

			insertarRegistro(connection, matricula, marca, modelo, precio1);

			break;

		case 1:
			String matriculaActualizar = JOptionPane.showInputDialog("Matricula del vehiculo a actualizar");
			String marcaActualizar = JOptionPane.showInputDialog("Marca nueva");
			String modeloActualizar = JOptionPane.showInputDialog("Modelo nuevo");
			String precioActualizar = JOptionPane.showInputDialog("Precio nuevo");

			Double precioActualizar1 = Double.parseDouble(precioActualizar);

			actualizarRegistro(connection, matriculaActualizar, marcaActualizar, modeloActualizar, precioActualizar1);
			break;

		case 2:
			String matriculaBorrar = JOptionPane.showInputDialog("Matricula del vehiculo a eliminar");
			borrarRegistro(connection, matriculaBorrar);

			break;
		case 3:

			selectRegistros(connection);

			break;

		case 4:

			comprar(connection);

			break;

		default:
			break;
		}

	}
}
