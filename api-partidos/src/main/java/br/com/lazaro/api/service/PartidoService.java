package br.com.lazaro.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lazaro.api.dto.PartidoDTO;
import br.com.lazaro.api.model.Partido;
import br.com.lazaro.api.repository.PartidoRepository;

@Service
public class PartidoService {

	@Autowired
	private PartidoRepository partidoRepository;

	@Transactional
	public PartidoDTO inserir(PartidoDTO partidoDTO) {

		Partido partido = new Partido();
		partido = instanciaPartido(partidoDTO);

		partido = partidoRepository.save(partido);
		
		return new PartidoDTO(partido);
	}

	public static Partido instanciaPartido(PartidoDTO partidoDTO) {
		Partido partido = new Partido();
		partido.setNome(partidoDTO.getNome());
		partido.setSigla(partidoDTO.getSigla());
		partido.setIdeologia(partidoDTO.getIdeologia());
		partido.setDataFundacao(partidoDTO.getDataFundacao());

		return partido;
	}
	
}
