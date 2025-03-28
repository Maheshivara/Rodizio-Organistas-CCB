package com.rodizio.www.visao.controle;

import java.awt.*;

/**
 * Classe Abstrata com o Metodo Estatico:
 * msg, que recebe os seguintes parametros:
 * int: Opcaoo: Numero da Mensagem a Ser Disparada
 * Component: frame: O Name Do Frame de Onde a Mensagem Foi Disparada.
 * Todas as Mensagens do Sistema estao e deverao estar presentes nesta Classe.
 * 
 * @author Wesley
 * 
 */
public abstract class DisparaMensagem {

	public static void msg(int opcao, Component frame) {
		switch (opcao) {
			case 1:
				/**
				 * O Campo Usuario devera ser preenchido.\nDigite novamente.
				 */
				Mensagens.setMensagens(frame, "O Campo Usuario devera ser preenchido.\nDigite novamente.");
				break;
			case 2:
				/**
				 * "O Campo Senha devera ser preenchido com no mï¿½nimo 3 caracteres.\nDigite
				 * novamente."
				 */
				Mensagens.setMensagens(frame,
						"O Campo Senha devera ser preenchido com no mï¿½nimo 3 caracteres.\nDigite novamente.");
				break;
			case 3:
				/**
				 * "Usuario ou Senha Invalidos."
				 */
				Mensagens.setMensagens(frame, "Usuario ou Senha Invalidos.");
				break;
			case 4:
				/**
				 * "Numero Mï¿½ximo de Tentativas Esgotados. \nAbra o Sistema Novamente."
				 */
				Mensagens.setMensagens(frame, "Numero Máximo de Tentativas Esgotados. \nAbra o Sistema Novamente.");
				break;
			case 5:
				/**
				 * "Sem Acesso ao Banco de Dados. \nPor favor Verifique o Servidor de Banco de
				 * Dados. \nE Abra o Sistema Novamente."
				 */
				Mensagens.setMensagens(frame,
						"Sem Acesso ao Banco de Dados. \nPor favor Verifique o Servidor de Banco de Dados. \nE Abra o Sistema Novamente.");
				break;
			case 6:
				/**
				 * "Existe Campo Vazio, por favor preencha."
				 */
				Mensagens.setMensagens(frame, "Existe Campo Vazio, por favor preencha.");
				break;
			case 7:
				/**
				 * "Voce devera selecionar um Item."
				 */
				Mensagens.setMensagens(frame, "Voce devera selecionar um Item.");
				break;
			case 8:
				/**
				 * "Senhas Nao Conferem, Digite novamente."
				 */
				Mensagens.setMensagens(frame, "Senhas Nao Conferem, Digite novamente.");
				break;
			case 9:
				/**
				 * Nao Foi possivel Gravar os Dados no Bando de Dados.\nVerifique e Tente
				 * novamente." ***
				 */
				Mensagens.setMensagens(frame,
						"Nao Foi possivel Gravar os Dados no Banco de Dados.\nVerifique e Tente novamente.");
				break;
			case 10:
				/**
				 * "Dados Gravados com Sucesso." ***
				 */
				Mensagens.setMensagens(frame, "Dados Gravados com Sucesso.");
				break;
			case 11:
				/**
				 * "Existe Campo com Tamanho Invalido, por favor preencha com o Tamanho
				 * correto." ***
				 */
				Mensagens.setMensagens(frame, "Existe Campo com Tamanho Invalido, por favor preencha com o Tamanho correto.");
				break;
			case 12:
				/**
				 * "Quantidade Max. de Caracteres: 10." ***
				 */
				Mensagens.setMensagens(frame, "Quantidade Max. de Caracteres: 10.");
				break;
			case 16:
				/**
				 * Cadastro Excluido com Sucesso."***
				 */
				Mensagens.setMensagens(frame, "Cadastro Excluido com Sucesso.");
				break;
			case 17:
				/**
				 * "Erro na Exclusao do Cadastro."***
				 */
				Mensagens.setMensagens(frame, "Erro na Exclusao do Cadastro.");
				break;
			case 18:
				/**
				 * "Voce deve selecionar uma Linha e entao Teclar Enter."***
				 */
				Mensagens.setMensagens(frame, "Voce deve selecionar uma Linha e entao Teclar Enter.");
				break;
			case 19:
				/**
				 * "Voce devera Digitar Outro Login, pois este ja existe no Sistema." ***
				 */
				Mensagens.setMensagens(frame, "Voce devera Digitar Outro Login, pois este ja existe no Sistema.");
				break;
		}

	}

	public static int msg(int opcao, Component frame, int tipoConfirma) {
		int confirma = -1;
		switch (opcao) {
			case 13:
				/**
				 * "Confirma a Exclusao?" ***
				 */
				confirma = (int) Mensagens.setMensagens(frame, tipoConfirma);
				break;

		}
		return confirma;
	}
}
