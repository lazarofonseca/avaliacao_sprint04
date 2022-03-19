package br.com.lazaro.api.dto;

public class AssociadoDtoPartidoDto {

	private Long idAssociado;
	private Long idPartido;

	public AssociadoDtoPartidoDto() {
	}

	public AssociadoDtoPartidoDto(Long idAssociado, Long idPartido) {
		this.idAssociado = idAssociado;
		this.idPartido = idPartido;
	}

	public Long getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

}
