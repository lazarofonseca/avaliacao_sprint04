package br.com.lazaro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lazaro.api.model.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{

	List<Partido> findByIdeologia(String ideologia);

}
