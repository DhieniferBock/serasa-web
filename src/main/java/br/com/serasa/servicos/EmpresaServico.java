package br.com.serasa.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.serasa.entidades.Empresa;
import br.com.serasa.repositorios.EmpresaRepositorio;

@Service
public class EmpresaServico {

	@Autowired
	EmpresaRepositorio empresaRepositorio;
	
	public List<Empresa> listarEmpresas() {
		return empresaRepositorio.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Empresa pegarEmpresaPeloNome(String nome) {
		return empresaRepositorio.findByNomeLike(nome);
	}
	
	public Empresa salvarEmpresa(Empresa empresa) {
		return empresaRepositorio.save(empresa);
	}
	
	public void processarImportacoes(Empresa empresa, int notasFiscais, int debitos) {
		System.out.println("	###	"+empresa.getNome());
		processarNotasFiscais(empresa, notasFiscais);
		System.out.println("Após notas fiscais: " + empresa.getPontuacao());
		processarDebitos(empresa, debitos);
		System.out.println("Após débitos: " + empresa.getPontuacao());
	}
	
	private void processarNotasFiscais (Empresa empresa, int notasFiscais) {
		final int pontuacaoMaxima = 100;
		final int porcentagemAumentadaPorNotaFiscal = 2;
		for(int i = 0; i < notasFiscais; i++) {
			if(empresa.getPontuacao() == 100) {
				break;
			}
			double resultadoPorcentagem = (empresa.getPontuacao() * porcentagemAumentadaPorNotaFiscal) / 100;
			int novaPontuacao = (int) (empresa.getPontuacao()  + Math.ceil(resultadoPorcentagem));
			
			if (novaPontuacao >= 100) {
				empresa.setPontuacao(pontuacaoMaxima);
			} else {
				empresa.setPontuacao(novaPontuacao);
			}
		}
	}
	
	private void processarDebitos (Empresa empresa, int debitos) {
		final int pontuacaoMinima = 1;
		final int porcentagemDiminuidaPorDebito = 4;
		for(int i = 0; i < debitos; i++) {
			if(empresa.getPontuacao() == 1) {
				break;
			}
			double resultadoPorcentagemDeb = (empresa.getPontuacao() * porcentagemDiminuidaPorDebito) / 100;
			int novaPontuacao = (int) (empresa.getPontuacao() - Math.floor(resultadoPorcentagemDeb));
			if (novaPontuacao <= 1) {
				empresa.setPontuacao(pontuacaoMinima);
			} else {
				empresa.setPontuacao(novaPontuacao);
			}			
		}
	}
	
}
