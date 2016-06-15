package Business;

import java.util.Set;

public class Assinatura {
	
	private int pacoteID;
	private Set<Pacote> pacotes;
	private Set<Pagamento> payment;
	private int diaDebito;
	private EstadoAssinatura estadoAssinatura;
	private AssinaturaDAO assinaturaDAO = AssinaturaDAO.getInstance();

	
	public int getPacoteID() {
		return pacoteID;
	}

	public void setPacoteID(int pacoteID) {
		this.pacoteID = pacoteID;
	}

	public int getID(){
		return assinaturaDAO.getID();
	}
	
	public EstadoAssinatura getEstadoAssinatura() {
		return estadoAssinatura;
	}

	public void setEstadoAssinatura(EstadoAssinatura estadoAssinatura) {
		this.estadoAssinatura = estadoAssinatura;
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
