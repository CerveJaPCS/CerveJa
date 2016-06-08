package Business;

import java.util.HashSet;
import java.util.Set;

public class Assinatura {
	Set<Pacote> pacotes;

	public Set<Pacote> getPacotes() {
		Set<Pacote> result = new HashSet<Pacote>();
	    for (Pacote p : pacotes) {
	        result.add(p);
	    }
		return result;
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
