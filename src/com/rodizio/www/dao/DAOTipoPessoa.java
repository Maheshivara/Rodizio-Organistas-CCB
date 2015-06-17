package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModTipoPessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOTipoPessoa {

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;

	public static  List<ModTipoPessoa> retornaTipoPessoa(Connection conn){
		List<ModTipoPessoa> lista = new ArrayList<>();
		String sql="";
		sql+="Select * From  cad_pessoa_tipo";

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
				ModTipoPessoa tipoPessoa = new ModTipoPessoa();
				tipoPessoa.setId_tipo_pessoa(rs.getString(1));
				tipoPessoa.setTipo_pessoa(rs.getString(2));
				lista.add(tipoPessoa);
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

	public static ModTipoPessoa selectId(String idTipoPessoa, Connection conn){

		ModTipoPessoa tipoPessoa = new ModTipoPessoa();

		String sql="";
		sql+=" Select * From  cad_pessoa_tipo ";
		sql+=" WHERE id_pessoa_tipo = ? ";

		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, idTipoPessoa);
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
			if(rs.next()){

				tipoPessoa.setId_tipo_pessoa(rs.getString(1));
				tipoPessoa.setTipo_pessoa(rs.getString(2));
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

		return tipoPessoa;

	}

}
