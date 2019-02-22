package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;



public class ConexionMySQL {

	private static ConexionMySQL instance;
	private static Connection con;
	private ConexionMySQL() {
		
	}
	
	public static ConexionMySQL getInstance() {
		if(instance==null) {
			instance = new ConexionMySQL();
		
		}
		
		return instance;
		
	}
	
	 public String intermedio(String query) throws SQLException, ClassNotFoundException {
		 System.out.println(query);
		 try {

				Class.forName("com.mysql.jdbc.Driver");
		 }catch(ClassNotFoundException e) {
			 
		 }
		Connection con = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_12","P09728_1_12", "a5oZPzlj");
		 String retorno = "";
		 try {
			 Statement stmt = con.createStatement();
				String[] queryArray=query.split(" ");
				if(queryArray[0].equals("SELECT")) {
					ResultSet rs = stmt.executeQuery(query);
					
					ResultSetMetaData rsmd = rs.getMetaData();
					int n = rsmd.getColumnCount();
					String impresion ="";
					while(rs.next()) {
					
					
						for (int i = 1; i <= n; i++) {
							if(i!=n)impresion += rs.getString(i)+",";
							else impresion+=rs.getString(i)+";";
						}
						
						retorno = impresion;
					}
				}else if(queryArray[0].equals("INSERT")||queryArray[0].equals("DELETE")||queryArray[0].equals("UPDATE")) {
					try {
							
							int rows =stmt.executeUpdate(query);
						
						if(rows>0) System.out.println("Se realizo correctamente");	
					}catch(Exception e) {
						retorno = "Por favor escriba una sentencia SQL valida";
						
					}
					
				}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("test");
		}
		 return retorno;
	 }
	
}

