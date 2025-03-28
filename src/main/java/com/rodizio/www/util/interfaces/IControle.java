/**
 * 
 */
package com.rodizio.www.util.interfaces;

import java.util.List;

/**
 * @author Wesley
 *
 */
public interface IControle {
	
	/**
	 * Metodo para Incluir dados do Objeto no Banco.
	 * @param o
	 * @return
	 */
	public boolean salvar(Object o);
	
	/**
	 * Metodo para Alterar dados do Objeto no Banco.
	 * @param o
	 * @return
	 */
	public boolean alterar(Object o);
	
	/**
	 * Metodo para Excluir dados do Objeto do Banco.
	 * @param o
	 * @return
	 */
	public boolean excluir(Object o);
	
	/**
	 * Metodo para Listar todos os Objetos referenciados no Banco.
	 * @param o
	 * @return
	 */
	public List<Object> listarTodos(Object o);
	
	/**
	 * Metodo para Listar todas os Objetos com o ID especificado.
	 * @param id
	 * @return
	 */
	public List<Object> listarPorID(Long id);
	

}
