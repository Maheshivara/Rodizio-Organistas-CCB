package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOUF;
import com.rodizio.www.modelo.ModUF;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.util.List;

public abstract class ControleUF {
	private static Connection conn;
	public ControleUF(){
		abreConexao();
	}

	public static List<ModUF> listaEstados(){
		abreConexao();
		return DAOUF.retornaEstados(conn);
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
