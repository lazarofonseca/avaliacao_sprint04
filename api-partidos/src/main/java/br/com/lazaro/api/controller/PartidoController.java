package br.com.lazaro.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lazaro.api.dto.PartidoDTO;
import br.com.lazaro.api.service.PartidoService;

@RestController
@RequestMapping("/")
public class PartidoController {
	
	@Autowired
	private PartidoService partidoService;
	
	@PostMapping("/partidos")
	public ResponseEntity<PartidoDTO> inserir(@RequestBody @Valid PartidoDTO partidoDTO){
		partidoDTO = partidoService.inserir(partidoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(partidoDTO.getId())
		.toUri();
		
		return ResponseEntity.created(uri).body(partidoDTO);
	}

}
