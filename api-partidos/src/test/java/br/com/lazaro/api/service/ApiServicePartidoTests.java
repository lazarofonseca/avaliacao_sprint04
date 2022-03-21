package br.com.lazaro.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
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
import br.com.lazaro.api.dto.PartidoDTO;
import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;
import br.com.lazaro.api.repository.PartidoRepository;

@ExtendWith(SpringExtension.class)
public class ApiServicePartidoTests {

	@InjectMocks
	private PartidoService service;

	@Mock
	private PartidoDTO partidodoDTO;
	
	@Mock
	private Partido partido;

	@Mock
	private PartidoRepository repository;

	private Long idExistente;
	private Long idNaoExiste;
	private Long idErroIntegridade;


	@BeforeEach
	void inicializaId() throws Exception {

		instanciaPartidoDTO();

		idExistente = 1L;
		idNaoExiste = 10000L;
		idErroIntegridade = 50L;

		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idNaoExiste);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idErroIntegridade);
	}

	@Test
	public void deveriaBuscarTodosPartidos() {

		Mockito.when(repository.findAll()).thenReturn(List.of(partido));

		List<PartidoDTO> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(PartidoDTO.class, response.get(0).getClass());
		assertEquals(1L, response.get(0).getIdPartido());
		assertEquals("Partido Neutro", response.get(0).getNomePartido());
		assertEquals("PN", response.get(0).getSigla());
		assertEquals("Centro", response.get(0).getIdeologia());

	}

	@Test
	public void deveriaDeletarQuandoIdPartidoExistir() {

		Assertions.assertDoesNotThrow(() -> {
			service.delete(idExistente);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(idExistente);

	}

	@Test
	public void deveriaLancarExcecaoParaIdNaoExistente() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			service.delete(idNaoExiste);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(idNaoExiste);

	}

	@Test
	public void deveriaLancarExcecaoParaErroDeIntegridadeDeDados() {

		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			service.delete(idErroIntegridade);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(idErroIntegridade);

	}
	
	

	private void instanciaPartidoDTO() {

		PartidoDTO partido = new PartidoDTO(1L, "Partido Neutro", "PN", "Centro", LocalDate.now());

	}

	private void instanciaPartido() {

		PartidoDTO partido = new PartidoDTO(1L, "Partido Neutro", "PN", "Centro", LocalDate.now());

	}

}
