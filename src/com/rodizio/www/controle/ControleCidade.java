package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOCidade;
import com.rodizio.www.modelo.ModCidade;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.util.List;

public abstract class ControleCidade {
	private static Connection conn;
	public ControleCidade(){
		abreConexao();
	}
	
	public static List<ModCidade> listaCidadess(String UF){

		abreConexao();
		return DAOCidade.retornaCidades(UF, conn);
		
	}
	
	public static ModCidade cidadeID(String id){

		abreConexao();
		return DAOCidade.retornaCidadeID(id, conn);
		
	}
	
	
	
	private static Connection abreConexao(){
		try {
		return	conn = Conexao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
}
