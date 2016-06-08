package Product;

public class Produto {
	
	private String productId;
	private String nomeProduto;
	private double price;
	private int volume;
	private boolean disponibilidade;
	private int estoque;
	private ListaPedido pedido;
	private String descricao;
	
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Produto(String productId, String nomeProduto, double price,
			int volume, boolean disponibilidade, int estoque) {
		this.productId = productId;
		this.nomeProduto = nomeProduto;
		this.price = price;
		this.volume = volume;
		this.disponibilidade = disponibilidade;
		this.estoque = estoque;
	}
	
	public ListaPedido getPedido() {
		return this.pedido;
	}

	public void setPedido(ListaPedido pedido) {
		this.pedido = pedido;
	}

	public int getEstoque() {
		return this.estoque;
	}

	public void incEstoque(int n){
		this.estoque += n;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getNomeProduto() {
		return this.nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isDisponivel() {
		return this.disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	
}
