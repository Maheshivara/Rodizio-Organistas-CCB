/**
 * 
 */
package com.rodizio.www.util.interfaces;

import java.util.List;

/**
 * @author Wesley
 *
 */
public interface IDAO {
	
	/**
	 * Metodo de Insert no Banco de Dados
	 * @param o
	 * @return
	 */
	public Long idInsert(Object o);
	
	/**
	 * Metodos de Update no Banco de Dados
	 * @param o
	 * @return
	 */
	public boolean update(Object o);
	
	/**
	 * Metodos de Delete no Banco de Dados
	 * @param o
	 * @return
	 */
	public boolean delete(Object o);
	
	/**
	 * Metodo de Selec All no Banco de Dados
	 * @param o
	 * @return
	 */
	public List<Object> selectAll(Object o);
	
	/**
	 * Metodo de Select All Where Id=
	 * @param Id
	 * @return
	 */
	public List<Object> selectId(Long Id);

}
