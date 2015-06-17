package com.rodizio.www.visao.abstractTableModel;
/**
 * Classe Reponsavel por Implementar os Metodos reponsaveis
 * Por Preencher e Manipular o Modelo na JTable do Cadastro de Usu�rios
 * Implementa��o feita utilizando explica��es do Site:
 * http://www.mballem.com/post/jtable-com-banco-de-dados
 * N�o foram Implementados todos os M�todos, como renderizar a Linha
 * Editar a Celula e ETC.
 * @author http://www.mballem.com/post/jtable-com-banco-de-dados
 */

import com.rodizio.www.modelo.ModOrganistas;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@SuppressWarnings("serial")
public class OrganistaTableModel extends AbstractTableModel {
	private static final int Col_Id_Pessoa = 0;
	private static final int Col_Nome_Pessoa =1;
	private static final int Col_Qtde_MH =2;
	private static final int Col_Qtde_PrimParte =3;
	private static final int Col_Qtde_SegParte =4;
	private static final int Col_Qtde_PrimParteRJM =5;
	private static final int Col_Qtde_SegParteRJM =6;
	private static final int Col_Qtde_Escolhida =7;
	private static final int Col_Ordem =8;

	private List<ModOrganistas> listPessoa;

	public OrganistaTableModel(List<ModOrganistas> listaPessoas){
		this.listPessoa = listaPessoas;
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		return this.listPessoa.size();
	}

	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {
		
		ModOrganistas pessoa = this.listPessoa.get(indexLinha);
		switch(indexColuna){
		case Col_Id_Pessoa:
			return pessoa.getId_pessoa();
		case Col_Nome_Pessoa:
			return pessoa.getNome_pessoa();
		case Col_Qtde_MH:
			return pessoa.getQtdeMeiaHora();
		case Col_Qtde_PrimParte:
			return pessoa.getQtdePrimParte();
		case Col_Qtde_SegParte:
			return pessoa.getQtdeSegParte();
		case Col_Qtde_PrimParteRJM:
			return pessoa.getQtdePrimParteRJM();
		case Col_Qtde_SegParteRJM:
			return pessoa.getQtdeSegParteRJM();
		case Col_Qtde_Escolhida:
			return pessoa.getQtdeVezesNoRodizio();
		case Col_Ordem:
			return pessoa.getOrdemRodizio();
		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int indexColuna){
		@SuppressWarnings("unused")
		String nomenColuna="";
		switch(indexColuna){
		case Col_Id_Pessoa:
			return nomenColuna="Cod. Pessoa";
		case Col_Nome_Pessoa:
			return nomenColuna="Organista";
		case Col_Qtde_MH:
			return nomenColuna="Qtde MH";
		case Col_Qtde_PrimParte:
			return nomenColuna="Qtde 1� Parte";
		case Col_Qtde_SegParte:
			return nomenColuna="Qtde 2� Parte";
		case Col_Qtde_PrimParteRJM:
			return nomenColuna="Qtde 1� RJM";
		case Col_Qtde_SegParteRJM:
			return nomenColuna="Qtde 2� RJM";
		case Col_Qtde_Escolhida:
			return nomenColuna="N� Dias";
		case Col_Ordem:
			return nomenColuna="Ordem";

		default:
			return null;
		}
	}

	public Class<?> getColumnClass (int indexColuna){
		switch(indexColuna){
		case Col_Id_Pessoa:
			return Long.class;
		case Col_Nome_Pessoa:
			return String.class;
		case Col_Qtde_MH:
			return Integer.class;
		case Col_Qtde_PrimParte:
			return Integer.class;
		case Col_Qtde_SegParte:
			return Integer.class;
		case Col_Qtde_PrimParteRJM:
			return Integer.class;
		case Col_Qtde_SegParteRJM:
			return Integer.class;
		case Col_Qtde_Escolhida:
			return Integer.class;
		case Col_Ordem:
			return Integer.class;
		default:
			return null;
		}
	}
	public ModOrganistas get(int linhaSelecionada){
		return this.listPessoa.get(linhaSelecionada);
	}

}
