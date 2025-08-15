package modelo.entidade.contato;

public class Contato {
	
	private Long id;

	private String telefone;

	private String instagram;

	public Contato() {

	}

	public Contato(Long id, String telefone, String instagram) {

		this.id = id;

		this.telefone = telefone;

		this.instagram = instagram;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;

	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}


}
