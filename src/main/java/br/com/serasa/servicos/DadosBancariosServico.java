package br.com.serasa.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.serasa.entidades.DadosBancarios;
import br.com.serasa.repositorios.DadosBancariosRepositorio;

@Service
public class DadosBancariosServico {

	@Autowired
	DadosBancariosRepositorio dadosBancariosRepositorio;
	
	public List<DadosBancarios> listarDadosBancarios() {
		return dadosBancariosRepositorio.findAll(Sort.by(Sort.Direction.ASC, "data"));
	}
	
	public DadosBancarios salvarDadosBancarios(DadosBancarios dadosBancarios) {
		return dadosBancariosRepositorio.save(dadosBancarios);
	}
	
	public List<DadosBancarios> removerLinhasVazias(List<DadosBancarios> dadosBancarios) {
		List<DadosBancarios> dadosBancariosValidados = new ArrayList<DadosBancarios>();
		for (DadosBancarios dados : dadosBancarios) {
			if (dados.getTipo() != null && !dados.getTipo().isEmpty()) {
				dadosBancariosValidados.add(dados);
			}
		}
		return dadosBancariosValidados;
	}
	
	public int contarEmissoesNotasFiscais(List<DadosBancarios> dadosBancarios) {
		int totalEmissoes = 0;
		for (DadosBancarios dados : dadosBancarios) {
			if (dados.getTipo().equalsIgnoreCase("EMISSAO")) {
				totalEmissoes++;
			}
		}
		return totalEmissoes;
	}
	
	public int contarDebitos(List<DadosBancarios> dadosBancarios) {
		int totalDebitos = 0;
		for (DadosBancarios dados : dadosBancarios ) {
			if (dados.getTipo().equalsIgnoreCase("DEBITO")) {
				totalDebitos++;
			}
		}
		return totalDebitos;
	}
	
}
