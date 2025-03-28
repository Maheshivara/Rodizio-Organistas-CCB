package com.rodizio.www.visao.controle;
/**
 * Classe Abastrata com Mï¿½todos Estaticos
 * para Lanï¿½ar em Tela as Mensagens Disparadas
 * Pela Classe DisparaMensagem.
 * @author Wesley
 */

import javax.swing.*;
import java.awt.*;

public abstract class Mensagens {

	public static void setMensagens(Component painel, String msg) {
		JOptionPane.showMessageDialog(painel, msg);
	}

	public static void setMensagens(Component painel, String msg,String titulo, int icone) {
		JOptionPane.showMessageDialog(painel, msg, titulo, icone);
	}
	public static int setMensagens(Component painel, int opcoes){
		return JOptionPane.showConfirmDialog(painel, "Confirma a Exclusao?", null, opcoes, 3);
	}
}
