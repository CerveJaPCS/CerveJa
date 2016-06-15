package Business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import User.MyConnection;

public class AssinaturaDAO {
	
	private static AssinaturaDAO instance = new AssinaturaDAO();
	
	private String sql;
	
	private AssinaturaDAO(){}
	
	
	public static AssinaturaDAO getInstance(){
		return instance;
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
				ea = EstadoAssinatura.valueOf(rs.getString("descricao"));
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
	
//	public Set<Pacote> getPacotes(int assinaturaID) throws SQLException{
//	    Set<Pacote> temp = new HashSet<Pacote>();
//		Connection conn = MyConnection.getConnection();
//		PreparedStatement getpack = null;
//		 
//		try {
//			sql = "SELECT A.assinaturaID, pacoteID, periodicidade, dataCriacao, quantidade, validade "
//					+ "FROM cerveja.assinatura A, cerveja.pacote B "
//					+ "WHERE A.assinaturaID = ? AND A.assinaturaID = B.assinaturaID";
//			getpack = conn.prepareStatement(sql);
//			getpack.setInt(1, assinaturaID);
//			ResultSet rs = getpack.executeQuery();
//			while(rs.next()){
//				Pacote p = new Pacote();
//				rs.getInt("assinaturaID");
//				LocalDate dc = rs.getDate("dataCriacao").toLocalDate();
//				p.setPacoteID(rs.getInt("pacoteID"));
//				p.setPeriodicidade(rs.getString("periodicidade"));					
//				p.setValidity(rs.getBoolean("validade"));
//				p.setCreateDate(dc);				
//				p.setQuantidade(rs.getInt("quantidade"));
//				temp.add(p);
//			}
//			return temp;
//			
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}finally {
//			if (getpack != null) {
//				getpack.close();
//			}
//
//			if (conn!= null) {
//				conn.close();
//			}
//		}
//		return temp;
//	}
	

}
