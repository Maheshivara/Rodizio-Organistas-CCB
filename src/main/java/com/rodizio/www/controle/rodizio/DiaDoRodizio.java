package com.rodizio.www.controle.rodizio;


public class DiaDoRodizio {
    
	public DiaDoRodizio(){}
	
	private String diaDaSemana;
	private String diaDoMes;
	private int parte;
	private String codIdOrganista;
	private String codRodizio;
	
	private boolean cultoEspecial;
	

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    
	    DiaDoRodizio other = (DiaDoRodizio) obj;
	    if (diaDoMes == null) {
		if (other.diaDoMes != null) return false;
	    } else if (!diaDoMes.equals(other.diaDoMes)) return false;
	    return true;
	}
	
	/**
	 * @return the diaDaSemana
	 */
	public String getDiaDaSemana() {
		return diaDaSemana;
	}
	/**
	 * @param diaDaSemana the diaDaSemana to set
	 */
	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	/**
	 * @return the dataMes
	 */
	public String getDiaDoMes() {
		return diaDoMes;
	}
	/**
	 * @param dataI the dataMes to set
	 */
	public void setDiaDoMes(String data) {
		this.diaDoMes = data;
	}
	/**
	 * @return the parte
	 */
	public int getParte() {
		return parte;
	}
	/**
	 * @param parte the parte to set
	 */
	public void setParte(int parte) {
		this.parte = parte;
	}
	public String getCodIdOrganista() {
		return codIdOrganista;
	}
	public void setCodIdOrganista(String codIdOrganista) {
		this.codIdOrganista = codIdOrganista;
	}
	public String getCodRodizio() {
		return codRodizio;
	}
	public void setCodRodizio(String codRodizio) {
		this.codRodizio = codRodizio;
	}

	public boolean isCultoEspecial() {
	    return cultoEspecial;
	}

	public void setCultoEspecial(boolean cultoEspecial) {
	    this.cultoEspecial = cultoEspecial;
	}

}
