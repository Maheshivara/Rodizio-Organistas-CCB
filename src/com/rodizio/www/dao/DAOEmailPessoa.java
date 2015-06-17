package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModEmailPessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class DAOEmailPessoa{

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	private static boolean status=false;
	@SuppressWarnings("unused")
	private Vector<ModEmailPessoa> vecEmailPessoa=new Vector<>();

	public static boolean insert(ModEmailPessoa email, Connection conn) {
		status =  false;
		String sql="";
		sql+="Select * From cad_email_pessoa ";
		sql+="WHERE id_email_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, email.getId_email());
			rs = pstm.executeQuery();
			if(!rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="Insert Into cad_email_pessoa ( id_pessoa, email_pessoa ) ";
				sql+="Values ( ? , ? ) ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, email.getId_pessoa());
				pstm.setString(2, email.getEmail_pessoa());
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
	public static boolean update(ModEmailPessoa email, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * From cad_email_pessoa ";
		sql+="WHERE id_email_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, email.getId_email());
			rs = pstm.executeQuery();
			if(rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="UPDATE cad_email_pessoa SET email_pessoa =? ";
				sql+="WHERE id_email_pessoa =? ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, email.getEmail_pessoa());
				pstm.setString(2, email.getId_email());
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
	public static boolean delete(ModEmailPessoa email, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * From cad_email_pessoa ";
		sql+="WHERE id_email_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, email.getId_email());
			rs = pstm.executeQuery();
			if(rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="DELETE FROM cad_email_pessoa ";
				sql+="WHERE id_email_pessoa =? ";
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, email.getId_email());
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
	public static List<ModEmailPessoa> selectAll(ModEmailPessoa email, Connection conn) {

		List<ModEmailPessoa> listaEmails = new ArrayList<>();
		String sql="";
		sql+="SELECT * From cad_email_pessoa ";
		sql+="WHERE id_pessoa = ? ";

		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, email.getId_pessoa());
			rs = pstm.executeQuery();
			while (rs.next()){
				ModEmailPessoa emailSelect = new ModEmailPessoa();
				emailSelect.setId_email(rs.getString(1));
				emailSelect.setId_pessoa(rs.getString(2));
				emailSelect.setEmail_pessoa(rs.getString(3));
				listaEmails.add(emailSelect);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaEmails;
	}


}
