package Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import User.MyConnection;
import User.UserDAO;

public class ListaPedidoDAO {
	
	
	private static ListaPedidoDAO instance = new ListaPedidoDAO();

	
	public static ListaPedidoDAO getInstance(){
		return instance;
	}
	

	public ListaPedidoDAO() {
	}

	private String sql ;
	
	public void insertPedido(int produtoID, int fornecedorID, int quantidade, LocalDate dataPedido, int valorUnitario, LocalDate dataRecebimento) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement insertuserinfo = null;
		try{
			Date dop = Date.valueOf(dataPedido);
			Date dor = Date.valueOf(dataRecebimento);

			sql = "INSERT INTO cerveja.pedido (produtoID, fornecedorID, quantidade, dataPedido, valorUnitario, dataRecebimento) VALUES (?,?,?,?,?,?)";
			insertuserinfo = conn.prepareStatement(sql);
			insertuserinfo.setInt(1, produtoID);
			insertuserinfo.setInt(2, fornecedorID);
			insertuserinfo.setInt(3, quantidade);
			insertuserinfo.setDate(4, dop);
			insertuserinfo.setInt(5, valorUnitario);
			insertuserinfo.setDate(6, dor);
		
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
	
	public List<String> getPedido(int pedidoID) throws SQLException{
		List<String> lst = new ArrayList<String>();
		Connection conn = MyConnection.getConnection();
		PreparedStatement getuser = null;
		try{
			sql = "SELECT produtoID, fornecedorID, quantidade, dataPedido, valorUnitario, dataRecebimento"
					+ "FROM cerveja.pedido" ;
			getuser = conn.prepareStatement(sql);
			getuser.setInt(1, pedidoID);
			ResultSet rs = getuser.executeQuery();
			while(rs.next()){
				Date dop = rs.getDate("dataPedido");
				Date dor = rs.getDate("dataRecebimento");

				
				lst.add(rs.getString("produtoID"));
				lst.add(rs.getString("fornecedorID"));
				lst.add(rs.getString("quantidade"));
				lst.add(dop.toString());
				lst.add(rs.getString("valorUnitario"));
				lst.add(dor.toString());

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
	
	
	
	public void deletePedido(String pedidoID) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement deluser = null;
		try{
		
			sql = "DELETE FROM cerveja.pedido WHERE pedidoID = ?";
			deluser = conn.prepareStatement(sql);
			deluser.setString(1, pedidoID);
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
	
	
	
	/*
	public void printTestTable() throws SQLException {
		try{
			Connection conn = MyConnection.getConnection();
			Statement stmnt = null;
			ResultSet rs = null;
			
			sql = "SELECT * FROM cerveja.pedido ";
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(sql);
			while(rs != null && rs.next()){
				int i = rs.getInt("pedidoID");
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
		*/
	
	
}
