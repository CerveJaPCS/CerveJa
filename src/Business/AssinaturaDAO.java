package Business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import User.MyConnection;

public class AssinaturaDAO {
	
	private static AssinaturaDAO instance = new AssinaturaDAO();
	
	private String sql;
	
	private AssinaturaDAO(){}
	
	
	public static AssinaturaDAO getInstance(){
		return instance;
	}
	
	public Set<Pacote> getPacotes(int assinaturaID) throws SQLException{
		Set<Pacote> temp = null;
		Connection conn = MyConnection.getConnection();
		PreparedStatement getpack = null;
		try {
			sql = "SELECT nome, email, cpf, rg, dataNascimento, endereco, telefone "
					+ "FROM cerveja.usuario A INNER JOIN cerveja.user_info B "
					+ "ON A.userInfoID=B.userInfoID "
					+ "WHERE usuarioID = ?";
			getpack = conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if (getpack != null) {
				getpack.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
		return temp;
	}
	

}
