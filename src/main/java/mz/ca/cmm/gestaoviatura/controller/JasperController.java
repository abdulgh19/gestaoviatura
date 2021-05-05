package mz.ca.cmm.gestaoviatura.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mz.ca.cmm.gestaoviatura.service.JasperService;


@Controller
@RequestMapping("/relatorios")
public class JasperController {

	@Autowired
	private JasperService service;

	@GetMapping("/reports")
	public String abrirReports() {
		return "reports";
	}
	
	
	@GetMapping("/pdf/jr1")
	public void exibirRelatorio01(@RequestParam("code") String code,
								  @RequestParam("acao") String acao,
								  HttpServletResponse response) throws IOException {
		
		byte[] bytes = service.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if (acao.equals("v")) {
			response.setHeader("Content-disposition", "inline; filename=relatorio-"+code+".pdf"); 
			}
		else {
			
			response.setHeader("Content-disposition", "attachment; filename=relatorio-"+code+".pdf");
			
		}
		response.getOutputStream().write(bytes);
		
		
	}
   
    

}
