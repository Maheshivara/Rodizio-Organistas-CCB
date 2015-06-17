package com.rodizio.www.util.interfaces;

/**
 * Classe Responsavel por Delegar os Metodos a Serem
 * Implementados em Classes JInternalFrame de Cadastros
 *
 * @version 1.0
 * @author Wesley
 * Interfaces
 * 
 */

public interface ICadastros {

	/**
	 * Inicializa os Componentes e Tratamentos.
	 */
	public abstract void inicializaComponentes();
	
	/**
	 * {@code} Remove a Barra de Títulos do JInternalFrame
	 * e Trava o Mesmo na Posição Definida.
	 */
	public abstract void removeTituloInternalFrame();

	/**
	 * Metodo que valida se os Campos do Cadastro Foram Preenchidos
	 * @return True / False
	 */
	public abstract boolean validaFormulario();

	/**
	 * {@code} Metodo para Incluir os Botões da tela de Cadastro.
	 * @return 
	 */
	public abstract void insereBotoes();

	/**
	 * Metodo para Setar Enabled (True/False) dos Botões, baseado em
	 * um botão quando Clicado. 
	 * @param opcao
	 */
	public abstract void trataBotoes(int opcao);

	/**
	 * Metodo para Setar Editable (True/False) Dos Campos de um Formulario
	 * Quando o Formulario é Carregado, Alterado e etc.
	 * @param opcao
	 */
	public abstract void trataCampos(int opcao);

	/**
	 * Metodo que Limpa os Campos de um Formulário, deixando-o
	 * Pronto para um novo Cadastro.
	 */
	public abstract void limpaTela();

	/**
	 * Metodo para Incluir ou Alterar um Cadastro
	 * Recebe True, para Alterar e False para Inclusão.
	 * @param alteracao
	 * Retorna True para Cadastro Incluido ou Alterado com Sucesso.
	 * @return
	 */
	public abstract boolean cadastroIncluirAlterar(boolean alteracao);

	/**
	 * Metodo para Excluir um Cadastro
	 * Retorna True para Cadastro Incluido ou Alterado com Sucesso.
	 * @return
	 */
	public abstract boolean cadastroExcluir();

	/**
	 * Metodo que Realiza a Busca no Banco de Dados com o Auxilio
	 * Das Classes de xxxControle, e DAOxxx.
	 */
	public abstract void efetuaPesquisa();

	/**
	 * Metodo que utiliza a implementação da Classe DefaultAbstractModel
	 * para carregar os dados Pesquisados para o JTable.
	 */
	public abstract void carregaTabela();

	/**
	 * Define o Tamenho das Colunas do Modelo dentro de Uma JTable.
	 */
	public abstract void dimensionaTabela();

	/**
	 * Limpa os Valores do Modelo dentro de uma JTable.
	 */
	public abstract void limpaTabela();

	/**
	 * Preenche os Campos do Formulario, com as informações Vindas da pesquisa.
	 */
	public abstract void preencheFormulario();
	
	/**
	 * Tratar os campos que será acionado TAB qundo precionar Enter.
	 */
	public abstract void trataEnter();

	
	
}
