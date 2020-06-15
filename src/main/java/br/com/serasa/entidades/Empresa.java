package br.com.serasa.entidades;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int pontuacao;
	@OneToMany(mappedBy = "empresa")
	private List<DadosBancarios> dadosBancarios;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public List<DadosBancarios> getDadosBancarios() {
		return dadosBancarios;
	}
	public void setDadosBancarios(List<DadosBancarios> dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}
	public Empresa() {
	}
	public Empresa(Long id, String nome, int pontuacao, List<DadosBancarios> dadosBancarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.pontuacao = pontuacao;
		this.dadosBancarios = dadosBancarios;
	}
}

