package User;

import Business.*;

public class Cliente extends Usuario {
	
	Assinatura assinatura;
	
	public Cliente(String userID, UserType userType, String email,
			String senha, UserInfo info) {
		super(userID, userType, email, senha, info);
	}
	
	public Cliente() {
		this(null, null, null, null, null);
	}
	
}
