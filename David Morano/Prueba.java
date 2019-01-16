import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prueba {

	public void connectDatabase() {
		try {
			try {
				// Se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrare el driver de MySQL:" + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			boolean valid = connection.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			insertaRegistro(connection);
			selectRegistro(connection);
			actualizaRegistro(connection);
			borraRegistro(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public void insertaRegistro(Connection conn) throws SQLException {
		String sql = "INSERT INTO usuario (user,pass,nombre,correo) VALUES (?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "David");
		statement.setString(2, "passDavid");
		statement.setString(3, "David");
		statement.setString(4, "david@aytos.es");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado");
		}
	}

	public void borraRegistro(Connection conn) throws SQLException {
		String sql = "DELETE FROM usuario WHERE user = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "IVI");

		int rowsdeleted = statement.executeUpdate();
		if (rowsdeleted > 0) {
			System.out.println("Usuario eliminado");
		}
	}

	public void actualizaRegistro(Connection conn) throws SQLException {
		String sql = "UPDATE usuario set user=?, pass=?, correo=? WHERE nombre=?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "IVI");
		statement.setString(2, "IviPass");
		statement.setString(3, "Ivi@aytos.es");
		statement.setString(4, "David");

		int rowsActualizado = statement.executeUpdate();
		if (rowsActualizado > 0) {
			System.out.println("Usuario actualizado");
		}
	}

	public void selectRegistro(Connection conn) throws SQLException {
		String sql = "SELECT * FROM usuario";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String pass = result.getString(2);
			String name = result.getString(3);
			String correo = result.getString("correo");
			String user = result.getString("user");
			count++;
			System.out.println(
					count + "-- nombre: " + name + " correo: " + correo + " usuario: " + user + " contraseña: " + pass);
		}

	}

	public static void main(String[] args) {
		Prueba javaSQLBasic = new Prueba();
		javaSQLBasic.connectDatabase();

	}

}
