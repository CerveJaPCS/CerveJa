package com.cerveja.Business;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public class Assinatura {
	
	private int assinaturaID;
	private Set<Pacote> pacotes;
	private Set<Pagamento> payment;
	private int diaDebito;
	private EstadoAssinatura estadoAssinatura;
	private AssinaturaDAO assinaturaDAO = AssinaturaDAO.getInstance();

	public void atualizarEstado() throws SQLException{
		if(estadoAssinatura == EstadoAssinatura.Inativa) return;
		LocalDate dataDebito;
		if(LocalDate.now().getDayOfMonth() <= diaDebito)
			dataDebito = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), diaDebito);
		else
			dataDebito = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 1, diaDebito);
		if(LocalDate.now() == dataDebito){
			estadoAssinatura = EstadoAssinatura.AguardandoPagamento;
			assinaturaDAO.atualizaEstado(this.assinaturaID, EstadoAssinatura.AguardandoPagamento);
		}
		if(estadoAssinatura == EstadoAssinatura.AguardandoPagamento
				&& LocalDate.now().isAfter(dataDebito.plusDays(10))){
			estadoAssinatura = EstadoAssinatura.NaoPaga;
			assinaturaDAO.atualizaEstado(this.assinaturaID, EstadoAssinatura.NaoPaga);
		}
	}
	
	public void pagar() throws SQLException{
		if(estadoAssinatura != EstadoAssinatura.AguardandoPagamento) {
			System.out.println("Assinatura não está aguardando pagamento!");
			return;
		}
		Pagamento pagamento = new Pagamento(this, getValorTotal(), LocalDate.now());
		pagamento.addPagamento();
		addPayment(pagamento);
		estadoAssinatura = EstadoAssinatura.Paga;
		assinaturaDAO.atualizaEstado(this.assinaturaID, EstadoAssinatura.Paga);
	}
	
	
	public int getAssinaturaID() {
		return assinaturaID;
	}

	public void addAssinatura(int diaDebito) throws SQLException{
		this.diaDebito = diaDebito;
		this.assinaturaID = assinaturaDAO.addAssinatura(diaDebito);
	}
	
	public EstadoAssinatura getEstadoAssinatura() {
		return estadoAssinatura;
	}

	public void setEstadoAssinatura(EstadoAssinatura estadoAssinatura) throws SQLException {
		this.estadoAssinatura = estadoAssinatura;
		assinaturaDAO.atualizaEstado(this.assinaturaID, estadoAssinatura);
	}

	public int getDiaDebito() {
		return diaDebito;
	}

	public void setDiaDebito(int diaDebito) {
		this.diaDebito = diaDebito;
	}

	public void deleteAssinatura(){
		//TODO
	}
	
	public Set<Pagamento> getPayment() {
		return payment;
	}

	public void setPayment(Set<Pagamento> payment) {
		this.payment = payment;
	}

	public void addPayment(Pagamento payment){
		this.payment.add(payment);
	}
	
	public Set<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(Set<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
	
	public int getValorTotal(){
		int valorTotal = 0;
		if(!pacotes.isEmpty()){
			for(Pacote p : pacotes){
				valorTotal += p.getPrice();
			}
		}
		return valorTotal;
	}
}
