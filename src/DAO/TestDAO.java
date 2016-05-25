package DAO;

import java.sql.*;
import java.util.Random;

public class TestDAO {
	
	private static TestDAO instance = new TestDAO();
	
	private String sql;
	
	private TestDAO(){}
	
	public static TestDAO getInstance(){
		return instance;
	}
	
	
	
	public void insert(String name) throws SQLException {
		try{
			Connection conn = MyConnection.getConnection();
			Statement stmnt = null;
			
			Random rnd = new Random();
			int age = rnd.nextInt(20) + 10;
			sql = "INSERT INTO cerveja.tb_teste (Name, Age) VALUES ('" + name + "', " + age + ")";
			stmnt = conn.createStatement();
			stmnt.execute(sql);
			stmnt.close();
			conn.close();
		}
		catch(Exception e){
			throw new SQLException("Erro ao inserir linha de teste: " + name, e);
		}
	}

	public void printTestTable() throws SQLException {
		try{
			Connection conn = MyConnection.getConnection();
			Statement stmnt = null;
			ResultSet rs = null;
			
			sql = "SELECT * FROM cerveja.tb_teste ";
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(sql);
			while(rs != null && rs.next()){
				int i = rs.getInt("Id");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				System.out.println(i + ": " + name + ", " + age);
			}
			rs.close();
			stmnt.close();
			conn.close();
		}
		catch(Exception e){
			throw new SQLException("Erro ao imprimir teste" ,e);
		}
		
	}

}
