package com.cerveja.Business;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import com.cerveja.User.*;
import com.cerveja.Product.*;

import java.time.LocalDate;

public class PacoteDAO {
	
	private static PacoteDAO instance = new PacoteDAO();
	
	private String sql;
	
	private PacoteDAO(){}
	
	public static PacoteDAO getInstance(){
		return instance;
	}
	
	public int insertPacote(String periodicidade, LocalDate createDate, Assinatura assinatura, boolean validity, int quantidade, Set<Produto> produtos) throws SQLException {
        Connection conn = MyConnection.getConnection();
        PreparedStatement stmnt = null;
        try{
            sql = "INSERT INTO cerveja.pacote (periodicidade, dataCriacao, assinaturaID, validade, quantidade) VALUES (?, ?, ?, ?, ?)";
            stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, periodicidade);
            stmnt.setDate(2, Date.valueOf(createDate));
            stmnt.setInt(3, assinatura.getAssinaturaID());
            stmnt.setBoolean(4, validity);
            stmnt.setInt(5, quantidade);
            stmnt.executeUpdate();
        }
        catch(Exception e){
            throw new SQLException("Erro ao inserir pacote", e);
        }
        finally{
            if(stmnt != null) stmnt.close();
        }
        Statement getPacoteID = null;
        ResultSet rsPacoteID = null;
        int pacoteID = 0;
        try{
            sql = "SELECT MAX(pacoteID) AS lastID FROM cerveja.pacote";
            getPacoteID = conn.createStatement();
            rsPacoteID = getPacoteID.executeQuery(sql);
            while(rsPacoteID != null && rsPacoteID.next()){
                pacoteID = rsPacoteID.getInt(1);
            }
        }
        catch(Exception e){
            throw new SQLException("Erro ao pegar ID do pacote", e);
        }
        finally{
            if(getPacoteID != null) getPacoteID.close();
            if(rsPacoteID != null) rsPacoteID.close();
        }
        
        for(Produto p : produtos){
            PreparedStatement stmnt2 = null;
            try{
                sql = "INSERT INTO cerveja.pacote_produto (pacoteID, produtoID) VALUES (?, ?)";
                stmnt2 = conn.prepareStatement(sql);
                stmnt2.setInt(1, pacoteID);
                stmnt2.setInt(2, p.getProductId());
                stmnt2.executeUpdate();
            }
            catch(Exception e){
                throw new SQLException("Erro ao inserir pacote_produto " + p.getProductId(), e);
            }
            finally{
                if(stmnt2 != null) stmnt2.close();
            }
        }
        if(conn != null) conn.close();
        return pacoteID;
    }
    
	public Pacote getPacote(int pacoteID) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getpacote = null;
		try{
			
			sql = "SELECT A.pacoteID, A.assinaturaID, B.pacoteProdutoID, C.produtoID, "
					+ "C.nome, C.preco, C.volumeID, C.disponibilidade, C.estoque, "
					+ "periodicidade, dataCriacao, validade, quantidade "
					+ "FROM cerveja.pacote A INNER JOIN cerveja.pacote_produto B INNER JOIN cerveja.produto C "
					+ "ON A.pacoteID = B.pacoteID AND B.produtoID = C.produtoID "
					+ "WHERE A.pacoteID = ?";
			getpacote = conn.prepareStatement(sql);
			getpacote.setInt(1, pacoteID);
			ResultSet rs = getpacote.executeQuery();
			String periodicidade = "";
			boolean validade = false;
			Set<Produto> produtos = new HashSet<Produto>();		
			Pacote pack = null;
			LocalDate dc = null;
			while(rs.next()){
				periodicidade = rs.getString("periodicidade");
				int assinaturaID = rs.getInt("assinaturaID");
				validade = rs.getBoolean("validade");
				LocalDate data = rs.getDate("dataCriacao").toLocalDate();
				dc = data;
				Produto p = new Produto(rs.getInt("produtoID"), rs.getString("nome"), rs.getInt("preco"), rs.getInt("volumeID"), rs.getBoolean("disponibilidade"), rs.getInt("estoque"));
				produtos.add(p);
				Pacote pacote = new Pacote(produtos, dc, periodicidade, validade, new Assinatura().getAssinatura(assinaturaID));
				pacote.setPacoteID(pacoteID);
				pack = pacote;
			}
			return pack;
		}
		catch(Exception e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {
			if (getpacote != null) {
				getpacote.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
	public int getPrice(int pacoteID) throws SQLException{
		Connection conn = MyConnection.getConnection();
		PreparedStatement getprice = null;
		int price = 0;
		try{
			
			sql = "SELECT A.pacoteID, A.assinaturaID, B.pacoteProdutoID, C.produtoID, "
					+ "C.nome, C.preco, C.volumeID, C.disponibilidade, C.estoque, "
					+ "periodicidade, dataCriacao, validade, quantidade "
					+ "FROM cerveja.pacote A INNER JOIN cerveja.pacote_produto B INNER JOIN cerveja.produto C "
					+ "ON A.pacoteID = B.pacoteID AND B.produtoID = C.produtoID "
					+ "WHERE A.pacoteID = ?";
			getprice = conn.prepareStatement(sql);
			getprice.setInt(1, pacoteID);
			ResultSet rs = getprice.executeQuery();
			while(rs.next()){
				price += rs.getInt("preco");
			}
			return price;
		}
		catch(Exception e){
			throw new SQLException("Erro ao buscar linha de teste.", e);
		}finally {
			if (getprice != null) {
				getprice.close();
			}
			if (conn!= null) {
				conn.close();
			}
		}
	}
	
    public void deletePacote(int pacoteID) throws SQLException {
        Connection conn = MyConnection.getConnection();
		PreparedStatement stmnt = null;
		try{
			sql = "DELETE FROM cerveja.pacote WHERE pacoteID = ?";
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, pacoteID);
			stmnt.execute();
		}
		catch(Exception e){
			throw new SQLException("Erro ao deletar pacote " + pacoteID, e);
		}
        finally {
			if (stmnt != null) stmnt.close();
			if (conn!= null) conn.close();
		}
    }

}
