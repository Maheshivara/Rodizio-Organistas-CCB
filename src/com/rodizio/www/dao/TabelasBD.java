package com.rodizio.www.dao;



public abstract class TabelasBD {
	private static String sqlCreateTables="";
	private static boolean ok=false;

	public static boolean verificaTabelas() {

		for (int i=1; i<=11; i++){
			
			ok=Executa.sqlCreateOrInsert(tabela(i));
		}

		return ok;
	}

	public static String tabela(int posicao){
		switch(posicao){
		case 1:
			//Tabelas de CADASTRO DE PAIS
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS endereco_pais ";
			sqlCreateTables+= " ( ";
			sqlCreateTables+= " id_pais INTEGER NOT NULL , ";
			sqlCreateTables+= " nome_pais varchar(60) DEFAULT NULL, ";
			sqlCreateTables+= " sigla_pais varchar(10) DEFAULT NULL, ";
			sqlCreateTables+= " PRIMARY KEY (id_pais) ";
			sqlCreateTables+= " ); ";
			break;
		case 2://Tabela de Cadastro de Estados
			sqlCreateTables= "";
			sqlCreateTables+= "  CREATE TABLE IF NOT EXISTS endereco_estado ( ";
			sqlCreateTables+= "id_estado INTEGER NOT NULL , ";
			sqlCreateTables+= "nome_estado varchar(75) NOT NULL, ";
			sqlCreateTables+= "uf_estado varchar(5) NOT NULL, ";
			sqlCreateTables+= "cod_pais INTEGER NOT NULL, ";
			sqlCreateTables+= "PRIMARY KEY (id_estado), ";
			sqlCreateTables+= "FOREIGN KEY (cod_pais) REFERENCES endereco_pais(id_pais) ";
			sqlCreateTables+= "ON DELETE RESTRICT ON UPDATE CASCADE "; 
			sqlCreateTables+= " ); ";
			break;
		case 3://Tabela de cadastro de Cidades
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS endereco_cidade ( ";
			sqlCreateTables+= "id_cidade INTEGER NOT NULL , ";
			sqlCreateTables+= "nome_cidade varchar(120) DEFAULT NULL, ";
			sqlCreateTables+= "cod_estado INTEGER NOT NULL, ";
			sqlCreateTables+= "PRIMARY KEY (id_cidade), ";
			sqlCreateTables+= "FOREIGN KEY (cod_estado) REFERENCES  endereco_estado (id_estado) ";
			sqlCreateTables+= "ON DELETE RESTRICT ON UPDATE CASCADE ";
			sqlCreateTables+= " ); ";
			break;
		case 4://Tabela de Cadastro de Comum Congregação
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS cad_comum ( ";
			sqlCreateTables+= " id_comum INTEGER NOT NULL , ";
			sqlCreateTables+= " nome_comum VARCHAR ( 50 ) DEFAULT  'null' , ";
			sqlCreateTables+= " lograd_comum VARCHAR ( 50 ) DEFAULT  'null'  , ";
			sqlCreateTables+= " complem_end_igreja VARCHAR ( 50 ) DEFAULT  'null'  , ";
			sqlCreateTables+= " num_comum INTEGER DEFAULT  'null'  ,";
			sqlCreateTables+= " cep_comum VARCHAR ( 8 ) DEFAULT  'null'  , ";
			sqlCreateTables+= " bairro_comum VARCHAR ( 50 ) DEFAULT  'null'  , ";
			sqlCreateTables+= " id_cidade_comum INTEGER DEFAULT  'null'  , ";
			sqlCreateTables+= " PRIMARY KEY ( id_comum ) , ";
			sqlCreateTables+= " FOREIGN KEY ( id_cidade_comum ) REFERENCES endereco_cidade ( id_cidade ) ON DELETE RESTRICT ON UPDATE CASCADE ";
			sqlCreateTables+= " ); ";
			break;
		case 5://Tabela de Cadastro de Dias de Cultos na Cocum
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS dias_culto_comum ( ";
			sqlCreateTables+= "id_dia_culto INTEGER NOT NULL , ";
			sqlCreateTables+= "id_comum INTEGER NOT NULL , ";
			sqlCreateTables+= "PRIMARY KEY (id_dia_culto, id_comum) , ";
			sqlCreateTables+= "FOREIGN KEY ( id_comum ) REFERENCES cad_comum(id_comum ) ON DELETE RESTRICT ON UPDATE CASCADE ";
			sqlCreateTables+= "); " ;
			break;
		case 6: //Tabela de Cadastro de Tipo de Pessoas
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS cad_pessoa_tipo ( ";
			sqlCreateTables+= "id_pessoa_tipo INTEGER NOT NULL , ";

			sqlCreateTables+= "pessoa_tipo VARCHAR ( 50 ) DEFAULT NULL , ";
			sqlCreateTables+= "PRIMARY KEY ( id_pessoa_tipo ) ";
			sqlCreateTables+= "); ";
			break;
		case 7://Tabela de cadastro de Pessoas
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE  IF NOT EXISTS cad_pessoa ( ";
			sqlCreateTables+= "id_pessoa INTEGER NOT NULL , ";
			sqlCreateTables+= "id_pessoa_tipo INTEGER NOT NULL , ";
			sqlCreateTables+= "id_igreja INTEGER NOT NULL , ";
			sqlCreateTables+= "pessoa_nome VARCHAR ( 100 ) DEFAULT  'null'  , ";
			sqlCreateTables+= "recebeEmail BOOL NOT NULL , ";
			sqlCreateTables+= "PRIMARY KEY ( id_pessoa) , ";
			sqlCreateTables+= "FOREIGN KEY ( id_igreja ) REFERENCES cad_comum ( id_comum ) ON DELETE RESTRICT ON UPDATE CASCADE , ";
			sqlCreateTables+= "FOREIGN KEY ( id_pessoa_tipo ) REFERENCES cad_pessoa_tipo ( id_pessoa_tipo ) ON DELETE RESTRICT ON UPDATE CASCADE ";
			sqlCreateTables+= "); ";

			break;
		case 8://tabela de Cadastro de E-mail de Pessoas
			sqlCreateTables=  " ";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS cad_email_pessoa ( ";
			sqlCreateTables+= "id_email_pessoa INTEGER NOT NULL , ";
			sqlCreateTables+= "id_pessoa INTEGER NOT NULL , ";
			sqlCreateTables+= "email_pessoa VARCHAR ( 100 ) DEFAULT  'null'  , ";
			sqlCreateTables+= "PRIMARY KEY ( id_email_pessoa ) , ";
			sqlCreateTables+= "FOREIGN KEY ( id_pessoa ) REFERENCES cad_pessoa ( id_pessoa ) ON DELETE RESTRICT ON UPDATE CASCADE ";
			sqlCreateTables+= ");";
			break;
		case 9: //Tabela de Cadastro de Tipo de Pessoas
			sqlCreateTables= "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS cad_telefone_tipo ( ";
			sqlCreateTables+= "id_telefone_tipo INTEGER NOT NULL , ";
			sqlCreateTables+= "telefone_tipo VARCHAR ( 50 ) DEFAULT NULL , ";
			sqlCreateTables+= "PRIMARY KEY ( id_telefone_tipo ) ";
			sqlCreateTables+= "); ";
			break;	
		case 10://Tabela de Cadastro de Telefones Pessoa
			sqlCreateTables=  "";
			sqlCreateTables+= " CREATE TABLE IF NOT EXISTS cad_telefone_pessoa ( ";
			sqlCreateTables+= "id_telefone_pessoa INTEGER NOT NULL , ";
			sqlCreateTables+= "id_pessoa INTEGER NOT NULL , ";
			sqlCreateTables+= "id_tipo_telefone INTEGER NOT NULL , ";
			sqlCreateTables+= "telefone_pessoa VARCHAR ( 10 ) DEFAULT  'null'  , ";
			sqlCreateTables+= "PRIMARY KEY ( id_telefone_pessoa ) , ";
			sqlCreateTables+= "FOREIGN KEY ( id_pessoa ) REFERENCES cad_pessoa ( id_pessoa ) ON DELETE RESTRICT ON UPDATE CASCADE, ";
			sqlCreateTables+= "FOREIGN KEY ( id_tipo_telefone ) REFERENCES cad_telefone_tipo ( id_telefone_tipo ) ON DELETE RESTRICT ON UPDATE CASCADE "; 
			sqlCreateTables+= ");";
			break;
		case 11://Cadastro Disponilibilidade Organista
			sqlCreateTables=  "";
			sqlCreateTables+= " CREATE  TABLE  IF NOT EXISTS cad_disp_organista ( ";
			sqlCreateTables+= " id_pessoa INTEGER NOT NULL ,";
			sqlCreateTables+= " id_dia INTEGER NOT NULL , ";
			sqlCreateTables+= " meiaHora BOOL NOT NULL , ";
			sqlCreateTables+= " primParte BOOL, ";
			sqlCreateTables+= " segParte BOOL, ";
			sqlCreateTables+= " primParteRJM BOOL, ";
			sqlCreateTables+= " segParteRJM BOOL, ";
			sqlCreateTables+= " PRIMARY KEY ( id_pessoa, id_dia) ";
			sqlCreateTables+= " FOREIGN KEY ( id_pessoa ) REFERENCES cad_pessoa ( id_pessoa ) ON DELETE RESTRICT ON UPDATE CASCADE ";
			sqlCreateTables+= " );";
			break;	

		}

		return sqlCreateTables;
	}
}
