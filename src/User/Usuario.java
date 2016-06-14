package User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Base64;

public abstract class  Usuario {

	private String userID;
	private UserType userType;
	private String email;
	private String senha;
	private Boolean ativo;
	public UserInfo info;
	private UserDAO userdao = UserDAO.getInstance();
    private final ThreadLocal<Random> random = new ThreadLocal<Random>();
	
	public  Usuario() {
		this(null, null, null, null);
	}
	
	public Usuario(UserType userType, String email, String senha, UserInfo info){
		
		String passwordHash = makePasswordHash(senha, Integer.toString(getRandom().nextInt()));
		
		this.userType = userType;
		if(email == null){
			System.out.println("Erro: valor inv�lido.");
		}
		else {
			this.email = email;
		}
		
		if(senha.length() < 8){
			System.out.println("Erro: a senha deve possuir no mínimo 8 digitos.");
		}
		else {
			this.senha = passwordHash;
		}
		this.ativo = false;
		
		this.info = info;
	}
	
	private Random getRandom() {
        Random result = random.get();
        if (result == null) {
            result = new Random();
            random.set(result);
        }
        return result;
    }
	
	private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
            // encode to string (instead of a byte array containing ASCII)
            //String base64EncodedData = base64Encoder.encodeToString(binaryData);
            //BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return encoder.encode(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
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
