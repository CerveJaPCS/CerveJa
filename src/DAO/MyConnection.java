package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {

	private static final String URL = "jdbc:mysql://labsoft.pcs.usp.br:3306/cerveja";
	private static final String USER = "cerveja";
	private static final String PASS = "cerveja";
	
	public static Connection getConnection() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASS);	
		}catch(Exception e){
			throw new SQLException("Erro ao abrir conex�o com o BD", e);
		}
	}
}
