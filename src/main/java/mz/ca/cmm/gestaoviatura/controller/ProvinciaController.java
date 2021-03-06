package mz.ca.cmm.gestaoviatura.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ca.cmm.gestaoviatura.dominio.Cidade;
import mz.ca.cmm.gestaoviatura.dominio.Provincia;
import mz.ca.cmm.gestaoviatura.service.ProvinciaService;

@Controller
@RequestMapping("/provincias")
public class ProvinciaController {

	@Autowired
	private ProvinciaService provinciaService;

	@GetMapping({ "/abrirpaginaPrincipal", "", "/" })
	public String abrirPaginaPrincipal(Provincia provincia) {

		return "/provincia/registo";
	}

	@PostMapping({ "/registar" })
	public String registarProvincia(@Valid Provincia provincia, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/provincia/registo";
		} else {

			try {
				provinciaService.registarProvincia(provincia);
				attr.addFlashAttribute("success", "Provincia registada com Sucesso!");
				
			} catch (Exception e) {
				
				String designacao = provincia.getDesignacao();
				if (provinciaService.provinciaExiste(designacao)) {
					attr.addFlashAttribute("fail", "A duplicacao de provincias nao eh permitida!");
				}

			}

			return "redirect:/provincias/visualizar";
		}
	}


	@GetMapping({ "/visualizar" })
	public String visualizarProvincia(ModelMap model) {

		model.addAttribute("listaDeProvincias", provinciaService.buscarTodasProvincias());
		return "/provincia/listar";
	}

	@GetMapping("/chamaRegistoParaActualizar/{id}")
	public String preActualizar(@PathVariable("id") Long id, ModelMap model) {

		model.addAttribute("provincia", provinciaService.buscarPorId(id));

		return "provincia/registo";

	}

	@PostMapping("/actualizar")
	public String actualizarProvincia(@Valid Provincia provincia, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/provincia/registo";
		}
		provinciaService.registarProvincia(provincia);
		attr.addFlashAttribute("success", "Provincia actualizada com Sucesso!");
		return "redirect:/provincias/visualizar";
	}

	@GetMapping({ "excluir/{id}" })
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (provinciaService.provinciaTemCidade(id)) {
			attr.addFlashAttribute("fail", "Provincia nao removida. Possui Cidade(s) associada(s)!");

		} else {
			provinciaService.remover(id);
			attr.addFlashAttribute("success", "Provincia excluida com Sucesso!");
		}

		return "redirect:/provincias/visualizar";
	}

}
