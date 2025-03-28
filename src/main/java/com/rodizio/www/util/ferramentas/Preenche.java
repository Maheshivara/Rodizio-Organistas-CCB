/**
 * 
 */
package com.rodizio.www.util.ferramentas;

import com.rodizio.www.controle.ControleCidade;
import com.rodizio.www.controle.ControleTipoPessoa;
import com.rodizio.www.controle.ControleUF;
import com.rodizio.www.modelo.ModCidade;
import com.rodizio.www.modelo.ModTipoPessoa;
import com.rodizio.www.modelo.ModUF;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley
 *
 */
public abstract class Preenche {
	
	public static JComboBox<String> comboCidade(String uf , JComboBox<String> cmbCidade, DefaultComboBoxModel<String> dcbmCidades){
		cmbCidade.removeAll();
		dcbmCidades.removeAllElements();
		List<ModCidade> listaCidades = new ArrayList<>();
		listaCidades.addAll(ControleCidade.listaCidadess(uf.substring(0,2)));
		dcbmCidades.addElement("< Selecione uma Cidade >");
		for(ModCidade cidade : listaCidades){
			dcbmCidades.addElement(cidade.getIdCidade()+" - "+cidade.getNome_Cidade());
		}
		cmbCidade.setModel(dcbmCidades);
		return cmbCidade;
	}
	public static JComboBox<String> comboUF(JComboBox<String> cmbUF, DefaultComboBoxModel<String> dcbmUF){
		
		for(ModUF uf:ControleUF.listaEstados()){
			dcbmUF.addElement(uf.getUf_Estado()+" - "+uf.getNome_Estado());
		}
		cmbUF.setModel(dcbmUF);
		return cmbUF;
	}
	public static JComboBox<String> comboTipoMinisterio(JComboBox<String> cmbTipoPessoa, DefaultComboBoxModel<String> dcbmTipoPessoa, int indexes){
		
		for(ModTipoPessoa tipoPessoa : ControleTipoPessoa.listaTiposPessoa()){
			dcbmTipoPessoa.addElement(tipoPessoa.getId_tipo_pessoa()+" - "+tipoPessoa.getTipo_pessoa());
		}
		cmbTipoPessoa.setModel(dcbmTipoPessoa);
		if(indexes != 7){
			dcbmTipoPessoa.removeElementAt(6);
		}
		return cmbTipoPessoa;
	}

}
