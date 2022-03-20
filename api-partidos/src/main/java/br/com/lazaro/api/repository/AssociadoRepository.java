package br.com.lazaro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lazaro.api.dto.AssociadoDTO;
import br.com.lazaro.api.model.Associado;
import br.com.lazaro.api.model.Partido;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>{

	List<Associado> findByCargoPolitico(String cargoPolitico);

}
