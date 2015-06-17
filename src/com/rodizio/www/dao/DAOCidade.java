package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModCidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOCidade {
	
	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	
	public static  List<ModCidade> retornaCidades(String UF, Connection conn){
		List<ModCidade> lista = new ArrayList<>();
		String sql="";
	
		sql+="SELECT     id_cidade, nome_cidade ";
		sql+="  FROM     endereco_cidade ";
		sql+="INNER JOIN endereco_estado ";
		sql+="        ON endereco_estado.id_estado = cod_estado ";
		sql+="     WHERE uf_estado = ? ";
		sql+="  ORDER BY id_cidade ";
		
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, UF);
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
				ModCidade cidade = new ModCidade();
				cidade.setIdCidade(rs.getString(1));
				cidade.setNome_Cidade(rs.getString(2));
				lista.add(cidade);
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
	
	public static  ModCidade retornaCidadeID(String idCidade, Connection conn){
		ModCidade cidade = new ModCidade();
		String sql="";
		sql+="SELECT     id_cidade, nome_cidade, cod_estado ";
		sql+="  FROM     endereco_cidade ";
		sql+="INNER JOIN endereco_estado ";
		sql+="        ON endereco_estado.id_estado = endereco_cidade.cod_estado ";
		sql+="     WHERE id_cidade = ? ";
				
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, idCidade);
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
			if (rs.next()){
				
				cidade.setIdCidade(rs.getString(1));
				cidade.setNome_Cidade(rs.getString(2));
				cidade.setCodEstado(rs.getString(3));
			}else{
				return null;
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
		
		return cidade;
	}

}
