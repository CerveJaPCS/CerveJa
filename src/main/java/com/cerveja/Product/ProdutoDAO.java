package com.cerveja.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.cerveja.User.*;

public class ProdutoDAO {
	
	private static ProdutoDAO instance = new ProdutoDAO();
	
	private String sql;
	
	private ProdutoDAO(){}
	
	public static ProdutoDAO getInstance(){
		return instance;
	}
	
	public void insertProduto(String nome, double preco, int volume,	
			boolean disponibilidade, int estoque, String descricao, int subTipoID, int nacionalidadeID, int marcaID, int volumeID) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertproduto = null;
		try{
		
				sql = "INSERT INTO `cerveja`.`produto` (`preco`, `disponibilidade`, `nome`, `descricao`, `estoque`, `subTipoID`, `nacionalidadeID`, `marcaID`, `volumeID`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
				insertproduto = conn.prepareStatement(sql);
				insertproduto.setDouble(1, preco);
				insertproduto.setBoolean(2, disponibilidade);
				insertproduto.setString(3, nome);
				insertproduto.setString(4, descricao);
				insertproduto.setInt(5, estoque);
				insertproduto.setInt(6, subTipoID);
				insertproduto.setInt(7, nacionalidadeID);
				insertproduto.setInt(8, marcaID);
				insertproduto.setInt(9, volumeID);

				insertproduto.executeUpdate();
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
	
	public Produto getProduto(int produtoID) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getproduct = null;
		try{
			
			sql = "SELECT nome, preco, volumeID, disponibilidade, estoque  FROM cerveja.produto WHERE produtoID = ?";
			getproduct = conn.prepareStatement(sql);
			getproduct.setInt(1, produtoID);
			ResultSet rs = getproduct.executeQuery();
			while(rs.next()){
				String nomeProduto = rs.getString(1);
				int price = rs.getInt(2);
				int volume = rs.getInt(3);
				boolean disponibilidade = rs.getBoolean(4);
				int estoque = rs.getInt(5);
				return new Produto(produtoID, nomeProduto, price, volume, disponibilidade, estoque);
			}
			return null;
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