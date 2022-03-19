package br.com.lazaro.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lazaro.api.dto.PartidoDTO;
import br.com.lazaro.api.model.Partido;
import br.com.lazaro.api.repository.PartidoRepository;
import br.com.lazaro.api.service.exception.EntityNotFoundException;

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
			partidoDTO.setNomePartido(partido.getNomePartido());
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
			partidoDTO.setNomePartido(partido.getNomePartido());
			partidoDTO.setSigla(partido.getSigla());
			partidoDTO.setIdeologia(partido.getIdeologia());
			partidoDTO.setDataFundacao(partido.getDataFundacao());

			listDTO.add(partidoDTO);
		}

		return listDTO;
	}
	
	@Transactional
	public PartidoDTO findById(Long id) throws EntityNotFoundException {
		Optional<Partido> objModel = partidoRepository.findById(id);
		Partido model = objModel.orElseThrow(() -> new EntityNotFoundException("Partido de Id " + id + " não foi encontrado"));
		return new PartidoDTO(model);
	}

	@Transactional
	public PartidoDTO update(Long id, PartidoDTO partidoDto) {
		
		try {
			Partido partido = partidoRepository.getOne(id);
			System.out.println(partido.getId());
			partido.setNomePartido(partidoDto.getNomePartido());
			partido.setIdeologia(partidoDto.getIdeologia());
			partido.setSigla(partidoDto.getSigla());
			partido.setDataFundacao(partidoDto.getDataFundacao());
			partido.setAssociados(partidoDto.getAssociados());
			partido = partidoRepository.save(partido);
			
			return new PartidoDTO(partido);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + " não encontrado");
		}
	}

	public void delete(Long id) {
		try {
		partidoRepository.deleteById(id);
		
		}catch(EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + id + " não encontrado");
		}
		
	}

	public static Partido instanciaPartido(PartidoDTO partidoDTO) {
		Partido partido = new Partido();
		partido.setId(partidoDTO.getId());
		partido.setNomePartido(partidoDTO.getNomePartido());
		partido.setSigla(partidoDTO.getSigla());
		partido.setIdeologia(partidoDTO.getIdeologia());
		partido.setDataFundacao(partidoDTO.getDataFundacao());
		partido.setAssociados(partidoDTO.getAssociados());
		return partido;
	}




}
