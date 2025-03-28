package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOTipoPessoa;
import com.rodizio.www.modelo.ModTipoPessoa;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.util.List;

public abstract class ControleTipoPessoa {
	private static Connection conn;
	public ControleTipoPessoa(){
		abreConexao();
	}

	public static List<ModTipoPessoa> listaTiposPessoa(){

		abreConexao();

		return DAOTipoPessoa.retornaTipoPessoa(conn);

	}
	public static ModTipoPessoa selectId(String idTipoPessoa){
		abreConexao();

		return DAOTipoPessoa.selectId(idTipoPessoa, conn);
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
