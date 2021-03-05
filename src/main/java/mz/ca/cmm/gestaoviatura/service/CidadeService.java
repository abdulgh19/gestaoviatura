package mz.ca.cmm.gestaoviatura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ca.cmm.gestaoviatura.dominio.Cidade;
import mz.ca.cmm.gestaoviatura.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	//Registar ou actualizar
	
	@Transactional(readOnly = false)
	public void registarCidade(Cidade cidade) {		
		cidadeRepository.save(cidade);		
	}
	
	
	//Eliminar
	@Transactional(readOnly = false)	
	public void removerCidade(Long id) {
		cidadeRepository.deleteById(id);;
	}
		
	
	//buscarpor ID
	@Transactional(readOnly = true)
	public Optional<Cidade> buscarCidadePorId(Long id) {
		return cidadeRepository.findById(id);
	}
	
	//Buscar Todos	
	@Transactional(readOnly = true)	
	public List<Cidade> buscarTodasCidades() {
		return cidadeRepository.findAll();
	}


}
