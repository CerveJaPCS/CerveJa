package Product;

import java.sql.SQLException;
import java.util.List;

import User.UserDAO;

public class Fornecedor {
	
	private int fornecedorID;
	private String nomeFornecedor;
	private String cnpj;
	private String telefone;
	private ListaPedido pedido;
	private FornecedorDAO fornDAO = FornecedorDAO.getInstance();

	
	public Fornecedor(int fornecedorID, String nomeFornecedor, String cnpj, String telefone) {
		this.fornecedorID = fornecedorID;
		this.nomeFornecedor = nomeFornecedor;
		this.cnpj = cnpj;
		this.telefone = telefone;
	}

	
	public void addFornecedor(){
		try{
			fornDAO.insertFornecedor(this.nomeFornecedor, this.cnpj, this.telefone);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteFornecedor(int fornecedorID) throws SQLException{
		fornDAO.deleteFornecedor(fornecedorID);
	}
	
	public void deleteFornecedor(String cnpj) throws SQLException{
		fornDAO.deleteFornecedor(cnpj);
	}
	
	public List<String> getFornecedor(int fornecedorID) throws SQLException{
		return fornDAO.getFornecedor(fornecedorID) ;
	}
	
	public List<String> getFornecedor(String cnpj) throws SQLException{
		return fornDAO.getFornecedor(cnpj) ;
	}
	
	public int getFornecedorID() {
		return fornecedorID;
	}
	
	public ListaPedido getPedido() {
		return pedido;
	}

	public void setPedido(ListaPedido pedido) {
		this.pedido = pedido;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
