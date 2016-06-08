package Business;

import java.util.Set;

public class Assinatura {
	
	private Set<Pacote> pacotes;
	private Set<Pagamento> payment;

	
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
