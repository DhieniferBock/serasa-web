package br.com.serasa.servicos;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.serasa.SerasaWebApplication;
import br.com.serasa.entidades.Empresa;

@SpringBootTest(classes = SerasaWebApplication.class)
public class EmpresaServicoTest {
	
	@Autowired
	private EmpresaServico empresaServico;
	
	@Test
	void processarImportacoes() {
		//Given
		int resultadoEsperado = 51;
		Empresa empresa = new Empresa();
		empresa.setNome("Empresa1");
		empresa.setPontuacao(50);
		int quantidadeEmissoes = 3;
		int quantidadeDebitos = 1;
		
		//When
		empresaServico.processarImportacoes(empresa, quantidadeEmissoes, quantidadeDebitos);
		
		//Then
		assert(empresa.getPontuacao() == resultadoEsperado);
	}
	
	@Test
	void processarImportacoesMaximo() {
		//Given
		int resultadoEsperado = 100;
		Empresa empresa = new Empresa();
		empresa.setNome("Empresa1");
		empresa.setPontuacao(50);
		int quantidadeEmissoes = 100;
		int quantidadeDebitos = 0;
		
		//When
		empresaServico.processarImportacoes(empresa, quantidadeEmissoes, quantidadeDebitos);
		
		//Then
		assert(empresa.getPontuacao() == resultadoEsperado);
	}
	
	@Test
	void processarImportacoesMinimo() {
		//Given
		int resultadoEsperado = 22;
		Empresa empresa = new Empresa();
		empresa.setNome("Empresa1");
		empresa.setPontuacao(50);
		int quantidadeEmissoes = 0;
		int quantidadeDebitos = 500;
		
		//When
		empresaServico.processarImportacoes(empresa, quantidadeEmissoes, quantidadeDebitos);
		
		//Then
		assert(empresa.getPontuacao() == resultadoEsperado);
	}
	
	@Test
	public void listarEmpresa() {
		//Given
		//Testando o import.sql
				
		//When
		List<Empresa> empresas = empresaServico.listarEmpresas();
		
		//Then
		assert(empresas.size() == 4);
	}
	

}
