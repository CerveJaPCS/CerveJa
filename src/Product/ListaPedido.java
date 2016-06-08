package Product;

import java.time.LocalDate;

public class ListaPedido {
	
	private int quantidade;
	private LocalDate dataPedido;
	private LocalDate dataRecebido;
	private int valorTotal;
	private Produto produto;
	private Fornecedor fornecedor;
	
	public void calculaValorTotal(){
		valorTotal = (int) (quantidade*(produto.getPrice()));
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
		return dataRecebido;
	}
	
	public void setDataRecebido(LocalDate dataRecebido) {
		this.dataRecebido = dataRecebido;
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
