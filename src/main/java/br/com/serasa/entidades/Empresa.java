package br.com.serasa.entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int pontuacao;
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
	
	public Empresa(Long id, String nome, int pontuacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	
	public Empresa(String nome) {
		final int pontuacaoInicialEmpresa = 50;
		this.nome = nome;
		this.pontuacao = pontuacaoInicialEmpresa;
	}
	
	public Empresa() {
	}
	
}

