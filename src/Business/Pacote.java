package Business;

import java.util.Set;

import Product.*;

public class Pacote {
	private Set<Produto> produtos;
	private String createDate;
	private boolean validity;
	
	public Pacote(Set<Produto> produtos, String createDate, boolean validity) {
		this.produtos = produtos;
		this.createDate = createDate;
		this.validity = validity;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void addProdutos(Produto produtos){
		this.produtos.add(produtos);
	}
	
	public void removeProdutos(Produto produto){
		try{
			if(!this.produtos.isEmpty()){
				this.produtos.remove(produto);
			}
		}
		catch(Exception e){
			
		}
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}
	
	
}
