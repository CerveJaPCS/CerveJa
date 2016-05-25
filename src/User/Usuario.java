package User;

import java.sql.SQLException;

public abstract class  Usuario {

	private String userID;
	private UserType userType;
	private String email;
	private String senha;
	private Boolean ativo;
	public UserInfo info;
	public static final int min_length = 2;
	public UserDAO userDAO;


	
	public  Usuario(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public Usuario(String userID, UserType userType, String email, String senha,
					String nome, String cpf, String rg, String dataNascimento, 
					String endereco, String telefone){
		
		if(userID == null || userID.length() < min_length){
			System.out.println("Erro: valor para ID inválido.");
		}
		else {
			this.userID = userID;
		}
		
		this.userType = userType;

	
		if(email == null || email.length() < min_length){
			System.out.println("Erro: email inválido.");
		}
		else {
			this.email = email;
		}
		
		if(senha.length() < 8){
			System.out.println("Erro: senha inválida.");
		}
		else {
			this.senha = senha;
		}
		this.ativo = false;
		
		this.info = new UserInfo(nome, cpf, rg, dataNascimento, endereco, telefone);
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
	
	public void insertUser() throws SQLException{
		userDAO.insert(this.info.getNome());
	}
	
	public String verifyUser() throws SQLException{
		// TODO
		// Chamar userDAO para verificar a existência do usuário no banco de dados.

		return null;
	}
	
}
