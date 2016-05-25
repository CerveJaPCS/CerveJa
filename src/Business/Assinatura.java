package Business;

import java.util.HashSet;
import java.util.Set;

import User.Cliente;

public class Assinatura {
	Set<Pacote> pacotes;
	Cliente cliente;

	public Set<Pacote> getPacotes() {
		Set<Pacote> result = new HashSet<Pacote>();
	    for (Pacote p : pacotes) {
	        result.add(p);
	    }
		return pacotes;
	}

	public void setPacotes(Set<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
}
