package com.cerveja.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class  Usuario {

	private int userID;
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
			String senhaHashed = userdao.makePasswordHash(senha);
			this.senha = senhaHashed;
		}
		this.ativo = false;
		
		this.info = info;
	}
	
	public void updateUserCliente(int usuarioID, String endereco, String telefone) throws SQLException{
		userdao.updateUserCliente(usuarioID, endereco, telefone);
	}
	
	public void mudarSenha(int usuarioID, String oldpass, String newpass) throws SQLException{
		userdao.mudarSenha(usuarioID, oldpass, newpass);
	}
	
	public boolean getUserAuth(String email, String senha) throws SQLException{
		return userdao.getUserAuth(email, senha);
	}
	
	public void delUser(String email, int infoid) throws SQLException{
		userdao.deleteUser(email, infoid);
	}
	
	public Cliente getUserCliente(int userID) throws SQLException{
		return userdao.getUserCliente(userID);
	}
	
	public boolean addUser(){
		try{
			String cpf = this.info.getCPF();
			int infoid = this.info.getUserInfoID(cpf);
			if(infoid != -1){
				userdao.insertUser(this.email, this.userType, this.senha, infoid);
				String activateCode = makeHashKey(this.email);
				sendMail(activateCode, this.email);
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
	
	public int getUserID(){
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

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		String senhaHashed = userdao.makePasswordHash(senha);
		this.senha = senhaHashed;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setInfo(UserInfo info) {
		this.info = info;
	}
	
	private void sendMail(String hashedKey, String email)
    {
    	ApplicationContext context = 
             new ClassPathXmlApplicationContext("Spring-Mail.xml");
    	 
    	MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("admin@cerveja.com",
    		   email,
    		   "Ativação de Conta no CerveJa", 
    		   "Obrigado por se cadastrar no CerveJa! \n\nSua conta foi criada com sucesso e "
    		   + "você poderá logar após a confirmação pelo link abaixo. \n\n\n"
    		   + "http://www.cerveja.com.br/verify.html?" + email + ".&hash" + hashedKey);
        
    }

	private String makeHashKey(String seed) {
		String md5 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(seed.getBytes(), 0, seed.length());
    		md5 = new BigInteger(1, digest.digest()).toString(16);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        }
    }

}
