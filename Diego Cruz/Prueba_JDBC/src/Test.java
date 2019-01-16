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
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
			boolean valid = connection.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			insertar(connection);
			actualizar(connection);
			select(connection);
			borrar(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public static void main(String[] args) {
		Test javaMySQLBasic = new Test();
		javaMySQLBasic.connectDatabase();
	}

	public static void insertar(Connection con) throws SQLException {

		String sql = "INSERT INTO usuario (user, pass, nombre, correo) VALUES (?, ?, ?, ?)";

		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, "3ax");
		stat.setString(2, "3axPass");
		stat.setString(3, "Bax");
		stat.setString(4, "3ax@correo.es");

		int rowsInserted = stat.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado!");
		}
	}

	public static void actualizar(Connection con) throws SQLException {

		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre=?";

		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, "3ax2");
		stat.setString(2, "3axPass2");
		stat.setString(3, "3ax2@correo.es");
		stat.setString(4, "Bax");

		int rowsInserted = stat.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario actualizado!");
		}
	}

	public static void borrar(Connection con) throws SQLException {

		String sql = "DELETE FROM usuario WHERE nombre=?";

		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, "Bax");

		int rowsInserted = stat.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println(rowsInserted + " Usuario/s borrado/s!");
		}
	}

	public void select(Connection con) throws SQLException {
		String sql = "SELECT * FROM usuario";

		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(sql);

		// int count = 0;

		while (result.next()) {
			String pass = result.getString(2);
			String nombre = result.getString("nombre");
			System.out.println("Nombre: " + nombre + "\nPass: " + pass + "\n");
		}
	}

}
