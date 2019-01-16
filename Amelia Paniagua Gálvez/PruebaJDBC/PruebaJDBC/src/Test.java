import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public void connectDatabase() {

		try {
			try {
				// se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection connection = null;

			// creamos la conexión
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			// Devuelve verdadero si la conexión no se ha cerrado y sigue siendo válida.
			// 50000 -> es los segundos de espera para la respuesta
			boolean valid = connection.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");
			// insertaRegistro(connection); comento para que no inserte cada vez que ejecute
			// actualizaRegistros(connection);
			// borraRegistros(connection);
			selectRegistros(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);

		}

	}

	public void insertaRegistro(Connection conn) throws SQLException {

		String sql = "INSERT INTO usuario (user, pass, nombre, correo) VALUES (?,?,?,?)";

		PreparedStatement statment = conn.prepareStatement(sql);
		statment.setString(1, "meli1234");
		statment.setString(2, "passMeli");
		statment.setString(3, "Meli");
		statment.setString(4, "meli@aytos.es");

		int rowsInserted = statment.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado");
		}
	}

	public void actualizaRegistros(Connection conn) throws SQLException {

		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre=?";

		PreparedStatement statment = conn.prepareStatement(sql);
		statment.setString(1, "admin");
		statment.setString(2, "pass123");
		statment.setString(3, "bill.gates@microsoft.com");
		statment.setString(4, "Juanito");

		int rowsUpdated = statment.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Actualizado.");
		}
	}

	public void borraRegistros(Connection conn) throws SQLException {

		String sql = "DELETE FROM usuario WHERE user=?";

		PreparedStatement statment = conn.prepareStatement(sql);
		statment.setString(1, "jose1234");

		int rowsDeleted = statment.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Registro eliminado.");
		}
	}

	public void selectRegistros(Connection conn) throws SQLException {

		String sql = "SELECT * FROM usuario";

		Statement statment = conn.createStatement();
		ResultSet result = statment.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String pass = result.getString(2); // el campo 2 es el pass, es una muestra para ver que se puede
												// referenciar
												// con el número de campo y con el nombre
			String nombre1 = result.getString(3);// el campo 3 es el nombre
			String nombre2 = result.getString("nombre");
			String email = result.getString("correo");
			count++;
			System.out.println("--" + count + " " + pass + " " + nombre1 + " " + nombre2 + " " + email);

		}

	}

	public static void main(String[] args) {

		Test javaMySQLBasic = new Test();
		javaMySQLBasic.connectDatabase();

	}

}
