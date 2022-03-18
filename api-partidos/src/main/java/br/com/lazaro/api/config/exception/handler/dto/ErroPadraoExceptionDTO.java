package br.com.lazaro.api.config.exception.handler.dto;

import java.time.Instant;

public class ErroPadraoExceptionDTO {

	private Integer status;
	private String mensagem;
	private String path;
	private Instant instant;
	private String error;

	public ErroPadraoExceptionDTO() {
	}

	public ErroPadraoExceptionDTO(Integer status, String mensagem, String path, Instant instant, String error) {
		this.status = status;
		this.mensagem = mensagem;
		this.path = path;
		this.instant = instant;
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
