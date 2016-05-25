package Product;

public class Categoria {
	private String nomeCategoria;
	private String categoriaId;
	private String description;
	
	public Categoria(String nomeCategoria, String categoriaId,
			String description) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.categoriaId = categoriaId;
		this.description = description;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
