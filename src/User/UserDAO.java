package User;

import java.sql.*;
import java.time.LocalDate;

public class UserDAO {
	
	private static UserDAO instance = new UserDAO();
	
	private String sql;
	
	private UserDAO(){}
	
	public static UserDAO getInstance(){
		return instance;
	}
	
	public void insertUserInfo(String nome, String cpf, String rg, LocalDate datanasc, 
			String endereco, String telefone) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertuserinfo = null;
		try{
		
			Date dob = Date.valueOf(datanasc);
			
			sql = "INSERT INTO cerveja.user_info (nome, cpf, rg, dataNascimento,endereco, telefone) VALUES (?,?,?,?,?,?)";
			insertuserinfo = conn.prepareStatement(sql);
			insertuserinfo.setString(1, nome);
			insertuserinfo.setString(2, cpf);
			insertuserinfo.setString(3, rg);
			insertuserinfo.setDate(4, dob);
			insertuserinfo.setString(5, endereco);
			insertuserinfo.setString(6, telefone);
			insertuserinfo.executeUpdate();
		}
		catch(Exception e){
			throw new SQLException("Erro ao inserir linha de teste.", e);
		}finally {

			if (insertuserinfo != null) {
				insertuserinfo.close();
			}

			if (conn!= null) {
				conn.close();
			}

		}
	}	
	
	public int getUserInfoID(String cpf) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement getuserinfo = null;
		int infoid = 0;
		try{
			sql = "SELECT userInfoID FROM cerveja.user_info WHERE cpf = ?";
			getuserinfo = conn.prepareStatement(sql);
			getuserinfo.setString(1, cpf);
			ResultSet rs = getuserinfo.executeQuery();
			while(rs.next()){
				infoid = rs.getInt("userInfoID");
			}
			return infoid;
		}
		catch(Exception e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {

			if (getuserinfo != null) {
				getuserinfo.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public void insertUser(String email, UserType tipo, String senha, int infoid) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertuser = null;
		try{
			
			sql = "INSERT INTO cerveja.usuario (userTypeID, email, senha, userInfoID) VALUES (?,?,?,?)";
			insertuser = conn.prepareStatement(sql);
			if(tipo.equals(UserType.Cliente)){
				insertuser.setInt(1, 1);
			}
			else{
				insertuser.setInt(1, 2);
			}
			insertuser.setString(2,email);
			insertuser.setString(3, senha);
			insertuser.setInt(4, infoid);
			insertuser.executeUpdate();
		}
		catch(Exception e){
			throw new SQLException("Erro ao inserir linha de teste.", e);
		}finally {

			if (insertuser != null) {
				insertuser.close();
			}

			if (conn!= null) {
				conn.close();
			}

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