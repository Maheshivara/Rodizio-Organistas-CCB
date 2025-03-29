package com.rodizio.www.dao;

import com.rodizio.www.util.ferramentas.ReadSqlFile;

public abstract class TabelasBD {
	private static boolean ok = false;

	public static boolean verificaTabelas() {
		String[] tabela = getCreateTable();
		for (int i = 0; i < tabela.length; i++) {
			ok = Executa.sqlCreateOrInsert(tabela[i].toString());
		}

		return ok;
	}

	private static String[] getCreateTable() {
		String[] sqlCreateTables = ReadSqlFile.readSqlFile("/com/rodizio/www/dao/sql/init.sql");
		return sqlCreateTables;
	}
}
