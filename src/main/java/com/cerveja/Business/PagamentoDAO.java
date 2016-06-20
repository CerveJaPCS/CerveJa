package com.cerveja.Business;

import java.sql.*;
import java.time.LocalDate;

import com.cerveja.User.*;

import java.sql.SQLException;

public class PagamentoDAO {
	
	private static PagamentoDAO instance = new PagamentoDAO();
	
	private String sql;
	
	private PagamentoDAO(){}
	
	public static PagamentoDAO getInstance(){
		return instance;
	}
	
	
    public void insertPagamento(int valor, LocalDate dataPagamento, Assinatura assinatura) throws SQLException {
        Connection conn = MyConnection.getConnection();
        PreparedStatement stmnt = null;
        try{
            
            sql = "INSERT INTO cerveja.pagamento (valor, dataPagamento, assinaturaID) VALUES (?, ?, ?)";
            stmnt = conn.prepareStatement(sql);
            stmnt.setInt(1, valor);
            stmnt.setDate(2, Date.valueOf(dataPagamento));
            stmnt.setInt(3, assinatura.getAssinaturaID());
            stmnt.executeUpdate();
        }
        catch(Exception e){
            throw new SQLException("Erro ao inserir pagamento", e);
        }
        finally{
            if(stmnt != null) stmnt.close();
            if(conn != null) conn.close();
        }
    }

}
