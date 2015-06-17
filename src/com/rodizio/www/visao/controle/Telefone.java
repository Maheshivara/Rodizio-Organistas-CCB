package com.rodizio.www.visao.controle;
/**
 * Classe para Validar Telefone.
 * @author Wesley
 *
 */

public abstract class Telefone {

	public static boolean eValido(String tel) {
		tel = tel.replaceAll("[^0-9]", "");
		if(tel.length()<10){
			return false;
		}else{
			if (!"11".equals(tel.substring(0, 2))) {
                return tel.length() == 10;

            } else {
				if (tel.length() != 11) {
					return false;
				}return true;
			}
		}
	}
}
