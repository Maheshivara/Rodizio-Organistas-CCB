package com.rodizio.www.modelo;

import java.util.Date;

public class ModDiaRodizio implements Comparable<ModDiaRodizio>{
	private Date data;
	private String idIgreja;
	private String idOrganistaMH;
	private String idOrganistaPrimParte;
	private String idOrganistaSegParte;
	private String idOrganistaPrimParteRJM;
	private String idOrganistaSegParteRJM;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getIdIgreja() {
		return idIgreja;
	}
	public void setIdIgreja(String idIgreja) {
		this.idIgreja = idIgreja;
	}
	public String getIdOrganistaMH() {
		return idOrganistaMH;
	}
	public void setIdOrganistaMH(String idOrganistaMH) {
		this.idOrganistaMH = idOrganistaMH;
	}
	public String getIdOrganistaPrimParte() {
		return idOrganistaPrimParte;
	}
	public void setIdOrganistaPrimParte(String idOrganistaPrimParte) {
		this.idOrganistaPrimParte = idOrganistaPrimParte;
	}
	public String getIdOrganistaSegParte() {
		return idOrganistaSegParte;
	}
	public void setIdOrganistaSegParte(String idOrganistaSegParte) {
		this.idOrganistaSegParte = idOrganistaSegParte;
	}
	public String getIdOrganistaPrimParteRJM() {
		return idOrganistaPrimParteRJM;
	}
	public void setIdOrganistaPrimParteRJM(String idOrganistaPrimParteRJM) {
		this.idOrganistaPrimParteRJM = idOrganistaPrimParteRJM;
	}
	public String getIdOrganistaSegParteRJM() {
		return idOrganistaSegParteRJM;
	}
	public void setIdOrganistaSegParteRJM(String idOrganistaSegParteRJM) {
		this.idOrganistaSegParteRJM = idOrganistaSegParteRJM;
	}
	
	@Override
	public int compareTo(ModDiaRodizio arg0) {
		return this.getData().compareTo(arg0.getData());
	}
	
	

}
