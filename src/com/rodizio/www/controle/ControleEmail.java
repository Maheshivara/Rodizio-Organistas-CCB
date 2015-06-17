/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOEmailPessoa;
import com.rodizio.www.modelo.ModEmailPessoa;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControleEmail {

	
	private Connection conn;

	public ControleEmail() throws Exception{
		abreConexao();
	}
	
	public ControleEmail(List<ModEmailPessoa> email) throws Exception{

		abreConexao();
		for(ModEmailPessoa listaEmail : email){
			switch(listaEmail.getStatusEmail()){
			case 0://Insert
				insert(listaEmail);
				break;
			case 1://Update
				update(listaEmail);
				break;
			case 2://Delete
				delete(listaEmail);
				break;
			}
		}

	}
	
	public List<ModEmailPessoa> listaEmail(ModEmailPessoa email){
		abreConexao();
		return DAOEmailPessoa.selectAll(email, conn);
	}

	private boolean update(ModEmailPessoa email){
		abreConexao();
		if(!DAOEmailPessoa.update(email, conn)){
			return false;
		}
		fechaConexao();
		return true;
	}
	private boolean insert(ModEmailPessoa email){
		abreConexao();
		if(!DAOEmailPessoa.insert(email, conn)){
			return false;
		}
		fechaConexao();
		return true;
	}
	private boolean delete(ModEmailPessoa email){
		abreConexao();
		if(!DAOEmailPessoa.delete(email, conn)){
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
