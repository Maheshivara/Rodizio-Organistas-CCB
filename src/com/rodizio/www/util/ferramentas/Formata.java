package com.rodizio.www.util.ferramentas;

public abstract class Formata {

	
	public static String soNumeros(String str){
		str = str.replaceAll("[^0-9]", "");
		return str;
	}
	
}
