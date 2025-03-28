/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOTelPessoa;
import com.rodizio.www.modelo.ModTelPessoa;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControleTelefone {

	private Connection conn;

	public ControleTelefone() throws Exception {

		abreConexao();

	}

	public ControleTelefone(List<ModTelPessoa> tel) throws Exception {

		abreConexao();
		for (ModTelPessoa listaTel : tel) {
			switch (listaTel.getStatusTel()) {
				case 0:// Insert
					insert(listaTel);
					break;
				case 1:// Update
					update(listaTel);
					break;
				case 2:// Delete
					delete(listaTel);
					break;
				default:
					fechaConexao();
					break;
			}
		}

	}

	/**
	 * Metodo para Incluir dados do Objeto no Banco.
	 * 
	 * @param o
	 * @return
	 */
	public boolean inserir(List<ModTelPessoa> tel) {

		for (ModTelPessoa listaTel : tel) {
			abreConexao();
			if (!DAOTelPessoa.insert(listaTel, conn)) {
				System.out.println("Nãoo Consegui Inserir o Email: " + listaTel.getTelefone_pessoa());
				return false;
			}
		}
		fechaConexao();
		return true;
	}

	public boolean update(List<ModTelPessoa> tel) {

		for (ModTelPessoa listaTel : tel) {
			abreConexao();
			if (!DAOTelPessoa.update(listaTel, conn)) {
				update(listaTel);
				System.out.println("Não Consegui Alterar o Email: " + listaTel.getTelefone_pessoa());
				return false;
			}
		}

		fechaConexao();

		return true;
	}

	private boolean update(ModTelPessoa tel) {
		abreConexao();
		if (!DAOTelPessoa.update(tel, conn)) {
			return false;
		}
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private boolean insert(ModTelPessoa tel) {
		abreConexao();
		if (!DAOTelPessoa.insert(tel, conn)) {
			return false;
		}
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private boolean delete(ModTelPessoa tel) {
		abreConexao();
		if (!DAOTelPessoa.delete(tel, conn)) {
			return false;
		}

		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public List<ModTelPessoa> listaTel(ModTelPessoa tel) {
		abreConexao();
		return DAOTelPessoa.selectAll(tel, conn);
	}

	private void fechaConexao() {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection abreConexao() {
		try {
			return conn = Conexao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
