package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModUF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOUF {
	
	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	
	public static  List<ModUF> retornaEstados(Connection conn){
		List<ModUF> lista = new ArrayList<>();
		String sql="";
		sql+="Select * From  endereco_estado";
		
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()){
				ModUF uf = new ModUF();
				uf.setId_Estado(rs.getString(1));
				uf.setNome_Estado(rs.getString(2));
				uf.setUf_Estado(rs.getString(3));
				lista.add(uf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

}
