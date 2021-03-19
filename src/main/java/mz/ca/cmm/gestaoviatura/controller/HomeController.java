package mz.ca.cmm.gestaoviatura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	
	@GetMapping({"", "/"})
	public String abrirHomePage() {
		
		return "home";
	}
	
	
	
}
