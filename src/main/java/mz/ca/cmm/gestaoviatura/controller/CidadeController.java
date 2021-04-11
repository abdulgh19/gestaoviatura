package mz.ca.cmm.gestaoviatura.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ca.cmm.gestaoviatura.dominio.Cidade;
import mz.ca.cmm.gestaoviatura.dominio.Provincia;
import mz.ca.cmm.gestaoviatura.service.CidadeService;
import mz.ca.cmm.gestaoviatura.service.ProvinciaService;

@Controller
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private ProvinciaService proviciaService;

	@GetMapping({ "/abrirpaginaPrincipal", "", "/" })
	public String abrirPaginaPrincipal(Cidade cidade) {

		return "/cidade/registo";
	}

	@PostMapping({ "/registar" })
	public String registarCidade(@Valid Cidade cidade, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/cidade/registo";
		}
		cidadeService.registarCidade(cidade);

		attr.addFlashAttribute("success", "Cidade registada com Sucesso");
		return "redirect:/cidades/visualizar";
	}

	@GetMapping({ "/visualizar" })
	public String visualizarCidade(ModelMap model) {

		model.addAttribute("listaDeCidades", cidadeService.buscarTodasCidades());
		return "/cidade/lista";
	}

	@ModelAttribute("listaDeProvincias")
	public List<Provincia> listarProvincias() {

		return proviciaService.buscarTodasProvincias();
	}

	@GetMapping("/chamaRegistoParaActualizar/{id}")
	public String preActualizar(@PathVariable("id") Long id, ModelMap model) {

		model.addAttribute("cidade", cidadeService.buscarCidadePorId(id));

		return "cidade/registo";

	}

	@PostMapping("/actualizar")
	public String actualizarCidade(@Valid Cidade cidade, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/cidade/registo";
		}
		cidadeService.registarCidade(cidade);
		attr.addFlashAttribute("success", "Cidade actualizada com Sucesso!");
		return "redirect:/cidades/visualizar";
	}

	@GetMapping({"excluir/{id}"})
	public String excluir(@PathVariable("id") Long id,  RedirectAttributes attr) {

		cidadeService.removerCidade(id);
		attr.addFlashAttribute("success", "Cidade excluida com Sucesso!");

		return "redirect:/cidades/visualizar";
	}

}
