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
import br.com.lazaro.api.service.AssociadoService;
import br.com.lazaro.api.service.PartidoService;
import br.com.lazaro.api.service.exception.EntityNotFoundException;

@RestController
@RequestMapping("/")
public class AssociadoController {

	@Autowired
	private AssociadoService associadoService;

	@PostMapping("/associados")
	public ResponseEntity<AssociadoDTO> insert(@RequestBody @Valid AssociadoDTO associadoDTO) {
		associadoDTO = associadoService.inserir(associadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(associadoDTO.getIdAssociado())
				.toUri();

		return ResponseEntity.created(uri).body(associadoDTO);
	}

	@GetMapping("/associados")
	public List<AssociadoDTO> findAll(String cargoPolitico) {

		if (cargoPolitico == null) {
			List<AssociadoDTO> associados = associadoService.findAll();
			return associados;
		} else {
			List<AssociadoDTO> associados = associadoService.findByCargo(cargoPolitico);
			return associados;
		}

	}
	
	@GetMapping(value = "/associados/{id}") 
	public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
		AssociadoDTO dto = associadoService.findById(id);
		return ResponseEntity.ok().body(dto);

	}
	
	@PutMapping("/associados/{id}")
	public ResponseEntity<AssociadoDTO> update(@PathVariable Long id, @RequestBody AssociadoDTO associadoDto) {
		associadoDto = associadoService.update(id, associadoDto);
		System.out.println(associadoDto.getIdAssociado());
		return ResponseEntity.ok().body(associadoDto);
	}
	
	@DeleteMapping(value = "/associados/{id}")
	public ResponseEntity<AssociadoDTO> delete(@PathVariable Long id) {
		associadoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
