package com.rodizio.www.controle.rodizio;

import com.toedter.calendar.IDateEvaluator;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class RangeDiasValidos implements IDateEvaluator {
	private DatasUteis datas = new DatasUteis();

	public RangeDiasValidos(List<Date> list) {
		datas.setListaDias(list);
	}

	@Override
	public Color getInvalidBackroundColor() {
		return Color.darkGray;
	}

	@Override
	public Color getInvalidForegroundColor() {
		return Color.ORANGE;
	}

	@Override
	public String getInvalidTooltip() {
		return "Não Tem Culto neste Dia Ou está Fora do Intervalo.";
	}

	@Override
	public Color getSpecialBackroundColor() {
		return Color.DARK_GRAY;
	}

	@Override
	public Color getSpecialForegroundColor() {
		return null;
	}

	@Override
	public String getSpecialTooltip() {
		return null;
	}

	@Override
	public boolean isInvalid(Date data) {

		return !datas.isEspecial(data);
	}

	@Override
	public boolean isSpecial(Date data) {

		return datas.isEspecial(data);
	}

}
