package com.rodizio.www.visao;

import com.rodizio.www.dao.InsertsIniciaisBD;
import com.rodizio.www.dao.TabelasBD;
import com.rodizio.www.persistencia.Conexao;
import com.rodizio.www.util.ferramentas.LoadImage;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tela de Inicializaï¿½ï¿½o do Sistema.
 * Implementada utilizando Explicaï¿½ï¿½es do Site Youtube demonstando como
 * Criar uma SPLASH Screem
 * http://www.youtube.com/watch?v=QOKdxxlok-w&feature=player_detailpage
 * 
 * @author Wesley
 *
 */

public class Splash {

	private JFrame frame;
	private JProgressBar progressBar;
	private JLabel lblLblinicializando;
	private JPanel panel;

	private boolean bancoVerificado = false;
	private boolean tabelasVerificadas = false;
	private boolean configuracoesCarregadas = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash window = new Splash();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Splash() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		initialize();

		new Thread() {
			@SuppressWarnings("unused")
			public void run() {

				for (int i = 0; i < 65; i++) {
					try {
						sleep(60);

						progressBar.setValue(i);

						if (progressBar.getValue() <= 10) {
							lblLblinicializando.setText("Verificando Banco de Dados.");
							if (!bancoVerificado) {
								try {
									bancoVerificado = Conexao.checkDatabase();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						} else if (progressBar.getValue() <= 60) {

							lblLblinicializando.setText("Verificando Tabelas do Banco de Dados");

							if (!tabelasVerificadas) {
								new Thread() {
									public void run() {
										if (!TabelasBD.verificaTabelas()) {
											JOptionPane.showMessageDialog(frame, "Erro nas Tabelas.\nContacte o Suporte.");
											System.exit(0);
										}
									}
								}.start();
								tabelasVerificadas = true;
							}
						} else {
							if (!configuracoesCarregadas) {
								lblLblinicializando.setText("Inserindo Dados iniciais no Banco de Dados.");

								if (!InsertsIniciaisBD.verificaInserts(frame)) {
									JOptionPane.showMessageDialog(frame, "Erro nos Dados Iniciais do Sistema.\nContacte o Suporte.");
									System.exit(0);
								}

								configuracoesCarregadas = true;
							}
							lblLblinicializando.setText("Abrindo o Sistema.");
						}

					} catch (InterruptedException ex) {
						Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
					}

				}

				MenuPrincipal telaInicial = new MenuPrincipal();

				frame.dispose();

			}

		}.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(LoadImage.loadImage("/com/rodizio/www/visao/imagens/claveDeSol35x35.jpg"));

		frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.setUndecorated(true);
		frame.setType(Type.POPUP);
		frame.setResizable(false);
		frame.setSize(650, 420);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setOpaque(false);
		panel.setBounds(0, 0, 650, 420);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnFechar.setBounds(10, 11, 89, 23);
		panel.add(btnFechar);

		lblLblinicializando = new JLabel("");
		lblLblinicializando.setForeground(Color.BLACK);
		lblLblinicializando.setBounds(10, 329, 630, 20);
		panel.add(lblLblinicializando);
		lblLblinicializando.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label = new JLabel("");
		label.setBounds(10, 11, 630, 332);
		panel.add(label);
		label.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/clave-sol.jpg")));
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, 354, 630, 15);
		panel.add(progressBar);
		progressBar.setMaximum(64);
		progressBar.setStringPainted(true);

		frame.setBounds(0, 0, 650, 420);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setCentro();

		// elementosInvisiveis();
	}

	public void setCentro() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = kit.getScreenSize();
		int width = tamanhoTela.width;
		int height = tamanhoTela.height;
		frame.setLocation(width / 4, height / 4);
	}

	public void elementosInvisiveis() {
		JLabel labelTotalCidade3 = new JLabel();
		labelTotalCidade3.setBounds(10, 374, 630, 20);
		panel.add(labelTotalCidade3);
		labelTotalCidade3.setHorizontalAlignment(SwingConstants.CENTER);
		JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setBounds(10, 399, 630, 15);
		panel.add(progressBar2);
		progressBar2.setStringPainted(true);
		progressBar2.setMinimum(0);
		progressBar2.setMaximum(5600);

	}

}
