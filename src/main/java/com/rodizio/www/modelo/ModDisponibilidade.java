package com.rodizio.www.modelo;

public class ModDisponibilidade {

    private String id_pessoa;
    private int diaCulto;
    private boolean meiaHora;
    private boolean primParte;
    private boolean segParte;
    private boolean primParteRJM;
    private boolean segParteRJM;
    private boolean cultoEspecial;
    private int statusDisponibilidade;


    public String getId_pessoa() {
	return id_pessoa;
    }
    public void setId_pessoa(String id_pessoa) {
	this.id_pessoa = id_pessoa;
    }
    public int getDiaCulto() {
	return diaCulto;
    }
    public void setDiaCulto(int diaCulto) {
	this.diaCulto = diaCulto;
    }
    public boolean isMeiaHora() {
	return meiaHora;
    }
    public void setMeiaHora(boolean meiaHora) {
	this.meiaHora = meiaHora;
    }
    public boolean isPrimParte() {
	return primParte;
    }
    public void setPrimParte(boolean primParte) {
	this.primParte = primParte;
    }
    public boolean isSegParte() {
	return segParte;
    }
    public void setSegParte(boolean segParte) {
	this.segParte = segParte;
    }

    public boolean isPrimParteRJM() {
	return primParteRJM;
    }
    public void setPrimParteRJM(boolean primParteRJM) {
	this.primParteRJM = primParteRJM;
    }
    public boolean isSegParteRJM() {
	return segParteRJM;
    }
    public void setSegParteRJM(boolean segParteRJM) {
	this.segParteRJM = segParteRJM;
    }
    
    public boolean isCultoEspecial() {
	return cultoEspecial;
    }
    public void setCultoEspecial(boolean cultoEspecial) {
	this.cultoEspecial = cultoEspecial;
    }
    
    /**
     * Status para Deteminar se Insere, Altera ou Exclui o Registro no Banco de Dados
     * @return
     */
    public int getStatusDisponibilidade() {
	return statusDisponibilidade;
    }
    public void setStatusDisponibilidade() {
	this.statusDisponibilidade = 2;
    }
}
