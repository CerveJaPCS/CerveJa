package com.cerveja.User;


public class Admin extends Usuario {
	
	public Admin(UserType userType, String email,
			String senha, UserInfo info) {
		super(userType, email, senha, info);
	}

	public Admin() {
		this(null, null, null, null);
	}
	
	public void deleteClient(Cliente c){
		// TODO
	}
}
