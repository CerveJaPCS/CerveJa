package Business;

import java.util.Set;
import java.time.LocalDate;
import java.sql.SQLException;

import Product.*;

public class Pacote {
	
	private int pacoteID;
	private Set<Produto> produtos;
	private int quantidade;
	private LocalDate createDate;
	private String periodicidade;
	private boolean validity;
	private Assinatura assinatura;
	private PacoteDAO dao = PacoteDAO.getInstance();
	
	public Pacote(Set<Produto> produtos, LocalDate createDate, String periodicidade, boolean validity, Assinatura assinatura) {
		this.produtos = produtos;
		this.createDate = createDate;
		this.periodicidade = periodicidade;
		this.validity = validity;
		this.quantidade = produtos.size();
		this.assinatura = assinatura;
	}
	
	public boolean addPacote(){
		try{
			pacoteID = dao.insertPacote(periodicidade, createDate, assinatura, validity, quantidade, produtos);
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletePacote(){
		try{
			dao.deletePacote(pacoteID);
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getPrice(){
		int valorPacote = 0;
		if(!this.produtos.isEmpty()){
			for(Produto p : produtos){
				valorPacote += p.getPrice();
			}
		}
		return valorPacote;
	}
	
	public int getPacoteID() {
		return pacoteID;
	}

	public void setPacoteID(int pacoteID) {
		this.pacoteID = pacoteID;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	private boolean contemProduto(Produto cerveja){
		return this.produtos.contains(cerveja);
	}
	
	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void addProdutos(Produto produto){
		if(!contemProduto(produto)){
			this.produtos.add(produto);
			this.quantidade = produtos.size();
		}
		else{
			System.out.println("Produto já existe no pacote.");
		}
	}
	
	public void removeProdutos(Produto produto){
		try{
			if(!this.produtos.isEmpty()){
				if(contemProduto(produto)){
					this.produtos.remove(produto);
					this.quantidade = produtos.size();
				}
				else{
					System.out.println("Produto não está contido no pacote.");
				}
			}
		}
		catch(Exception e){
			System.out.println("Pacote Vazio.");
		}
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}
	
	public Assinatura getAssinatura(){
		return assinatura;
	}
	
	public void setAssinatura(Assinatura assinatura){
		this.assinatura = assinatura;
	}
	
	
}
