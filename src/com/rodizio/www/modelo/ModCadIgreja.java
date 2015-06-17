package com.rodizio.www.modelo;

import java.util.List;

public class ModCadIgreja {
	
	private String id_igreja;
	private String nome_comum;
	private String logradouro_igreja;
	private String complem_end_igreja;
	private String num_end_igreja;
	private String bairro_igreja;
	private String cep_igreja;
	private String id_cidade;
	private List<ModDiasCulto> listaDiasCulto;
	private int statusCadIgreja;
	
	public String getId_igreja() {
		return id_igreja;
	}
	public void setId_igreja(String id_igreja) {
		this.id_igreja = id_igreja;
	}
	public String getNome_comum() {
		return nome_comum;
	}
	public void setNome_comum(String nome_comum) {
		this.nome_comum = nome_comum;
	}
	public String getLogradouro_igreja() {
		return logradouro_igreja;
	}
	public void setLogradouro_igreja(String logradouro_igreja) {
		this.logradouro_igreja = logradouro_igreja;
	}
	public String getComplem_end_igreja() {
		return complem_end_igreja;
	}
	public void setComplem_end_igreja(String complem_end_igreja) {
		this.complem_end_igreja = complem_end_igreja;
	}
	public String getNum_end_igreja() {
		return num_end_igreja;
	}
	public void setNum_end_igreja(String num_end_igreja) {
		this.num_end_igreja = num_end_igreja;
	}
	public String getBairro_igreja() {
		return bairro_igreja;
	}
	public void setBairro_igreja(String bairro_igreja) {
		this.bairro_igreja = bairro_igreja;
	}
	public String getCep_igreja() {
		return cep_igreja;
	}
	public void setCep_igreja(String cep_igreja) {
		this.cep_igreja = cep_igreja;
	}
	public String getId_cidade() {
		return id_cidade;
	}
	public void setId_cidade(String id_cidade) {
		this.id_cidade = id_cidade;
	}
	public int getStatusCadIgreja() {
		return statusCadIgreja;
	}
	public void setStatusCadIgreja(int statusCadIgreja) {
		this.statusCadIgreja = statusCadIgreja;
	}
	public List<ModDiasCulto> getListaDiasCulto() {
		return listaDiasCulto;
	}
	public void setListaDiasCulto(List<ModDiasCulto> listaDiasCulto) {
		this.listaDiasCulto = listaDiasCulto;
	}
}
