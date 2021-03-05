package mz.ca.cmm.gestaoviatura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ca.cmm.gestaoviatura.dominio.Marca;
import mz.ca.cmm.gestaoviatura.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	
	//Registar ou actualizar
	
	@Transactional(readOnly = false)
	public void registarMarca(Marca marca) {		
		marcaRepository.save(marca);		
	}
	
	
	//Eliminar
	@Transactional(readOnly = false)	
	public void removerMarca(Long id) {
		marcaRepository.deleteById(id);;
	}
		
	
	//buscarpor ID
	@Transactional(readOnly = true)
	public Marca buscarMarcaPorId(Long id) {
		return marcaRepository.findById(id).get();
	}
	
	//Buscar Todos	
	@Transactional(readOnly = true)	
	public List<Marca> buscarTodasMarcas() {
		return marcaRepository.findAll();
	}


}
