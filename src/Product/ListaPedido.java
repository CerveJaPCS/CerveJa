package Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ListaPedido {
	
	private int quantidade;
	private LocalDate dataPedido;
	private LocalDate dataRecebimento;
	private int valorUnitario;
	private int valorTotal;
	private Produto produto;
	private Fornecedor fornecedor;
	private ListaPedidoDAO pedidoDAO = ListaPedidoDAO.getInstance();
	private int produtoID;
	private int fornecedorID;
	
	public ListaPedido(Produto produto, Fornecedor fornecedor, int quantidade, LocalDate dataPedido, int valorUnitario, LocalDate dataRecebimento){
		this.quantidade=quantidade;
		this.dataPedido=dataPedido;
		this.dataRecebimento=dataRecebimento;
		this.valorUnitario=valorUnitario;
		this.produto=produto;
		this.fornecedor=fornecedor;
		this.calculaValorTotal();
		produtoID=produto.getProductId();
		fornecedorID=fornecedor.getFornecedorID();
	}
	
	public ListaPedido(int produtoID, int fornecedorID, int quantidade, LocalDate dataPedido, int valorUnitario, LocalDate dataRecebimento){
		this.quantidade=quantidade;
		this.dataPedido=dataPedido;
		this.dataRecebimento=dataRecebimento;
		this.valorUnitario=valorUnitario;
		this.calculaValorTotal();
		this.produtoID=produtoID;
		this.fornecedorID=fornecedorID;
	}
	
	
	
	public void addPedido(){
		try{
			pedidoDAO.insertPedido(produtoID, fornecedorID, quantidade, dataPedido, valorUnitario, dataRecebimento);	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void deletePedido(int pedidoID) throws SQLException{
		pedidoDAO.deletePedido(pedidoID);
	}
	
	
	public List<String> getPedido(int pedidoID) throws SQLException{
		return pedidoDAO.getPedido(pedidoID);
	}
	
	
	
	public void calculaValorTotal(){
		valorTotal= (int) (quantidade*valorUnitario);
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public LocalDate getDataRecebido() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public int getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}
