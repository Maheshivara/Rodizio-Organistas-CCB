package com.rodizio.www.controle.rodizio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatasUteis {
	private List<Date> listaDatasEspeciais = new ArrayList<>();

	public DatasUteis() {
	}
	
	public void setListaDias(List<Date> list){
		this.listaDatasEspeciais.addAll(list);
	}

	public boolean isEspecial(Date data){
		for (Date datas : this.listaDatasEspeciais){
			 if(datas.compareTo(data)==0){
				 return true;
			 }
		}
		return false;
	}
	
}
