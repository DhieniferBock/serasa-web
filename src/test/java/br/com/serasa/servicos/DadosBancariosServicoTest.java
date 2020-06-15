package br.com.serasa.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.serasa.SerasaWebApplication;
import br.com.serasa.entidades.DadosBancarios;

@SpringBootTest(classes = SerasaWebApplication.class)
public class DadosBancariosServicoTest {
	
	@Autowired
	private DadosBancariosServico dadosBancariosServico;
	
	@Test
	public void contarDebitos() {
		//Given
		int quantidadeEmissoes = 3;
		int quantidadeDebitos = 1;
		List<DadosBancarios> dadosBancarios = 
				getListaDadosBancarios(quantidadeEmissoes, quantidadeDebitos);
		
		//When
		int dadosRetornados = dadosBancariosServico.contarDebitos(dadosBancarios);
		
		//Then
		assert(quantidadeDebitos == dadosRetornados);
	}
	
	@Test
	public void contarEmissoes() {
		//Given
		int quantidadeEmissoes = 3;
		int quantidadeDebitos = 1;
		List<DadosBancarios> dadosBancarios = 
				getListaDadosBancarios(quantidadeEmissoes, quantidadeDebitos);
		
		//When
		int dadosRetornados = dadosBancariosServico.contarEmissoesNotasFiscais(dadosBancarios);
		
		//Then
		assert(quantidadeEmissoes == dadosRetornados);
	}
	
	@Test
	public void removerLinhasVazias() {
		//Given
		int quantidadeEmissoes = 3;
		int quantidadeDebitos = 1;
		int quantidadeLinhasVazias = 4;
		List<DadosBancarios> dadosBancarios = 
				getListaDadosBancariosComLinhasVazias(quantidadeEmissoes, quantidadeDebitos, quantidadeLinhasVazias);
		
		//When
		List<DadosBancarios> linhasValidadas = dadosBancariosServico.removerLinhasVazias(dadosBancarios);
		
		//Then
		assert(linhasValidadas.size() == quantidadeEmissoes + quantidadeDebitos);
	}
	
	
	 @Test 
	 public void salvarDepoislistarDadosBancarios() { 
		 //Given 
		 int quantidadeEmissoes = 3; 
		 int quantidadeDebitos = 1; 
		 List<DadosBancarios> dadosBancarios = 
				 getListaDadosBancarios(quantidadeEmissoes, quantidadeDebitos);
		  
		 //When 
		 for(DadosBancarios linhaDadosBancarios : dadosBancarios) {
			 dadosBancariosServico.salvarDadosBancarios(linhaDadosBancarios); 
		 }
		 List<DadosBancarios> listaDadosBancarios = dadosBancariosServico.listarDadosBancarios();
		  
		 //Then 
		 assert(listaDadosBancarios.size() == dadosBancarios.size()); 
	 }
	 
	
	private List<DadosBancarios> getListaDadosBancarios(int quantidadeEmissoes, int quantidadeDebitos) {
		List<DadosBancarios> dadosBancarios = new ArrayList<DadosBancarios>();
		DadosBancarios linhaDadosBancarios;
		for (int i =0; i < quantidadeDebitos; i++) {
			linhaDadosBancarios = new DadosBancarios();
			linhaDadosBancarios.setTipo("DEBITO");
			dadosBancarios.add(linhaDadosBancarios);
		}
		for (int i =0; i < quantidadeEmissoes; i++) {
			linhaDadosBancarios = new DadosBancarios();
			linhaDadosBancarios.setTipo("EMISSAO");
			dadosBancarios.add(linhaDadosBancarios);
		}
		return dadosBancarios;
	}
	
	private List<DadosBancarios> getListaDadosBancariosComLinhasVazias(
			int quantidadeEmissoes, 
			int quantidadeDebitos, 
			int quantidadeLinhasVazias) {
		List<DadosBancarios> dadosBancarios = getListaDadosBancarios(quantidadeEmissoes, quantidadeDebitos);
		DadosBancarios linhaDadosBancarios;
		for (int i =0; i < quantidadeLinhasVazias; i++) {
			linhaDadosBancarios = new DadosBancarios();
			dadosBancarios.add(linhaDadosBancarios);
		}
		return dadosBancarios;
	}

}
