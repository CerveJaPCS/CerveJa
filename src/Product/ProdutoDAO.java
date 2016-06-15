package Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import User.MyConnection;

public class ProdutoDAO {
	
	private static ProdutoDAO instance = new ProdutoDAO();
	
	private String sql;
	
	private ProdutoDAO(){}
	
	public static ProdutoDAO getInstance(){
		return instance;
	}
	
	public void insertProduto(String nomeProduto, double price, int volume,	
			boolean disponibilidade, int estoque) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertproduto = null;
		try{
		
			if(getProductID(nomeProduto) == -1){	// ??????????
				sql = "INSERT INTO cerveja.produto (nomeProduto, price, volume, "
						+ "disponibilidade, estoque) VALUES (?,?,?,?,?)";
				insertproduto = conn.prepareStatement(sql);
				insertproduto.setString(1, nomeProduto);
				insertproduto.setDouble(2, price);
				insertproduto.setInt(3, volume);
				insertproduto.setBoolean(4, disponibilidade);
				insertproduto.setInt(5, estoque);
				insertproduto.executeUpdate();
			}
			else{
				System.out.println("Produto já cadastrado.");
			}
		}
		catch(Exception e){
			throw new SQLException("Erro ao inserir linha de teste.", e);
		}finally {

			if (insertproduto != null) {
				insertproduto.close();
			}

			if (conn!= null) {
				conn.close();
			}

		}
	}	
	
	public int getProductID(String nomeProduto) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getproduct = null;
		int infoid = -1;
		try{
			sql = "SELECT productID FROM cerveja.produto WHERE nomeProduto = ?"; //?????
			getproduct = conn.prepareStatement(sql);
			getproduct.setString(1, nomeProduto);
			ResultSet rs = getproduct.executeQuery();
			while(rs.next()){
				infoid = rs.getInt("productID");
			}
			return infoid;
		}
		catch(Exception e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {

			if (getproduct != null) {
				getproduct.close();
			}

			if (conn!= null) {
				conn.close();
			}
		}
	}
	

}
