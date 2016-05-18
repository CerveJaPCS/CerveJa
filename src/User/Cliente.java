package User;

import java.util.*;
import Business.*;

public class Cliente extends Usuario {
	
	Assinatura assinatura;
	
	public Cliente(String userID, UserType userType, String email,
			String senha, String nome, String cpf,
			String rg, Date dataNasc, String end,
			String tel) {
		super(userID, userType, email, senha, nome, cpf, rg, dataNasc, end, tel);
	}
	
}
