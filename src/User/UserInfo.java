package User;

import java.sql.SQLException;
import java.time.LocalDate;

public class UserInfo {
	
	private String nome;
	private String cpf;
	private String rg;
	private LocalDate dataNascimento;
	private String endereco;
	private String telefone;
	private UserDAO userdao = UserDAO.getInstance();
	
	public UserInfo(String nome, String cpf, String rg, LocalDate dataNascimento, String endereco, String telefone){
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public boolean addUserInfo(){
		try{
			userdao.insertUserInfo(this.nome, this.cpf, this.rg, this.dataNascimento, this.endereco, this.telefone);
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public int getUserInfoID(String cpf){
		try{
			return userdao.getUserInfoID(cpf);
		}
		catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getCPF(){
		return this.cpf;
	}
	
	public String getRG(){
		return this.rg;
	}
	
	public LocalDate getDataNascimento(){
		return this.dataNascimento;
	}
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public String getTelefone(){
		return this.telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
