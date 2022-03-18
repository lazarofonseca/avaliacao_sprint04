package br.com.lazaro.api.service;

import java.util.ArrayList;
import java.util.List;

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

	@Transactional
	public List<PartidoDTO> findAll() {
		List<Partido> list = partidoRepository.findAll();
		List<PartidoDTO> listDTO = new ArrayList<>();

		for (Partido partido : list) {
			PartidoDTO partidoDTO = new PartidoDTO();
			partidoDTO.setId(partido.getId());
			partidoDTO.setNome(partido.getNome());
			partidoDTO.setSigla(partido.getSigla());
			partidoDTO.setIdeologia(partido.getIdeologia());
			partidoDTO.setDataFundacao(partido.getDataFundacao());

			listDTO.add(partidoDTO);
		}

		return listDTO;
	}
	
	@Transactional
	public List<PartidoDTO> findByIdeologia(String ideologia) {
		List<Partido> list = partidoRepository.findByIdeologia(ideologia);
		List<PartidoDTO> listDTO = new ArrayList<>();

		for (Partido partido : list) {
			PartidoDTO partidoDTO = new PartidoDTO();
			partidoDTO.setId(partido.getId());
			partidoDTO.setNome(partido.getNome());
			partidoDTO.setSigla(partido.getSigla());
			partidoDTO.setIdeologia(partido.getIdeologia());
			partidoDTO.setDataFundacao(partido.getDataFundacao());

			listDTO.add(partidoDTO);
		}

		return listDTO;
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
