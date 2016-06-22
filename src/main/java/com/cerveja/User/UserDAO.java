package com.cerveja.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class UserDAO {
	
	private static UserDAO instance = new UserDAO();
	
	private String sql;
	
	private UserDAO(){}
	
	
	public static UserDAO getInstance(){
		return instance;
	}
	
	public Cliente getUserCliente(String email) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getuser = null;
		Cliente user = new Cliente();
		try{
			sql = "SELECT A.usuarioID, B.userInfoID, A.userTypeID, nome, email, senha, cpf, rg, dataNascimento, endereco, telefone "
					+ "FROM cerveja.usuario A INNER JOIN cerveja.user_info B "
					+ "ON A.userInfoID=B.userInfoID "
					+ "WHERE A.email = ?";
			getuser = conn.prepareStatement(sql);
			getuser.setString(1, email);
			ResultSet rs = getuser.executeQuery();
			while(rs.next()){
				LocalDate dob = rs.getDate("dataNascimento").toLocalDate();
				UserInfo info = new UserInfo(rs.getString("nome"), rs.getString("cpf"), rs.getString("rg"), dob, 
						rs.getString("endereco"), rs.getString("telefone"));
				user.setEmail(rs.getString("email"));
				user.setSenha(rs.getString("senha"));
				user.setUserID(rs.getInt("usuarioID"));
				user.setUserType(UserType.Cliente);
				user.setInfo(info);
				user.info.setUserInfoID(rs.getInt("userInfoID"));
			}
			return user;
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar pelo usuário de email " +email, e);
		}finally {
			if (getuser != null) {
				getuser.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public void updateUserCliente(String email, String endereco, String telefone) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement upuser = null;
		Cliente user = new Cliente();
		try{
			user = getUserCliente(email);
			sql = "UPDATE cerveja.user_info SET (endereco, telefone) "
					+ "VALUES(?, ?) "
					+ "WHERE userInfoID = ?";
			upuser = conn.prepareStatement(sql);
			upuser.setString(1, endereco);
			upuser.setString(2, telefone);
			upuser.setInt(3, user.info.getUserInfoID());
			upuser.executeUpdate();
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar pelo usuário de email " + email, e);
		}finally {
			if (upuser != null) {
				upuser.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public void mudarSenha(String email, String oldpass, String newpass) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement msenha = null;
		Cliente user = new Cliente();
		try{
			user = getUserCliente(email);
			if(user.getSenha().equals(makePasswordHash(oldpass))){
				sql = "UPDATE cerveja.usuario SET senha = ? "
						+ "WHERE email = ?";
				msenha = conn.prepareStatement(sql);
				msenha.setString(1, makePasswordHash(newpass));
				msenha.setString(2, email);
				msenha.executeUpdate();
			}
			else{
				System.out.println("Senha antiga inválida.");
			}
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar pelo usuário de email " + email, e);
		}finally {
			if (msenha != null) {
				msenha.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public boolean getHashKey(String email, String hashKey) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement ghashk = null;
		try{
			sql = "SELECT hashkey "
					+ "FROM cerveja.usuario "
					+ "WHERE email = ?";
			ghashk = conn.prepareStatement(sql);
			ghashk.setString(1, email);
			ResultSet rs = ghashk.executeQuery();
			while(rs.next()){
				if(rs.getString("hashkey").equals(hashKey)){
					return true;
				}
			}
			return false;
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar pelo usuário de email " + email, e);
		}finally {
			if (ghashk != null) {
				ghashk.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public void ativarConta(String email, String hashKey) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement aconta = null;
		Cliente user = new Cliente();
		try{
			user = getUserCliente(email);
			if(user != null && getHashKey(email, hashKey)){
				sql = "UPDATE cerveja.usuario SET ativo = ? "
						+ "WHERE email = ?";
				aconta = conn.prepareStatement(sql);
				aconta.setInt(1, 1);
				aconta.setString(2, email);
				aconta.executeUpdate();
			}
			else{
				System.out.println("Código de ativação inválido.");
			}
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar pelo usuário de email " + email, e);
		}finally {
			if (aconta != null) {
				aconta.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public boolean getUserAuth(String email, String senha) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getuserauth = null;
		try{
			sql = "SELECT email, senha FROM cerveja.usuario WHERE email = ?";
			getuserauth = conn.prepareStatement(sql);
			getuserauth.setString(1, email);
			ResultSet rs = getuserauth.executeQuery();
			if(rs != null && rs.next()){
				String hashedPsw = rs.getString("senha");
		        if (!hashedPsw.equals(makePasswordHash(senha))) {
		            return false;
		        }
		        else{
		        	return true;
		        }
			}
			else{
				System.out.println("Usuário e/ou senha inválida.");
				return false;
			}
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {

			if (getuserauth != null) {
				getuserauth.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public void insertUserInfo(String nome, String cpf, String rg, LocalDate datanasc, 
			String endereco, String telefone) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertuserinfo = null;
		try{
		
			Date dob = Date.valueOf(datanasc);
			if(getUserInfoID(cpf) == -1){
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
			else{
				System.out.println("Usuário já cadastrado.");
			}
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
		int infoid = -1;
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
	
	public boolean verifyUser(String email) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement vuser = null;
		try{
			sql = "SELECT userInfoID FROM cerveja.usuario WHERE email = ?";
			vuser = conn.prepareStatement(sql);
			vuser.setString(1, email);
			ResultSet rs = vuser.executeQuery();
			if(!rs.next()){
				return false;
			}
			return true;
		}
		catch(Exception e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {

			if (vuser != null) {
				vuser.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public boolean insertUser(String email, UserType tipo, String senha, int infoid) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertuser = null;
		
		String passwordHash = makePasswordHash(senha);

		try{
			if(!verifyUser(email)){
				sql = "INSERT INTO cerveja.usuario (userTypeID, email, senha, userInfoID) VALUES (?,?,?,?)";
				insertuser = conn.prepareStatement(sql);
				if(tipo.equals(UserType.Cliente)){
					insertuser.setInt(1, 1);
				}
				else{
					insertuser.setInt(1, 2);
				}
				insertuser.setString(2,email);
				insertuser.setString(3, passwordHash);
				insertuser.setInt(4, infoid);
				insertuser.executeUpdate();
				return true;
			}
			else{
				System.out.println("Usuário já cadastrado.");
				return false;
			}
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

	
	public void deleteUser(String email, int infoid) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement deluser = null;
		try{
		
			sql = "DELETE FROM cerveja.usuario WHERE email = ?";
			deluser = conn.prepareStatement(sql);
			deluser.setString(1, email);
			deluser.execute();
			deluser.close();
			System.out.println("Usuario deletado com sucesso...");
		}
		catch(Exception e){
			throw new SQLException("Erro ao deletar linha de teste.", e);
		}
		try{
			sql = "DELETE FROM cerveja.user_info WHERE userInfoID = ?";
			deluser = conn.prepareStatement(sql);
			deluser.setInt(1, infoid);
			deluser.execute();
			System.out.println("Informação de usuário deletado com sucesso...");
		}catch(Exception e){
			throw new SQLException("Erro ao deletar linha de teste.", e);
		}finally {
			if (deluser != null) {
				deluser.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}	
	


	public boolean validateSignup(String username, String password, String verify, String email,
            HashMap<String, String> errors) {
		String USER_RE = "^[a-zA-Z0-9_-]{3,20}$";
		String PASS_RE = "^.{3,20}$";
		String EMAIL_RE = "^[\\S]+@[\\S]+\\.[\\S]+$";
		
		errors.put("username_error", "");
		errors.put("password_error", "");
		errors.put("verify_error", "");
		errors.put("email_error", "");
		
		if (!username.matches(USER_RE)) {
			errors.put("username_error", "invalid username. try just letters and numbers");
			return false;
		}
		
		if (!password.matches(PASS_RE)) {
			errors.put("password_error", "invalid password.");
			return false;
		}
		
		
		if (!password.equals(verify)) {
			errors.put("verify_error", "password must match");
			return false;
		}
		
		if (!email.equals("")) {
			if (!email.matches(EMAIL_RE)) {
				errors.put("email_error", "Invalid Email Address");
				return false;
			}
		}
		
		return true;
	}
	
	public String makePasswordHash(String password) {
		String md5 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
    		md5 = new BigInteger(1, digest.digest()).toString(16);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        }
    }
	

}