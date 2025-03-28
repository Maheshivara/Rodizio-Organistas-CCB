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

import com.rodizio.www.modelo.ModPessoa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@SuppressWarnings("serial")
public class EmailTableModel extends AbstractTableModel {
	private static final int Col_Id_Pessoa = 0;
	private static final int Col_Pessoa =1;

	private List<ModPessoa> listPessoas;

	public EmailTableModel(List<ModPessoa> listaPessoa){
		this.listPessoas = listaPessoa;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return this.listPessoas.size();
	}

	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {
		
		ModPessoa pessoa = this.listPessoas.get(indexLinha);
		switch(indexColuna){
		case Col_Id_Pessoa:
			return pessoa.getId_pessoa();
		case Col_Pessoa:
			return pessoa.getNome_pessoa();
		
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
			return nomenColuna="Id Pessoa";
		case Col_Pessoa:
			return nomenColuna="Nome Pessoa";
		default:
			return null;
		}
	}

	public Class<?> getColumnClass (int indexColuna){
		switch(indexColuna){
		case Col_Id_Pessoa:
			return Long.class;
		case Col_Pessoa:
			return String.class;

		default:
			return null;
		}
	}
	public ModPessoa get(int linhaSelecionada){
		return this.listPessoas.get(linhaSelecionada);
	}

}
