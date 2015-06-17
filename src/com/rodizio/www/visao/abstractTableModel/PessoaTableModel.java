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

import com.rodizio.www.controle.ControleTipoPessoa;
import com.rodizio.www.modelo.ModPessoa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@SuppressWarnings("serial")
public class PessoaTableModel extends AbstractTableModel {
	private static final int Col_Id_Pessoa = 0;
	private static final int Col_Nome_Pessoa =1;
	private static final int Col_Tipo_Pessoa =2;

	private List<ModPessoa> listPessoa;

	public PessoaTableModel(List<ModPessoa> listaPessoas){
		this.listPessoa = listaPessoas;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return this.listPessoa.size();
	}

	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {
		
		ModPessoa pessoa = this.listPessoa.get(indexLinha);
		switch(indexColuna){
		case Col_Id_Pessoa:
			return pessoa.getId_pessoa();
		case Col_Nome_Pessoa:
			return pessoa.getNome_pessoa();
		case Col_Tipo_Pessoa:
			return ControleTipoPessoa.selectId(pessoa.getIdTipoPessoa()).getTipo_pessoa();

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
			return nomenColuna="Nome da Pessoa";
		case Col_Tipo_Pessoa:
			return nomenColuna="Minist�rio";

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
		case Col_Tipo_Pessoa:
			return String.class;
		default:
			return null;
		}
	}
	public ModPessoa get(int linhaSelecionada){
		return this.listPessoa.get(linhaSelecionada);
	}

}
