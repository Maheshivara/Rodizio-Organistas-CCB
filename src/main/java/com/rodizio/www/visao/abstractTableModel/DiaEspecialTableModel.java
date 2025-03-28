package com.rodizio.www.visao.abstractTableModel;
/**
 * Classe Reponsavel por Implementar os Metodos reponsaveis
 * Por Preencher e Manipular o Modelo na JTable.
 * Implementacao feita utilizando explicacoes do Site:
 * http://www.mballem.com/post/jtable-com-banco-de-dados
 * Nao foram Implementados todos os Metodos, como renderizar a Linha
 * Editar a Celula e ETC.
 * @author http://www.mballem.com/post/jtable-com-banco-de-dados
 */

import com.rodizio.www.controle.rodizio.Data;
import com.rodizio.www.modelo.ModDiaEspecial;

import javax.swing.table.AbstractTableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@SuppressWarnings("serial")
public class DiaEspecialTableModel extends AbstractTableModel {
	private SimpleDateFormat  sdf = new SimpleDateFormat ("dd/MM/yyyy");
	private static final int Col_Data = 0;
	private static final int Col_Dia_Semana = 1;
	
	private String[] colunas = {"DATA","DIA SEMANA"};	
	
	private List<ModDiaEspecial> diasEspeciais;
	
	public DiaEspecialTableModel(List<ModDiaEspecial> diasEspeciais){
		this.diasEspeciais = diasEspeciais;
	}

	public DiaEspecialTableModel() {
	    this.diasEspeciais = new ArrayList<>();
	}

	    public void addRow(ModDiaEspecial dia){
		this.diasEspeciais.add(dia);
	        this.fireTableDataChanged();
	    }
	    
	    public void removeRow(int linhaSelecionada){
		this.diasEspeciais.remove(linhaSelecionada);
	        this.fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return  this.diasEspeciais.size();
	}

	@SuppressWarnings("static-access")
	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {

		ModDiaEspecial dia = this.diasEspeciais.get(indexLinha);
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(dia.getDataEspecial());
		
		switch(indexColuna){
		case Col_Data:
			return sdf.format(dia.getDataEspecial());
		case Col_Dia_Semana:
			return Data.diaSemanaPorExtenso(gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK));
		
		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int indexColuna){
        		@SuppressWarnings("unused")
        		String nomenColuna="";
        		switch(indexColuna){
        		case Col_Data:
        			return nomenColuna="DATA";
        		case Col_Dia_Semana:
        			return nomenColuna="DIA SEMANA";
        		default:
        			return null;
        		}
	}

	public Class<?> getColumnClass (int indexColuna){
		switch(indexColuna){
		case Col_Data:
			return String.class;
		case Col_Dia_Semana:
			return String.class;
		default:
			return null;
		}
	}
	
	public ModDiaEspecial get(int linhaSelecionada){
		return this.diasEspeciais.get(linhaSelecionada);
	}
	

}
