import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CocheCrud {

	public void conexionBD() {

		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection conexion = null;

			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			boolean esValido = conexion.isValid(50000);
			System.out.println(esValido ? "TEST OK" : "TEST FAIL");

			seleccionarOpcion(conexion);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}

	}

	public void insertaRegistro(Connection conex) throws SQLException {
		String marca;
		boolean aa;
		int id;
		double precio;

		Scanner s = new Scanner(System.in);

		System.out.println("Insertar el n�mero de coche: ");
		id = s.nextInt();
		System.out.println("Insertar la marca: ");
		marca = s.next();
		System.out.println("�Tiene aire acondicionado?: ");
		aa = s.nextBoolean();
		System.out.println("Insertar el precio: ");
		precio = s.nextDouble();
//		System.out.println("Disponible??: ");
//		boolean disponible = s.nextBoolean();

		Coche coche = new Coche(marca, aa, id, precio, true);

		String sql = "INSERT INTO coche (id, marca, aa, precio, disponible) VALUES (?,?,?,?,?)";

		PreparedStatement statment = conex.prepareStatement(sql);
		statment.setInt(1, coche.getId());
		statment.setString(2, coche.getMarca());
		statment.setBoolean(3, coche.isAa());
		statment.setDouble(4, coche.getPrecio());
		statment.setBoolean(5, coche.isDisponible());

		int rowsInserted = statment.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche insertado");
		}

	}

	public void actualizaRegistro(Connection conex) throws SQLException {
		int id;
		double precio;

		Scanner s = new Scanner(System.in);

		System.out.println("Indique el n�mero de coche");
		id = s.nextInt();
		System.out.println("Introducir el nuevo precio:");
		precio = s.nextDouble();

		String sql = "UPDATE coche SET precio=? WHERE id=?";

		PreparedStatement statment = conex.prepareStatement(sql);
		statment.setDouble(1, precio);
		statment.setInt(2, id);

		int rowsUpdated = statment.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Actualizado el coche.");
		}

	}

	public void borraRegistro(Connection conex) throws SQLException {

		int id;

		Scanner s = new Scanner(System.in);

		System.out.println("�Que n�mero de coche quiere eliminar?");
		id = s.nextInt();

		String sql = "DELETE FROM coche WHERE id=?";

		PreparedStatement statment = conex.prepareStatement(sql);
		statment.setInt(1, id);

		int rowsDeleted = statment.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Coche eliminado.");
		}

	}

	public void selectRegistros(Connection conex) throws SQLException {

		// creamos un objeto coche para luego settearle los valores que recoge el result
		// luego para mostrar los datos podemos imprimir el toString o bien
		// imprimir los getter
		Coche coche = new Coche();

		String sql = "SELECT * FROM coche";

		Statement statment = conex.createStatement();
		ResultSet result = statment.executeQuery(sql);

		while (result.next()) {
			int id = result.getInt(1);
			String marca = result.getString(2);
			boolean aa = result.getBoolean(3);
			double precio = result.getDouble(4);
			boolean disponible = result.getBoolean(5);

			coche.setId(id);
			coche.setMarca(marca);
			coche.setAa(aa);
			coche.setPrecio(precio);
			coche.setDisponible(disponible);

			// se podr�a imprimir tambi�n llamando a los getter
			// System.out.println(coche.getMarca());
			// System.out.println(coche.isAa());

			System.out.println(coche.toString());

		}

	}

	public void compraCoche(Connection conex) throws SQLException {

		try {
			selectRegistros(conex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// id nombre apellido direccion idCoche

		int idCoche;
		String nombre;
		String apellido;
		String direccion;

		Scanner s = new Scanner(System.in);
		System.out.println("Elige el n�mero de coche que desea comprar: ");
		idCoche = s.nextInt();

		System.out.println("Introduzca el nombre del cliente: ");
		nombre = s.next();
		System.out.println("Introduzca el primer apellido del cliente: ");
		apellido = s.next();
		System.out.println("Introduzca la direcci�n del cliente: ");
		direccion = s.next();

		// Insertamos en la tabla clientes

		String sql = "INSERT INTO clientes (nombre, apellido, direccion, idCoche) VALUES (?,?,?,?)";

		PreparedStatement statment = conex.prepareStatement(sql);
		statment.setString(1, nombre);
		statment.setString(2, apellido);
		statment.setString(3, direccion);
		statment.setInt(4, idCoche);

		int rowsInserted = statment.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Cliente creado.");
		}

		// modificamos de la tabla coche para indicar que no est� disponible
		sql = "UPDATE coche SET disponible=? WHERE id=?";
		
		statment = conex.prepareStatement(sql);
		statment.setBoolean(1, false);
		statment.setInt(2, idCoche);

		int rowsUpdated = statment.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Coche vendido.");
		}

		// mostramos la lista filtrada para que aparezcan los coches disponibles solo
		statment.close();
		Coche coche = new Coche();
		
		sql = "SELECT * FROM coche WHERE disponible=true";
		Statement statment2 = conex.createStatement();
		//statment.setBoolean(1, false);
		
		ResultSet result = statment2.executeQuery(sql);
		
		

		while (result.next()) {
			int id = result.getInt("id");
			String marca = result.getString("marca");
			boolean aa = result.getBoolean("aa");
			double precio = result.getDouble("precio");
			boolean disponible = result.getBoolean("disponible");
			
			coche.setId(id);
			coche.setMarca(marca);
			coche.setAa(aa);
			coche.setPrecio(precio);
			coche.setDisponible(disponible);
			
			System.out.println("========================");
			System.out.println("  COCHES DISPONIBLES");
			System.out.println(coche.toString());
			
		}

	}

	public void seleccionarOpcion(Connection conexion) {

		int opcion;

		Scanner s = new Scanner(System.in);

		do {
			System.out.println("========================");
			System.out.println("        MEN�");
			System.out.println(
					"1. Insertar un coche \n2. Actualizar un coche \n3. Eliminar un coche \n4. Mostrar lista de coches \n5. Comprar coche. \n6. Salir.");

			System.out.println("Elige una opcion: ");

			opcion = s.nextInt();

			switch (opcion) {

			case 1:

				try {
					insertaRegistro(conexion);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:

				try {
					actualizaRegistro(conexion);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:

				try {
					borraRegistro(conexion);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:

				try {
					selectRegistros(conexion);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					compraCoche(conexion);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("Hasta luego.");
			}
		} while (opcion != 6);

	}

	public static void main(String[] args) {

		CocheCrud javaMySQLBasic = new CocheCrud();
		javaMySQLBasic.conexionBD();

	}

}
