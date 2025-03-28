package com.rodizio.www.visao;

import com.rodizio.www.controle.ControleIgreja;
import com.rodizio.www.controle.ControlePessoa;
import com.rodizio.www.modelo.ModCadIgreja;
import com.rodizio.www.modelo.ModEmailPessoa;
import com.rodizio.www.modelo.ModPessoa;
import com.rodizio.www.modelo.ModTelPessoa;
import com.rodizio.www.util.ferramentas.Formata;
import com.rodizio.www.util.ferramentas.LoadImage;
import com.rodizio.www.util.ferramentas.Preenche;
import com.rodizio.www.visao.abstractTableModel.IgrejaTableModel;
import com.rodizio.www.visao.abstractTableModel.PessoaTableModel;
import com.rodizio.www.visao.controle.Telefone;
import com.rodizio.www.visao.controle.TrataEnter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class CadastroMinisterio extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 661691031366148852L;
	private JPanel pnlGeralCadMinisterio;
	JPanel tabCadastro;
	private JTabbedPane tabbedPane;
	private JButton btnLocalizar;
	private JButton btnSalvar;
	private JButton btnAlterar;
	protected JButton btnCancelar;
	protected JButton btnExcluir;
	protected JButton btnIncluir;
	protected JButton btnCarregar;
	private JButton btnBuscar;
	private JButton btnOk;
	private JButton btnBuscar_1;

	int incluir = 1;
	private int alterar = 2;
	private int salvar = 3;
	int cancelarOuExcluir = 4;
	private int localizar = 5;
	int carregar = 6;
	private int enable = 1;
	int disable = 2;
	Boolean eInsert = false;
	Boolean eDelete = false;
	String IdTipoPessoa = "0";

	List<Component> camposTrataEnter = new ArrayList<Component>();
	List<JTextField> listaCamposTexto = new ArrayList<JTextField>();
	private List<JFormattedTextField> listaCamposFormatados = new ArrayList<>();
	JTextField txtIdIgreja;
	JTextField txtNomeDaComum;

	private ControleIgreja controleIgreja;
	private IgrejaTableModel datmIgreja;

	private ControlePessoa controlePessoa;
	PessoaTableModel datmPessoa;

	private JScrollPane scrollPane_1;
	JTable table;

	@SuppressWarnings("rawtypes")
	JComboBox cmbTipoMinisterio;
	DefaultComboBoxModel<String> dcbmTipoMinisterio = new DefaultComboBoxModel<>();
	JLabel lblSelecioneUmTipo;
	JLabel lblNomeDaoIrmaoOuIrma;
	JPanel pnlTelefones;
	JLabel lblTelefones;
	JLabel label;
	JTextField txtNomePessoa;
	JFormattedTextField txtTelResidencial;
	JFormattedTextField txtTelComercial;
	JFormattedTextField txtTelCelular;
	JTextField txtEmail1;
	JTextField txtEmail2;
	JTextField txtEmail3;
	JTextField txtIdEmail1;
	JTextField txtIdEmail2;
	JTextField txtIdEmail3;

	JTextField txtIdTelResidencial;
	JTextField txtIdTelComercial;
	JTextField txtIdTelCelular;

	protected JTextField txtIdPessoa;

	protected JLabel lblComercial;
	protected JLabel lblCelular;

	protected JPanel pnlEmails;
	protected JLabel lblEmails;
	protected JLabel lblEmail;
	protected JLabel lblEmail_1;
	protected JLabel lblEmail_2;

	private MaskFormatter mascaraTelefone;
	private ModPessoa pessoa;
	protected List<ModEmailPessoa> listaEmailPessoa;
	protected List<ModTelPessoa> listaTelPessoa;
	protected ModEmailPessoa email1;
	protected ModEmailPessoa email2;
	protected ModEmailPessoa email3;

	protected ModTelPessoa telefoneComercial;
	protected ModTelPessoa telefoneResidencial;
	protected ModTelPessoa telefoneCelular;
	protected JTextField txtBuscaNome;
	protected static JInternalFrame cadMinisterio;
	protected JRadioButton rdButtonEnviaEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cadMinisterio = new CadastroMinisterio();
					cadMinisterio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public CadastroMinisterio() {

		setRootPaneCheckingEnabled(false);
		setFont(new Font("Arial Black", Font.BOLD, 18));
		setTitle("Cadastro de Ministério");
		setName("frmCadastroMinisterio");
		getContentPane().setPreferredSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		setBounds(50, 50, 800, 600);
		getContentPane().setLayout(null);

		pnlGeralCadMinisterio = new JPanel();
		pnlGeralCadMinisterio.setPreferredSize(new Dimension(800, 600));
		pnlGeralCadMinisterio.setBounds(0, 0, 784, 569);
		getContentPane().add(pnlGeralCadMinisterio);
		pnlGeralCadMinisterio.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 508);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlGeralCadMinisterio.add(tabbedPane);

		tabCadastro = new JPanel();
		tabCadastro.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabbedPane.addTab("Cadastro", null, tabCadastro, null);
		tabCadastro.setLayout(null);

		txtIdIgreja = new JTextField();
		txtIdIgreja.setName("ID");
		txtIdIgreja.setEditable(false);
		txtIdIgreja.setFont(new Font("Dialog", Font.BOLD, 13));
		txtIdIgreja.setBounds(87, 34, 90, 39);
		tabCadastro.add(txtIdIgreja);
		txtIdIgreja.setColumns(10);

		txtNomeDaComum = new JTextField();
		txtNomeDaComum.setEditable(false);
		txtNomeDaComum.setFont(new Font("Dialog", Font.BOLD, 13));
		txtNomeDaComum.setBounds(221, 35, 361, 39);
		tabCadastro.add(txtNomeDaComum);
		txtNomeDaComum.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insereTabLocalizaIgreja();
			}
		});
		btnBuscar.setBounds(588, 35, 90, 38);
		tabCadastro.add(btnBuscar);

		JLabel lblIdIgreja = new JLabel("ID Igreja");
		lblIdIgreja.setFont(new Font("Dialog", Font.BOLD, 13));
		lblIdIgreja.setBounds(87, 6, 90, 24);
		tabCadastro.add(lblIdIgreja);

		JLabel lblNomeDaComum = new JLabel("Nome da Comum");
		lblNomeDaComum.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNomeDaComum.setBounds(221, 6, 361, 24);
		tabCadastro.add(lblNomeDaComum);

		txtNomePessoa = new JTextField();
		txtNomePessoa.setName("Nome");
		txtNomePessoa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtIdIgreja.getText().isEmpty()) {
					btnBuscar.doClick();
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				maiuscula(txtNomePessoa);
			}
		});
		txtNomePessoa.setFont(new Font("Dialog", Font.BOLD, 13));
		txtNomePessoa.setBounds(87, 114, 591, 38);
		tabCadastro.add(txtNomePessoa);
		txtNomePessoa.setColumns(10);

		lblNomeDaoIrmaoOuIrma = new JLabel("Nome da(o) Irm\u00E3(o)");
		lblNomeDaoIrmaoOuIrma.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNomeDaoIrmaoOuIrma.setBounds(87, 86, 591, 24);
		tabCadastro.add(lblNomeDaoIrmaoOuIrma);

		txtIdPessoa = new JTextField();
		txtIdPessoa.setName("ID");
		txtIdPessoa.setBounds(0, 0, 0, 0);
		tabCadastro.add(txtIdPessoa);

		lblSelecioneUmTipo = new JLabel("Selecione um Tipo de Cadastro");
		lblSelecioneUmTipo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSelecioneUmTipo.setBounds(87, 152, 200, 38);
		tabCadastro.add(lblSelecioneUmTipo);

		cmbTipoMinisterio = new JComboBox();

		cmbTipoMinisterio.setFont(new Font("Dialog", Font.BOLD, 13));
		cmbTipoMinisterio.setBounds(299, 151, 376, 39);
		tabCadastro.add(cmbTipoMinisterio);

		pnlTelefones = new JPanel();
		pnlTelefones.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlTelefones.setBounds(21, 196, 313, 201);
		tabCadastro.add(pnlTelefones);
		pnlTelefones.setLayout(null);

		lblTelefones = new JLabel("Telefones");
		lblTelefones.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTelefones.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefones.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTelefones.setBounds(6, 6, 283, 33);
		pnlTelefones.add(lblTelefones);

		label = new JLabel("Residencial:");
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		label.setBounds(6, 45, 85, 39);
		pnlTelefones.add(label);

		try {
			mascaraTelefone = new MaskFormatter("(##) #### - ####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtTelResidencial = new JFormattedTextField(mascaraTelefone);
		txtTelResidencial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				validaCampoTelefone(txtTelResidencial);
			}
		});
		txtTelResidencial.setFont(new Font("Dialog", Font.BOLD, 13));
		txtTelResidencial.setBounds(86, 45, 222, 39);
		pnlTelefones.add(txtTelResidencial);
		txtTelResidencial.setColumns(10);

		txtIdTelResidencial = new JTextField();
		txtIdTelResidencial.setName("ID");
		txtIdTelResidencial.setBounds(0, 0, 0, 0);
		pnlTelefones.add(txtIdTelResidencial);

		lblComercial = new JLabel("Comercial:");
		lblComercial.setFont(new Font("Dialog", Font.BOLD, 13));
		lblComercial.setBounds(6, 96, 77, 39);
		pnlTelefones.add(lblComercial);

		txtTelComercial = new JFormattedTextField(mascaraTelefone);
		txtTelComercial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				validaCampoTelefone(txtTelComercial);
			}
		});
		txtTelComercial.setFont(new Font("Dialog", Font.BOLD, 13));
		txtTelComercial.setBounds(85, 96, 222, 39);
		pnlTelefones.add(txtTelComercial);
		txtTelComercial.setColumns(10);

		txtIdTelComercial = new JTextField();
		txtIdTelComercial.setName("ID");
		txtIdTelComercial.setBounds(0, 0, 0, 0);
		pnlTelefones.add(txtIdTelComercial);

		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCelular.setBounds(6, 146, 77, 39);
		pnlTelefones.add(lblCelular);

		txtTelCelular = new JFormattedTextField(mascaraTelefone);
		txtTelCelular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				validaCampoTelefone(txtTelCelular);
			}
		});
		txtTelCelular.setFont(new Font("Dialog", Font.BOLD, 13));
		txtTelCelular.setBounds(85, 146, 222, 39);
		pnlTelefones.add(txtTelCelular);
		txtTelCelular.setColumns(10);

		txtIdTelCelular = new JTextField();
		txtIdTelCelular.setName("ID");
		txtIdTelCelular.setBounds(0, 0, 0, 0);
		pnlTelefones.add(txtIdTelCelular);

		pnlEmails = new JPanel();
		pnlEmails.setLayout(null);
		pnlEmails.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlEmails.setBounds(346, 196, 421, 201);
		tabCadastro.add(pnlEmails);

		lblEmails = new JLabel("E-Mails");
		lblEmails.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEmails.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmails.setBounds(6, 6, 283, 33);
		pnlEmails.add(lblEmails);

		lblEmail = new JLabel("E-mail 1:");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEmail.setBounds(6, 45, 77, 39);
		pnlEmails.add(lblEmail);

		lblEmail_1 = new JLabel("E-mail 2:");
		lblEmail_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEmail_1.setBounds(6, 101, 77, 33);
		pnlEmails.add(lblEmail_1);

		lblEmail_2 = new JLabel("E-mail 3:");
		lblEmail_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEmail_2.setBounds(6, 146, 77, 33);
		pnlEmails.add(lblEmail_2);

		txtEmail1 = new JTextField();
		txtEmail1.setName("ID");
		txtEmail1.setFont(new Font("Dialog", Font.BOLD, 13));
		txtEmail1.setColumns(10);
		txtEmail1.setBounds(79, 45, 336, 39);
		pnlEmails.add(txtEmail1);

		txtIdEmail1 = new JTextField();
		txtIdEmail1.setName("ID");
		txtIdEmail1.setBounds(0, 0, 0, 0);
		pnlEmails.add(txtIdEmail1);

		txtEmail2 = new JTextField();
		txtEmail2.setName("ID");
		txtEmail2.setFont(new Font("Dialog", Font.BOLD, 13));
		txtEmail2.setColumns(10);
		txtEmail2.setBounds(79, 96, 336, 39);
		pnlEmails.add(txtEmail2);

		txtIdEmail2 = new JTextField();
		txtIdEmail2.setName("ID");
		txtIdEmail2.setBounds(0, 0, 0, 0);
		pnlEmails.add(txtIdEmail2);

		txtEmail3 = new JTextField();
		txtEmail3.setName("ID");
		txtEmail3.setFont(new Font("Dialog", Font.BOLD, 13));
		txtEmail3.setColumns(10);
		txtEmail3.setBounds(79, 146, 336, 39);
		pnlEmails.add(txtEmail3);

		txtIdEmail3 = new JTextField();
		txtIdEmail3.setName("ID");
		txtIdEmail3.setBounds(0, 0, 0, 0);
		pnlEmails.add(txtIdEmail3);

		rdButtonEnviaEmail = new JRadioButton("Enviar Rodizio por E-Mail");
		rdButtonEnviaEmail.setHorizontalAlignment(SwingConstants.CENTER);
		rdButtonEnviaEmail.setBounds(356, 399, 398, 18);
		tabCadastro.add(rdButtonEnviaEmail);

		inicializaComponentes();
	}

	@SuppressWarnings("unchecked")
	protected void inicializaComponentes() {

		insereBotoes();
		trataBotoes(4);
		preparaArrayDeComponentes();
		trataEnter();
		// trataCampos(enable);
		trataCampos(disable);
		Preenche.comboTipoMinisterio(cmbTipoMinisterio, dcbmTipoMinisterio, 6);
		// insereTabLocalizaPessoa();
		// insereTabLocalizaIgreja();

	}

	protected void insereBotoes() {
		/**
		 * Botao Alterar
		 * Ocupa a Mesma Posicao do Botao Salvar
		 */
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eInsert = false;
				trataBotoes(alterar);
				trataCampos(enable);
			}
		});
		btnAlterar
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/BotaoAlterar.png")));
		btnAlterar.setBounds(190, 510, 130, 58);
		btnAlterar.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnAlterar);
		btnAlterar.setVisible(true);

		/**
		 * Botao Cancelar
		 * Ocupa a Mesma Posiï¿½ao do Botao Incluir
		 */
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trataBotoes(cancelarOuExcluir);
				trataCampos(disable);
				removeTab();
			}
		});
		btnCancelar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Cancel15x15.png")));
		btnCancelar.setBounds(48, 510, 130, 58);
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnCancelar);
		btnCancelar.setVisible(false);

		/**
		 * Botao Carregar
		 */
		btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() > -1) {

					trataBotoes(carregar);
					trataCampos(disable);
					try {
						controlePessoa = new ControlePessoa();
					} catch (Exception e) {
						e.printStackTrace();
					}
					preencheFormulario(controlePessoa.selectID(datmPessoa.get(table.getSelectedRow()).getId_pessoa()));
					removeTab();

				} else {
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha da tabela.");
				}
			}
		});
		btnCarregar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Reload15x15.png")));
		btnCarregar.setBounds(332, 510, 130, 58);
		btnCarregar.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnCarregar);

		/**
		 * Botao Excluir.
		 */
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eInsert = false;
				eDelete = true;
				salvarForm();
				trataBotoes(cancelarOuExcluir);
				trataCampos(disable);
				limpaTela();
			}
		});
		btnExcluir.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Delete15x15.png")));
		btnExcluir.setBounds(332, 510, 130, 58);
		btnExcluir.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnExcluir);

		/**
		 * Botao Fechar.
		 */
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Close15x15.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setBounds(616, 510, 130, 58);
		btnFechar.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnFechar);

		/**
		 * Botao Incluir.
		 * Ocupa a Mesma Posiï¿½ao do Botao Cancelar
		 */
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eInsert = true;
				eDelete = false;
				trataBotoes(incluir);
				trataCampos(enable);
				limpaTela();
			}
		});
		btnIncluir
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Add15x15.png")));
		btnIncluir.setBounds(48, 510, 130, 58);
		btnIncluir.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnIncluir);

		/**
		 * Botao Localizar
		 */
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insereTabLocalizaPessoa();
				trataBotoes(localizar);
			}
		});
		btnLocalizar
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/BotaoLocalizar.png")));
		btnLocalizar.setBounds(474, 510, 130, 58);
		btnLocalizar.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnLocalizar);

		/**
		 * Botao Salvar.
		 * Ocupa a Mesma Posiï¿½ao do Botao Alterar
		 */
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validaFormulario()) {
					salvarForm();
					trataBotoes(salvar);
					trataCampos(disable);
				}
			}
		});
		btnSalvar
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/BotaoSalvar.png")));
		btnSalvar.setBounds(190, 510, 130, 58);
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnlGeralCadMinisterio.add(btnSalvar);

	}

	protected void trataBotoes(int opcao) {
		switch (opcao) {
			case 1: // Incluir
				trataCampos(disable);
				btnIncluir.setEnabled(false);
				btnIncluir.setVisible(false);

				btnCancelar.setEnabled(true);
				btnCancelar.setVisible(true);

				btnAlterar.setEnabled(false);
				btnAlterar.setVisible(false);

				btnSalvar.setEnabled(true);
				btnSalvar.setVisible(true);

				btnExcluir.setEnabled(false);

				btnLocalizar.setEnabled(false);

				btnCarregar.setEnabled(false);

				eInsert = true;

				break;
			case 2: // Alterar
				trataCampos(disable);
				btnIncluir.setEnabled(false);
				btnIncluir.setVisible(false);

				btnCancelar.setEnabled(true);
				btnCancelar.setVisible(true);

				btnAlterar.setEnabled(false);
				btnAlterar.setVisible(false);

				btnSalvar.setEnabled(true);
				btnSalvar.setVisible(true);

				btnExcluir.setEnabled(false);

				btnLocalizar.setEnabled(false);

				btnCarregar.setEnabled(false);

				break;
			case 3: // Salvar
				trataCampos(enable);
				btnIncluir.setEnabled(true);
				btnIncluir.setVisible(true);

				btnCancelar.setEnabled(false);
				btnCancelar.setVisible(false);

				btnAlterar.setEnabled(false);
				btnAlterar.setVisible(false);

				btnSalvar.setEnabled(false);
				btnSalvar.setVisible(true);

				btnExcluir.setEnabled(false);

				btnLocalizar.setEnabled(true);

				btnCarregar.setEnabled(false);

				break;
			case 4: // Cancelar ou Excluir

				btnIncluir.setEnabled(true);
				btnIncluir.setVisible(true);

				btnCancelar.setEnabled(false);
				btnCancelar.setVisible(false);

				btnAlterar.setEnabled(false);
				btnAlterar.setVisible(false);

				btnSalvar.setEnabled(false);
				btnSalvar.setVisible(true);

				btnExcluir.setEnabled(false);

				btnLocalizar.setEnabled(true);

				btnCarregar.setEnabled(false);

				Boolean eAlterElemLista = false;
				if (!eAlterElemLista) {
					limpaTela();
				} else {
					// preencheFormulario();
					trataBotoes(6);
				}
				break;
			case 5: // Localizar
				btnIncluir.setEnabled(false);
				btnIncluir.setVisible(false);

				btnCancelar.setEnabled(true);
				btnCancelar.setVisible(true);

				btnAlterar.setEnabled(false);
				btnAlterar.setVisible(false);

				btnSalvar.setEnabled(false);
				btnSalvar.setVisible(true);

				btnExcluir.setEnabled(false);

				btnLocalizar.setEnabled(false);

				btnCarregar.setEnabled(false);
				btnCarregar.setVisible(true);

				break;
			case 6: // Carregar
				btnIncluir.setEnabled(false);
				btnIncluir.setVisible(false);

				btnCancelar.setEnabled(true);
				btnCancelar.setVisible(true);

				btnAlterar.setEnabled(true);
				btnAlterar.setVisible(true);

				btnSalvar.setEnabled(false);
				btnSalvar.setVisible(false);

				btnExcluir.setEnabled(true);

				btnLocalizar.setEnabled(true);

				btnCarregar.setEnabled(false);
				btnCarregar.setVisible(false);
				break;
		}
	}

	protected void trataCampos(int opcao) {
		switch (opcao) {
			case 1:// Enable
				for (Component componente : camposTrataEnter) {
					if (componente != null) {
						componente.setEnabled(true);
					}
				}
				break;
			case 2:// Disable
				for (Component componente : camposTrataEnter) {
					if (componente != null) {
						componente.setEnabled(false);
					}
				}
				break;
		}
	}

	protected void trataEnter() {
		for (Component campo : camposTrataEnter) {
			TrataEnter.acionaEnter(campo);
		}
	}

	protected void preparaArrayDeComponentes() {
		listaCamposTexto.add(txtEmail1);
		listaCamposTexto.add(txtEmail2);
		listaCamposTexto.add(txtEmail3);
		listaCamposTexto.add(txtIdEmail1);
		listaCamposTexto.add(txtIdEmail2);
		listaCamposTexto.add(txtIdEmail3);
		listaCamposTexto.add(txtIdTelCelular);
		listaCamposTexto.add(txtIdTelComercial);
		listaCamposTexto.add(txtIdTelResidencial);
		listaCamposTexto.add(txtIdIgreja);
		listaCamposTexto.add(txtIdPessoa);
		listaCamposTexto.add(txtNomePessoa);
		listaCamposTexto.add(txtNomeDaComum);
		listaCamposFormatados.add(txtTelCelular);
		listaCamposFormatados.add(txtTelComercial);
		listaCamposFormatados.add(txtTelResidencial);

		camposTrataEnter.addAll(listaCamposTexto);

		camposTrataEnter.add(cmbTipoMinisterio);

	}

	protected void insereTabLocalizaPessoa() {
		removeTab();
		JPanel tabLocalizaPessoa = new JPanel();
		tabLocalizaPessoa.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabbedPane.addTab("Localiza Pessoa", null, tabLocalizaPessoa, null);
		tabLocalizaPessoa.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 79, 678, 263);
		tabLocalizaPessoa.add(scrollPane_1);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					limpaTela();
					btnCarregar.doClick();
					arg0.consume();
				}
			}
		});
		scrollPane_1.setViewportView(table);

		txtBuscaNome = new JTextField();
		txtBuscaNome.setBounds(50, 39, 532, 28);
		tabLocalizaPessoa.add(txtBuscaNome);
		txtBuscaNome.setColumns(10);

		btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCarregar.setEnabled(false);
				try {
					List<ModPessoa> listaPessoas = new ArrayList<>();
					controlePessoa = new ControlePessoa();
					listaPessoas.addAll(controlePessoa.select(txtBuscaNome.getText(), IdTipoPessoa));
					datmPessoa = new PessoaTableModel(listaPessoas);

					if (listaPessoas.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane,
								"Não Encontrei um Cadastro Correspondente.\nMude o nome e Pesquise novamente.");
						txtBuscaNome.selectAll();
						txtBuscaNome.grabFocus();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				table.setModel(datmPessoa);
				ajustaColunasTable(3);

				if (datmPessoa.getRowCount() > 0) {
					table.setRowSelectionInterval(0, 0);
					table.grabFocus();
					btnCarregar.setEnabled(true);
				}
			}
		});
		btnBuscar_1.setBounds(594, 39, 129, 28);
		tabLocalizaPessoa.add(btnBuscar_1);
		tabLocalizaPessoa.setDoubleBuffered(false);

		JLabel lblDigiteONome = new JLabel(
				"Digite o Nome ou Parte dele, Ou Deixe em branco para buscar Todas Pessoas Cadastradas.");
		lblDigiteONome.setBounds(50, 19, 532, 16);
		tabLocalizaPessoa.add(lblDigiteONome);
		tabLocalizaPessoa.setVisible(true);
		tabbedPane.setSelectedIndex(1);

	}

	protected void insereTabLocalizaIgreja() {
		JPanel tabLocalizaIgreja = new JPanel();
		tabLocalizaIgreja.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabbedPane.addTab("Localiza Igreja", null, tabLocalizaIgreja, null);
		tabLocalizaIgreja.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 59, 678, 283);
		tabLocalizaIgreja.add(scrollPane_1);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					btnOk.doClick();
					arg0.consume();
				}
			}
		});
		scrollPane_1.setViewportView(table);

		ModCadIgreja cadIgreja = new ModCadIgreja();
		cadIgreja.setStatusCadIgreja(3);

		try {
			List<ModCadIgreja> listaIgrejas = new ArrayList<>();
			controleIgreja = new ControleIgreja();
			listaIgrejas.addAll(controleIgreja.select(cadIgreja));
			datmIgreja = new IgrejaTableModel(listaIgrejas);

		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(datmIgreja);
		ajustaColunasTable(2);

		tabLocalizaIgreja.setDoubleBuffered(false);
		tabLocalizaIgreja.setVisible(true);
		tabbedPane.setSelectedIndex(1);

		if (datmIgreja.getRowCount() > 0) {
			table.setRowSelectionInterval(0, 0);
		}

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() > -1) {
					txtIdIgreja.setText(datmIgreja.get(table.getSelectedRow()).getId_igreja());
					txtNomeDaComum.setText(datmIgreja.get(table.getSelectedRow()).getNome_comum());

					removeTab();
				} else {
					if (datmIgreja.getRowCount() < 1) {
						JOptionPane.showMessageDialog(rootPane, "Não existe Igreja Cadastrada\nPrimeiro cadastre a Igreja.");
						btnCancelar.doClick();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Selecione uma linha e então Clique em OK.");
						table.grabFocus();
					}
				}
			}
		});
		btnOk.setBounds(337, 354, 90, 28);
		tabLocalizaIgreja.add(btnOk);
	}

	protected void preencheFormulario(ModPessoa p) {
		limpaTela();
		txtIdPessoa.setText(p.getId_pessoa());
		txtNomePessoa.setText(p.getNome_pessoa());
		rdButtonEnviaEmail.setSelected(p.getRecebeEmail());
		cmbTipoMinisterio.setSelectedIndex(Integer.parseInt(p.getIdTipoPessoa()) - 1);

		preencheDadosComum(p.getId_Igreja());
		preenchePainelTelefones(p.getListaTelPessoa());
		preenchePainelEmail(p.getListaEmailPessoa());

	}

	protected void preenchePainelEmail(List<ModEmailPessoa> listaEmailPessoa2) {
		for (ModEmailPessoa email : listaEmailPessoa2) {
			if (txtIdEmail1.getText().isEmpty()) {
				txtIdEmail1.setText(email.getId_email());
				txtEmail1.setText(email.getEmail_pessoa());
			} else if (txtIdEmail2.getText().isEmpty()) {
				txtIdEmail2.setText(email.getId_email());
				txtEmail2.setText(email.getEmail_pessoa());
			} else if (txtIdEmail3.getText().isEmpty()) {
				txtIdEmail3.setText(email.getId_email());
				txtEmail3.setText(email.getEmail_pessoa());
			}
		}
	}

	protected void preenchePainelTelefones(List<ModTelPessoa> listaTelPessoa2) {

		for (ModTelPessoa tel : listaTelPessoa2) {
			switch (tel.getIdTipoTelefone()) {
				case 1:// Comercial
					txtIdTelComercial.setText(tel.getId_telefone());
					txtTelComercial.setText(tel.getTelefone_pessoa());
					break;
				case 2: // Residencial
					txtIdTelResidencial.setText(tel.getId_telefone());
					txtTelResidencial.setText(tel.getTelefone_pessoa());
					break;
				case 3: // Celular
					txtIdTelCelular.setText(tel.getId_telefone());
					txtTelCelular.setText(tel.getTelefone_pessoa());
					break;
			}
		}
	}

	protected void preencheDadosComum(String id_Igreja) {
		try {
			controleIgreja = new ControleIgreja();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModCadIgreja igreja = controleIgreja.selectId(id_Igreja);

		txtIdIgreja.setText(igreja.getId_igreja());
		txtNomeDaComum.setText(igreja.getNome_comum());

	}

	protected void salvarForm() {
		pessoa = new ModPessoa();

		if (eInsert) {// INSERT
			pessoa.setStatusPessoa(0);
		} else if (!eDelete) { // UPDATE
			pessoa.setStatusPessoa(1);
			pessoa.setId_pessoa(txtIdPessoa.getText());
		} else if (eDelete) {// Delete
			pessoa.setStatusPessoa(2);
			pessoa.setId_pessoa(txtIdPessoa.getText());
		}
		// Pega a String e tira letras(pega o elemento do Modelo do Combo (que estï¿½ na
		// posiï¿½ao index do Combo)).
		pessoa.setIdTipoPessoa(Formata.soNumeros(dcbmTipoMinisterio.getElementAt(cmbTipoMinisterio.getSelectedIndex())));

		pessoa.setId_Comum(txtIdIgreja.getText());

		pessoa.setNome_pessoa(txtNomePessoa.getText());
		pessoa.setRecebeEmail(rdButtonEnviaEmail.isSelected());
		// E-Mails
		pessoa.setListaEmailPessoa(preencheListaEmails());
		// Telefones
		pessoa.setListaTelPessoa(preencheListaTels());

		try {
			new ControlePessoa(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<ModEmailPessoa> preencheListaEmails() {
		listaEmailPessoa = new ArrayList<>();

		if (!txtEmail1.getText().isEmpty()) {
			email1 = new ModEmailPessoa();
		}
		if (!txtEmail2.getText().isEmpty()) {
			email2 = new ModEmailPessoa();
		}
		if (!txtEmail3.getText().isEmpty()) {
			email3 = new ModEmailPessoa();
		}

		if (!txtEmail1.getText().isEmpty()) {
			email1.setId_pessoa(pessoa.getId_pessoa());
			email1.setStatusEmail(pessoa.getStatusPessoa());
			email1.setEmail_pessoa(txtEmail1.getText());
			if (!txtIdEmail1.getText().isEmpty()) {
				email1.setId_email(txtIdEmail1.getText());
			} else {
				email1.setStatusEmail(0);
			}
			listaEmailPessoa.add(email1);
		}
		if (!txtEmail2.getText().isEmpty()) {
			email2.setId_pessoa(pessoa.getId_pessoa());
			email2.setStatusEmail(pessoa.getStatusPessoa());
			email2.setEmail_pessoa(txtEmail2.getText());
			if (!txtIdEmail2.getText().isEmpty()) {
				email2.setId_email(txtIdEmail2.getText());
			} else {
				email2.setStatusEmail(0);
			}
			listaEmailPessoa.add(email2);
		}
		if (!txtEmail3.getText().isEmpty()) {
			email3.setId_pessoa(pessoa.getId_pessoa());
			email3.setStatusEmail(pessoa.getStatusPessoa());
			email3.setEmail_pessoa(txtEmail2.getText());
			if (!txtIdEmail3.getText().isEmpty()) {
				email3.setId_email(txtIdEmail3.getText());
			} else {
				email3.setStatusEmail(0);
			}
			listaEmailPessoa.add(email3);
		}

		// Variaveis para servir de Delete para Emails Excluidos.
		if (txtEmail1.getText().isEmpty() && !txtIdEmail1.getText().isEmpty()) {
			ModEmailPessoa emailDelete1 = new ModEmailPessoa();
			emailDelete1.setId_email(txtIdEmail1.getText());
			emailDelete1.setStatusEmail(2);
			listaEmailPessoa.add(emailDelete1);
		}
		if (txtEmail2.getText().isEmpty() && !txtIdEmail2.getText().isEmpty()) {
			ModEmailPessoa emailDelete2 = new ModEmailPessoa();
			emailDelete2.setId_email(txtIdEmail2.getText());
			emailDelete2.setStatusEmail(2);
			listaEmailPessoa.add(emailDelete2);
		}
		if (txtEmail3.getText().isEmpty() && !txtIdEmail3.getText().isEmpty()) {
			ModEmailPessoa emailDelete3 = new ModEmailPessoa();
			emailDelete3.setId_email(txtIdEmail3.getText());
			emailDelete3.setStatusEmail(2);
			listaEmailPessoa.add(emailDelete3);
		}

		return listaEmailPessoa;
	}

	protected List<ModTelPessoa> preencheListaTels() {
		listaTelPessoa = new ArrayList<>();

		if (!Formata.soNumeros(txtTelComercial.getText()).isEmpty()) {
			telefoneComercial = new ModTelPessoa();
		}
		if (!Formata.soNumeros(txtTelResidencial.getText()).isEmpty()) {
			telefoneResidencial = new ModTelPessoa();
		}
		if (!Formata.soNumeros(txtTelCelular.getText()).isEmpty()) {
			telefoneCelular = new ModTelPessoa();
		}

		if (!Formata.soNumeros(txtTelComercial.getText()).isEmpty()) {
			telefoneComercial.setId_pessoa(pessoa.getId_pessoa());
			telefoneComercial.setIdTipoTelefone(1);
			telefoneComercial.setStatusTel(pessoa.getStatusPessoa());
			telefoneComercial.setTelefone_pessoa(txtTelComercial.getText());
			if (!txtIdTelComercial.getText().isEmpty()) {
				telefoneComercial.setId_telefone(txtIdTelComercial.getText());
			} else {
				telefoneComercial.setStatusTel(0);
			}
			listaTelPessoa.add(telefoneComercial);
		}

		if (!Formata.soNumeros(txtTelResidencial.getText()).isEmpty()) {
			telefoneResidencial.setId_pessoa(pessoa.getId_pessoa());
			telefoneResidencial.setStatusTel(pessoa.getStatusPessoa());
			telefoneResidencial.setIdTipoTelefone(2);
			telefoneResidencial.setTelefone_pessoa(txtTelResidencial.getText());
			if (!txtIdTelResidencial.getText().isEmpty()) {
				telefoneResidencial.setId_telefone(txtIdTelResidencial.getText());
			} else {
				telefoneResidencial.setStatusTel(0);
			}
			listaTelPessoa.add(telefoneResidencial);
		}

		if (!Formata.soNumeros(txtTelCelular.getText()).isEmpty()) {
			telefoneCelular.setId_pessoa(pessoa.getId_pessoa());
			telefoneCelular.setStatusTel(pessoa.getStatusPessoa());
			telefoneCelular.setIdTipoTelefone(3);
			telefoneCelular.setTelefone_pessoa(txtTelCelular.getText());
			if (!txtIdTelCelular.getText().isEmpty()) {
				telefoneCelular.setId_telefone(txtIdTelCelular.getText());
			} else {
				telefoneCelular.setStatusTel(0);
			}
			listaTelPessoa.add(telefoneCelular);
		}

		// Variaveis para servir de Delete para Telefones Excluidos.
		if (Formata.soNumeros(txtTelComercial.getText()).isEmpty() && !txtIdTelComercial.getText().isEmpty()) {
			ModTelPessoa telDelete1 = new ModTelPessoa();
			telDelete1.setId_telefone(txtIdTelComercial.getText());
			telDelete1.setStatusTel(2);
			listaTelPessoa.add(telDelete1);
		}
		if (Formata.soNumeros(txtTelResidencial.getText()).isEmpty() && !txtIdTelResidencial.getText().isEmpty()) {
			ModTelPessoa telDelete2 = new ModTelPessoa();
			telDelete2.setId_telefone(txtIdTelResidencial.getText());
			telDelete2.setStatusTel(2);
			listaTelPessoa.add(telDelete2);
		}
		if (Formata.soNumeros(txtTelCelular.getText()).isEmpty() && !txtIdTelCelular.getText().isEmpty()) {
			ModTelPessoa telDelete3 = new ModTelPessoa();
			telDelete3.setId_telefone(txtIdTelCelular.getText());
			telDelete3.setStatusTel(2);
			listaTelPessoa.add(telDelete3);
		}

		return listaTelPessoa;
	}

	public void maiuscula(JTextField txt) {
		txt.setText(txt.getText().toUpperCase());
	}

	protected boolean validaCampoTelefone(JFormattedTextField telefone) {
		boolean ok = Telefone.eValido(telefone.getText());
		if (!ok) {
			telefone.setBorder(
					new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), Color.RED, new Color(255, 0, 0), Color.RED));
			telefone.grabFocus();
		} else {
			telefone.setBorder(new BevelBorder(BevelBorder.LOWERED, UIManager.getColor("nimbusFocus"),
					UIManager.getColor("nimbusFocus"), UIManager.getColor("nimbusFocus"), UIManager.getColor("nimbusFocus")));
			telefone.setEnabled(true);
		}
		return ok;
	}

	protected void limpaTela() {
		for (JTextField campo : listaCamposTexto) {
			campo.setText("");
		}

		for (JFormattedTextField campo : listaCamposFormatados) {
			campo.setValue("");
		}
		rdButtonEnviaEmail.setSelected(false);
		repaint();
	}

	protected void ajustaColunasTable(int colunas) {

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);

		switch (colunas) {
			case 2:
				table.getColumnModel().getColumn(1).setPreferredWidth(500);
				break;
			case 3:
				table.getColumnModel().getColumn(1).setPreferredWidth(300);
				table.getColumnModel().getColumn(2).setPreferredWidth(300);
				break;
		}
	}

	protected void removeTab() {
		if (tabbedPane.getTabCount() > 1) {
			tabbedPane.removeTabAt(1);
			tabbedPane.setSelectedIndex(0);
		}
	}

	protected boolean validaFormulario() {
		for (JTextField campoTxt : listaCamposTexto) {
			if (campoTxt.getText().isEmpty() && campoTxt.getName().compareTo("ID") != 0) {
				JOptionPane.showMessageDialog(campoTxt, "O Campo: \"" + campoTxt.getName() + "\" deve ser preenchido.");
				campoTxt.grabFocus();
				return false;
			}
		}
		return true;
	}
}
