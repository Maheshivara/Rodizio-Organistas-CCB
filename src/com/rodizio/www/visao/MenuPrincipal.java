package com.rodizio.www.visao;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import com.rodizio.www.visao.controle.Remove;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal {

	private JFrame frmPrincipal;
	private JDesktopPane desktopPane;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmPrincipal.getContentPane().setFont(new Font("SansSerif", Font.BOLD, 15));
		frmPrincipal.setFont(new Font("Arial", Font.BOLD, 16));
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/rodizio/www/visao/imagens/claveDeSol58x58.jpg")));
		frmPrincipal.setTitle("Sistema Gerador de Rodizio de Organistas");
		frmPrincipal.setResizable(false);
		frmPrincipal.setBounds(50, 10, 1024, 680);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		}
		
		//Toolkit tlkit = frmPrincipal.getToolkit();
	    //frmPrincipal.setBounds(new Rectangle(tlkit.getScreenSize()));
        JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBounds(0, 0, 1017, 45);
		frmPrincipal.getContentPane().add(menuBar);
		menuBar.setFont(new Font("Dialog", Font.BOLD, 15));

        JMenu mnCadastros = new JMenu("Cadastros");
		mnCadastros.setFont(new Font("Dialog", Font.BOLD, 15));
		menuBar.add(mnCadastros);
		
		JSeparator separator_3 = new JSeparator();
		mnCadastros.add(separator_3);

        JMenuItem mntmComum = new JMenuItem("Cadastro de Comum");
		mntmComum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CadastroIgreja cadComum = new CadastroIgreja();
                Remove.barraInternalFrame(cadComum);
                desktopPane.add(cadComum);
                cadComum.setVisible(true);
            }
        });
		mntmComum.setFont(new Font("Dialog", Font.BOLD, 15));
		mnCadastros.add(mntmComum);

        JSeparator separator_4 = new JSeparator();
		mnCadastros.add(separator_4);

        JMenuItem mntmCadastroMinisterio = new JMenuItem("Cadastro de Minist\u00E9rio");
		mntmCadastroMinisterio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CadastroMinisterio cadMinisterio = new CadastroMinisterio();
                Remove.barraInternalFrame(cadMinisterio);
                desktopPane.add(cadMinisterio);
                cadMinisterio.setVisible(true);
            }
        });
		mntmCadastroMinisterio.setFont(new Font("Dialog", Font.BOLD, 15));
		mnCadastros.add(mntmCadastroMinisterio);
		
		JSeparator separator_2 = new JSeparator();
		mnCadastros.add(separator_2);

        JMenuItem mntmOrganistas = new JMenuItem("Cadastro de Organistas");
		mntmOrganistas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CadastroOrganistas cadOrganistas = new CadastroOrganistas();
                Remove.barraInternalFrame(cadOrganistas);
                desktopPane.add(cadOrganistas);
                cadOrganistas.setVisible(true);
            }
        });
		mntmOrganistas.setFont(new Font("Dialog", Font.BOLD, 15));
		mnCadastros.add(mntmOrganistas);

        JSeparator separator_7 = new JSeparator();
		mnCadastros.add(separator_7);

        JSeparator separator = new JSeparator();
		separator.setMaximumSize(new Dimension(2, 90));
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);

        JMenu mnRodzios = new JMenu("Rod\u00EDzios");
		mnRodzios.setFont(new Font("Dialog", Font.BOLD, 15));
		menuBar.add(mnRodzios);
		
		JMenuItem mntmCadastroDeRodizio = new JMenuItem("Cadastro de Rodizio");
		mntmCadastroDeRodizio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroRodizio cadRodizio = new CadastroRodizio();
				Remove.barraInternalFrame(cadRodizio);
				desktopPane.add(cadRodizio);
				cadRodizio.setVisible(true);
				
			}
		});

        JSeparator separator_5 = new JSeparator();
		mnRodzios.add(separator_5);
		mntmCadastroDeRodizio.setFont(new Font("Dialog", Font.BOLD, 15));
		mnRodzios.add(mntmCadastroDeRodizio);

        JSeparator separator_6 = new JSeparator();
		mnRodzios.add(separator_6);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setMaximumSize(new Dimension(2, 90));
		menuBar.add(separator_1);
		
		desktopPane = new JDesktopPane();
		desktopPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		desktopPane.setBounds(0, 0, 1024, 768);
		frmPrincipal.getContentPane().add(desktopPane);
		
		 centralizaFrame();
		frmPrincipal.setVisible(true);
	}
	public void centralizaFrame(){
        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frmPrincipal.getSize();
        if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
        }
        frmPrincipal.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}
}
