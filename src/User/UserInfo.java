package User;


public class UserInfo {
	
	private String nome;
	private String cpf;
	private String rg;
	private String dataNascimento;
	private String endereco;
	private String telefone;
	public static final int min_length = 2;

	
	public UserInfo(String nome, String cpf, String rg, String dataNascimento, String endereco, String telefone){
		if(nome == null || nome.length() < min_length){
			System.out.println("Erro: valor para nome inválido.");
		}
		else{
			this.nome = nome;	
		}
		if(cpf == null || cpf.length()<11){
			System.out.println("Erro: valor de cpf inválido.");
		}
		else{
			this.cpf = cpf;
		}
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.telefone = telefone;
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
	
	public String getDataNascimento(){
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

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
