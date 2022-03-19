package br.com.lazaro.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;

public class AssociadoDTO {

	private Long id;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cargoPolitico;
	@NotNull
	private LocalDate dataNascimento;
	@NotNull
	@NotEmpty
	private String sexo;

	private Partido partido;

	public AssociadoDTO() {
	}

	public AssociadoDTO(Long id, String nome, String cargoPolitico, LocalDate dataNascimento, String sexo,
			Partido partido) {
		this.id = id;
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.partido = partido;
	}

	public AssociadoDTO(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		this.dataNascimento = associado.getDataNascimento();
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
