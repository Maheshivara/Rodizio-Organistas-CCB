/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOIgreja;
import com.rodizio.www.modelo.ModCadIgreja;
import com.rodizio.www.modelo.ModDiasCulto;
import com.rodizio.www.persistencia.Conexao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControleIgreja {

	private Connection conn;
	/**
	 * Para Exluir uma IKgreja, percorrer todos as Pessoas sinalizando o Status
	 * como 2 - EXCLUIR
	 * @param cadIgreja
	 * @throws Exception
	 */
	public ControleIgreja(ModCadIgreja cadIgreja) throws Exception{
		abreConexao();
		switch(cadIgreja.getStatusCadIgreja()){
		case 0://Insert
			insert(cadIgreja);
			break;
		case 1://Update
			update(cadIgreja);
			break;
		case 2://Delete
			delete(cadIgreja);
			break;
		case 3://Select por id ou por nome
			select(cadIgreja);
			break;
		default:
			fechaConexao();
			break;

		}
	}

	public ModCadIgreja selectId(String idIgreja){
		abreConexao();
		ModCadIgreja igreja = DAOIgreja.selectId(idIgreja, conn);
		ModDiasCulto diaIgreja = new ModDiasCulto();
		diaIgreja.setIdIgreja(idIgreja);
		try {
			igreja.setListaDiasCulto(new ControleIgrejaDiasCulto().listaDiasCulto(diaIgreja));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return igreja;
	}

	public List<ModCadIgreja> select(ModCadIgreja cadIgreja) {
		abreConexao();
		// TODO Auto-generated method stub
		return DAOIgreja.selectAll(cadIgreja, conn);
	}
	private boolean delete(ModCadIgreja cadIgreja) {
		Boolean status;
		try {
			new ControleIgrejaDiasCulto(cadIgreja.getListaDiasCulto(),cadIgreja.getId_igreja(), cadIgreja.getStatusCadIgreja());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		abreConexao();
		status = DAOIgreja.delete(cadIgreja, conn);
		fechaConexao();
		return status;
	}
	private boolean update(ModCadIgreja cadIgreja) {
		Boolean status;
		if(!cadIgreja.getListaDiasCulto().isEmpty()){
			for(ModDiasCulto diasCulto : cadIgreja.getListaDiasCulto()){
				diasCulto.setIdIgreja(""+cadIgreja.getId_igreja());
			}
		}
		try {
			new ControleIgrejaDiasCulto(cadIgreja.getListaDiasCulto(),cadIgreja.getId_igreja(), cadIgreja.getStatusCadIgreja());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		abreConexao();
		status = DAOIgreja.update(cadIgreja, conn);
		fechaConexao();
		return status;
	}
	private boolean insert(ModCadIgreja cadIgreja) {
		abreConexao();
		int idIgreja = DAOIgreja.insert(cadIgreja, conn);
		if(idIgreja == -1){
			System.out.println("Erro ao Salvar a Pessoa no Banco de Dados!");
		}else{
			if(!cadIgreja.getListaDiasCulto().isEmpty()){
				for(ModDiasCulto diasCulto: cadIgreja.getListaDiasCulto()){
					diasCulto.setIdIgreja(""+idIgreja);
				}

				try {
					new ControleIgrejaDiasCulto(cadIgreja.getListaDiasCulto(),cadIgreja.getId_igreja(), cadIgreja.getStatusCadIgreja() );
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro para Gravar os dias de Culto", "ERRO!", 3);
					e.printStackTrace();
					return false;
				}
			}
		}
		fechaConexao();
		return true;
	}
	public ControleIgreja() throws Exception{
		abreConexao();
	}
	private void fechaConexao(){
		try {
			if(!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Connection abreConexao(){
		try {
			return	conn = Conexao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
