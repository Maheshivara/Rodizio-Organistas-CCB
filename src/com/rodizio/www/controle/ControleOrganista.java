/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOOrganista;
import com.rodizio.www.dao.DAOPessoa;
import com.rodizio.www.modelo.*;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControleOrganista {

	private Connection conn;
	private ControleEmail controleEmail;
	private ControleTelefone controleTel;
	private ControleDisponibilidade controleDispon;


	public ControleOrganista() throws Exception{
		conn = Conexao.getConnection();
	}

	public ControleOrganista(String nomePessoa, String idTipoPessoa){
		select(nomePessoa, idTipoPessoa );
	}
	/**
	 * Para Exluir uma pessoa, percorrer todos os emails e telefones sinalizando o Status
	 * como 2 - EXCLUIR
	 * @param pessoa
	 * @throws Exception
	 */
	public ControleOrganista(ModOrganistas pessoa) throws Exception{
		abreConexao();
		switch(pessoa.getStatusPessoa()){
		case 0://Insert
			insert(pessoa);
			break;
		case 1://Update
			update(pessoa);
			break;
		case 2://Delete
			delete(pessoa);
			break;
		case 3://Select id
			break;
		}
	}

	public List<ModPessoa> select(String nomePessoa, String idTipoPessoa) {
		abreConexao();
		return DAOPessoa.selectNome(nomePessoa, idTipoPessoa, conn);

	}
	
	public List<ModOrganistas> selectAll(String idIgreja) {
		abreConexao();
		List<ModOrganistas> listaOrganistas = DAOOrganista.selectAll(idIgreja, conn);
		for(ModOrganistas p: listaOrganistas){
			ModDisponibilidade disp = new ModDisponibilidade();
			disp.setId_pessoa(p.getId_pessoa());
			try {
				controleDispon = new ControleDisponibilidade();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.setListaDisponibilidades(controleDispon.listaDisponibilidade(disp));
		}
			
		return listaOrganistas;

	}

	public ModOrganistas selectID(String idPessoa) {
		abreConexao();
		ModOrganistas p = new ModOrganistas();
		ModEmailPessoa email = new ModEmailPessoa();
		ModTelPessoa tel = new ModTelPessoa();
		ModDisponibilidade disp = new ModDisponibilidade();

		p = DAOOrganista.selectID(idPessoa, conn);
		if(p!=null){
			email.setId_pessoa(idPessoa);
			try {
				controleEmail = new ControleEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
			p.setListaEmailPessoa(controleEmail.listaEmail(email));

			tel.setId_pessoa(idPessoa);

			try {
				controleTel = new ControleTelefone();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.setListaTelPessoa(controleTel.listaTel(tel));
			
			disp.setId_pessoa(idPessoa);
			try {
				controleDispon = new ControleDisponibilidade();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.setListaDisponibilidades(controleDispon.listaDisponibilidade(disp));
			
		}

		return p;

	}
	private boolean delete(ModOrganistas pessoa) {
		abreConexao();
		if(!pessoa.getListaEmailPessoa().isEmpty()){
			try {

				controleEmail = new ControleEmail(pessoa.getListaEmailPessoa());
			} catch (Exception e) {
				System.out.println("Erro ao Excluir o cadastro de Telefones da Pessoa.");
				e.printStackTrace();
				return false;
			}
		}
		if(!pessoa.getListaTelPessoa().isEmpty()){
			try {
				controleTel = new ControleTelefone(pessoa.getListaTelPessoa());
			} catch (Exception e) {
				System.out.println("Erro ao Excluir o cadastro de E-mail da Pessoa.");
				e.printStackTrace();
				return false;
			}
		}
		if(!pessoa.getListaDisponibilidades().isEmpty()){
			for(ModDisponibilidade listaDispon : pessoa.getListaDisponibilidades()){
				listaDispon.setId_pessoa(pessoa.getId_pessoa());
				listaDispon.setStatusDisponibilidade();
			}
			try {
				controleDispon = new ControleDisponibilidade(pessoa.getListaDisponibilidades());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			fechaConexao();
		}
		abreConexao();
		if(!DAOOrganista.delete(pessoa, conn)){
			System.out.println("Erro ao Excluir o cadastro da Pessoa.");
			return false;
		}

		fechaConexao();
		return true;
	}
	private boolean update(ModOrganistas pessoa) {
		abreConexao();
		if(!DAOOrganista.update(pessoa, conn)){
			System.out.println("Erro ao Alterar o cadastro da Pessoa.");
			return false;
		}else{		
			if(!pessoa.getListaEmailPessoa().isEmpty()){
				try {
					controleEmail = new ControleEmail(pessoa.getListaEmailPessoa());
				} catch (Exception e) {
					System.out.println("Erro ao Alterar o cadastro da Pessoa.");
					e.printStackTrace();
					return false;
				}
			}
			if(!pessoa.getListaTelPessoa().isEmpty()){
				try {
					controleTel = new ControleTelefone(pessoa.getListaTelPessoa());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(!pessoa.getListaDisponibilidades().isEmpty()){
				for(ModDisponibilidade listaDispon : pessoa.getListaDisponibilidades()){
					listaDispon.setId_pessoa(pessoa.getId_pessoa());
				}
				try {
					controleDispon = new ControleDisponibilidade(pessoa.getListaDisponibilidades());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				fechaConexao();
			}
		}
		fechaConexao();
		return true;

	}
	private boolean insert(ModOrganistas pessoa) {
		abreConexao();
		String idPessoa = ""+DAOOrganista.insert(pessoa, conn);
		if(idPessoa.compareTo("-1") == 0){
			System.out.println("Erro ao Salvar a Pessoa no Banco de Dados!");
		}else{
			if(!pessoa.getListaEmailPessoa().isEmpty()){
				for(ModEmailPessoa listaEmail: pessoa.getListaEmailPessoa()){
					listaEmail.setId_pessoa(idPessoa);
				}
				try {
					controleEmail = new ControleEmail(pessoa.getListaEmailPessoa());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(!pessoa.getListaTelPessoa().isEmpty()){
				for(ModTelPessoa listaTel: pessoa.getListaTelPessoa()){
					listaTel.setId_pessoa(idPessoa);
				}
				try {
					controleTel = new ControleTelefone(pessoa.getListaTelPessoa());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fechaConexao();
			}
			
			if(!pessoa.getListaDisponibilidades().isEmpty()){
				for(ModDisponibilidade listaDispon : pessoa.getListaDisponibilidades()){
					listaDispon.setId_pessoa(idPessoa);
				}
				try {
					controleDispon = new ControleDisponibilidade(pessoa.getListaDisponibilidades());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				fechaConexao();
			}
		}

		return true;
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
