package com.rodizio.www.modelo;

import java.util.List;

public class ModOrganistas implements Comparable<ModOrganistas>{
	private List<ModDisponibilidade> listaDisponibilidades;

	private String id_pessoa;
	private List<ModEmailPessoa> listaEmailPessoa;
	private List<ModTelPessoa> listaTelPessoa;
	private String nome_pessoa;
	private String id_tipo_pessoa;
	private String id_Igreja;
	private int statusPessoa;
	private Boolean recebeEmail;
	private int qtdeVezesNoRodizio;
	private int qtdeMeiaHora;
	private int qtdePrimParte;
	private int qtdeSegParte;
	private int qtdePrimParteRJM;
	private int qtdeSegParteRJM;
	private int ordemRodizio=0;
	
	public String getId_tipo_pessoa() {
		return id_tipo_pessoa;
	}
	public void setId_tipo_pessoa(String id_tipo_pessoa) {
		this.id_tipo_pessoa = id_tipo_pessoa;
	}
	public int getQtdeMeiaHora() {
		return qtdeMeiaHora;
	}
	public void setQtdeMeiaHora(int qtdeMeiaHora) {
		this.qtdeVezesNoRodizio +=qtdeMeiaHora;
		this.qtdeMeiaHora += qtdeMeiaHora;
	}
	public int getQtdePrimParte() {
		return qtdePrimParte;
	}
	public void setQtdePrimParte(int qtdePrimParte) {
		this.qtdeVezesNoRodizio +=qtdePrimParte;
		this.qtdePrimParte += qtdePrimParte;
	}
	public int getQtdeSegParte() {
		return qtdeSegParte;
	}
	public void setQtdeSegParte(int qtdeSegParte) {
		this.qtdeVezesNoRodizio +=qtdeSegParte;
		this.qtdeSegParte += qtdeSegParte;
	}
	public int getQtdePrimParteRJM() {
		return qtdePrimParteRJM;
	}
	public void setQtdePrimParteRJM(int qtdePrimParteRJM) {
		this.qtdePrimParteRJM += qtdePrimParteRJM;
	}
	public int getQtdeSegParteRJM() {
		return qtdeSegParteRJM;
	}
	public void setQtdeSegParteRJM(int qtdeSegParteRJM) {
		this.qtdeSegParteRJM += qtdeSegParteRJM;
	}
	public void setId_Igreja(String id_Igreja) {
		this.id_Igreja = id_Igreja;
	}

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
	public void setId_IgrejaComum(String id_Igreja) {
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

	public List<ModDisponibilidade> getListaDisponibilidades() {
		return listaDisponibilidades;
	}

	public void setListaDisponibilidades(List<ModDisponibilidade> listaDisponibilidades) {
		this.listaDisponibilidades = listaDisponibilidades;
	}
	public int getQtdeVezesNoRodizio() {
		return qtdeVezesNoRodizio;
	}

	public int getOrdemRodizio() {
		return ordemRodizio;
	}
	public void setOrdemRodizio(int ordemRodizio) {
		if(this.ordemRodizio==0){
			this.ordemRodizio = ordemRodizio;	
		}
		
	}
	@Override
	public int compareTo(ModOrganistas org) {
		if(this.qtdeVezesNoRodizio < org.getQtdeVezesNoRodizio()){
			return -1;
		}
		if(this.qtdeVezesNoRodizio > org.getQtdeVezesNoRodizio()){
			return 1;
		}
		return 0;
	}

}
