package mz.ca.cmm.gestaoviatura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	
	@GetMapping({"", "/"})
	public String abrirHomePage() {
		
		return "home";
	}
	
	@GetMapping({"/login"})
	public String abrirTelaDeLogin() {
		
		return "login";
	}
	
	
	@GetMapping({"/login-error"})
	public String loginError(ModelMap model) {
		
		model.addAttribute("alerta", "erro");			//La na tela de Login, se a variavel alerta estiver presente e o valor for "erro", o titulo, texto e subtexto serao mostrados
		model.addAttribute("titulo", "Crendenciais inválidas!");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
		model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já activados.");
		return "login";
	
	}
	
	
}
