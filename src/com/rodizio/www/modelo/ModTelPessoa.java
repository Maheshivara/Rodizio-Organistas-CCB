package com.rodizio.www.modelo;

import javax.swing.*;

public class ModTelPessoa {
	private String id_telefone;
	private String id_pessoa;
	private String telefone_pessoa;
	private int statusTel;
	private int idTipoTelefone;
	
	public String getId_telefone() {
		return id_telefone;
	}
	public void setId_telefone(String id_telefone) {
		this.id_telefone = id_telefone;
	}
	public String getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(String id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public String getTelefone_pessoa() {
		return telefone_pessoa;
	}
	public void setTelefone_pessoa(String telefone_pessoa) {
		this.telefone_pessoa = telefone_pessoa;
	}
	
	public int getStatusTel() {
		return statusTel;
	}
	public void setStatusTel(int statusTelefone) {
		if(statusTelefone<0 || statusTelefone>2){
			JOptionPane.showMessageDialog(null, "status do E-mail invalido!\nSomente Valores entre 0 e 2, s�o aceitos!");
		}
		this.statusTel = statusTelefone;
	}
	public int getIdTipoTelefone() {
		return idTipoTelefone;
	}
	public void setIdTipoTelefone(int idTipoTelefone) {
		this.idTipoTelefone = idTipoTelefone;
	}
}
