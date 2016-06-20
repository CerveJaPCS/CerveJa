package com.cerveja.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cerveja.User.*;

public class FornecedorDAO {
	
	private String nomeFornecedor;
	private String cnpj;
	private String telefone;
	private ListaPedido pedido;
	
	private static FornecedorDAO instance = new FornecedorDAO();

	
	public FornecedorDAO() {
	}

	private String sql ;
	
	public static FornecedorDAO getInstance(){
		return instance;
	}
	
	public void insertFornecedor(String nome, String cnpj, String telefone) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertuserinfo = null;
		try{
					
			sql = "INSERT INTO cerveja.fornecedor (nome, cnpj, telefone) VALUES (?,?,?)";
			insertuserinfo = conn.prepareStatement(sql);
			insertuserinfo.setString(1, nome);
			insertuserinfo.setString(2, cnpj);
			insertuserinfo.setString(3, telefone);
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
	
	public List<String> getFornecedor(int fornecedorID) throws SQLException{
		List<String> lst = new ArrayList<String>();
		Connection conn = MyConnection.getConnection();
		PreparedStatement getuser = null;
		try{
			sql = "SELECT nome, cnpj, telefone"
					+ "FROM cerveja.fornecedor" ;
			getuser = conn.prepareStatement(sql);
			getuser.setInt(1, fornecedorID);
			ResultSet rs = getuser.executeQuery();
			while(rs.next()){
				lst.add(rs.getString("nome"));
				lst.add(rs.getString("cnpj"));
				lst.add(rs.getString("telefone"));

			}
			return lst;
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {

			if (getuser != null) {
				getuser.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public List<String> getFornecedor(String cnpj) throws SQLException{
		List<String> lst = new ArrayList<String>();
		Connection conn = MyConnection.getConnection();
		PreparedStatement getuser = null;
		try{
			sql = "SELECT nome, cnpj, telefone"
					+ "FROM cerveja.fornecedor" ;
			getuser = conn.prepareStatement(sql);
			getuser.setString(3, cnpj);
			ResultSet rs = getuser.executeQuery();
			while(rs.next()){
				lst.add(rs.getString("nome"));
				lst.add(rs.getString("cnpj"));
				lst.add(rs.getString("telefone"));

			}
			return lst;
		}
		catch(SQLException e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {

			if (getuser != null) {
				getuser.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	
	
	public void deleteFornecedor(String cnpj) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement deluser = null;
		try{
		
			sql = "DELETE FROM cerveja.fornecedor WHERE cnpj = ?";
			deluser = conn.prepareStatement(sql);
			deluser.setString(3, cnpj);
			deluser.execute();
			deluser.close();
		}
		catch(Exception e){
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
	
	
	public void deleteFornecedor(int id) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement deluser = null;
		try{
		
			sql = "DELETE FROM cerveja.fornecedor WHERE cnpj = ?";
			deluser = conn.prepareStatement(sql);
			deluser.setInt(1, id);
			deluser.execute();
			deluser.close();
		}
		catch(Exception e){
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
	
	
	
	public void printTestTable() throws SQLException {
		try{
			Connection conn = MyConnection.getConnection();
			Statement stmnt = null;
			ResultSet rs = null;
			
			sql = "SELECT * FROM cerveja.fornecedor ";
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(sql);
			while(rs != null && rs.next()){
				int i = rs.getInt("fornecedorID");
				String name = rs.getString("nome");
				String cnpj = rs.getString("cnpj");
				String telefone = rs.getString("telefone");
				System.out.println(i + ": " + name + ", " + cnpj + ", " + telefone);
			}
			rs.close();
			stmnt.close();
			conn.close();
		}
		catch(Exception e){
			throw new SQLException("Erro ao imprimir teste" ,e);
		}}
		
	
	
}
