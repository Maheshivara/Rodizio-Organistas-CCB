package com.rodizio.www.dao;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

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

		InputStream inputStream = InsertsIniciaisBD.class.getResourceAsStream("/com/rodizio/www/dao/sql/seed.sql");
		if (inputStream == null) {
			throw new RuntimeException("File not found: /com/rodizio/www/dao/sql/seed.sql");
		}
		byte[] bytes = new byte[0];
		try {
			bytes = inputStream.readAllBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String original = new String(bytes, StandardCharsets.UTF_8);
		String withoutComments = original.replaceAll("(?m)^--.*$", "");

		String[] listaDeInserts = withoutComments.replace("\r\n", "\n").split(";");
		for (int i = 0; i < listaDeInserts.length; i++) {
			listaDeInserts[i] = listaDeInserts[i].replace("\n", " ").trim();
		}

		return listaDeInserts;

	}
}
