package User;


import java.util.List;
import java.sql.SQLException;


public abstract class  Usuario {

	private String userID;
	private UserType userType;
	private String email;
	private String senha;
	private Boolean ativo;
	public UserInfo info;
	private UserDAO userdao = UserDAO.getInstance();
	
	public  Usuario() {
		this(null, null, null, null);
	}
	
	public Usuario(UserType userType, String email, String senha, UserInfo info){
				
		this.userType = userType;
		if(email != null && !email.equals("")){
			this.email = email;
		}
		else {
			System.out.println("Erro: valor inv�lido.");
		}
		
		if(senha.length() < 8){
			System.out.println("Erro: a senha deve possuir no mínimo 8 digitos.");
		}
		else {
			this.senha = senha;
		}
		this.ativo = false;
		
		this.info = info;
	}
	
	public boolean getUserAuth(String email, String senha) throws SQLException{
		return userdao.getUserAuth(email, senha);
	}
	
	public void delUser(String email, int infoid) throws SQLException{
		userdao.deleteUser(email, infoid);
	}
	
	public List<String> getUser(int userID) throws SQLException{
		return userdao.getUser(userID);
	}
	
	public boolean addUser(){
		try{
			String cpf = this.info.getCPF();
			int infoid = this.info.getUserInfoID(cpf);
			if(infoid != -1){
				userdao.insertUser(this.email, this.userType, this.senha, infoid);
				return true;
			}
			else{
				return false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
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
