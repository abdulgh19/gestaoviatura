package mz.ca.cmm.gestaoviatura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mz.ca.cmm.gestaoviatura.dominio.Cidade;

@Controller
public class HomeController {


	
	@GetMapping({"", "/"})
	public String abrirHomePage() {
		
		return "home";
	}
	
	
	
	
	
	
}
