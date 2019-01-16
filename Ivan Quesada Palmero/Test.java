import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

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
			selectRegistros(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public void insertarRegistro(Connection conn) throws SQLException {
		String sql = "INSERT INTO usuario(user, pass, nombre, correo) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "ivan1234");
		statement.setString(2, "passIvan");
		statement.setString(3, "Iván");
		statement.setString(4, "ivan@aytos.es");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado!");
		}
	}

	public void actualizarRegistro(Connection conn) throws SQLException {
		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "admin");
		statement.setString(2, "pass");
		statement.setString(3, "bill.gates@microsoft.com");
		statement.setString(4, "Iván");

		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Usuario actualizado!");
		}
	}

	public void borrarRegistro(Connection conn) throws SQLException {
		String sql = "DELETE FROM usuario WHERE user=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "admin");

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Usuario borrado!");
		}
	}

	public void selectRegistros(Connection conn) throws SQLException {
		String sql = "SELECT * FROM usuario";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String pass = result.getString(2);
			String nombre = result.getString(3);
			String correo = result.getString("correo");
			String usuario = result.getString("user");
			count++;

			System.out.println(count + "-- nombre: " + nombre + "correo: " + correo + "usuario: " + usuario
					+ "contraseña: " + pass);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test javaMSQLBasic = new Test();
		javaMSQLBasic.conectar();

	}

}
