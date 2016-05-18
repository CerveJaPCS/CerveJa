package User;

import java.util.Date;

public class Admin extends Usuario {
	
	public Admin(String userID, UserType userType, String email,
			String senha, String nome, String cpf,
			String rg, Date dataNasc, String end,
			String tel) {
		super(userID, userType, email, senha, nome, cpf, rg, dataNasc, end, tel);
	}

	public Admin() {
		this(null, null, null, null, null, null, null, null, null, null);
	}
}