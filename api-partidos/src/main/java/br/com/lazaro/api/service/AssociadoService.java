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
import br.com.lazaro.api.repository.PartidoRepository;
import br.com.lazaro.api.service.exception.EntityNotFoundException;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	@Transactional
	public AssociadoDTO inserir(AssociadoDTO associadoDTO) {

		Associado associado = new Associado();
		associado = instanciaAssociado(associadoDTO);

		associado = associadoRepository.save(associado);

		return new AssociadoDTO(associado);
	}

	@Transactional
	public AssociadoDTO inserirAssociadoAoPartido(Long idAssociado, Long idPartido) {

		Optional<Associado> associadoDTO = associadoRepository.findById(idAssociado);
		Optional<Partido> partidoDTO = partidoRepository.findById(idPartido);
		//List<Associado> listaAssociados = new ArrayList<>();
		
		Associado socioModelo = associadoDTO.orElseThrow(() -> new EntityNotFoundException("Associado de Id " + idAssociado + " não foi encontrado"));
		Partido partidoModelo = partidoDTO.orElseThrow(() -> new EntityNotFoundException("Partido de Id " + idPartido + " não foi encontrado"));
		
		//listaAssociados.add(socioModelo);
		
		AssociadoDTO socioDTO = new AssociadoDTO(socioModelo);
		System.out.println(socioDTO.getNome());
		socioDTO.setPartido(partidoModelo);
		System.out.println(socioDTO.getPartido().getSigla());
		PartidoDTO partidoDTO2 = new PartidoDTO(partidoModelo);
		
		socioModelo.setPartido(partidoModelo);
		
		//partidoModelo.setAssociados(listaAssociados);

		Associado associado = new Associado();
		associado = instanciaAssociado(socioDTO);
		Partido partido = new Partido();
		partido = instanciaPartido(partidoDTO2);
		
		partido = partidoRepository.save(partido);
		associado = associadoRepository.save(socioModelo);

		System.out.println(partido.getAssociados().get(0));
		
		return new AssociadoDTO(associado);
	}

	@Transactional
	public List<AssociadoDTO> findAll() {
		List<Associado> list = associadoRepository.findAll();
		List<AssociadoDTO> listDTO = new ArrayList<>();

		for (Associado associado : list) {
			AssociadoDTO associadoDTO = new AssociadoDTO();
			associadoDTO.setIdAssociado(associado.getIdAssociado());
			associadoDTO.setPartido(associado.getPartido());
			associadoDTO.setCargoPolitico(associado.getCargoPolitico());
			associadoDTO.setDataNascimento(associado.getDataNascimento());
			associadoDTO.setSexo(associado.getSexo());
			associadoDTO.setNome(associado.getNome());

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
			associadoDTO.setIdAssociado(associado.getIdAssociado());
			associadoDTO.setPartido(associado.getPartido());
			associadoDTO.setCargoPolitico(associado.getCargoPolitico());
			associadoDTO.setDataNascimento(associado.getDataNascimento());
			associadoDTO.setNome(associado.getNome());
			associadoDTO.setSexo(associado.getSexo());

			listDTO.add(associadoDTO);
		}

		return listDTO;
	}

	@Transactional
	public AssociadoDTO findById(Long id) throws EntityNotFoundException {
		Optional<Associado> objModel = associadoRepository.findById(id);
		Associado model = objModel
				.orElseThrow(() -> new EntityNotFoundException("Associado de Id " + id + " não foi encontrado"));
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
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + " não encontrado");
		}
	}

	public void delete(Long id) {
		try {
			associadoRepository.deleteById(id);

		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + id + " não encontrado");
		}

	}

	public static Associado instanciaAssociado(AssociadoDTO associadoDTO) {
		Associado associado = new Associado();
		associado.setIdAssociado(associadoDTO.getIdAssociado());
		associado.setNome(associadoDTO.getNome());
		associado.setCargoPolitico(associadoDTO.getCargoPolitico());
		associado.setDataNascimento(associadoDTO.getDataNascimento());
		associado.setPartido(associadoDTO.getPartido());
		associado.setSexo(associadoDTO.getSexo());
		return associado;
	}

	public static Partido instanciaPartido(PartidoDTO partidoDTO) {
		Partido partido = new Partido();
		partido.setIdPartido(partidoDTO.getIdPartido());
		partido.setNomePartido(partidoDTO.getNomePartido());
		partido.setSigla(partidoDTO.getSigla());
		partido.setIdeologia(partidoDTO.getIdeologia());
		partido.setDataFundacao(partidoDTO.getDataFundacao());
		partido.setAssociados(partidoDTO.getAssociados());
		return partido;
	}

}
