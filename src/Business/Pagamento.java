package Business;

import java.time.LocalDate;

public class Pagamento {
	
	private Assinatura assinatura;
	private int valor;
	private LocalDate dataPagamento;
	
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
