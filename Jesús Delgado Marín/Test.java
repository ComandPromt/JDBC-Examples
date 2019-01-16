import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public void conectarDatabase() throws SQLException {
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
		System.out.println(valid ? "Test OK" : "Test Fail");

		// insertar
		Test.insertarRegistro(cn);
		Test.seleccionarRegistro(cn);
		Test.actualizaRegistro(cn);
		Test.borrarRegistro(cn);

		/*
		 * // Creamos el Statement para poder hacer consultas Statement st =
		 * cn.createStatement();
		 * 
		 * 
		 * 
		 * // La consulta es un String con código SQL String sql1 =
		 * "SELECT * FROM cuentas";
		 * 
		 * // Ejecuta una consulta que devuelve resultados ResultSet rs =
		 * st.executeQuery(sql1); while (rs.next()) { System.out.println (rs.getString
		 * ("propietario") + " " + rs.getString (2)+ " " + rs.getInt(saldo)); }
		 * 
		 * String sql2 = "UPDATE cuentas SET saldo = saldo - " + cantidad +
		 * " WHERE codigo = '" + codigo + "' AND saldo >= " + cantidad;
		 * 
		 * // Ejecuta una consulta de tipo insert, update o delete
		 * st.executeUpdate(sql2); /
		 */
	}

	public static void insertarRegistro(Connection conn) throws SQLException {
		String sql = "INSERT INTO usuario(user,pass,nombre,correo) values(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "paco1234");
		statement.setString(2, "passPaco");
		statement.setString(3, "Paco");
		statement.setString(4, "paco@ayto.es");

		int rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("Usuario insertado");
		}

	}

	public static void seleccionarRegistro(Connection conn) throws SQLException {
		String sql = "SELECT * FROM usuario";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String pass = result.getString(2);
			String nombre = result.getString(3);
			String email = result.getString(4);
			count++;
			System.out.println("Registros: " + nombre + " " + pass + " " + email);
		}

	}

	public static void actualizaRegistro(Connection conn) throws SQLException {
		String sql = "UPDATE usuario SET user=?,pass=?,correo=? WHERE nombre=?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "paco1234");
		statement.setString(2, "passPaco");
		statement.setString(3, "paco@aytos.es");
		statement.setString(4, "Juan");

		int rowsUpdated = statement.executeUpdate();

		if (rowsUpdated > 0) {
			System.out.println("Actualizado");
		}

	}

	public static void borrarRegistro(Connection conn) throws SQLException {
		String sql = "DELETE FROM usuario where user=?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "paco1234");

		int rowsDeleted = statement.executeUpdate();

		if (rowsDeleted > 0) {
			System.out.println("Borrado");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		try {
			test.conectarDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
