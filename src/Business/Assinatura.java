package Business;

import java.sql.SQLException;
import java.util.Set;

public class Assinatura {
	
	private int assinaturaID;
	private Set<Pacote> pacotes;
	private Set<Pagamento> payment;
	private int diaDebito;
	private EstadoAssinatura estadoAssinatura;
	private AssinaturaDAO assinaturaDAO = AssinaturaDAO.getInstance();

	
	public int getAssinaturaID() {
		return assinaturaID;
	}

	public void addAssinatura(int diaDebito) throws SQLException{
		this.diaDebito = diaDebito;
		this.assinaturaID = assinaturaDAO.addAssinatura(diaDebito);
	}
	
	public void setAssinaturaID(int assinaturaID) {
		this.assinaturaID = assinaturaID;
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
