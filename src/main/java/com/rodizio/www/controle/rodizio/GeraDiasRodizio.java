package com.rodizio.www.controle.rodizio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/*
 * Classe que gera os dias de Culto da Comum para a Escolha no Rodizio.
 */
public abstract class GeraDiasRodizio {
	private static List<DiaDoRodizio> rodizioTotal = new ArrayList<>();
	private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public GeraDiasRodizio() {
	}

	/**
	 * Para gerar um ArrayList de todos os dias de culto, compreendidos
	 * entre a Data Inicial e Data Final, somente adicionando os dias de culto
	 * que coincidam com os dias da semana que tenha culto na comum, List diasCulto.
	 * 
	 * @param inicio
	 * @param fim
	 * @param diasCulto
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<DiaDoRodizio> modeloRodizio(Date inicio, Date fim, List<Integer> diasCulto,
			List<Date> listaDiasEspeciais) {
		gregorianCalendar.setTime(inicio);

		int dias = Data.diasEntreAsDatas(inicio, fim);

		if (dias < 0) {
			System.out.println("ERRO nas Datas");
		} else {
			for (int i = 0; i < dias; i++) {
				int qtdePartes = 0;
				/**
				 * Verifica se o Dia da Semana é Domingo, para gerar as Partes da Reunião de
				 * Jovens e Menores.
				 */
				if ((gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK)) == 1) {
					qtdePartes = 5;
				} else {
					qtdePartes = 3;
				}
				/**
				 * Verifica se o Dia da Semana atual faz parte de um dos Dias de Culto.
				 */
				if (diasCulto.contains(gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK))) {
					for (int j = 1; j <= qtdePartes; j++) {
						DiaDoRodizio diaRodizio = new DiaDoRodizio();
						/**
						 * Utiliza o Metodo Para retornar o dia da Semana por Extenso.
						 */
						diaRodizio.setDiaDaSemana(Data.diaSemanaPorExtenso(gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK)));
						diaRodizio.setDiaDoMes(sdf.format(inicio));
						diaRodizio.setParte(j);
						rodizioTotal.add(diaRodizio);
					}
				}
				gregorianCalendar.add(gregorianCalendar.DAY_OF_MONTH, 1);
				inicio = gregorianCalendar.getTime();
			}
		}

		return rodizioTotal;
	}

}
