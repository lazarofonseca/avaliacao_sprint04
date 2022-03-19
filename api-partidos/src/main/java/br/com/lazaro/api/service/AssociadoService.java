package br.com.lazaro.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lazaro.api.dto.AssociadoDTO;
import br.com.lazaro.api.dto.PartidoDTO;
import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;
import br.com.lazaro.api.repository.AssociadoRepository;
import br.com.lazaro.api.service.exception.EntityNotFoundException;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Transactional
	public AssociadoDTO inserir(AssociadoDTO associadoDTO) {

		Associado associado = new Associado();
		associado = instanciaAssociado(associadoDTO);

		associado = associadoRepository.save(associado);

		return new AssociadoDTO(associado);
	}

	@Transactional
	public List<AssociadoDTO> findAll() {
		List<Associado> associados = associadoRepository.findAll();;
		List<AssociadoDTO> listDTO = new ArrayList<>();

		for (Associado associado : associados) {
			AssociadoDTO associadoDTO = new AssociadoDTO();
			associadoDTO.setId(associado.getId());
			associado.setNome(associado.getNome());
			associado.setCargoPolitico(associado.getCargoPolitico());
			associado.setDataNascimento(associado.getDataNascimento());
			associado.setPartido(associado.getPartido());
			associado.setSexo(associado.getSexo());

			listDTO.add(associadoDTO);
		}

		return listDTO;
	}
	
	@Transactional
	public List<AssociadoDTO> findByCargo(String cargoPolitico) {
		List<Associado> list = associadoRepository.findByCargoPolitico(cargoPolitico);
		List<AssociadoDTO> listDTO = new ArrayList<>();

		for (Associado associado : list) {
			AssociadoDTO associadoDTO = new AssociadoDTO();
			associadoDTO.setId(associado.getId());
			associado.setNome(associado.getNome());
			associado.setCargoPolitico(associado.getCargoPolitico());
			associado.setDataNascimento(associado.getDataNascimento());
			associado.setPartido(associado.getPartido());
			associado.setSexo(associado.getSexo());

			listDTO.add(associadoDTO);
		}

		return listDTO;
	}
	
	@Transactional
	public AssociadoDTO findById(Long id) throws EntityNotFoundException {
		Optional<Associado> objModel = associadoRepository.findById(id);
		Associado model = objModel.orElseThrow(() -> new EntityNotFoundException("Partido de Id " + id + " não foi encontrado"));
		return new AssociadoDTO(model);
	}

	@Transactional
	public AssociadoDTO update(Long id, AssociadoDTO associadoDto) {
		
		try {
			Associado associado = associadoRepository.getOne(id);
			associado.setNome(associadoDto.getNome());
			associado.setCargoPolitico(associadoDto.getCargoPolitico());
			associado.setDataNascimento(associadoDto.getDataNascimento());
			associado.setPartido(associadoDto.getPartido());
			associado.setSexo(associadoDto.getSexo());
			associado = associadoRepository.save(associado);
			
			return new AssociadoDTO(associado);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + " não encontrado");
		}
	}

	public void delete(Long id) {
		try {
		associadoRepository.deleteById(id);
		
		}catch(EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + id + " não encontrado");
		}
		
	}

	public static Associado instanciaAssociado(AssociadoDTO associadoDTO) {
		Associado associado = new Associado();
		associado.setId(associadoDTO.getId());
		associado.setNome(associadoDTO.getNome());
		associado.setCargoPolitico(associado.getCargoPolitico());
		associado.setDataNascimento(associadoDTO.getDataNascimento());
		associado.setPartido(associadoDTO.getPartido());
		associado.setSexo(associadoDTO.getSexo());
		return associado;
	}




}
