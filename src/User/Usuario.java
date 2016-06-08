package User;

public abstract class  Usuario {

	private String userID;
	private UserType userType;
	private String email;
	private String senha;
	private Boolean ativo;
	public UserInfo info;
	private static int MINLENGHT = 2;
	
	public  Usuario() {
		this(null, null, null, null, null);
	}
	

	public Usuario(String userID, UserType userType, String email, String senha,
					UserInfo info){
		
		if(userID == null || userID.length() < MINLENGHT){
			System.out.println("Erro: valor inv�lido.");
		}
		else {
			this.userID = userID;
		}
		
		this.userType = userType;

	
		if(email == null){
			System.out.println("Erro: valor inv�lido.");
		}
		else {
			this.email = email;
		}
		
		if(senha.length() < 8){
			System.out.println("Erro: senha inv�lida.");
		}
		else {
			this.senha = senha;
		}
		this.ativo = false;
		
		this.info = info;
	}
	
	public void ativar(){
		this.ativo = true;
	}
	
	public String getUserID(){
		return this.userID;
	}
	
	public UserType getUserType(){
		return this.userType;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getSenha(){
		return this.senha;
	}
	
	public Boolean getAtivo(){
		return this.ativo;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setInfo(UserInfo info) {
		this.info = info;
	}
	

}
