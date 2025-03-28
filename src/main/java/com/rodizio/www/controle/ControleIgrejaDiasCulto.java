/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOIgrejaDiaCulto;
import com.rodizio.www.modelo.ModDiasCulto;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControleIgrejaDiasCulto {


	private Connection conn;

    public ControleIgrejaDiasCulto() throws Exception{
		abreConexao();
	}

	public ControleIgrejaDiasCulto(List<ModDiasCulto> diasCulto, String idIgreja, int statusCadIgreja) throws Exception{
        ModDiasCulto diaCulto = new ModDiasCulto();
		diaCulto.setIdIgreja(idIgreja);
		//abreConexao();
		delete(diaCulto);
		if(!diasCulto.isEmpty() && statusCadIgreja!=2){
			for(ModDiasCulto listaDiasCulto : diasCulto){
				switch(listaDiasCulto.getStatusDiaCulto()){
				case 0://Insert
					insert(listaDiasCulto);
					break;
				case 1://Update
					insert(listaDiasCulto);
					break;
				case 2://Delete
					delete(listaDiasCulto);
					break;
				}
			}
		}
	}

	public List<ModDiasCulto> listaDiasCulto(ModDiasCulto diaCulto){
		abreConexao();

		return DAOIgrejaDiaCulto.selectAll(diaCulto, conn);
	}
	public List<ModDiasCulto> listaDiasCulto(String idIgreja){
		abreConexao();

		return DAOIgrejaDiaCulto.selectAll(idIgreja, conn);
	}

	private boolean insert(ModDiasCulto diaCulto){

		abreConexao();
		if(!DAOIgrejaDiaCulto.insert(diaCulto, conn)){
			return false;
		}
		fechaConexao();
		return true;
	}
	private boolean delete(ModDiasCulto diaCulto){
		abreConexao();
		if(!DAOIgrejaDiaCulto.delete(diaCulto, conn)){
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
		//fechaConexao();
		try {
			return	conn = Conexao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
