package com.rodizio.www.visao.controle;
/**
 * Classe Abstrata com alguns m�todos Est�ricos:
 * eValidoTxtBox; eValidoComboBox;
 * Respons�veis por validar o Conte�do de Determinados Campos.
 */

import javax.swing.*;
import java.awt.*;

public abstract class ValidaUI {
	public static boolean eValidoTxtBox(JTextField txtCampo,Component frame){
		if(txtCampo.getText().isEmpty()){
			DisparaMensagem.msg(6,frame);
			return false;
		}return true;
	}
	public static boolean eValidoTxtBox(JTextField txtCampo,Component frame,int tamanho){
		if(txtCampo.getText().isEmpty()||txtCampo.getText().length()>tamanho){
			DisparaMensagem.msg(11,frame);
			DisparaMensagem.msg(12,frame);
			return false;
		}return true;
	}
	@SuppressWarnings("rawtypes")
	public static boolean eValidoComboBox(JComboBox cmbBox,Component frame){
		if(cmbBox.getSelectedIndex()==0){
			DisparaMensagem.msg(7,frame);
			return false;
		}return true;
	}
}
