package mz.ca.cmm.gestaoviatura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

//

import mz.ca.cmm.gestaoviatura.dominio.Cidade;
import mz.ca.cmm.gestaoviatura.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	// Registar ou actualizar

	@Transactional(readOnly = false)
	public void registarCidade(Cidade cidade) {
		cidade.setNumero_de_vacinasCalculado(cidade.getNumero_de_habitantes() * 3);
		cidadeRepository.save(cidade);
	}

	// Eliminar
	@Transactional(readOnly = false)
	public void removerCidade(Long id) {
		cidadeRepository.deleteById(id);
	}

	// buscarpor ID
	@Transactional(readOnly = true)
	public Cidade buscarCidadePorId(Long id) {
		return cidadeRepository.findById(id).get();
	}

	// Buscar Todos
	@Transactional(readOnly = true)
	public List<Cidade> buscarTodasCidades() {
		return cidadeRepository.findAll();
	}

	public String exportarRelatorio(String formatoDoRelatorio) throws FileNotFoundException, JRException {
		String path = "C:\\Users\\abdul\\Desktop\\Relatorios";
		List<Cidade> cidades = cidadeRepository.findAll();

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:reports/rel_cidade2.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cidades);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Momade Abdul");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (formatoDoRelatorio.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\cidades.html");
		}
		if (formatoDoRelatorio.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\cidades.pdf");
		}

		return "Relatorio gerado em : " + path;
	}

}
