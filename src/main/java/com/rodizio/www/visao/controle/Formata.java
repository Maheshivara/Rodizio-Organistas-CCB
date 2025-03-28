
package com.rodizio.www.visao.controle;
/**
 * Classe Responsavel por Formatar Dados para Gravação ou Manipulação em Banco de Dados.
 * @author Wesley
 *
 */
public abstract class Formata {
	
	public static int soNumeros(String texto){
		int retorno;
		texto = texto.replaceAll("[^0-9]", "");
		if (!texto.isEmpty()){
			retorno = Integer.parseInt(texto);
		}else{
			retorno =0;
		}
		return retorno;
	}
}
