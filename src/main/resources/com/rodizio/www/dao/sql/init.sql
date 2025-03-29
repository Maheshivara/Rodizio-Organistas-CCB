-- Tabelas de CADASTRO DE PAIS
CREATE TABLE IF NOT EXISTS endereco_pais
(
  id_pais INTEGER NOT NULL ,
  nome_pais varchar(60) DEFAULT NULL,
  sigla_pais varchar(10) DEFAULT NULL,
  PRIMARY KEY (id_pais)
);

-- Tabela de Cadastro de Estados
CREATE TABLE IF NOT EXISTS endereco_estado (
  id_estado INTEGER NOT NULL ,
  nome_estado varchar(75) NOT NULL,
  uf_estado varchar(5) NOT NULL,
  cod_pais INTEGER NOT NULL,
  PRIMARY KEY (id_estado),
  FOREIGN KEY (cod_pais) REFERENCES endereco_pais(id_pais)
  ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de cadastro de Cidades
CREATE TABLE IF NOT EXISTS endereco_cidade (
  id_cidade INTEGER NOT NULL ,
  nome_cidade varchar(120) DEFAULT NULL,
  cod_estado INTEGER NOT NULL,
  PRIMARY KEY (id_cidade),
  FOREIGN KEY (cod_estado) REFERENCES  endereco_estado (id_estado)
  ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de Cadastro de Comum Congregação
CREATE TABLE IF NOT EXISTS cad_comum (
  id_comum INTEGER NOT NULL ,
  nome_comum VARCHAR ( 50 ) DEFAULT  'null',
  lograd_comum VARCHAR ( 50 ) DEFAULT  'null',
  complem_end_igreja VARCHAR ( 50 ) DEFAULT  'null',
  num_comum INTEGER DEFAULT  'null',
  cep_comum VARCHAR ( 8 ) DEFAULT  'null',
  bairro_comum VARCHAR ( 50 ) DEFAULT  'null',
  id_cidade_comum INTEGER DEFAULT  'null',
  PRIMARY KEY ( id_comum ) ,
  FOREIGN KEY ( id_cidade_comum ) REFERENCES endereco_cidade ( id_cidade ) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de Cadastro de Dias de Cultos na Comum
CREATE TABLE IF NOT EXISTS dias_culto_comum (
  id_dia_culto INTEGER NOT NULL,
  id_comum INTEGER NOT NULL,
  PRIMARY KEY (id_dia_culto, id_comum),
  FOREIGN KEY ( id_comum ) REFERENCES cad_comum(id_comum ) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de Cadastro de Tipo de Pessoas
CREATE TABLE IF NOT EXISTS cad_pessoa_tipo (
  id_pessoa_tipo INTEGER NOT NULL,
  pessoa_tipo VARCHAR ( 50 ) DEFAULT NULL,
  PRIMARY KEY ( id_pessoa_tipo )
);

-- Tabela de cadastro de Pessoas
CREATE TABLE  IF NOT EXISTS cad_pessoa (
  id_pessoa INTEGER NOT NULL,
  id_pessoa_tipo INTEGER NOT NULL,
  id_igreja INTEGER NOT NULL,
  pessoa_nome VARCHAR ( 100 ) DEFAULT  'null',
  recebeEmail BOOL NOT NULL,
  PRIMARY KEY ( id_pessoa),
  FOREIGN KEY ( id_igreja ) REFERENCES cad_comum ( id_comum ) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY ( id_pessoa_tipo ) REFERENCES cad_pessoa_tipo ( id_pessoa_tipo ) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de Cadastro de E-mail de Pessoas
CREATE TABLE IF NOT EXISTS cad_email_pessoa (
  id_email_pessoa INTEGER NOT NULL,
  id_pessoa INTEGER NOT NULL,
  email_pessoa VARCHAR ( 100 ) DEFAULT  'null',
  PRIMARY KEY ( id_email_pessoa ),
  FOREIGN KEY ( id_pessoa ) REFERENCES cad_pessoa ( id_pessoa ) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de Cadastro de Tipo de Pessoas
CREATE TABLE IF NOT EXISTS cad_telefone_tipo (
  id_telefone_tipo INTEGER NOT NULL,
  telefone_tipo VARCHAR ( 50 ) DEFAULT NULL,
  PRIMARY KEY ( id_telefone_tipo )
);

-- Tabela de Cadastro de Telefones Pessoa
 CREATE TABLE IF NOT EXISTS cad_telefone_pessoa (
  id_telefone_pessoa INTEGER NOT NULL,
  id_pessoa INTEGER NOT NULL,
  id_tipo_telefone INTEGER NOT NULL,
  telefone_pessoa VARCHAR ( 10 ) DEFAULT  'null',
  PRIMARY KEY ( id_telefone_pessoa ),
  FOREIGN KEY ( id_pessoa ) REFERENCES cad_pessoa ( id_pessoa ) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY ( id_tipo_telefone ) REFERENCES cad_telefone_tipo ( id_telefone_tipo ) ON DELETE RESTRICT ON UPDATE CASCADE 
);

-- Cadastro Disponibilidade Organista
CREATE  TABLE  IF NOT EXISTS cad_disp_organista (
  id_pessoa INTEGER NOT NULL, 
  id_dia INTEGER NOT NULL,
  meiaHora BOOL NOT NULL,
  primParte BOOL,
  segParte BOOL,
  primParteRJM BOOL,
  segParteRJM BOOL,
  PRIMARY KEY ( id_pessoa, id_dia),
  FOREIGN KEY ( id_pessoa ) REFERENCES cad_pessoa ( id_pessoa ) ON DELETE RESTRICT ON UPDATE CASCADE
);