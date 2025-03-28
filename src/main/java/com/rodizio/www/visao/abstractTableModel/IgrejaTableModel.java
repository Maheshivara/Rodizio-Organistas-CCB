package com.rodizio.www.visao.abstractTableModel;
/**
 * Classe Reponsavel por Implementar os Metodos reponsaveis
 * Por Preencher e Manipular o Modelo na JTable do Cadastro de Usuï¿½rios
 * Implementaï¿½ï¿½o feita utilizando explicaï¿½ï¿½es do Site:
 * http://www.mballem.com/post/jtable-com-banco-de-dados
 * Nï¿½o foram Implementados todos os Mï¿½todos, como renderizar a Linha
 * Editar a Celula e ETC.
 * @author http://www.mballem.com/post/jtable-com-banco-de-dados
 */

import com.rodizio.www.modelo.ModCadIgreja;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@SuppressWarnings("serial")
public class IgrejaTableModel extends AbstractTableModel {
	private static final int Col_Id_Igreja = 0;
	private static final int Col_Nome_Igreja =1;
	//private static final int Col_Status =2;

	private List<ModCadIgreja> listIgreja;

	public IgrejaTableModel(List<ModCadIgreja> listaIgreja){
		this.listIgreja = listaIgreja;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return this.listIgreja.size();
	}

	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {
		
		ModCadIgreja igreja = this.listIgreja.get(indexLinha);
		switch(indexColuna){
		case Col_Id_Igreja:
			return igreja.getId_igreja();
		case Col_Nome_Igreja:
			return igreja.getNome_comum();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int indexColuna){
		@SuppressWarnings("unused")
		String nomenColuna="";
		switch(indexColuna){
		case Col_Id_Igreja:
			return nomenColuna="Cod.Id Comum";
		case Col_Nome_Igreja:
			return nomenColuna="Nome da Comum";

		default:
			return null;
		}
	}

	public Class<?> getColumnClass (int indexColuna){
		switch(indexColuna){
		case Col_Id_Igreja:
			return Long.class;
		case Col_Nome_Igreja:
			return String.class;
		default:
			return null;
		}
	}
	public ModCadIgreja get(int linhaSelecionada){
		return this.listIgreja.get(linhaSelecionada);
	}

}
