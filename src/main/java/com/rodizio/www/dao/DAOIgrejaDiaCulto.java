package com.rodizio.www.dao;

import com.rodizio.www.modelo.ModDiasCulto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOIgrejaDiaCulto{

	private static PreparedStatement pstm=null;
	private static ResultSet rs=null;
	private static boolean status=false;

	public static boolean insert(ModDiasCulto diaCulto, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * FROM  dias_culto_comum ";
		sql+="WHERE   id_dia_culto = ? AND id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, diaCulto.getDiaCulto());
			pstm.setString(2, diaCulto.getIdIgreja());
			rs = pstm.executeQuery();
			if(!rs.next()){
				pstm = null;
				rs = null;

				sql="";
				sql+="INSERT INTO dias_culto_comum (id_dia_culto, id_comum) ";
				sql+="VALUES ( ? , ? ) ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, diaCulto.getDiaCulto());
				pstm.setString(2, diaCulto.getIdIgreja());
				
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
	public static boolean update(ModDiasCulto diaCulto, Connection conn) {
		status =  false;
		String sql="";
		sql+="SELECT * FROM  dias_culto_comum ";
		sql+="WHERE   id_dia_culto = ? AND id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, diaCulto.getDiaCulto());
			pstm.setString(2, diaCulto.getIdIgreja());
			rs = pstm.executeQuery();
			if(rs.next()){

				sql="";
				sql+="UPDATE dias_culto_comum ";
				sql+="SET ";
				sql+="id_dia_culto = ?, ";
				sql+="id_comum = ? ";
				sql+="WHERE   id_dia_culto = ? AND id_comum = ? ";

				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, diaCulto.getDiaCulto());
				pstm.setString(2, diaCulto.getIdIgreja());
				pstm.setString(3, diaCulto.getDiaCulto());
				pstm.setString(4, diaCulto.getIdIgreja());
				
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
	public static boolean delete(ModDiasCulto diasCulto, Connection conn) {
		status =  false;
		String sql="";
		sql+="DELETE FROM  dias_culto_comum ";
		sql+="WHERE   id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, diasCulto.getIdIgreja());
			
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
	public static List<ModDiasCulto> selectAll(ModDiasCulto diaCulto, Connection conn) {
		List<ModDiasCulto> listaDiasCulto = new ArrayList<>();
		
		String sql="";
		sql+="SELECT * FROM  dias_culto_comum ";
		sql+="WHERE   id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, diaCulto.getIdIgreja());
			rs = pstm.executeQuery();
			while(rs.next()){
				ModDiasCulto diasCulto = new ModDiasCulto();
				diasCulto.setDiaCulto(rs.getString(1));
				diasCulto.setIdIgreja(rs.getString(2));
				listaDiasCulto.add(diasCulto);
			}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDiasCulto;
	}
	public static List<ModDiasCulto> selectAll(String idIgreja, Connection conn) {
List<ModDiasCulto> listaDiasCulto = new ArrayList<>();
		
		String sql="";
		sql+="SELECT * FROM  dias_culto_comum ";
		sql+="WHERE   id_comum = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, idIgreja);
			rs = pstm.executeQuery();
			while(rs.next()){
				ModDiasCulto diasCulto = new ModDiasCulto();
				diasCulto.setDiaCulto(rs.getString(1));
				diasCulto.setIdIgreja(rs.getString(2));
				listaDiasCulto.add(diasCulto);
			}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDiasCulto;
	}


}
