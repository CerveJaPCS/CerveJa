package com.cerveja.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class  Usuario {

	private int userID;
	private UserType userType;
	private String email;
	private String senha;
	private Boolean ativo;
	private String activationCode;
	private int assinaturaID;
	public UserInfo info;
	private UserDAO userdao = UserDAO.getInstance();
    private final ThreadLocal<Random> random = new ThreadLocal<Random>();

	
//	public  Usuario() {
//		this(null, null, null, null);
//	}
//	
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
	

	public void setAssinaturaID(int assinaturaID) {
		this.assinaturaID = assinaturaID;
	}
	
	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public void updateUserCliente(String email, String endereco, String telefone) throws SQLException{
		userdao.updateUserCliente(email, endereco, telefone);
	}
	
	public void mudarSenha(String email, String oldpass, String newpass) throws SQLException{
		userdao.mudarSenha(email, oldpass, newpass);
	}
	
	public boolean getUserAuth(String email, String senha) throws SQLException{
		return userdao.getUserAuth(email, senha);
	}
	
	public void delUser(String email, int infoid) throws SQLException{
		userdao.deleteUser(email, infoid);
	}
	
	public Cliente getUserCliente(String email) throws SQLException{
		return userdao.getUserCliente(email);
	}
	
	public boolean addUser(){
		try{
			int infoid = this.info.getUserInfoID();
			if(infoid != -1){
				if(userdao.insertUser(this.email, this.userType, this.senha, infoid)){
					String activateCode = makeHashKey(this.email);
					this.activationCode = activateCode;
					//sendMail(activateCode, this.email);
					return true;
				}
			}
			else{
				return false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public void ativar() throws SQLException{
		userdao.ativarConta(this.email, this.activationCode);
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
        	String word = seed + getRandom().toString();
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(word.getBytes(), 0, word.length());
    		md5 = new BigInteger(1, digest.digest()).toString(16);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        }
    }
	
	private Random getRandom() {
        Random result = random.get();
        if (result == null) {
            result = new Random();
            random.set(result);
        }
        return result;
    }

}
