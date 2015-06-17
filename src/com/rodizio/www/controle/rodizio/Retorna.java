package com.rodizio.www.controle.rodizio;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public abstract class Retorna {
	private static List<Date> intervaloRodizio = new ArrayList<>();
	private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
	
	

	public static String parteCulto(int opcao){
		String parte=null;
		switch(opcao){
		case 1:
			/**
			 * Meia Hora
			 */
			parte = "MH";
			break;
		case 2: 
			/**
			 * Primeira Parte do Culto
			 */
			parte = "PP";
			break;
		case 3:
			/**
			 * Segunda Parte do Culto
			 */
			parte = "SP";
			break;
		case 4:
			/**
			 * Primeira Parte da Reunião
			 * de Jovens e Menores
			 */
			parte = "RJM-PP";
			break;
		case 5:
			/**
			 * Segunda Parte da Reunião
			 * de Jovens e Menores
			 */
			parte = "RJM-SP";
			break;
		}
		
		return parte;
	}
	
	@SuppressWarnings("static-access")
	public static List<Date> intervaloRodizio(Date dataIni, Date dataFin, List<Integer> diasCulto){
		
		gregorianCalendar.setTime(dataIni);
		
		int dias = Data.diasEntreAsDatas(dataIni, dataFin);
		
		if (dias<0){
			System.out.println("ERRO nas Datas");
		}else{
			for (int i=0; i<dias;i++){
				if(diasCulto.contains(gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK))){//Verifica se o Dia da Semana atual faz parte de um dos Dias de Culto.
					intervaloRodizio.add(dataIni);
				}
				gregorianCalendar.add(gregorianCalendar.DAY_OF_MONTH,1);//Adiciona um dia a Data Inicial
				dataIni = gregorianCalendar.getTime(); //faz a dataI assumir a Data com o Dia adicionado.
			}
		}
		
		return intervaloRodizio;
	}

}
