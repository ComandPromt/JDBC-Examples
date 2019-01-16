import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

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

			// Insertar, actualizar y borrar usuarios
			insertarRegistro(connection);
			actualizarRegistros(connection);
			borrarRegistros(connection);
			selectRegistros(connection);

			System.out.println(valid ? "TEST OK" : "TEST FAIL");

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error" + sqle);
		}
	}

	public static void insertarRegistro(Connection conn) throws SQLException {
		String sql = "INSERT INTO usuario (user, pass, nombre, correo) VALUES (?,?,?,?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, "paco1234");
		statement.setString(2, "passPaco");
		statement.setString(3, "Paco");
		statement.setString(4, "paco@ayto.es");

		int rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("Registro insertado!");
		}

	}

	public static void actualizarRegistros(Connection conn) throws SQLException {

		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre = ?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, "paco1234");
		statement.setString(2, "Paco");
		statement.setString(3, "paco@aytos.es");
		statement.setString(4, "Juanito");

		int rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("Registro Actualizado!!");
		}

	}

	public static void borrarRegistros(Connection conn) throws SQLException {

		String sql = "DELETE FROM usuario WHERE user=?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, "paco1234");

		int rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("Registro Borrado!!!");
		}

	}

	public static void selectRegistros(Connection conn) throws SQLException {

		String sql = "SELECT * FROM usuario";

		java.sql.Statement statement = conn.createStatement();

		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String pass = result.getString(2); // pass
			String nombre1 = result.getString(1); // nombre
			String nombre = result.getString("nombre"); // nombre
			String email = result.getString("correo"); // pass

			System.out.println("---" + count + pass + nombre1 + nombre + email);
		}

	}

	public static void main(String[] args) {
		Test.connectDatabase();

	}

}
