package br.com.serasa.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DadosBancarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String data;
	private String tipo;
	private String valor;
	@ManyToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public DadosBancarios() {
		super();
	}
	public DadosBancarios(Long id, String descricao, String data, String tipo, String valor, Empresa empresa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.tipo = tipo;
		this.valor = valor;
		this.empresa = empresa;
	}	
}
