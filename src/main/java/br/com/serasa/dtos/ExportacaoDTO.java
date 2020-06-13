package br.com.serasa.dtos;

public class ExportacaoDTO {
	
	private String empresa;
	private int emissoes;
	private int debitos;
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public int getEmissoes() {
		return emissoes;
	}
	public void setEmissoes(int emissoes) {
		this.emissoes = emissoes;
	}
	public int getDebitos() {
		return debitos;
	}
	public void setDebitos(int debitos) {
		this.debitos = debitos;
	}
	
	public ExportacaoDTO() {
	}
	
	public ExportacaoDTO(String empresa, int emissoes, int debitos) {
		super();
		this.empresa = empresa;
		this.emissoes = emissoes;
		this.debitos = debitos;
	}
	
	

}
