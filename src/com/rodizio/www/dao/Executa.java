package com.rodizio.www.dao;

import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Executa {
	private static Connection conn;
	private static Statement sCreate = null;

	public static boolean sqlCreateOrInsert(String strSql){

		try {
			conn = (Connection) Conexao.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			sCreate = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			sCreate.execute(strSql);
			sCreate.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
