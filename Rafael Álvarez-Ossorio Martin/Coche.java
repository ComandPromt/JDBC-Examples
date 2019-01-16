package prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class Coche {
	
	public String marca;
	public String modelo;
	public String color;
	public Integer bastidor;
	public Date matriculacion;
	
	

	public Coche(String marca, String modelo, String color, Integer bastidor, Date matriculacion) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.bastidor = bastidor;
		this.matriculacion = matriculacion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getBastidor() {
		return bastidor;
	}

	public void setBastidor(Integer bastidor) {
		this.bastidor = bastidor;
	}

	public Date getMatriculacion() {
		return matriculacion;
	}

	public void setMatriculacion(Date matriculacion) {
		this.matriculacion = matriculacion;
	}
	
	private static java.sql.Timestamp getCurrentTimeStamp() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Coche coche = null;
		
		try {
			try {
			    Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
			    System.out.println("No se encontro el Driver MySQL para JDBC.");
			}
	
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
			
			boolean valid = cn.isValid(5000);
			
			System.out.println(valid ? "TEST OK" :  "TEST FAIL");
			
			boolean salir = false;
		       int opcion; 
		        
		       while(!salir){
		            
		           System.out.println("1. Crear Coche");
		           System.out.println("2. Actualizar Coche");
		           System.out.println("3. Borrar Coche");
		           System.out.println("5. Mostrar Coche");
		           System.out.println("4. Salir");
		            
		           System.out.println("Escribe una de las opciones");
		           opcion = sc.nextInt();
		            
		           switch(opcion){
		               case 1:
		                   System.out.println("Introduce la marca");
		                   String marca = sc.next();
		                   System.out.println("Introduce la modelo");
		                   String modelo = sc.next();
		                   System.out.println("Introduce la color");
		                   String color = sc.next();
		                   System.out.println("Introduce el numero bastidor");
		                   int bastidor = sc.nextInt();
		                   
		                   coche = new Coche(marca, modelo, color, bastidor, getCurrentTimeStamp());
		                   
		                   coche.insertarRegistro(cn);
		                   break;
		               case 2:
		            	   
		                   System.out.println("Introduce el numero bastidor del coche a actualizar");
		                   int bastidorA = sc.nextInt();
		            	   
		                   System.out.println("Introduce la marca");
		                   String marcaA = sc.next();
		                   System.out.println("Introduce la modelo");
		                   String modeloA = sc.next();
		                   System.out.println("Introduce la color");
		                   String colorA = sc.next();

		                   Coche coche2 = new Coche(marcaA, modeloA, colorA, bastidorA, getCurrentTimeStamp());
		                   coche2.actualizarRegistro(cn);
		                   
		                   break;
		                case 3:
		                   System.out.println("Itroduce el numero de bastidor para borrar el coche");
		                   
		                   coche.actualizarRegistro(cn);
		                   break;
		                case 4:
		                   coche.selectRegistro(cn);
		                   break;		                   
		                case 5:
		                   salir=true;
		                   break;
		                default:
		                   System.out.println("Solo números entre 1 y 5");
		           }
		            
		       }
			
			
			
			//actualizarRegistro(cn);
			//borrarRegistro(cn);
			//selectRegistro(cn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	
	public void insertarRegistro(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO coche(marca,modelo,color,bastidor,matriculacion) VALUES(?,?,?,?,?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, this.getMarca());
		statement.setString(2, this.getModelo());
		statement.setString(3, this.getColor());
		statement.setInt(4, this.getBastidor());
		statement.setTimestamp(5, (Timestamp) this.getMatriculacion());
	
		int rowInserted = statement.executeUpdate();
		
		if(rowInserted > 0)
		{
			System.out.println("Usuario Insertado");
		}
		
	}
	
	public void actualizarRegistro(Connection conn) throws SQLException
	{
		String sql = "UPDATE coche SET marca=?, modelo=?, color=? matriculacion=?, WHERE bastidor=?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, this.getMarca());
		statement.setString(2, this.getModelo());
		statement.setString(3, this.getColor());
		statement.setInt(4, this.getBastidor());
		statement.setTimestamp(5, (Timestamp) this.getMatriculacion());

	
		int rowUpdate = statement.executeUpdate();
		
		if(rowUpdate > 0)
		{
			System.out.println("Actualizada");
		}
	}
	
	public void borrarRegistro(Connection conn) throws SQLException
	{
		String sql = "DELETE FROM coche WHERE bastidor=?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setLong(1, this.getBastidor());
		
		int rowDelete= statement.executeUpdate();
		
		if(rowDelete > 0)
		{
			System.out.println("Borrada");
		}
	}
	
	public void selectRegistro(Connection conn) throws SQLException
	{
		String sql = "Select * from coche";
		
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()) 
		{
			String marca = result.getString(1);
			String modelo = result.getString(2);
			String color = result.getString(3);
			Integer bastidor = result.getInt(4);
			String matriculacion = result.getString(5);
			
			System.out.println(String.format("--",marca,modelo,color,bastidor,matriculacion));
		}
	}

}
