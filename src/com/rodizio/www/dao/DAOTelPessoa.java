package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModEmailPessoa;
import com.rodizio.www.modelo.ModTelPessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class DAOTelPessoa{

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	private static boolean status=false;
	@SuppressWarnings("unused")
	private Vector<ModEmailPessoa> vecEmailPessoa=new Vector<>();

	public static boolean insert(ModTelPessoa tel, Connection conn) {
		status =  false;
		String sql="";
		sql+="Select * From cad_telefone_pessoa ";
		sql+="WHERE  id_telefone_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, tel.getId_telefone());
			rs = pstm.executeQuery();
			if(!rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="Insert Into cad_telefone_pessoa ( id_pessoa, telefone_pessoa, id_tipo_telefone ) ";
				sql+="Values ( ? , ? , ? ) ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, tel.getId_pessoa());
				pstm.setString(2, tel.getTelefone_pessoa());
				pstm.setInt(3, tel.getIdTipoTelefone());
				pstm.execute();
				status = true;
			}

			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status =  false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status =  false;
		}
		return status;
	}
	public static boolean update(ModTelPessoa tel, Connection conn) {
		status =  false;
		String sql="";
		sql+="Select * From cad_telefone_pessoa ";
		sql+="WHERE  id_telefone_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, tel.getId_telefone());
			rs = pstm.executeQuery();
			if(rs.next()){

				sql="";
				sql+="UPDATE cad_telefone_pessoa SET telefone_pessoa =? ";
				sql+="WHERE id_telefone_pessoa =? ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, tel.getTelefone_pessoa());
				pstm.setString(2, tel.getId_telefone());
				pstm.execute();
				status = true;
			}

			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status =  false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status =  false;
		}
		return status;
	}
	public static boolean delete(ModTelPessoa tel, Connection conn) {
		status =  false;
		String sql="";
		sql+="DELETE From cad_telefone_pessoa ";
		sql+="WHERE id_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, tel.getId_pessoa());
			status = pstm.execute();
			status =  true;

			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status =  false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status =  false;
		}
		return status;
	}
	public static List<ModTelPessoa> selectAll(ModTelPessoa tel, Connection conn) {
		List<ModTelPessoa> listaTels = new ArrayList<>();
		
		String sql="";
		sql+="SELECT * From cad_telefone_pessoa ";
		sql+="WHERE id_pessoa = ? ";
		
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, tel.getId_pessoa());
			rs = pstm.executeQuery();
			while (rs.next()){
				ModTelPessoa telefone = new ModTelPessoa();
				telefone.setId_telefone(rs.getString(1));
				telefone.setId_pessoa(rs.getString(2));
				telefone.setIdTipoTelefone(rs.getInt(3));
				telefone.setTelefone_pessoa(rs.getString(4));
				
				listaTels.add(telefone);
				
			}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaTels;
	}


}
