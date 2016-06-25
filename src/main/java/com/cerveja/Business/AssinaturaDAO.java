package com.cerveja.Business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
import java.sql.Types;

import com.cerveja.User.*;

public class AssinaturaDAO {
	
	private static AssinaturaDAO instance = new AssinaturaDAO();
	
	private String sql;
	
	private AssinaturaDAO(){}
	
	
	public static AssinaturaDAO getInstance(){
		return instance;
	}

	public void atualizaEstado(int assinaturaID, EstadoAssinatura ea) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement upstate = null;
		try{
			sql = "UPDATE cerveja.assinatura SET cerveja.assinatura.estadoAssinaturaID = ? WHERE cerveja.assinatura.assinaturaID = ? "; 
			upstate = conn.prepareStatement(sql);
			upstate.setInt(1, ea.ordinal() + 1);
			upstate.setInt(2, assinaturaID);
			upstate.executeUpdate();
		}catch (SQLException e){
            throw new SQLException("Erro ao atualizar estado da assinatura", e);

		}finally {
			if (upstate != null) {
				upstate.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public int addAssinatura(int diaDebito) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement addassin = null;
		PreparedStatement getid = null;
		int assinaturaid = 0;

		try {
			sql = "INSERT INTO cerveja.assinatura "
					+ "(diaDebito, estadoAssinaturaID) "
					+ "VALUES (?, ?)";
			addassin = conn.prepareStatement(sql);
			addassin.setInt(1, diaDebito);
			addassin.setInt(2, 1);
			addassin.executeUpdate();
			addassin.close();
			
		} catch (SQLException e) {
            throw new SQLException("Erro ao inserir assinatura", e);
		}finally {
			if (addassin != null) {
				addassin.close();
			}
		}
		
		try{
			sql = "SELECT MAX(assinaturaID) AS lastID FROM cerveja.assinatura"; 
			getid = conn.prepareStatement(sql);
			ResultSet rs = getid.executeQuery();
			while(rs.next()){
				assinaturaid = rs.getInt("lastID");
				return assinaturaid;
			}
			return assinaturaid;
		}catch (SQLException e){
            throw new SQLException("Erro ao pegar ID da assinatura", e);

		}finally {
			if (getid != null) {
				getid.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public Assinatura getAssinatura(int assinaturaID) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement geta = null;
		Assinatura a = new Assinatura();
		try {
			sql = "SELECT assinaturaID, descricao AS estado , diaDebito "
					+ "FROM cerveja.assinatura A INNER JOIN cerveja.estados_assinaturas B "
					+ "WHERE A.assinaturaID = ? AND A.estadoAssinaturaID = B.estadoAssinaturaID";
			
			geta = conn.prepareStatement(sql);
			geta.setInt(1, assinaturaID);
			ResultSet rs = geta.executeQuery();
			while(rs.next()){
				a.setDiaDebito(rs.getInt("diaDebito"));
				a.setEstadoAssinatura(EstadoAssinatura.valueOf(rs.getString("estado")));
				a.setAssinaturaID(assinaturaID);
				return a;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if (geta != null) {
				geta.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
		return a;
	}
	
	public int getDiaDebito(int assinaturaID) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getdd = null;
		int dd = -1;
		try {
			sql = "SELECT diaDebito "
					+ "FROM cerveja.assinatura "
					+ "WHERE assinaturaID = ?";
			getdd = conn.prepareStatement(sql);
			getdd.setInt(1, assinaturaID);
			ResultSet rs = getdd.executeQuery();
			while(rs.next()){
				dd = rs.getInt("diaDebito");
				return dd;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if (getdd != null) {
				getdd.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
		return dd;
	}
	
	public EstadoAssinatura getEstadoAssinatura(int assinaturaID) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getea = null;
		EstadoAssinatura ea = null;
		try {
			sql = "SELECT descricao "
					+ "FROM cerveja.assinatura A, cerveja.estados_assinaturas B "
					+ "WHERE A.assinaturaID = ? AND A.estadoAssinaturaID=B.estadoAssinaturaID";
			getea = conn.prepareStatement(sql);
			getea.setInt(1, assinaturaID);
			ResultSet rs = getea.executeQuery();
			while(rs.next()){
				ea = EstadoAssinatura.valueOf(null, rs.getString("descricao"));
				return ea;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if (getea != null) {
				getea.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
		return ea;
	}
	
	public void deleteAssinatura(String email, int assinaturaID) throws SQLException {
        Connection conn = MyConnection.getConnection();
		PreparedStatement stmnt = null;
		PreparedStatement stmnt2 = null;

		try{
			sql = "DELETE FROM cerveja.assinatura WHERE assinaturaID = ?";
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1,assinaturaID);
			stmnt.execute();
		}
		catch(Exception e){
			throw new SQLException("Erro ao deletar assinatura " + assinaturaID, e);
		}
        finally {
			if (stmnt != null) stmnt.close();
		}
		try{
			sql = "UPDATE cerveja.usuario SET cerveja.usuario.assinaturaID = ?  WHERE email = ?";
			stmnt2 = conn.prepareStatement(sql);
			stmnt2.setNull(1, Types.INTEGER);;
			stmnt2.setString(2, email);
			stmnt2.execute();
		}
		catch(Exception e){
			throw new SQLException("Erro ao deletar assinatura " + assinaturaID, e);
		}
        finally {
			if (stmnt != null) stmnt.close();
			if (conn!= null) conn.close();
		}
    }
	

}
