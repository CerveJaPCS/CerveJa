package User;

import Business.*;

public class Cliente extends Usuario {
	
	Assinatura assinatura;
	
	public Cliente(UserType userType, String email,
			String senha, UserInfo info) {
		super(userType, email, senha, info);
	}
	
	public Cliente() {
		this(null, null, null, null);
	}

	public Assinatura getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}
	
	
}
