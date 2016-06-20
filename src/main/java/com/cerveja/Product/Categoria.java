package com.cerveja.Product;

import java.util.Set;

public class Categoria {
	private String nomeCategoria;
	private String categoriaId;
	private String description;
	private Set<Produto> produtos;
	
	public Categoria(String nomeCategoria, String categoriaId,
			String description) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.categoriaId = categoriaId;
		this.description = description;
	}

	public void addProdutosCategoria(Produto cerveja){
		if(!verificaProduto(cerveja)){
			this.produtos.add(cerveja);
		}
		else{
			System.out.println("Produto já existe na categoria " + nomeCategoria);
		}
	}
	
	public void rmProdutosCategoria(Produto cerveja){
		if(verificaProduto(cerveja)){
			this.produtos.remove(cerveja);
		}
		else{
			System.out.println("Produto não existe na categoria " + nomeCategoria);
		}
	}
	
	private boolean verificaProduto(Produto cerveja){
//		for(Produto p : produtos){
//			if(p.getNomeProduto().equals(cerveja.getNomeProduto())){
//				return true;
//			}
//		}
//		return false;
		return this.produtos.contains(cerveja);
	}
	
	public Set<Produto> getProdutosCategoria() {
		return produtos;
	}

	public void setProdutosCategoria(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
