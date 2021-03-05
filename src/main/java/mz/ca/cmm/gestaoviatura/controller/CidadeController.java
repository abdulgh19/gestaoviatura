package mz.ca.cmm.gestaoviatura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mz.ca.cmm.gestaoviatura.dominio.Cidade;

@Controller
@RequestMapping("/cidades")
public class CidadeController {


	
	@GetMapping({"/abrirpagiaPrincipal","", "/"})
	public String abrirTelaRegistoCidade(Cidade cidade) {
		
		return "/cidade/registo";
	}
	
	
	
	@GetMapping({"/listar"})
	public String abrirTelaQuelista() {
		
		return "/cidade/lista";
	}
	
	
	
	
	
	
}
