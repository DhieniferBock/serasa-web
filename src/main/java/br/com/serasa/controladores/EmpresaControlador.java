package br.com.serasa.controladores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.serasa.dtos.ExportacaoDTO;
import br.com.serasa.entidades.Empresa;
import br.com.serasa.servicos.CsvServico;
import br.com.serasa.servicos.EmpresaServico;

@Controller
@RequestMapping("/")
public class EmpresaControlador {
	
	@Autowired
	private EmpresaServico empresaServico;
	
	@Autowired
	private CsvServico csvServico;
	
	@GetMapping 
	public ModelAndView listar() { 
		List<Empresa> empresas = empresaServico.listarEmpresas();
		ModelAndView modelAndView = new ModelAndView("empresas");
	    modelAndView.addObject("empresas", empresas);
	    return modelAndView;
	}
	
	@PostMapping("/exportar")
	public String exportarArquivo(@RequestParam("file") MultipartFile arquivoCsv) {
	    List<ExportacaoDTO> linhasArquivo = csvServico.converterCsvParaExportacaoDto(arquivoCsv);
	    
	    for (ExportacaoDTO linha : linhasArquivo) {
	    	 
	    	Empresa empresaImportada = 
	    			empresaServico.pegarEmpresaPeloNome(linha.getEmpresa());
	    	 
	    	if (empresaImportada == null) {
	    		empresaImportada = new Empresa(linha.getEmpresa());
	    		empresaImportada = empresaServico.salvarEmpresa(empresaImportada);
	        	empresaServico.processarImportacoes(empresaImportada, linha.getEmissoes(), linha.getDebitos());
	        	empresaServico.salvarEmpresa(empresaImportada);
	    	} else {
	    		empresaServico.processarImportacoes(empresaImportada, linha.getEmissoes(), linha.getDebitos());
	        	empresaServico.salvarEmpresa(empresaImportada);
	    	}
	    }

		return "redirect:/";
	}
	 
	
}
