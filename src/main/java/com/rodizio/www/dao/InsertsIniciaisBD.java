package com.rodizio.www.dao;

import javax.swing.*;

import com.rodizio.www.util.ferramentas.ReadSqlFile;

public abstract class InsertsIniciaisBD {

	private static boolean ok = false;

	public static boolean verificaInserts(JFrame frame) {

		String[] inserts = getListaInserts();
		JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setStringPainted(true);
		progressBar2.setBounds(10, 399, 630, 15);
		frame.getContentPane().add(progressBar2);
		progressBar2.setMinimum(0);
		progressBar2.setMaximum(inserts.length);

		JLabel labelTotalCidade3 = new JLabel();
		labelTotalCidade3.setBounds(10, 374, 630, 20);
		frame.getContentPane().add(labelTotalCidade3);
		labelTotalCidade3.setHorizontalAlignment(SwingConstants.CENTER);

		labelTotalCidade3.setText("Se o Sistema Travar, Feche e Abra novamente.");
		for (int i = 0; i < inserts.length; i++) {
			ok = Executa.sqlCreateOrInsert(inserts[i].toString());
			progressBar2.setValue(i);
		}
		labelTotalCidade3.setVisible(false);
		progressBar2.setVisible(false);

		return ok;
	}

	private static String[] getListaInserts() {

		String[] listaDeInserts = ReadSqlFile.readSqlFile("/com/rodizio/www/dao/sql/seed.sql");

		return listaDeInserts;

	}
}
