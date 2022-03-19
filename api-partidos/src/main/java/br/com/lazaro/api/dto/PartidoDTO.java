package br.com.lazaro.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;

public class PartidoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Long idPartido;

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
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataFundacao;

	List<Associado> associados = new ArrayList<>();

	public PartidoDTO() {
	}

	public PartidoDTO(Long id, String nomePartido, String sigla, String ideologia, LocalDate dataFundacao) {
		this.idPartido = id;
		this.nomePartido = nomePartido;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
	}

	public PartidoDTO(Partido partido) {
		this.idPartido = partido.getIdPartido();
		this.nomePartido = partido.getNomePartido();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
		this.associados = partido.getAssociados();
	}
	
	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
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
