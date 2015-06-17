package com.rodizio.www.controle.rodizio;
import java.util.Date;

/**
 * Classe para Tratar Datas
 * retorna a Diferença entre a Data Inicial e Data Final.
 * retorna o Dia da Semana por Extenso.
 * @author Wesley Reis
 *
 */

public abstract class Data {
    	/**
    	 * Calcula a quantidade de Dias compreendidos entre Duas Datas.
    	 * @param ini
    	 * @param fim
    	 * @return
    	 */
	public static int diasEntreAsDatas(Date ini, Date fim){

		return (int) ((fim.getTime()- ini.getTime() + 3600000L)/86400000L);
	}
	
	/**
	 * Retorna o Dia da Semana por Extenso.
	 * @param dia
	 * @return
	 */
	public static String diaSemanaPorExtenso(int dia){
		String diaSemanaExtenso=null;
		switch(dia){
		case 1:
			diaSemanaExtenso="Domingo";
			break;
		case 2:
			diaSemanaExtenso="Segunda-Feira";
			break;
		case 3:
			diaSemanaExtenso="Terça-Feira";
			break;
		case 4:
			diaSemanaExtenso="Quarta-Feira";
			break;
		case 5:
			diaSemanaExtenso="Quinta-Feira";
			break;
		case 6:
			diaSemanaExtenso="Sexta-Feira";
			break;
		case 7:
			diaSemanaExtenso="Sábado";
			break;
		}
		return diaSemanaExtenso;
	}
	/**
	 * Retorna o Dia por Extenso Resumido.
	 * @param dia
	 * @return
	 */
	public static String diaSemanaPorExtensoCurto(int dia){
		String diaSemanaExtenso=null;
		switch(dia){
		case 1:
			diaSemanaExtenso="Dom";
			break;
		case 2:
			diaSemanaExtenso="Seg";
			break;
		case 3:
			diaSemanaExtenso="Ter";
			break;
		case 4:
			diaSemanaExtenso="Qua";
			break;
		case 5:
			diaSemanaExtenso="Qui";
			break;
		case 6:
			diaSemanaExtenso="Sex";
			break;
		case 7:
			diaSemanaExtenso="Sáb";
			break;
		default:
			return null;
		}
		return diaSemanaExtenso;
	}

}
