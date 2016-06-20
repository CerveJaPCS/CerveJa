package Business;

import java.sql.*;
import java.util.Set;

import Product.Produto;
import User.MyConnection;

import java.time.LocalDate;
import java.sql.SQLException;

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
        
        Produto[] produtosArr = produtos.toArray(new Produto[produtos.size()]);
        for(int i = 0; i < produtos.size(); i++){
            PreparedStatement stmnt2 = null;
            try{
                sql = "INSERT INTO cerveja.pacote_produto (pacoteID, produtoID) VALUES (?, ?)";
                stmnt2 = conn.prepareStatement(sql);
                stmnt2.setInt(1, pacoteID);
                stmnt2.setInt(2, produtosArr[i].getProductId());
                stmnt2.executeUpdate();
            }
            catch(Exception e){
                throw new SQLException("Erro ao inserir pacote_produto " + i, e);
            }
            finally{
                if(stmnt2 != null) stmnt2.close();
            }
        }
        if(conn != null) conn.close();
        return pacoteID;
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
