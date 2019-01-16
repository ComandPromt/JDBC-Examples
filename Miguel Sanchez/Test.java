import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

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
				// insertar(connection);
				// update(connection);
				// delete(connection);
				select(connection);
			}
		} catch (java.sql.SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	public void insertar(Connection connection) throws SQLException {
		String sql = "INSERT INTO usuario (user, pass, nombre, correo) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "migue1234");
		statement.setString(2, "passMigue");
		statement.setString(3, "Migue");
		statement.setString(4, "migue@aytos.es");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado!");
		}
	}

	public void update(Connection connection) throws SQLException {
		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "migue12345");
		statement.setString(2, "passMigue1");
		statement.setString(3, "migue1@aytos.es");
		statement.setString(4, "Migue");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario modificado!");
		}
	}

	public void delete(Connection connection) throws SQLException {
		String sql = "DELETE FROM usuario WHERE user=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "migue12345");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario eliminado!");
		}
	}

	public void select(Connection connection) throws SQLException {
		String sql = "SELECT * FROM usuario";

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int contador = 0;

		while (result.next()) {
			String pass = result.getString(2); // Se puede coger el campo por numero
			String nombre1 = result.getString(3);
			String nombre = result.getString("nombre"); // O por nombre de campo
			String email = result.getString("correo");
			contador++;
			System.out.println("--" + contador + " " + pass + " " + nombre1 + " " + nombre + " " + email);
		}
	}

	public static void main(String[] args) {
		Test javaMySQLBasic = new Test();
		javaMySQLBasic.conectarBD();
	}

}
