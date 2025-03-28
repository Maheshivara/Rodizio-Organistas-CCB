package com.rodizio.www.modelo;

import javax.swing.*;

public class ModEmailPessoa {
	private String id_email;
	private String id_pessoa;
	private String email_pessoa;
	private int statusEmail;

	/**
	 * @return the id_email
	 */
	public String getId_email() {

		return id_email;
	}

	/**
	 * @param id_email the id_email to set
	 */
	public void setId_email(String id_email) {

		this.id_email = id_email;

	}

	/**
	 * @return the id_pessoa
	 */
	public String getId_pessoa() {

		return id_pessoa;
	}

	/**
	 * @param id_pessoa the id_pessoa to set
	 */

	public void setId_pessoa(String id_pessoa) {

		this.id_pessoa = id_pessoa;

	}

	/**
	 * @return the email_pessoa
	 */
	public String getEmail_pessoa() {
		return email_pessoa;
	}

	/**
	 * @param email_pessoa the email_pessoa to set
	 */
	public void setEmail_pessoa(String email_pessoa) {
		this.email_pessoa = email_pessoa;
	}

	@Override
	public String toString() {
		return "ModEmailPessoa [id_email=" + id_email + ", id_pessoa="
				+ id_pessoa + ", email_pessoa=" + email_pessoa + "]";
	}

	public int getStatusEmail() {
		return statusEmail;
	}

	public void setStatusEmail(int statusEmail) {
		if (statusEmail < 0 || statusEmail > 2) {
			JOptionPane.showMessageDialog(null, "status do E-mail invalido!\nSomente Valores entre 0 e 2, são aceitos!");
		}
		this.statusEmail = statusEmail;
	}

}
