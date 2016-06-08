package Business;

import java.util.Set;

public class Assinatura {
	Set<Pacote> pacotes;

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
