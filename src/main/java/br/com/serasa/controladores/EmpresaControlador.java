package br.com.serasa.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.serasa.entidades.Empresa;
import br.com.serasa.servicos.EmpresaServico;

@Controller
@RequestMapping("/")
public class EmpresaControlador {
	
	@Autowired
	private EmpresaServico empresaServico;
	
	@GetMapping(path = "/")
	public String inicio() { 
		return "redirect:/ranking";
	}	
	
	@GetMapping(path = "/ranking")
	public ModelAndView ranking() { 
		List<Empresa> empresas = empresaServico.listarEmpresas();
		ModelAndView modelAndView = new ModelAndView("ranking");
	    modelAndView.addObject("empresas", empresas);
	    return modelAndView;
	}
	
}
