package Product;

public class Fornecedor {
	private String nomeFornecedor;
	private String cnpj;
	private String telefone;
	
	public Fornecedor(String nomeFornecedor, String cnpj, String telefone) {
		this.nomeFornecedor = nomeFornecedor;
		this.cnpj = cnpj;
		this.telefone = telefone;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
