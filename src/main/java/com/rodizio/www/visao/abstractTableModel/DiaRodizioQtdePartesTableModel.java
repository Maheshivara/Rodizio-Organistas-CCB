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

import com.rodizio.www.modelo.ModOrganistas;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@SuppressWarnings("serial")
public class DiaRodizioQtdePartesTableModel extends AbstractTableModel {
	private static final int Col_Nome_Organista = 0;
	private static final int Col_Qtde_Total = 1;
	private static final int Col_Qtde_MH = 2;
	private static final int Col_Qtde_P_Parte = 3;
	private static final int Col_Qtde_S_Parte = 4;
	private static final int Col_Qtde_P_Parte_RJM = 5;
	private static final int Col_Qtde_S_Parte_RJM = 6;

	private List<ModOrganistas> listPessoa;

	public DiaRodizioQtdePartesTableModel(List<ModOrganistas> listaPessoas) {
		this.listPessoa = listaPessoas;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return this.listPessoa.size();
	}

	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {

		ModOrganistas pessoa = this.listPessoa.get(indexLinha);

		switch (indexColuna) {
			case Col_Nome_Organista:
				return pessoa.getNome_pessoa();
			case Col_Qtde_Total:
				return pessoa.getQtdeVezesNoRodizio();
			case Col_Qtde_MH:
				return pessoa.getQtdeMeiaHora();
			case Col_Qtde_P_Parte:
				return pessoa.getQtdePrimParte();
			case Col_Qtde_S_Parte:
				return pessoa.getQtdeSegParte();
			case Col_Qtde_P_Parte_RJM:
				return pessoa.getQtdePrimParteRJM();
			case Col_Qtde_S_Parte_RJM:
				return pessoa.getQtdeSegParteRJM();
			default:
				return null;
		}
	}

	@Override
	public String getColumnName(int indexColuna) {
		@SuppressWarnings("unused")
		String nomenColuna = "";
		switch (indexColuna) {
			case Col_Nome_Organista:
				return nomenColuna = "ORGANISTA";
			case Col_Qtde_Total:
				return nomenColuna = "QTDE TOTAL";
			case Col_Qtde_MH:
				return nomenColuna = "QTDE MEIA HORA";
			case Col_Qtde_P_Parte:
				return nomenColuna = "QTDE 1° PARTE";
			case Col_Qtde_S_Parte:
				return nomenColuna = "QTDE 2º PARTE";
			case Col_Qtde_P_Parte_RJM:
				return nomenColuna = "QTDE 1° PARTE RJM";
			case Col_Qtde_S_Parte_RJM:
				return nomenColuna = "QTDE 2° PARTE RJM";

			default:
				return null;
		}
	}

	public Class<?> getColumnClass(int indexColuna) {
		switch (indexColuna) {
			case Col_Nome_Organista:
				return String.class;
			case Col_Qtde_Total:
				return String.class;
			case Col_Qtde_MH:
				return String.class;
			case Col_Qtde_P_Parte:
				return String.class;
			case Col_Qtde_S_Parte:
				return String.class;
			case Col_Qtde_P_Parte_RJM:
				return String.class;
			case Col_Qtde_S_Parte_RJM:
				return String.class;
			default:
				return null;
		}
	}

	public ModOrganistas get(int linhaSelecionada) {
		return this.listPessoa.get(linhaSelecionada);
	}

}
