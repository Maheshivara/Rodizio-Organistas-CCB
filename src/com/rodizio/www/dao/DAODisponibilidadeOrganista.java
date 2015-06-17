package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModDisponibilidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAODisponibilidadeOrganista{

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	private static boolean status=false;

	public static boolean insert(ModDisponibilidade dispon, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * FROM  cad_disp_organista ";
		sql+="WHERE  id_pessoa = ? AND id_dia = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, dispon.getId_pessoa());
			pstm.setInt(2, dispon.getDiaCulto());
			rs = pstm.executeQuery();
			if(!rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="INSERT INTO cad_disp_organista ( id_pessoa, id_dia, meiaHora, primParte, segParte, primParteRJM, segParteRJM) ";
				sql+="VALUES ( ? , ? , ? , ? , ? , ? , ? ) ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, dispon.getId_pessoa());
				pstm.setInt(2, dispon.getDiaCulto());
				pstm.setBoolean(3, dispon.isMeiaHora());
				pstm.setBoolean(4, dispon.isPrimParte());
				pstm.setBoolean(5, dispon.isSegParte());
				pstm.setBoolean(6, dispon.isPrimParteRJM());
				pstm.setBoolean(7, dispon.isSegParteRJM());
				
				
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
	public static boolean update(ModDisponibilidade dispon, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * FROM  cad_disp_organista ";
		sql+="WHERE  id_pessoa = ? AND id_dia = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, dispon.getId_pessoa());
			pstm.setInt(2, dispon.getDiaCulto());
			rs = pstm.executeQuery();
			if(rs.next()){

				sql="";
				sql+="UPDATE cad_disp_organista           ";
				sql+="SET                                 ";
				sql+="meiaHora = ?,                       ";
				sql+="primParte = ?,                      ";
				sql+="segParte = ?,                       ";
				sql+="primParteRJM = ?,                   ";
				sql+="segParteRJM = ?                     ";
				sql+="WHERE  id_pessoa = ? AND id_dia = ? ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setBoolean(1, dispon.isMeiaHora());
				pstm.setBoolean(2, dispon.isPrimParte());
				pstm.setBoolean(3, dispon.isSegParte());
				pstm.setBoolean(4, dispon.isPrimParteRJM());
				pstm.setBoolean(5, dispon.isSegParteRJM());
				pstm.setString(6, dispon.getId_pessoa());
				pstm.setInt(7, dispon.getDiaCulto());
				
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
	public static boolean delete(ModDisponibilidade dispon, Connection conn) {
		status =  false;
		String sql="";
		sql+="DELETE FROM  cad_disp_organista ";
		sql+="WHERE  id_pessoa = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, dispon.getId_pessoa());
			//pstm.setInt(2, dispon.getDiaCulto());
			
			status = pstm.execute();
			status = true;

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
	public static List<ModDisponibilidade> selectAll(ModDisponibilidade dispon, Connection conn) {
		List<ModDisponibilidade> listDispon = new ArrayList<>();
		
		String sql="";
		sql+="SELECT * FROM cad_disp_organista ";
		sql+="WHERE id_pessoa = ? ";
		
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, dispon.getId_pessoa());
			rs = pstm.executeQuery();
			while (rs.next()){
				ModDisponibilidade disponibilidade = new ModDisponibilidade();
				disponibilidade.setId_pessoa(rs.getString(1));
				disponibilidade.setDiaCulto(rs.getInt(2));
				disponibilidade.setMeiaHora(rs.getBoolean(3));
				disponibilidade.setPrimParte(rs.getBoolean(4));
				disponibilidade.setSegParte(rs.getBoolean(5));
				disponibilidade.setPrimParteRJM(rs.getBoolean(6));
				disponibilidade.setSegParteRJM(rs.getBoolean(7));
				
				listDispon.add(disponibilidade);
				
			}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listDispon;
	}


}
