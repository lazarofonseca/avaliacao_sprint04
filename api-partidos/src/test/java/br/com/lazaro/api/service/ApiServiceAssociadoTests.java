package br.com.lazaro.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.lazaro.api.dto.AssociadoDTO;
import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.repository.AssociadoRepository;
import br.com.lazaro.api.service.exception.EntityNotFoundServiceException;

@ExtendWith(SpringExtension.class)
public class ApiServiceAssociadoTests {

	@InjectMocks
	private AssociadoService service;
	
	private AssociadoDTO associadoDTO;
	
	private Associado associado;
	
	

	@Mock
	private AssociadoRepository repository;

	private Long idExistente;
	private Long idNaoExiste;
	private Long idErroIntegridade;
	private String nomeAssociado;
	private String cargoPolitico;
	private String sexo;

	@BeforeEach
	void inicializaId() throws Exception {
		
		
		nomeAssociado = "Lázaro";
		cargoPolitico = "Vereador";
		sexo = "Vereador";
		idExistente = 1L;
		idNaoExiste = 10000L;
		idErroIntegridade = 50L;
		

		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idNaoExiste);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idErroIntegridade);
	}
	
	

	@Test
	public void deveriaDeletarQuandoIdExistirSemAssociacaoAoPartido() {

		Assertions.assertDoesNotThrow(() -> {
			service.delete(idExistente);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(idExistente);

	}

	@Test
	public void deveriaLancarExcecaoParaIdNaoExistente() {

		Assertions.assertThrows(EntityNotFoundServiceException.class, () -> {
			service.delete(idNaoExiste);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(idNaoExiste);

	}

	@Test
	public void deveriaLancarExcecaoParaErroDeIntegridadeDeDados() {

		Assertions.assertThrows(EntityNotFoundServiceException.class, () -> {
			service.delete(idErroIntegridade);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(idErroIntegridade);

	}

}
