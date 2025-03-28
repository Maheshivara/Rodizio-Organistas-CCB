/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAODisponibilidadeOrganista;
import com.rodizio.www.modelo.ModDisponibilidade;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControleDisponibilidade {

	
	private Connection conn;

	public ControleDisponibilidade() throws Exception{
		abreConexao();
	}
	
	public ControleDisponibilidade(List<ModDisponibilidade> dispon) throws Exception{

		abreConexao();
		delete(dispon.get(0));
		for(ModDisponibilidade listaDispon : dispon){
			switch(listaDispon.getStatusDisponibilidade()){
			case 0://Insert
				insert(listaDispon);
				break;
			case 1://Update
				insert(listaDispon);
				break;
			case 2://Delete
				delete(listaDispon);
				break;
			}
		}

	}
	
	public List<ModDisponibilidade> listaDisponibilidade(ModDisponibilidade dispon){
		abreConexao();
		
		return DAODisponibilidadeOrganista.selectAll(dispon, conn);
	}

	@SuppressWarnings("unused")
	private boolean update(ModDisponibilidade dispon){
		abreConexao();
		delete(dispon);
		if(!DAODisponibilidadeOrganista.update(dispon, conn)){
			return false;
		}
		fechaConexao();
		return true;
	}
	private boolean insert(ModDisponibilidade dispon){

		abreConexao();
		if(!DAODisponibilidadeOrganista.insert(dispon, conn)){
			return false;
		}
		fechaConexao();
		return true;
	}
	private boolean delete(ModDisponibilidade dispon){
		abreConexao();
		if(!DAODisponibilidadeOrganista.delete(dispon, conn)){
			return false;
		}
		fechaConexao();
		
		return true;
	}
	private void fechaConexao(){
		try {
			if(!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Connection abreConexao(){
		try {
		return	conn = Conexao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
