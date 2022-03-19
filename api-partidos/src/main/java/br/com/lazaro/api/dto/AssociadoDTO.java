package br.com.lazaro.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;

public class AssociadoDTO {

	private Long idAssociado;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cargoPolitico;
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;
	@NotNull
	@NotEmpty
	private String sexo;

	private Partido partido;

	public AssociadoDTO() {
	}

	public AssociadoDTO(Long id, String nome, String cargoPolitico, LocalDate dataNascimento, String sexo,
			Partido partido) {
		this.idAssociado = id;
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.partido = partido;
	}

	public AssociadoDTO(Associado associado) {
		this.idAssociado = associado.getIdAssociado();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		this.dataNascimento = associado.getDataNascimento();
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido();
	}

	public Long getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargoPolitico() {
		return cargoPolitico;
	}

	public void setCargoPolitico(String cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAssociado == null) ? 0 : idAssociado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssociadoDTO other = (AssociadoDTO) obj;
		if (idAssociado == null) {
			if (other.idAssociado != null)
				return false;
		} else if (!idAssociado.equals(other.idAssociado))
			return false;
		return true;
	}

}
