import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CocheJDBC {

	public Connection connectDatabase() {
		Connection connection = null;
		try {
			try {
				// Se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrare el driver de MySQL:" + ex);
			}

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coche", "root", "");

			boolean valid = connection.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
		return connection;

	}

	public void insertaRegistro(Connection conn, String matricula, String marca, String color, int caballos,
			float precio, boolean vendido) throws SQLException {
		String sql = "INSERT INTO coche (matricula,marca,color,caballos,precio,vendido) VALUES('" + matricula + "','"
				+ marca + "','" + color + "'," + caballos + "," + precio + "," + vendido + ")";
		PreparedStatement statement = conn.prepareStatement(sql);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche insertado");
		}
	}

	public void borraRegistro(Connection conn, String matricula) throws SQLException {
		String sql = "DELETE FROM coche WHERE matricula = '" + matricula + "'";
		PreparedStatement statement = conn.prepareStatement(sql);

		int rowsdeleted = statement.executeUpdate();
		if (rowsdeleted > 0) {
			System.out.println("Usuario eliminado");
		}
	}

	public void actualizaRegistro(Connection conn, String marca, String color, int caballos, float precio,
			boolean vendido, String matricula) throws SQLException {
		String sql = "UPDATE coche set marca= '" + marca + "', color= '" + color + "',caballos =" + caballos
				+ ",precio =" + precio + ",vendido =" + vendido + " WHERE matricula='" + matricula + "'";
		PreparedStatement statement = conn.prepareStatement(sql);

		int rowsUpdate = statement.executeUpdate();
		if (rowsUpdate > 0) {
			System.out.println("Coche actualizado");
		}
	}

	public void compraCoche(Connection conn, String matricula) throws SQLException {

		String sql = "UPDATE coche set vendido= true WHERE matricula='" + matricula + "'";
		PreparedStatement statement = conn.prepareStatement(sql);

		int rowsUpdate = statement.executeUpdate();
		if (rowsUpdate > 0) {
			System.out.println("Coche vendido");
		}
	}

	public void registroCliente(Connection conn, int id, String nombre, String apellido, String direccion,
			String idCoche) throws SQLException {

		String sql = "INSERT INTO cliente (id,nombre,apellidos,direccion,idCoche) VALUES('" + id + "','" + nombre
				+ "','" + apellido + "','" + direccion + "','" + idCoche + "')";
		PreparedStatement statement = conn.prepareStatement(sql);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Cliente insertado insertado");
		}
	}

	public void selectRegistro(Connection conn) throws SQLException {
		String sql = "SELECT * FROM coche WHERE vendido=false";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;
		String lista = "";

		while (result.next()) {
			String matricula = result.getString("matricula");
			String marca = result.getString("marca");
			String color = result.getString("color");
			int caballos = result.getInt("caballos");
			float precio = result.getFloat("precio");
			boolean vendido = result.getBoolean("vendido");
			count++;
			lista += count + "-- " + matricula + ", " + marca + ", " + color + ", " + caballos + ", " + precio + ", "
					+ vendido + "\n";

		}

		JOptionPane.showMessageDialog(null, lista);
	}

	public static void main(String[] args) throws SQLException {

		CocheJDBC javaSQLBasic = new CocheJDBC();
		javaSQLBasic.connectDatabase();
		Connection conn = javaSQLBasic.connectDatabase();

		String[] opciones = { "No", "Si" };
		String[] opcionesMenu = { "Inserta un nuevo coche", "Actualiza un coche existente", "Ver la lista de coches",
				"Borrar un coche" };

		Coche coche;
		String matricula;
		String marca;
		String color;
		float precio;
		int caballos;
		boolean vendido;

		// Cliente cliente;
		int id = 1;
		String nombre;
		String apellido;
		String direccion;

		String precio1;
		String caballos1;
		int vendido1;

		int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción del menú", null,
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesMenu, opcionesMenu[0]);

		switch (opcion) {
		case 0:
			matricula = JOptionPane.showInputDialog("Introduzca la matricula");
			marca = JOptionPane.showInputDialog("Introduzca la marca");
			color = JOptionPane.showInputDialog("Introduzca el color");
			caballos1 = JOptionPane.showInputDialog("Introduzca los caballos");
			caballos = Integer.parseInt(caballos1);
			precio1 = JOptionPane.showInputDialog("Introduzca el precio");
			precio = Float.parseFloat(precio1);
			vendido1 = JOptionPane.showOptionDialog(null, "Introduzca si tiene vendido o no", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			if (vendido1 == 0)
				vendido = false;
			else
				vendido = true;

			coche = new Coche(matricula, marca, color, caballos, precio, vendido);
			javaSQLBasic.insertaRegistro(conn, coche.getMatricula(), coche.getMarca(), coche.getColor(),
					coche.getCaballos(), coche.getPrecio(), coche.isVendido());

			break;

		case 1:
			matricula = JOptionPane.showInputDialog("Introduzca la matricula del coche a modificar");
			marca = JOptionPane.showInputDialog("Introduzca la marca");
			color = JOptionPane.showInputDialog("Introduzca el color");
			caballos1 = JOptionPane.showInputDialog("Introduzca los caballos");
			caballos = Integer.parseInt(caballos1);
			precio1 = JOptionPane.showInputDialog("Introduzca el precio");
			precio = Float.parseFloat(precio1);
			vendido1 = JOptionPane.showOptionDialog(null, "Introduzca si tiene vendido o no", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			if (vendido1 == 0)
				vendido = false;
			else
				vendido = true;

			coche = new Coche(matricula, marca, color, caballos, precio, vendido);
			javaSQLBasic.actualizaRegistro(conn, coche.getMarca(), coche.getColor(), coche.getCaballos(),
					coche.getPrecio(), coche.isVendido(), coche.getMatricula());

			break;

		case 2:

			javaSQLBasic.selectRegistro(conn);
			int elegido = JOptionPane.showOptionDialog(null, "¿Quiere comprar alguno?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

			if (elegido == 1) {
				nombre = JOptionPane.showInputDialog("Introduzca su nombre");
				apellido = JOptionPane.showInputDialog("Introduzca sus apellidos");
				direccion = JOptionPane.showInputDialog("Introduzca su direccion");
				matricula = JOptionPane.showInputDialog("Introduzca la matricula del coche a comprar");
				javaSQLBasic.registroCliente(conn, id, nombre, apellido, direccion, matricula);
				id++;
				javaSQLBasic.compraCoche(conn, matricula);
				break;
			} else {
				break;
			}

		case 3:
			matricula = JOptionPane.showInputDialog("Introduzca la matricula del coche a borrar");

			javaSQLBasic.borraRegistro(conn, matricula);

			break;

		}

	}

}
