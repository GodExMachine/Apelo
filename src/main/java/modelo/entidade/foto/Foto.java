package modelo.entidade.foto;

public class Foto {

	private Long idFoto;
	private Long idUsuario;
	private Long idAnimal;
	private Long idEvento;
	private byte[] byteFoto;
	private String extensaoFoto;

	public Foto() {
	}

	public Foto(Long idFoto, Long idUsuario, Long idAnimal, Long idEvento, byte[] byteFoto, String extensaoFoto) {
		this.idFoto = idFoto;
		this.idUsuario = idUsuario;
		this.idAnimal = idAnimal;
		this.idEvento = idEvento;
		this.byteFoto = byteFoto;
		this.extensaoFoto = extensaoFoto;
	}

	public Foto(Long idUsuario, Long idAnimal, Long idEvento, byte[] byteFoto, String extensaoFoto) {
		this.idUsuario = idUsuario;
		this.idAnimal = idAnimal;
		this.idEvento = idEvento;
		this.byteFoto = byteFoto;
		this.extensaoFoto = extensaoFoto;
	}

	public Foto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public Long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public byte[] getByteFoto() {
		return byteFoto;
	}

	public void setByteFoto(byte[] byteFoto) {
		this.byteFoto = byteFoto;
	}

	public String getExtensaoFoto() {
		return extensaoFoto;
	}

	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
	}
}
