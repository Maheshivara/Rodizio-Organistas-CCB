package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModCadIgreja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOIgreja{

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	private static boolean status=false;
	private static int idIgreja;

	public static int insert(ModCadIgreja igreja, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * FROM cad_comum ";
		sql+="WHERE  id_comum  = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, igreja.getId_igreja());
			rs = pstm.executeQuery();
			if(!rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="INSERT INTO cad_comum ";
				sql+="( ";
				sql+=" nome_comum,         ";
				sql+=" lograd_comum,       ";
				sql+=" complem_end_igreja, ";
				sql+=" num_comum,          ";
				sql+=" cep_comum,          ";
				sql+=" bairro_comum,       ";
				sql+=" id_cidade_comum     ";
				sql+=" )                   ";
				sql+="Values ( ?, ? , ? , ? , ? , ? , ? ) ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, igreja.getNome_comum());
				pstm.setString(2, igreja.getLogradouro_igreja());
				pstm.setString(3, igreja.getComplem_end_igreja());
				pstm.setString(4, igreja.getNum_end_igreja());
				pstm.setString(5, igreja.getCep_igreja());
				pstm.setString(6, igreja.getBairro_igreja());
				pstm.setString(7, igreja.getId_cidade());
				pstm.execute();

				rs = null;
				rs = pstm.getGeneratedKeys();
				idIgreja = rs.getInt(1);
				status = true;
			}

			try {
				fechaRecursos();
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
		return idIgreja;
	}
	public static boolean update(ModCadIgreja igreja, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * FROM cad_comum ";
		sql+="WHERE  id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, igreja.getId_igreja());
			rs = pstm.executeQuery();
			if(rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="UPDATE cad_comum        ";
				sql+=" SET                    ";
				sql+=" nome_comum =? ,        ";
				sql+=" lograd_comum =? ,      ";
				sql+=" complem_end_igreja =? ,";
				sql+=" num_comum =? ,         ";
				sql+=" cep_comum =? ,         ";
				sql+=" bairro_comum =? ,      ";
				sql+=" id_cidade_comum  =?    ";
				sql+="WHERE  id_comum = ?         ";
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, igreja.getNome_comum());
				pstm.setString(2, igreja.getLogradouro_igreja());
				pstm.setString(3, igreja.getComplem_end_igreja());
				pstm.setString(4, igreja.getNum_end_igreja());
				pstm.setString(5, igreja.getCep_igreja());
				pstm.setString(6, igreja.getBairro_igreja());
				pstm.setString(7, igreja.getId_cidade());
				pstm.setString(8, igreja.getId_igreja());
				pstm.execute();
				status = true;
			}

			try {
				fechaRecursos();
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
	public static boolean delete(ModCadIgreja igreja, Connection conn) {
		status =  false;
		String sql="";
		sql+="DELETE From cad_comum ";
		sql+="WHERE  id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, igreja.getId_igreja());
			pstm.execute();
			status = true;

			try {
				fechaRecursos();
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

	public static List<ModCadIgreja> selectAll(ModCadIgreja igreja, Connection conn){
		List<ModCadIgreja> listaIgrejas = new ArrayList<>();
		String sql="";
		sql+="SELECT * FROM cad_comum ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()){
				ModCadIgreja cadIgreja = new ModCadIgreja();
				cadIgreja.setId_igreja(rs.getString(1));
				cadIgreja.setNome_comum(rs.getString(2));
				listaIgrejas.add(cadIgreja);
			}
			
			try {
				fechaRecursos();
			} catch (SQLException e) {
				e.printStackTrace();
				status =  false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status =  false;
			return null;
		}
		return 	listaIgrejas;
	}

	public static ModCadIgreja selectId(String idIgreja, Connection conn){
		ModCadIgreja cadIgreja = new ModCadIgreja();
		String sql="";
		sql+="SELECT * FROM cad_comum ";
		sql+="WHERE   id_comum =? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, idIgreja);
			rs = pstm.executeQuery();
			if(rs.next()){

				cadIgreja.setId_igreja(rs.getString(1));
				cadIgreja.setNome_comum(rs.getString(2));
				cadIgreja.setLogradouro_igreja(rs.getString(3));
				cadIgreja.setComplem_end_igreja(rs.getString(4));
				cadIgreja.setNum_end_igreja(rs.getString(5));
				cadIgreja.setCep_igreja(rs.getString(6));
				cadIgreja.setBairro_igreja(rs.getString(7));
				cadIgreja.setId_cidade(rs.getString(8));
			}
			
			try {
				fechaRecursos();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return cadIgreja;
	}
	public static void fechaRecursos() throws SQLException {
		pstm.close();
		//rs.close();
	}

}
