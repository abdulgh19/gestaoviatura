package mz.ca.cmm.gestaoviatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.ca.cmm.gestaoviatura.dominio.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
		
}
