package mz.ca.cmm.gestaoviatura.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyErrorView implements ErrorViewResolver{

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		
		/*
		 * map.forEach((k,v) -> System.out.println(k + ":" + v + "\n"));
		 */	
		
		ModelAndView model = new ModelAndView("error");
		model.addObject("status", status.value());
		switch (status.value()) {
		case 404:
			model.addObject("error", "Pagina na encontrada");
			model.addObject("message", "A URL da pagina" + map.get("path") + "' nao existe!");
			break;	
		case 500:
			model.addObject("error", "Ocorreu um erro interno no servidor");
			model.addObject("message", "Ocorreu um erro inesperado, tente mais tarde!");
			break;
		case 403:
			model.addObject("error", "Ocorreu um erro interno no servidor");
			model.addObject("message", "NAO TENS PERMISSAO PARA TAL RECURSO");
			break;
		default:
			model.addObject("error", map.get("error"));
			model.addObject("message", map.get("message"));
			break;
		}
		
		return model;
	}

}
