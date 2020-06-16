package br.com.serasa.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.serasa.entidades.DadosBancarios;
import br.com.serasa.entidades.Empresa;
import br.com.serasa.servicos.CsvServico;
import br.com.serasa.servicos.DadosBancariosServico;
import br.com.serasa.servicos.EmpresaServico;

@Controller
@RequestMapping("/dados-bancarios")
public class DadosBancariosControlador {
	
	@Autowired
	private EmpresaServico empresaServico;
	
	@Autowired
	private DadosBancariosServico dadosBancariosServico;
	
	@Autowired
	private CsvServico csvServico;
	
	@GetMapping("/incluir")
	public ModelAndView incluirDadosBancarios(
			@RequestParam(value ="erro", required = false) String erro) { 
		List<Empresa> empresas = empresaServico.listarEmpresas();
		ModelAndView modelAndView = new ModelAndView("incluir-dados-bancarios");
	    modelAndView.addObject("empresas", empresas);
	    modelAndView.addObject("erro", erro);
	    return modelAndView;
	}
	
	@GetMapping("/listar")
	public ModelAndView listarDadosBancarios() { 
		List<Empresa> empresas = empresaServico.listarEmpresas();
		ModelAndView modelAndView = new ModelAndView("listar-dados-bancarios");
	    modelAndView.addObject("empresas", empresas);
	    return modelAndView;
	}
	
	@PostMapping("/exportar")
	public String exportarArquivo(@RequestParam("file") MultipartFile arquivoCsv, @RequestParam("empresa") int idEmpresa) {
		if (!csvServico.validarFormatoArquivo(arquivoCsv)) {
			return "redirect:/dados-bancarios/incluir?erro=O arquivo deve ser conforme o modelo no formato .csv";
		}
	    List<DadosBancarios> linhasArquivo = csvServico.converterCsvParaDadosBancarios(arquivoCsv);
	    linhasArquivo = dadosBancariosServico.removerLinhasVazias(linhasArquivo);
	    Empresa empresa = empresaServico.pegarEmpresaPeloId(idEmpresa);
	    int totalEmissoes = dadosBancariosServico.contarEmissoesNotasFiscais(linhasArquivo);
	    int totalDebitos = dadosBancariosServico.contarDebitos(linhasArquivo);
	    for (DadosBancarios linha : linhasArquivo) {
	    	linha.setEmpresa(empresa);
	    	dadosBancariosServico.salvarDadosBancarios(linha);
	    }
    	empresaServico.processarImportacoes(empresa, totalEmissoes, totalDebitos);
    	empresaServico.salvarEmpresa(empresa);
		return "redirect:/dados-bancarios/listar";
	}

}
