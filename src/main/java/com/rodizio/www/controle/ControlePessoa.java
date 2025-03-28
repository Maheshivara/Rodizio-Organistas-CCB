/**
 * 
 */
package com.rodizio.www.controle;

import com.rodizio.www.dao.DAOPessoa;
import com.rodizio.www.modelo.ModEmailPessoa;
import com.rodizio.www.modelo.ModPessoa;
import com.rodizio.www.modelo.ModTelPessoa;
import com.rodizio.www.persistencia.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Wesley
 *
 */
public class ControlePessoa {

	private Connection conn;
	private ControleEmail controleEmail;
	@SuppressWarnings("unused")
	private List<ModEmailPessoa> listaEmails;
	private ControleTelefone controleTel;
	@SuppressWarnings("unused")
	private List<ModTelPessoa> listaTel;


	public ControlePessoa() throws Exception{
		conn = Conexao.getConnection();
	}

	public ControlePessoa(String nomePessoa, String idTipoPessoa){
		select(nomePessoa, idTipoPessoa );
	}
	/**
	 * Para Exluir uma pessoa, percorrer todos os emails e telefones sinalizando o Status
	 * como 2 - EXCLUIR
	 * @param pessoa
	 * @throws Exception
	 */
	public ControlePessoa(ModPessoa pessoa) throws Exception{
		conn = Conexao.getConnection();
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
	public ControlePessoa(List<ModPessoa> pessoas) throws Exception{

		conn = Conexao.getConnection();
		for(ModPessoa pessoa : pessoas){
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
			}
		}

	}

	public List<ModPessoa> select(String nomePessoa, String idTipoPessoa) {
		abreConexao();
		return DAOPessoa.selectNome(nomePessoa, idTipoPessoa, conn);

	}

	public ModPessoa selectID(String idPessoa) {
		abreConexao();
		ModPessoa p = new ModPessoa();
		ModEmailPessoa email = new ModEmailPessoa();
		ModTelPessoa tel = new ModTelPessoa();

		p = DAOPessoa.selectID(idPessoa, conn);
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
		}

		return p;

	}
	private boolean delete(ModPessoa pessoa) {
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
		abreConexao();
		if(!DAOPessoa.delete(pessoa, conn)){
			System.out.println("Erro ao Excluir o cadastro da Pessoa.");
			return false;
		}

		fechaConexao();
		return true;
	}
	private boolean update(ModPessoa pessoa) {
		abreConexao();
		if(!DAOPessoa.update(pessoa, conn)){
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
		}
		fechaConexao();
		return true;

	}
	private boolean insert(ModPessoa pessoa) {
		abreConexao();
		String idPessoa = ""+DAOPessoa.insert(pessoa, conn);
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
