package modelo.entidade.evento;

import java.time.LocalDate;

public class Evento {
	private Long id;
	private Long idUsuario;
	private Long idEndereco;
	private Long idAnimal;
	private LocalDate dataEvento;
	private String comentario;
	private String tipoEvento;

	public Evento() {
	}

	public Evento(Long idUsuario, Long idEndereco, Long idAnimal, LocalDate dataEvento, String comentario) {
		this(null, idUsuario, idEndereco, idAnimal, dataEvento, comentario, null);
	}

	public Evento(Long id, Long idUsuario, Long idEndereco, Long idAnimal, LocalDate dataEvento, String comentario) {
		this(id, idUsuario, idEndereco, idAnimal, dataEvento, comentario, null);
	}

	public Evento(Long idUsuario, Long idEndereco, Long idAnimal, LocalDate dataEvento, String comentario, String tipoEvento) {
		this(null, idUsuario, idEndereco, idAnimal, dataEvento, comentario, tipoEvento);
	}


	public Evento(Long id, Long idUsuario, Long idEndereco, Long idAnimal, LocalDate dataEvento, String comentario,	String tipoEvento) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idEndereco = idEndereco;
		this.idAnimal = idAnimal;
		this.dataEvento = dataEvento;
		this.comentario = comentario;
		this.tipoEvento = tipoEvento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
}
