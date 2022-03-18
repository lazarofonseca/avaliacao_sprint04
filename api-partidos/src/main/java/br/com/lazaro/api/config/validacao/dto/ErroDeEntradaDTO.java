package br.com.lazaro.api.config.validacao.dto;

public class ErroDeEntradaDTO {

	private String campo;
	private String erro;

	public ErroDeEntradaDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
