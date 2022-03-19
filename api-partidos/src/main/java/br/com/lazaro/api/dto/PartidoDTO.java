package br.com.lazaro.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;

public class PartidoDTO {

	private Long id;

	@NotNull
	@NotEmpty
	private String nomePartido;

	@NotNull
	@NotEmpty
	private String sigla;

	@NotNull
	@NotEmpty
	private String ideologia;

	@NotNull
	private LocalDate dataFundacao;

	List<Associado> associados = new ArrayList<>();

	public PartidoDTO() {
	}

	public PartidoDTO(Long id, String nomePartido, String sigla, String ideologia, LocalDate dataFundacao) {
		this.id = id;
		this.nomePartido = nomePartido;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
	}

	public PartidoDTO(Partido partido) {
		this.id = partido.getId();
		this.nomePartido = partido.getNomePartido();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
		this.associados = partido.getAssociados();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePartido() {
		return nomePartido;
	}

	public void setNomePartido(String nomePartido) {
		this.nomePartido = nomePartido;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(String ideologia) {
		this.ideologia = ideologia;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public List<Associado> getAssociados() {
		return associados;
	}

	public void setAssociados(List<Associado> associados) {
		this.associados = associados;
	}

}
