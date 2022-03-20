package br.com.lazaro.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lazaro.api.dto.AssociadoDTO;
import br.com.lazaro.api.dto.PartidoDTO;
import br.com.lazaro.api.service.PartidoService;
import br.com.lazaro.api.service.exception.EntityNotFoundServiceException;

@RestController
@RequestMapping("/")
public class PartidoController {

	@Autowired
	private PartidoService partidoService;

	@PostMapping("/partidos")
	public ResponseEntity<PartidoDTO> insert(@RequestBody @Valid PartidoDTO partidoDTO) {
		partidoDTO = partidoService.inserir(partidoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(partidoDTO.getIdPartido())
				.toUri();

		return ResponseEntity.created(uri).body(partidoDTO);
	}

	@GetMapping("/partidos")
	public List<PartidoDTO> findAll(String ideologia) {

		if (ideologia == null) {
			List<PartidoDTO> partidos = partidoService.findAll();
			return partidos;
		} else {
			List<PartidoDTO> partidos = partidoService.findByIdeologia(ideologia);
			return partidos;
		}

	}
	
	@GetMapping(value = "/partidos/{id}") 
	public ResponseEntity<PartidoDTO> findById(@PathVariable Long id) throws EntityNotFoundServiceException {
		PartidoDTO dto = partidoService.findById(id);
		return ResponseEntity.ok().body(dto);

	}
	
	@GetMapping(value = "/partidos/{id}/associados") 
	public List<AssociadoDTO> findByIdAssociados(@PathVariable Long id) throws EntityNotFoundServiceException {
		System.out.println(id);
		List<AssociadoDTO> listDto = partidoService.findAllAssociadosPartido(id);
		return listDto;

	}
	
	@PutMapping("/partidos/{id}")
	public ResponseEntity<PartidoDTO> update(@PathVariable Long id, @RequestBody PartidoDTO partidoDto) {
		partidoDto = partidoService.update(id, partidoDto);
		System.out.println(partidoDto.getIdPartido());
		return ResponseEntity.ok().body(partidoDto);
	}
	
	@DeleteMapping(value = "/partidos/{id}")
	public ResponseEntity<PartidoDTO> delete(@PathVariable Long id) {
		partidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
