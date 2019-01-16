import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

public class CocheTest {

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
			menu(connection);
			/*
			 * insertar(connection); actualizar(connection); select(connection);
			 * borrar(connection);
			 */

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public static void main(String[] args) {

		CocheTest javaMySQLBasic = new CocheTest();
		javaMySQLBasic.connectDatabase();

	}

	public static void menu(Connection con) throws SQLException {
		String opcion = "5";
		do {
			opcion = (String) JOptionPane.showInputDialog(
					"Seleccione una opción\n0. Ver coches\n1.Insertar\n2.Cambiar\n3.Borrar\n4.Salir\n6.Comprar");
			if (opcion == null) {
				opcion = "4";
			}
			switch (opcion) {
			case "0":
				select(con);
				opcion = "4";
				break;
			case "1":
				insert(con);
				opcion = "4";
				break;
			case "2":
				update(con);
				opcion = "4";
				break;
			case "3":
				delete(con);
				opcion = "4";
				break;
			case "4":
				break;
			case "6":
				comprar(con);
				break;
			default:
				opcion = "5";
				JOptionPane.showMessageDialog(null, "Esa opción no es correcta");
				break;
			}
		} while (!opcion.equals("4"));
	}

	public static void select(Connection con) throws SQLException {
		String sql = "SELECT * FROM Coche";

		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(sql);

		// int count = 0;

		while (result.next()) {
			String matricula = result.getString(1);
			int serie = result.getInt(2);
			double precio = result.getDouble(3);
			Timestamp fecha = result.getTimestamp(4);
			boolean stock = result.getBoolean(5);
			JOptionPane.showMessageDialog(null, "Matricula: " + matricula + "\nSerie: " + serie + "\nPrecio: " + precio
					+ "\nFecha de fabricación: " + fecha + "\nEn stock: " + (stock ? "Si" : "No"));
		}
		menu(con);
	}

	public static void insert(Connection con) throws SQLException {

		String matricula = (String) JOptionPane.showInputDialog("Introduzca la matrícula");
		int serie = Integer.parseInt((String) JOptionPane.showInputDialog("Introduzca el número de serie"));
		double precio = Double.parseDouble((String) JOptionPane.showInputDialog("Introduzca el precio"));
		Timestamp fecha = new Timestamp(new Date().getTime());
		boolean stock = true;

		String sql = "INSERT INTO coche (matricula, serie, precio, fabricacion, enStock) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, matricula);
		stat.setInt(2, serie);
		stat.setDouble(3, precio);
		stat.setTimestamp(4, fecha);
		stat.setBoolean(5, stock);

		int rowsInserted = stat.executeUpdate();
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "Coche insertado!");
		}
		menu(con);
	}

	public static void delete(Connection con) throws SQLException {

		String matricula = (String) JOptionPane.showInputDialog("Introduzca la matrícula");

		String sql = "DELETE FROM coche WHERE matricula = ?";

		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, matricula);

		int rowsInserted = stat.executeUpdate();
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "Coche borrado!");
		}
		menu(con);

	}

	public static void update(Connection con) throws SQLException {

		String matricula = (String) JOptionPane.showInputDialog("Introduzca la matrícula a cambiar");
		String matricula2 = (String) JOptionPane.showInputDialog("Introduzca la matrícula nueva");

		String sql = "UPDATE coche SET matricula = ? WHERE matricula = ?";

		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, matricula2);
		stat.setString(2, matricula);

		int rowsInserted = stat.executeUpdate();
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "Matrícula cambiada!");
		}
		menu(con);

	}

	public static void comprar(Connection con) throws SQLException {
		int coche = 0;
		String coche1 = (String) JOptionPane.showInputDialog(lista(con));
		if (coche1 != null) {
			coche = Integer.parseInt(coche1);
			comprar2(con, coche);
		} else {
			JOptionPane.showMessageDialog(null, "Esa serie no es correcta");
			menu(con);
		}
	}

	public static void comprar2(Connection con, int coche) throws SQLException {
		String sql = "UPDATE coche SET enStock = 0 WHERE serie = ?";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setInt(1, coche);
		stat.executeUpdate();

		String nombre = (String) JOptionPane.showInputDialog("Introduzca el nombre del cliente");
		String apellidos = (String) JOptionPane.showInputDialog("Introduzca los apellidos del cliente");
		String direccion = (String) JOptionPane.showInputDialog("Introduzca la dirección cliente");
		String sql2 = "INSERT INTO cliente (nombre,apellidos,direccion,id,serieCoche) VALUES (?,?,?,?,?)";

		PreparedStatement stat2 = con.prepareStatement(sql2);
		stat2.setString(1, nombre);
		stat2.setString(2, apellidos);
		stat2.setString(3, direccion);
		stat2.setInt(4, id(con));
		stat2.setInt(5, coche);
		stat2.executeUpdate();
		menu(con);
	}

	public static String lista(Connection con) throws SQLException {
		String lista = "COCHES EN STOCK";

		String sql = "SELECT serie FROM Coche WHERE enStock = 1";

		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(sql);

		int count = 1;

		while (result.next()) {
			int matricula = result.getInt(1);
			lista += "\n" + count + " - " + matricula;
			count++;
		}
		lista += "\n\nSeleccione la serie del coche a comprar";
		return lista;
	}

	public static int id(Connection con) throws SQLException {
		int id = 0;
		String sql = "SELECT MAX(id) FROM cliente";
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(sql);
		result.next();
		int id2 = result.getInt(1);
		if (id2 != 0) {
			id = id2;
		}
		return id;
	}

}
