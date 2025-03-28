package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModOrganistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOOrganista{

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	private static boolean status=false;
	private static int idPessoa;

	public static int insert(ModOrganistas pessoa, Connection conn) {
		pstm = null;
		rs = null;
		status=false;
		String sql="";
		sql+="Select * From cad_pessoa ";
		sql+="WHERE id_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, pessoa.getId_pessoa());
			rs = pstm.executeQuery();
			if(!rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="Insert Into cad_pessoa (";
				sql+="id_pessoa_tipo, pessoa_nome, id_igreja, recebeEmail) ";
				sql+="Values (?,?,?,?) ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, pessoa.getIdTipoPessoa());
				pstm.setString(2, pessoa.getNome_pessoa());
				pstm.setString(3, pessoa.getId_Igreja());
				pstm.setBoolean(4, pessoa.getRecebeEmail());
				pstm.execute();

				rs = null;
				rs = pstm.getGeneratedKeys();
				idPessoa = rs.getInt(1);
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
		if(!status){
			return	-1;
		}
		return idPessoa;
	}
	public static boolean update(ModOrganistas pessoa, Connection conn) {
		pstm = null;
		rs = null;
		status=false;
		String sql="";
		sql+="Select * From cad_pessoa ";
		sql+="WHERE id_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, pessoa.getId_pessoa());
			rs = pstm.executeQuery();
			if(rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="UPDATE cad_pessoa ";
				sql+="SET id_pessoa_tipo =? , ";
				sql+="pessoa_nome =? ,";
				sql+="id_igreja =? ,";
				sql+="recebeEmail =? ";
				sql+="WHERE id_pessoa =? ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, pessoa.getIdTipoPessoa());
				pstm.setString(2, pessoa.getNome_pessoa());
				pstm.setString(3, pessoa.getId_Igreja());
				pstm.setBoolean(4, pessoa.getRecebeEmail());
				pstm.setString(5, pessoa.getId_pessoa());
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
	public static boolean delete(ModOrganistas pessoa, Connection conn) {
		pstm = null;
		rs = null;
		status=false;
		String sql="";
		sql+="DELETE From cad_pessoa ";
		sql+="WHERE id_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, pessoa.getId_pessoa());
			
			try {
				status = pstm.execute();
				status = true;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status =  false;
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

	public static List<ModOrganistas> selectNome(String nomePessoa, String IdTipoPessoa, Connection conn){
		pstm = null;
		rs = null;
		List<ModOrganistas> listaPessoas = new ArrayList<>();

		String sql="";

		sql+="SELECT  cad_pessoa.id_pessoa,  cad_pessoa.pessoa_nome, cad_pessoa.id_pessoa_tipo ";
		sql+="  FROM cad_pessoa ";
		sql+=" WHERE cad_pessoa.pessoa_nome like ? ";
		if(IdTipoPessoa.compareToIgnoreCase("7")!=0){
			sql+=" 	AND id_pessoa_tipo <> 7 ";
		}else{
			sql+=" 	AND id_pessoa_tipo = 7 ";
		}
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, "%"+nomePessoa+"%");
			rs = pstm.executeQuery();
			while (rs.next()){

				ModOrganistas p = new ModOrganistas();
				p.setId_pessoa(rs.getString(1));
				p.setNome_pessoa(rs.getString(2));
				p.setIdTipoPessoa(rs.getString(3));

				listaPessoas.add(p);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status =  false;
		}

		return listaPessoas;
	}
	public static ModOrganistas selectID(String idPessoa, Connection conn){
		pstm = null;
		rs = null;
		ModOrganistas pessoa = new ModOrganistas();

		String sql="";
		sql+="Select * From cad_pessoa ";
		sql+="WHERE id_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, idPessoa);
			rs = pstm.executeQuery();
			if(rs.next()){
				pessoa.setId_pessoa(rs.getString(1));
				pessoa.setIdTipoPessoa(rs.getString(2));
				pessoa.setId_IgrejaComum(rs.getString(3));
				pessoa.setNome_pessoa(rs.getString(4));
				pessoa.setRecebeEmail(rs.getBoolean(5));

			}else{
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoa;
	}
	
	public static List<ModOrganistas> selectAll(String idIgreja, Connection conn){
		pstm = null;
		rs = null;
		List<ModOrganistas> listaPessoas = new ArrayList<>();

		String sql="";

		sql+="SELECT  *";
		sql+="  FROM cad_pessoa ";
		sql+=" WHERE cad_pessoa.id_igreja = ? ";
		sql+=" 	AND id_pessoa_tipo = 7 ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, idIgreja);
			rs = pstm.executeQuery();
			while (rs.next()){

				ModOrganistas p = new ModOrganistas();
				p.setId_pessoa(rs.getString(1));
				p.setNome_pessoa(rs.getString(4));
				p.setIdTipoPessoa(rs.getString(2));

				listaPessoas.add(p);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status =  false;
		}

		return listaPessoas;
	}
}
