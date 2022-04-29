package com.ti2cc;

public class Empresa {
	private String cnpj;
	private String situacaoCadastral;
	private String dataAbertura;
	private String nome;
	private String ceo;
	private String endereco;
	private double capital;
	
	public Empresa() {
		this.cnpj = "xxx.xxx.xxx.xx";
		this.situacaoCadastral = "sem-info";
		this.dataAbertura = "dd/mm/aa";
		this.nome = "void";
		this.ceo = "void";
		this.endereco = "void";
		this.capital = 0;
	}
	
	public Empresa(String cnpj, String situacaoCadastral, String dataAbertura, String nome, String ceo, String endereço, double capital) {
		this.cnpj = cnpj;
		this.situacaoCadastral = situacaoCadastral;
		this.dataAbertura = dataAbertura;
		this.nome = nome;
		this.ceo = ceo;
		this.endereco = endereco;
		this.capital = capital;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getSituacaoCadastral() {
		return situacaoCadastral;
	}

	public void setSituacaoCadastral(String situacaoCadastral) {
		this.situacaoCadastral = situacaoCadastral;
	}
	
	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String  getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	
	
	public String toString() {
		return "Empresa [nome=" + this.nome + ", ceo=" + this.ceo + ", cnpj=" + this.cnpj + ", Endereco= " + this.endereco + ", Data de Abertura=" + this.dataAbertura + ", Situacao Cadastral=" + this.situacaoCadastral + ", capital= " + this.capital +"]";
	}	
}
