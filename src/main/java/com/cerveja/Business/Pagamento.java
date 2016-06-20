package com.cerveja.Business;

import java.time.LocalDate;
import java.sql.SQLException;

public class Pagamento {
	
	private Assinatura assinatura;
	private int valor;
	private LocalDate dataPagamento;
	private PagamentoDAO dao = PagamentoDAO.getInstance();
	
	public Pagamento(Assinatura assinatura, int valor, LocalDate dataPagamento) {
		this.assinatura = assinatura;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
	}
	
	public boolean addPagamento() {
		try{
			dao.insertPagamento(this.valor, this.dataPagamento, this.assinatura);
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public Assinatura getAssinatura() {
		return assinatura;
	}
	
	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}

	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

}
