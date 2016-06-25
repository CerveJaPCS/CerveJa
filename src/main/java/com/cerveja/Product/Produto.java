package com.cerveja.Product;

public class Produto {
	
	private int productId;
	private String nomeProduto;
	private int price;
	private int volume;
	private boolean disponibilidade;
	private int estoque;
	private ListaPedido pedido;
	private ProdutoDAO dao = ProdutoDAO.getInstance();
	
	public Produto(int productId, String nomeProduto, int price,
			int volume, boolean disponibilidade, int estoque) {
		this.productId = productId;
		this.nomeProduto = nomeProduto;
		this.price = price;
		this.volume = volume;
		this.disponibilidade = disponibilidade;
		this.estoque = estoque;
	}
	
	public ListaPedido getPedido() {
		return pedido;
	}

	public void setPedido(ListaPedido pedido) {
		this.pedido = pedido;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isDisponivel() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	
}
