package mz.ca.cmm.gestaoviatura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ca.cmm.gestaoviatura.dominio.Provincia;
import mz.ca.cmm.gestaoviatura.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Transactional(readOnly = false)
	public void registarProvincia(Provincia provincia) {
		provinciaRepository.save(provincia);
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		provinciaRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Provincia> buscarTodasProvincias() {
		return provinciaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Provincia buscarPorId(Long id) { // Busca Categorias por ID
		return provinciaRepository.findById(id).get();
	}
}
