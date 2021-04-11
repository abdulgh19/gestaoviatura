package mz.ca.cmm.gestaoviatura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mz.ca.cmm.gestaoviatura.dominio.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	@Query("select p from Provincia p where p.designacao like :designacao")
	Provincia findByDesignacao(String designacao);
		
}
