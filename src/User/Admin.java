package User;


public class Admin extends Usuario {
	
	public Admin(String userID, UserType userType, String email,
			String senha, UserInfo info) {
		super(userID, userType, email, senha, info);
	}

	public Admin() {
		this(null, null, null, null, null);
	}
	
	public void deleteClient(Cliente c){
		// TODO
	}
}
