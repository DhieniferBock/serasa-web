package br.com.serasa.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
	
	public Empresa pegarEmpresaPeloId(int id) {
		long idLong = id;
		Optional<Empresa> empresa = empresaRepositorio.findById(idLong); 
		if (empresa.isEmpty()) {
			return null;
		}
		return empresa.get();
	}
	
	public Empresa salvarEmpresa(Empresa empresa) {
		return empresaRepositorio.save(empresa);
	}
	
	public void processarImportacoes(Empresa empresa, int notasFiscais, int debitos) {
		processarNotasFiscais(empresa, notasFiscais);
		processarDebitos(empresa, debitos);
	}
	
	private void processarNotasFiscais (Empresa empresa, int notasFiscais) {
		final int pontuacaoMaxima = 100;
		final int porcentagemAumentadaPorNotaFiscal = 2;
		for(int i = 0; i < notasFiscais; i++) {
			if(empresa.getPontuacao() == 100) {
				break;
			}
			double resultadoPorcentagem = (empresa.getPontuacao() * porcentagemAumentadaPorNotaFiscal) / 100;
			double novaPontuacao = empresa.getPontuacao()  + resultadoPorcentagem;
			int novaPontuacaoInteira = (int) Math.floor(novaPontuacao);
			if (novaPontuacao >= 100) {
				empresa.setPontuacao(pontuacaoMaxima);
			} else {
				empresa.setPontuacao(novaPontuacaoInteira);
			}
		}
	}
	
	private void processarDebitos (Empresa empresa, int debitos) {
		final int pontuacaoMinima = 1;
		final BigDecimal porcentagemDiminuidaPorDebito = new BigDecimal(4);
		for(int i = 0; i < debitos; i++) {
			if(empresa.getPontuacao() == 1) {
				break;
			}
			
			BigDecimal pontuacaoEmpresa = new BigDecimal(empresa.getPontuacao());
			BigDecimal porcentagem = new BigDecimal(100);
			BigDecimal porcentagemMultiplicacao = pontuacaoEmpresa.multiply(porcentagemDiminuidaPorDebito);
			BigDecimal resultadoPorcentagem = porcentagemMultiplicacao.divide(porcentagem);
			BigDecimal novaPontuacaoCompleta = pontuacaoEmpresa.subtract(resultadoPorcentagem);
			BigDecimal novaPontuacaoUmaCasaDecimal = novaPontuacaoCompleta.setScale(1, RoundingMode.FLOOR);
			BigDecimal novaPontuacaoArredondada = novaPontuacaoUmaCasaDecimal.setScale(0, RoundingMode.CEILING);
			
			int novaPontuacaoInteira = novaPontuacaoArredondada.intValue();
			if (novaPontuacaoInteira <= 1) {
				empresa.setPontuacao(pontuacaoMinima);
			} else {
				empresa.setPontuacao(novaPontuacaoInteira);
			}			
		}
	}
	
}
