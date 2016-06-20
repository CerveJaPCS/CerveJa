package com.cerveja.Product;

public class Fornecedor {
	
	private String nomeFornecedor;
	private String cnpj;
	private String telefone;
	private ListaPedido pedido;
	
	public Fornecedor(String nomeFornecedor, String cnpj, String telefone) {
		this.nomeFornecedor = nomeFornecedor;
		this.cnpj = cnpj;
		this.telefone = telefone;
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
