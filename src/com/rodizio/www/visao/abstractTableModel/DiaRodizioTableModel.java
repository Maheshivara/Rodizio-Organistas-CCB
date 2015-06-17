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

import com.rodizio.www.controle.ControleOrganista;
import com.rodizio.www.controle.rodizio.Data;
import com.rodizio.www.modelo.ModDiaRodizio;
import com.rodizio.www.modelo.ModOrganistas;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@SuppressWarnings("serial")
public class DiaRodizioTableModel extends AbstractTableModel {
	private SimpleDateFormat  sdf = new SimpleDateFormat ("dd/MM/yyyy");
	private static final int Col_Data = 0;
	private static final int Col_Dia_Semana = 1;
	private static final int Col_Nome_Org_MH = 2;
	private static final int Col_Nome_Org_P_Parte = 3;
	private static final int Col_Nome_Org_S_Parte = 4;
	private static final int Col_Nome_Org_P_Parte_RJM = 5;
	private static final int Col_Nome_Org_S_Parte_RJM = 6;

	private List<ModDiaRodizio> listPessoa;

	public DiaRodizioTableModel(List<ModDiaRodizio> listaPessoas){
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

	@SuppressWarnings("static-access")
	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {

		ModDiaRodizio dia = this.listPessoa.get(indexLinha);
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(dia.getData());
		
		switch(indexColuna){
		case Col_Data:
			return sdf.format(dia.getData());
		case Col_Dia_Semana:
			return Data.diaSemanaPorExtensoCurto(gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK));
		case Col_Nome_Org_MH:
			String nome1="";
			ModOrganistas org1 = new ModOrganistas();
			try {
				org1 = new ControleOrganista().selectID(dia.getIdOrganistaMH());
				if(org1!=null){
					nome1 =  org1.getNome_pessoa();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return nome1;
		case Col_Nome_Org_P_Parte:
			String nome2="";
			ModOrganistas org2 = new ModOrganistas();
			try {
				org2 = new ControleOrganista().selectID(dia.getIdOrganistaPrimParte());
				if(org2!=null){
					nome2= org2.getNome_pessoa();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nome2;
		case Col_Nome_Org_S_Parte:
			String nome3="";
			ModOrganistas org3 = new ModOrganistas();
			try {
				org3 = new ControleOrganista().selectID(dia.getIdOrganistaSegParte());
				if(org3!=null){
					nome3=org3.getNome_pessoa();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  nome3;
		case Col_Nome_Org_P_Parte_RJM:
			String nome4="";
			ModOrganistas org4 = new ModOrganistas();
			try {
				org4 = new ControleOrganista().selectID(dia.getIdOrganistaPrimParteRJM());
				if(org4!=null){
					nome4 = org4.getNome_pessoa();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nome4;
		case Col_Nome_Org_S_Parte_RJM:
			String nome5="";
			ModOrganistas org5 = new ModOrganistas();
			try {
				org5 = new ControleOrganista().selectID(dia.getIdOrganistaSegParteRJM());
				if(org5!=null){
					nome5 = org5.getNome_pessoa();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nome5;
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
			return nomenColuna="DIA";
		case Col_Nome_Org_MH:
			return nomenColuna="MEIA HORA";
		case Col_Nome_Org_P_Parte:
			return nomenColuna="1� PARTE";
		case Col_Nome_Org_S_Parte:
			return nomenColuna="2� PARTE";
		case Col_Nome_Org_P_Parte_RJM:
			return nomenColuna="1� PARTE RJM";
		case Col_Nome_Org_S_Parte_RJM:
			return nomenColuna="2� PARTE RJM";

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
		case Col_Nome_Org_MH:
			return String.class;
		case Col_Nome_Org_P_Parte:
			return String.class;
		case Col_Nome_Org_S_Parte:
			return String.class;
		case Col_Nome_Org_P_Parte_RJM:
			return String.class;
		case Col_Nome_Org_S_Parte_RJM:
			return String.class;
		default:
			return null;
		}
	}
	public ModDiaRodizio get(int linhaSelecionada){
		return this.listPessoa.get(linhaSelecionada);
	}

}
