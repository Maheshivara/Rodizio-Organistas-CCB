package com.rodizio.www.modelo;

import java.util.List;

public class ModPessoa {

	private String id_pessoa;
	private List<ModEmailPessoa> listaEmailPessoa;
	private List<ModTelPessoa> listaTelPessoa;
	private String nome_pessoa;
	private String id_tipo_pessoa;
	private String id_Igreja;
	private int statusPessoa;
	private Boolean recebeEmail;

	public String getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(String string) {
		this.id_pessoa = string;
	}
	public String getIdTipoPessoa() {
		return id_tipo_pessoa;
	}
	public void setIdTipoPessoa(String id_tipo_pessoa) {
		this.id_tipo_pessoa = id_tipo_pessoa;
	}
	public String getNome_pessoa() {
		return nome_pessoa;
	}
	public void setNome_pessoa(String nome_pessoa) {
		this.nome_pessoa = nome_pessoa;
	}
	public List<ModTelPessoa> getListaTelPessoa() {
		return listaTelPessoa;
	}
	public void setListaTelPessoa(List<ModTelPessoa> listaTelPessoa) {
		this.listaTelPessoa = listaTelPessoa;
	}
	public List<ModEmailPessoa> getListaEmailPessoa() {
		return listaEmailPessoa;
	}
	public void setListaEmailPessoa(List<ModEmailPessoa> listaEmailPessoa) {
		this.listaEmailPessoa = listaEmailPessoa;
	}
	public String getId_Igreja() {
		return id_Igreja;
	}
	public void setId_Comum(String id_Igreja) {
		this.id_Igreja = id_Igreja;
	}
	public Boolean getRecebeEmail() {
		return recebeEmail;
	}
	public void setRecebeEmail(Boolean recebeEmail) {
		this.recebeEmail = recebeEmail;
	}
	public int getStatusPessoa() {
		return statusPessoa;
	}
	public void setStatusPessoa(int statusPessoa) {
		this.statusPessoa = statusPessoa;
	}
}
