package com.rodizio.www.visao;

import com.rodizio.www.controle.ControleIgreja;
import com.rodizio.www.controle.ControleIgrejaDiasCulto;
import com.rodizio.www.controle.ControleOrganista;
import com.rodizio.www.controle.rodizio.Data;
import com.rodizio.www.controle.rodizio.DiaDoRodizio;
import com.rodizio.www.controle.rodizio.GeraDiasRodizio;
import com.rodizio.www.controle.rodizio.RangeDiasValidos;
import com.rodizio.www.modelo.*;
import com.rodizio.www.util.ferramentas.CreateFolder;
import com.rodizio.www.util.ferramentas.LoadImage;
import com.rodizio.www.visao.abstractTableModel.DiaEspecialTableModel;
import com.rodizio.www.visao.abstractTableModel.DiaRodizioQtdePartesTableModel;
import com.rodizio.www.visao.abstractTableModel.DiaRodizioTableModel;
import com.rodizio.www.visao.abstractTableModel.IgrejaTableModel;
import com.rodizio.www.visao.abstractTableModel.OrganistaTableModel;
import com.rodizio.www.visao.controle.DisparaMensagem;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("unused")
public class CadastroRodizio extends JInternalFrame {

	private static final long serialVersionUID = 2646634243302668743L;
	private JTabbedPane tabbedPane;
	private JPanel panel;

	private int incluir = 1;
	private int alterar = 2;
	// private int salvar = 3;
	private int cancelarOuExcluir = 4;
	private int localizar = 5;
	// private int carregar = 6;
	private int enable = 1;
	private int disable = 2;
	protected Boolean eInsert = false;
	protected Boolean eDelete = false;
	private int ordemRodizio = 0;

	private JButton btnLocalizar;
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnIncluir;
	private JButton btnCarregar;
	private JButton btnFechar;
	private JTable table;
	private JButton btnOk;
	private IgrejaTableModel datmIgreja;
	private OrganistaTableModel datmOrganistas;
	private DiaRodizioTableModel datmDiasDoRodizio;
	private DiaEspecialTableModel datmDiasEspeciais;

	protected JTextField txtIdIgreja;
	protected JTextField txtNomeDaComum;
	private JButton btnBuscar;

	private JCheckBox chkDomingo;
	private JCheckBox chkSegunda;
	private JCheckBox chkTerca;
	private JCheckBox chkQuarta;
	private JCheckBox chkQuinta;
	private JCheckBox chkSexta;
	private JCheckBox chkSabado;

	private List<JCheckBox> listaDiasCheckBox = new ArrayList<>();

	private JPanel pnlDiasCultoIgreja;
	private JDateChooser dataInicial;
	private JDateChooser dataFinal;

	private List<Integer> diasCulto = new ArrayList<>();
	private List<DiaDoRodizio> dadosRodizioGerado = new ArrayList<>();
	private List<Date> datasGeradas = new ArrayList<>();
	private List<ModOrganistas> listaOrganistas = new ArrayList<>();
	private List<ModOrganistas> listaOrganistasDia;
	private List<ModDiaRodizio> listaDiasRodizio = new ArrayList<>();
	private List<Date> listaDatasEspeciais = new ArrayList<>();
	private ModDiaRodizio dia;
	private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private JPanel pnlDiaRodizio;
	private JCalendar calendar;
	private JPanel pnlRelatorioRodizioGerado;
	private JTable table_1;

	private JTextField txtOrgMeiaHora;
	private JTextField txtOrgPrimParte;
	private JTextField txtOrgSegParte;
	private JTextField txtOrgPrimParteRJM;
	private JTextField txtOrgSegParteRJM;

	private JPanel pnlRJM;

	private JButton btnOrgMH;
	private JButton btnOrgPrimParte;
	private JButton btnOrgSegParte;
	private JButton btnOrgPrimParteRJM;
	private JButton btnOrgSegParteRJM;
	private JScrollPane scrollPane;
	private JPanel pnlOrganistas;
	private JLabel lblIdOrgMH;
	private JLabel lblIdOrgPrimParte;
	private JLabel lblIdOrgSegParte;
	private JLabel lblIdOrgPrimParteRJM;
	private JLabel lblIdOrgSegParteRJM;
	private JTable table_3;
	private JTable tableDiasEspeciais;

	private DefaultTableModel modelDiasEspeciais;

	private JDateChooser dataEspecial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroRodizio frame = new CadastroRodizio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroRodizio() {
		setRootPaneCheckingEnabled(false);
		setFont(new Font("Arial Black", Font.BOLD, 18));
		setTitle("Cadastro de Rodízios");
		setName("frmCadastroRodizio");
		getContentPane().setPreferredSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		setBounds(50, 50, 800, 600);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 784, 569);
		getContentPane().add(panel);
		panel.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 508);
		panel.add(tabbedPane);

		JPanel pnlDadosRodizio = new JPanel();
		pnlDadosRodizio.setFont(new Font("SansSerif", Font.PLAIN, 14));
		pnlDadosRodizio.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabbedPane.addTab("Dados Rodizio", null, pnlDadosRodizio, null);
		pnlDadosRodizio.setLayout(null);

		txtIdIgreja = new JTextField();
		txtIdIgreja.setName("ID");
		txtIdIgreja.setEditable(false);
		txtIdIgreja.setFont(new Font("Dialog", Font.BOLD, 13));
		txtIdIgreja.setBounds(87, 34, 90, 39);
		pnlDadosRodizio.add(txtIdIgreja);
		txtIdIgreja.setColumns(10);

		txtNomeDaComum = new JTextField();
		txtNomeDaComum.setEditable(false);
		txtNomeDaComum.setFont(new Font("Dialog", Font.BOLD, 13));
		txtNomeDaComum.setBounds(221, 35, 361, 39);
		pnlDadosRodizio.add(txtNomeDaComum);
		txtNomeDaComum.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insereTabLocalizaIgreja();
			}
		});
		btnBuscar.setBounds(588, 35, 90, 38);
		pnlDadosRodizio.add(btnBuscar);

		JLabel lblIdIgreja = new JLabel("ID Igreja");
		lblIdIgreja.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIdIgreja.setBounds(87, 6, 90, 24);
		pnlDadosRodizio.add(lblIdIgreja);

		JLabel lblNomeDaComum = new JLabel("Nome da Comum");
		lblNomeDaComum.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNomeDaComum.setBounds(221, 6, 361, 24);
		pnlDadosRodizio.add(lblNomeDaComum);

		pnlDiasCultoIgreja = new JPanel();
		pnlDiasCultoIgreja.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlDiasCultoIgreja.setBounds(15, 85, 733, 60);
		pnlDadosRodizio.add(pnlDiasCultoIgreja);

		chkDomingo = new JCheckBox("Domingo");
		chkDomingo.setFont(new Font("Dialog", Font.BOLD, 20));
		chkDomingo.setName("1");

		chkDomingo.setBounds(17, 25, 78, 18);
		pnlDiasCultoIgreja.add(chkDomingo);

		chkSegunda = new JCheckBox("Segunda");
		chkSegunda.setFont(new Font("Dialog", Font.BOLD, 20));
		chkSegunda.setName("2");

		chkSegunda.setBounds(106, 25, 84, 18);
		pnlDiasCultoIgreja.add(chkSegunda);

		chkTerca = new JCheckBox("Ter\u00E7a");
		chkTerca.setFont(new Font("Dialog", Font.BOLD, 20));
		chkTerca.setName("3");

		chkTerca.setBounds(190, 25, 84, 18);
		pnlDiasCultoIgreja.add(chkTerca);

		chkQuarta = new JCheckBox("Quarta");
		chkQuarta.setFont(new Font("Dialog", Font.BOLD, 20));
		chkQuarta.setName("4");

		chkQuarta.setBounds(276, 25, 84, 18);
		pnlDiasCultoIgreja.add(chkQuarta);

		chkQuinta = new JCheckBox("Quinta");
		chkQuinta.setFont(new Font("Dialog", Font.BOLD, 20));
		chkQuinta.setName("5");

		chkQuinta.setBounds(372, 25, 84, 18);
		pnlDiasCultoIgreja.add(chkQuinta);

		chkSexta = new JCheckBox("Sexta");
		chkSexta.setFont(new Font("Dialog", Font.BOLD, 20));
		chkSexta.setName("6");

		chkSexta.setBounds(457, 25, 84, 18);
		pnlDiasCultoIgreja.add(chkSexta);

		chkSabado = new JCheckBox("S\u00E1bado");
		chkSabado.setFont(new Font("Dialog", Font.BOLD, 20));
		chkSabado.setName("7");

		chkSabado.setBounds(541, 25, 84, 18);
		pnlDiasCultoIgreja.add(chkSabado);

		dataInicial = new JDateChooser();
		dataInicial.setCalendar(gregorianCalendar);
		dataInicial.setFont(new Font("SansSerif", Font.PLAIN, 18));
		dataInicial.setBounds(200, 164, 153, 28);
		pnlDadosRodizio.add(dataInicial);
		dataInicial.setDate(new Date(System.currentTimeMillis()));

		JLabel lblDataInicialDo = new JLabel("Data Inicial do Rodizio");
		lblDataInicialDo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDataInicialDo.setBounds(15, 164, 185, 28);
		pnlDadosRodizio.add(lblDataInicialDo);

		dataFinal = new JDateChooser();
		dataFinal.setCalendar(gregorianCalendar);
		dataFinal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		dataFinal.setBounds(200, 203, 153, 28);
		pnlDadosRodizio.add(dataFinal);
		dataFinal.setDate(new Date(System.currentTimeMillis()));

		JLabel lblDataFinalDo = new JLabel("Data Final do Rodizio");
		lblDataFinalDo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDataFinalDo.setBounds(15, 203, 185, 28);
		pnlDadosRodizio.add(lblDataFinalDo);

		JButton btnGerarCalendarioRodizio = new JButton("Gerar Calendario Rodizio");
		btnGerarCalendarioRodizio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtIdIgreja.getText().isEmpty()) {
					btnBuscar.doClick();
				} else if (dataFinal.getDate().compareTo(dataInicial.getDate()) < 1) {
					JOptionPane.showMessageDialog(rootPane, "A data Final deve ser Maior que a data Inicial");
					dataInicial.grabFocus();
				} else {

					if (datmDiasEspeciais.getRowCount() > 0) {
						gerarRodizioDiasEspeciais();
					}
					dadosRodizioGerado.addAll(GeraDiasRodizio.modeloRodizio(dataInicial.getDate(), dataFinal.getDate(), diasCulto,
							listaDatasEspeciais));
					geraDiasUteisCalendario();
				}
			}

		});
		btnGerarCalendarioRodizio.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnGerarCalendarioRodizio.setBounds(278, 426, 230, 28);
		pnlDadosRodizio.add(btnGerarCalendarioRodizio);

		datmDiasEspeciais = new DiaEspecialTableModel();

		dataEspecial = new JDateChooser();
		dataEspecial.setCalendar(gregorianCalendar);
		dataEspecial.setFont(new Font("SansSerif", Font.PLAIN, 18));
		dataEspecial.setBounds(200, 242, 153, 28);
		dataFinal.setDate(new Date());
		pnlDadosRodizio.add(dataEspecial);

		JLabel lblDiasEspeciais = new JLabel("Dias Especiais");
		lblDiasEspeciais.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDiasEspeciais.setBounds(71, 252, 129, 28);
		pnlDadosRodizio.add(lblDiasEspeciais);

		JButton btnAdicionar = new JButton("Adicionar Dia");
		btnAdicionar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {

				if (dataEspecial.getDate().before(dataInicial.getDate())) {
					JOptionPane.showMessageDialog(pnlDiasCultoIgreja,
							"Data Especial não pode Ser Antes da Data inicial do Rodizio",
							"DATA ESPECIAL INVÁLIDA!!!", 0);
				} else if (dataEspecial.getDate().after(dataFinal.getDate())) {
					JOptionPane.showMessageDialog(pnlDiasCultoIgreja,
							"Data Especial não pode Ser Após a Data final do Rodizio",
							"DATA ESPECIAL INVÁLIDA!!!", 0);
				} else {
					ModDiaEspecial dia = new ModDiaEspecial();
					dia.setDataEspecial(dataEspecial.getDate());
					GregorianCalendar gregorianCalendar = new GregorianCalendar();
					gregorianCalendar.setTime(dia.getDataEspecial());
					dia.setDiaSemana(Data.diaSemanaPorExtensoCurto(gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK)));

					datmDiasEspeciais.addRow(dia);
				}
			}
		});
		btnAdicionar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAdicionar.setBounds(200, 281, 153, 38);
		pnlDadosRodizio.add(btnAdicionar);

		JButton btnRemoverDiaSelecionado = new JButton("Remover Dia Selec.");
		btnRemoverDiaSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (datmDiasEspeciais.getRowCount() > 0 && tableDiasEspeciais.getSelectedRow() > -1) {
					datmDiasEspeciais.removeRow(tableDiasEspeciais.getSelectedRow());
				}
			}
		});
		btnRemoverDiaSelecionado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnRemoverDiaSelecionado.setBounds(200, 330, 153, 38);
		pnlDadosRodizio.add(btnRemoverDiaSelecionado);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(363, 242, 269, 173);
		pnlDadosRodizio.add(scrollPane_2);

		tableDiasEspeciais = new JTable();
		scrollPane_2.setViewportView(tableDiasEspeciais);
		tableDiasEspeciais.setAutoCreateRowSorter(true);
		tableDiasEspeciais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDiasEspeciais.setModel(datmDiasEspeciais);

		tableDiasEspeciais.getColumnModel().getColumn(0).setResizable(false);
		tableDiasEspeciais.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableDiasEspeciais.getColumnModel().getColumn(1).setResizable(false);
		tableDiasEspeciais.getColumnModel().getColumn(1).setPreferredWidth(140);
		tableDiasEspeciais.setRowHeight(18);
		tableDiasEspeciais.setFont(new Font("SansSerif", Font.PLAIN, 14));

		/**
		 * Botao Fechar.
		 */
		btnFechar = new JButton("Fechar");
		btnFechar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Close15x15.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setBounds(616, 510, 130, 58);
		btnFechar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnFechar);

		inicializaComponentes();

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
				// trataCampos(enable);
			}
		});
		btnAlterar
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/BotaoAlterar.png")));
		btnAlterar.setBounds(190, 510, 130, 58);
		btnAlterar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnAlterar);
		btnAlterar.setVisible(true);

		/**
		 * Botao Cancelar
		 * Ocupa a Mesma Posiï¿½ao do Botao Incluir
		 */
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trataBotoes(cancelarOuExcluir);
				// trataCampos(disable);
				// removeTab();
			}
		});
		btnCancelar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Cancel15x15.png")));
		btnCancelar.setBounds(48, 510, 130, 58);
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnCancelar);
		btnCancelar.setVisible(false);

		/**
		 * Botao Carregar
		 */
		btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnCarregar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Reload15x15.png")));
		btnCarregar.setBounds(332, 510, 130, 58);
		btnCarregar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnCarregar);

		/**
		 * Botao Excluir.
		 */
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eInsert = false;
				eDelete = true;
				// salvarForm();
				trataBotoes(cancelarOuExcluir);
				// trataCampos(disable);
				// limpaTela();
			}
		});
		btnExcluir.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Delete15x15.png")));
		btnExcluir.setBounds(332, 510, 130, 58);
		btnExcluir.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnExcluir);

		/**
		 * Botao Fechar.
		 */
		btnFechar = new JButton("Fechar");
		btnFechar.setIcon(
				new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Close15x15.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setBounds(616, 510, 130, 58);
		btnFechar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnFechar);

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
				// trataCampos(enable);
				// limpaTela();
			}
		});
		btnIncluir
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/Button Add15x15.png")));
		btnIncluir.setBounds(48, 510, 130, 58);
		btnIncluir.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnIncluir);

		/**
		 * Botao Localizar
		 */
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// insereTabLocalizaPessoa();
				trataBotoes(localizar);
			}
		});
		btnLocalizar
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/BotaoLocalizar.png")));
		btnLocalizar.setBounds(474, 510, 130, 58);
		btnLocalizar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnLocalizar);

		/**
		 * Botao Salvar.
		 * Ocupa a Mesma Posiï¿½ao do Botao Alterar
		 */
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * if(validaFormulario()){
				 * salvarForm();
				 * trataBotoes(salvar);
				 * trataCampos(disable);
				 * }
				 */
			}
		});
		btnSalvar
				.setIcon(new ImageIcon(LoadImage.loadImage("/com/rodizio/www/visao/imagens/BotaoSalvar.png")));
		btnSalvar.setBounds(190, 510, 130, 58);
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnSalvar);

	}

	protected void trataBotoes(int opcao) {
		switch (opcao) {
			case 1: // Incluir
				// trataCampos(disable);
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
				// trataCampos(disable);
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
				// trataCampos(enable);
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
					// limpaTela();
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

	protected void insereTabLocalizaIgreja() {
		JPanel tabLocalizaIgreja = new JPanel();
		tabLocalizaIgreja.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabbedPane.addTab("Localiza Igreja", null, tabLocalizaIgreja, null);
		tabLocalizaIgreja.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
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
			ControleIgreja controleIgreja = new ControleIgreja();
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
					inicializaCheckBox(datmIgreja.get(table.getSelectedRow()).getId_igreja());
					preencheListaOrganistas(datmIgreja.get(table.getSelectedRow()).getId_igreja());
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

	private void ajustaColunaTabelaOrganistas(JTable table) {
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
	}

	public void preencheListasCheckBox() {

		listaDiasCheckBox.add(chkDomingo);
		listaDiasCheckBox.add(chkSegunda);
		listaDiasCheckBox.add(chkTerca);
		listaDiasCheckBox.add(chkQuarta);
		listaDiasCheckBox.add(chkQuinta);
		listaDiasCheckBox.add(chkSexta);
		listaDiasCheckBox.add(chkSabado);

		inicializaCheckBox();
	}

	private void inicializaCheckBox() {

		for (JCheckBox chkTratar : listaDiasCheckBox) {
			chkTratar.setSelected(false);
			chkTratar.setEnabled(false);
		}
	}

	private void inicializaCheckBox(String idIgreja) {
		List<ModDiasCulto> dias = new ArrayList<>();
		try {
			dias = new ControleIgrejaDiasCulto().listaDiasCulto(idIgreja);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (JCheckBox chkTratar : listaDiasCheckBox) {
			for (ModDiasCulto dia : dias) {
				if (dia.getDiaCulto().compareTo(chkTratar.getName()) == 0) {
					diasCulto.add(Integer.parseInt(chkTratar.getName()));
					chkTratar.setSelected(true);
				}
			}
		}
		repaint();
	}

	private void geraDiasUteisCalendario() {

		for (int i = 0; i < dadosRodizioGerado.size(); i++) {
			try {
				datasGeradas.add(sdf.parse(dadosRodizioGerado.get(i).getDiaDoMes()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		RangeDiasValidos rangeDiasValidos = new RangeDiasValidos(datasGeradas);
		insereTabCalendario(rangeDiasValidos);
	}

	private void gerarRodizioDiasEspeciais() {

		for (int i = 0; i < datmDiasEspeciais.getRowCount(); i++) {
			listaDatasEspeciais.add(datmDiasEspeciais.get(i).getDataEspecial());
		}

	}

	private void insereTabCalendario(RangeDiasValidos diasRodizio) {
		removeTab();
		JPanel pnlCalendarioRodizio = new JPanel();
		tabbedPane.addTab("Calendario Rodizio", null, pnlCalendarioRodizio, null);
		tabbedPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlCalendarioRodizio.setBounds(15, 85, 733, 60);
		pnlCalendarioRodizio.setLayout(null);

		pnlRelatorioRodizioGerado = new JPanel();
		pnlRelatorioRodizioGerado.setBounds(0, 6, 752, 446);
		pnlRelatorioRodizioGerado.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlCalendarioRodizio.add(pnlRelatorioRodizioGerado);
		pnlRelatorioRodizioGerado.setLayout(null);

		calendar = new JCalendar();
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (tabbedPane.getSelectedIndex() == 1) {
					if (!eDiaRepetido() && tabbedPane.getSelectedIndex() > 0) {
						insereTabDiaDoRodizio(calendar.getDate());
						evt.equals(null);
					} else {
						JOptionPane.showMessageDialog(pnlRelatorioRodizioGerado,
								"Ja foram escolhidas as Organistas para Este Dia!");
						evt.equals(null);
					}
				}
			}
		});
		calendar.setBounds(17, 6, 713, 388);
		pnlRelatorioRodizioGerado.add(calendar);
		calendar.setFont(new Font("SansSerif", Font.BOLD, 18));
		calendar.getDayChooser().addDateEvaluator(diasRodizio);
		calendar.setSundayForeground(Color.red);
		calendar.setTodayButtonVisible(false);
		calendar.setDate(dataInicial.getDate());

		JButton btnListarRodizio = new JButton("Listar Rodizio");
		btnListarRodizio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insereTableOrganistasParte();
			}
		});
		btnListarRodizio.setBounds(619, 392, 115, 36);
		pnlRelatorioRodizioGerado.add(btnListarRodizio);

		tabbedPane.setSelectedIndex(1);
	}

	private boolean eDiaRepetido() {
		Collections.sort(listaDiasRodizio);
		if (!listaDiasRodizio.isEmpty()) {
			for (ModDiaRodizio dia : listaDiasRodizio) {
				if (dia.getData().compareTo(calendar.getDate()) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	private void preencheListaOrganistas(String idIgreja) {
		try {
			listaOrganistas = new ControleOrganista().selectAll(idIgreja);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	private void insereTabDiaDoRodizio(Date diaEscolhido) {

		gregorianCalendar.setTime(diaEscolhido);
		Collections.sort(listaOrganistas);
		listaOrganistasDia = new ArrayList<>();
		for (ModOrganistas organista : listaOrganistas) {
			for (ModDisponibilidade disp : organista.getListaDisponibilidades()) {
				if (disp.getDiaCulto() == gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK)) {
					listaOrganistasDia.add(organista);
				}
			}
		}
		Collections.sort(listaOrganistasDia);

		removeTab(2);
		pnlDiaRodizio = new JPanel();
		tabbedPane.addTab("Seleção do Dia: " + sdf.format(diaEscolhido), null, pnlDiaRodizio, null);
		tabbedPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlDiaRodizio.setBounds(15, 85, 733, 60);
		pnlDiaRodizio.setLayout(null);

		pnlRelatorioRodizioGerado = new JPanel();
		pnlRelatorioRodizioGerado.setBounds(0, 0, 764, 458);
		pnlRelatorioRodizioGerado.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlDiaRodizio.add(pnlRelatorioRodizioGerado);
		pnlRelatorioRodizioGerado.setLayout(null);

		JPanel pnlCultoOficial = new JPanel();
		pnlCultoOficial.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlCultoOficial.setBounds(28, 6, 708, 135);
		pnlRelatorioRodizioGerado.add(pnlCultoOficial);
		pnlCultoOficial.setLayout(null);

		txtOrgMeiaHora = new JTextField();
		txtOrgMeiaHora.setEditable(false);
		txtOrgMeiaHora.setBounds(216, 30, 331, 30);
		txtOrgMeiaHora.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtOrgMeiaHora.setColumns(10);
		pnlCultoOficial.add(txtOrgMeiaHora);

		JLabel label_2 = new JLabel("Organista Meia Hora");
		label_2.setBounds(16, 30, 198, 30);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		pnlCultoOficial.add(label_2);

		JLabel label_3 = new JLabel("Organista 1\u00AA Parte");
		label_3.setBounds(16, 62, 198, 30);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		pnlCultoOficial.add(label_3);

		txtOrgPrimParte = new JTextField();
		txtOrgPrimParte.setEditable(false);
		txtOrgPrimParte.setBounds(216, 62, 331, 30);
		txtOrgPrimParte.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtOrgPrimParte.setColumns(10);
		pnlCultoOficial.add(txtOrgPrimParte);

		JLabel label_4 = new JLabel("Organista 2\u00AA Parte");
		label_4.setBounds(16, 94, 194, 30);
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		pnlCultoOficial.add(label_4);

		txtOrgSegParte = new JTextField();
		txtOrgSegParte.setEditable(false);
		txtOrgSegParte.setBounds(216, 94, 331, 30);
		txtOrgSegParte.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtOrgSegParte.setColumns(10);
		pnlCultoOficial.add(txtOrgSegParte);

		JLabel lblCultosOficiais = new JLabel("Cultos Oficiais");
		lblCultosOficiais.setHorizontalAlignment(SwingConstants.CENTER);
		lblCultosOficiais.setBounds(6, 6, 696, 16);
		pnlCultoOficial.add(lblCultosOficiais);

		btnOrgMH = new JButton("Preencher");
		btnOrgMH.setEnabled(false);
		btnOrgMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtOrgSegParte.getText().isEmpty()) {
					btnOrgPrimParte.doClick();
				} else {
					if (txtOrgMeiaHora.getText().isEmpty()) {
						if (gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK) == 1) {
							btnOrgMH.setEnabled(false);
							pnlRJM.setVisible(true);
							btnOrgPrimParteRJM.setEnabled(true);
						}
						insereTableOrganistasParte(listaOrganistasDia, 1, txtOrgMeiaHora, lblIdOrgMH);
						repaint();
					} else {
						JOptionPane.showMessageDialog(txtOrgMeiaHora, "Esta Parte Ja Foi Preenchida.");
					}
				}
			}
		});
		btnOrgMH.setBounds(547, 30, 90, 30);
		pnlCultoOficial.add(btnOrgMH);

		btnOrgPrimParte = new JButton("Preencher");
		btnOrgPrimParte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtOrgPrimParte.getText().isEmpty()) {
					btnOrgPrimParte.setEnabled(false);
					btnOrgSegParte.setEnabled(true);
					insereTableOrganistasParte(listaOrganistasDia, 2, txtOrgPrimParte, lblIdOrgPrimParte);
					repaint();
				} else {
					JOptionPane.showMessageDialog(txtOrgPrimParte, "Esta Parte Ja Foi Preenchida.");
				}
			}
		});
		btnOrgPrimParte.setEnabled(true);
		btnOrgPrimParte.setBounds(547, 62, 90, 30);
		pnlCultoOficial.add(btnOrgPrimParte);

		btnOrgSegParte = new JButton("Preencher");
		btnOrgSegParte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtOrgPrimParte.getText().isEmpty()) {
					btnOrgPrimParte.doClick();
				} else {
					if (txtOrgSegParte.getText().isEmpty()) {
						btnOrgSegParte.setEnabled(false);
						btnOrgMH.setEnabled(true);
						insereTableOrganistasParte(listaOrganistasDia, 3, txtOrgSegParte, lblIdOrgSegParte);
						repaint();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Esta Parte Ja Foi Preenchida.");
					}
				}
			}
		});
		btnOrgSegParte.setEnabled(false);
		btnOrgSegParte.setBounds(547, 94, 90, 30);
		pnlCultoOficial.add(btnOrgSegParte);

		lblIdOrgMH = new JLabel("");
		lblIdOrgMH.setName("1");
		lblIdOrgMH.setBounds(0, 0, 0, 0);
		pnlCultoOficial.add(lblIdOrgMH);

		lblIdOrgPrimParte = new JLabel("");
		lblIdOrgPrimParte.setName("2");
		lblIdOrgPrimParte.setBounds(0, 0, 0, 0);
		pnlCultoOficial.add(lblIdOrgPrimParte);

		lblIdOrgSegParte = new JLabel("");
		lblIdOrgSegParte.setName("3");
		lblIdOrgSegParte.setBounds(0, 0, 0, 0);
		pnlCultoOficial.add(lblIdOrgSegParte);

		pnlRJM = new JPanel();
		pnlRJM.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlRJM.setBounds(28, 140, 708, 103);
		pnlRelatorioRodizioGerado.add(pnlRJM);
		pnlRJM.setLayout(null);
		pnlRJM.setVisible(false);

		lblIdOrgPrimParteRJM = new JLabel("");
		lblIdOrgPrimParteRJM.setName("4");
		lblIdOrgPrimParteRJM.setBounds(0, 0, 0, 0);
		pnlRJM.add(lblIdOrgPrimParteRJM);

		lblIdOrgSegParteRJM = new JLabel("");
		lblIdOrgSegParteRJM.setName("5");
		lblIdOrgSegParteRJM.setBounds(0, 0, 0, 0);
		pnlRJM.add(lblIdOrgSegParteRJM);

		JLabel label = new JLabel("Organista 1\u00AA Parte RJM");
		label.setBounds(16, 27, 191, 30);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		pnlRJM.add(label);

		txtOrgPrimParteRJM = new JTextField();
		txtOrgPrimParteRJM.setBounds(216, 27, 331, 30);
		txtOrgPrimParteRJM.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtOrgPrimParteRJM.setColumns(10);
		pnlRJM.add(txtOrgPrimParteRJM);

		JLabel label_1 = new JLabel("Organista 2\u00AA Parte RJM");
		label_1.setBounds(16, 60, 188, 30);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		pnlRJM.add(label_1);

		txtOrgSegParteRJM = new JTextField();
		txtOrgSegParteRJM.setBounds(216, 60, 331, 30);
		txtOrgSegParteRJM.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtOrgSegParteRJM.setColumns(10);
		pnlRJM.add(txtOrgSegParteRJM);

		JLabel label_5 = new JLabel("Reuni\u00E3o de Jovens e Menores");
		label_5.setBounds(6, 6, 696, 16);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRJM.add(label_5);

		btnOrgPrimParteRJM = new JButton("Preencher");
		btnOrgPrimParteRJM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtOrgMeiaHora.getText().isEmpty()) {
					btnOrgMH.doClick();
				} else {
					if (txtOrgPrimParteRJM.getText().isEmpty()) {
						btnOrgPrimParteRJM.setEnabled(false);
						btnOrgSegParteRJM.setEnabled(true);
						insereTableOrganistasParte(listaOrganistasDia, 4, txtOrgPrimParteRJM, lblIdOrgPrimParteRJM);
						repaint();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Esta Parte Ja Foi Preenchida.");
					}
				}
			}
		});
		btnOrgPrimParteRJM.setEnabled(false);
		btnOrgPrimParteRJM.setBounds(547, 27, 90, 30);
		pnlRJM.add(btnOrgPrimParteRJM);

		btnOrgSegParteRJM = new JButton("Preencher");
		btnOrgSegParteRJM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtOrgPrimParteRJM.getText().isEmpty()) {
					btnOrgPrimParteRJM.doClick();
				} else {
					if (txtOrgSegParteRJM.getText().isEmpty()) {
						btnOrgSegParteRJM.setEnabled(false);
						insereTableOrganistasParte(listaOrganistasDia, 5, txtOrgSegParteRJM, lblIdOrgSegParteRJM);
						repaint();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Esta Parte Ja Foi Preenchida.");
					}
				}
			}
		});
		btnOrgSegParteRJM.setEnabled(false);
		btnOrgSegParteRJM.setBounds(547, 60, 90, 30);
		pnlRJM.add(btnOrgSegParteRJM);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validaOrganistasDiaEscolhido()) {
					listaDiasRodizio.add(dia);
					removeTab(2);
				}
			}
		});
		btnVoltar.setBounds(668, 424, 90, 28);
		pnlRelatorioRodizioGerado.add(btnVoltar);

		tabbedPane.setSelectedIndex(2);

		dia = new ModDiaRodizio();
		dia.setData(diaEscolhido);
		dia.setIdIgreja(txtIdIgreja.getText());

		// insereTableOrganistasParte(listaOrganistasDia, 1 , txtOrgSegParteRJM,
		// lblIdOrgSegParteRJM);
	}

	private boolean validaOrganistasDiaEscolhido() {
		if (txtOrgPrimParte.getText().isEmpty()) {
			JOptionPane.showMessageDialog(pnlOrganistas, "Você não Preencheu uma das Organistas Deste Dia.!");
			txtOrgPrimParte.grabFocus();
			return false;
		} else if (txtOrgSegParte.getText().isEmpty()) {
			JOptionPane.showMessageDialog(pnlOrganistas, "Você não Preencheu uma das Organistas Deste Dia.!");
			txtOrgSegParte.grabFocus();
			return false;
		} else if (txtOrgMeiaHora.getText().isEmpty()) {
			JOptionPane.showMessageDialog(pnlOrganistas, "Você não Preencheu uma das Organistas Deste Dia.!");
			txtOrgMeiaHora.grabFocus();
			return false;
		} else if (txtOrgPrimParteRJM.getText().isEmpty() && gregorianCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
			JOptionPane.showMessageDialog(pnlOrganistas, "Você não Preencheu uma das Organistas Deste Dia.!");
			txtOrgPrimParteRJM.grabFocus();
			return false;
		} else if (txtOrgSegParteRJM.getText().isEmpty() && gregorianCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
			JOptionPane.showMessageDialog(pnlOrganistas, "Você não Preencheu uma das Organistas Deste Dia.!");
			txtOrgSegParteRJM.grabFocus();
			return false;
		}
		return true;
	}

	@SuppressWarnings("static-access")
	private void insereTableOrganistasParte(final List<ModOrganistas> listaOrganistasDia, int parte,
			final JTextField txtField, final JLabel lblId) {

		List<ModOrganistas> listaOrganistasParte = new ArrayList<>();
		for (ModOrganistas org : listaOrganistasDia) {
			for (ModDisponibilidade disp : org.getListaDisponibilidades()) {
				if (disp.getDiaCulto() == gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK)) {
					switch (parte) {
						case 1:// Meia Hora
							if (disp.isMeiaHora()) {
								listaOrganistasParte.add(org);
							}
							break;
						case 2:// Prim Hora
							if (disp.isPrimParte()) {
								listaOrganistasParte.add(org);
							}
							break;
						case 3:// Seg Hora
							if (disp.isSegParte()) {
								listaOrganistasParte.add(org);
							}
							break;
						case 4:// Prim Parte RJM
							if (disp.isPrimParteRJM()) {
								listaOrganistasParte.add(org);
							}
							break;
						case 5:// Prim Parte RJM
							if (disp.isSegParteRJM()) {
								listaOrganistasParte.add(org);
							}
							break;
					}
				}

			}
		}

		pnlOrganistas = new JPanel();
		pnlOrganistas.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlOrganistas.setBounds(6, 246, 752, 179);
		pnlRelatorioRodizioGerado.add(pnlOrganistas);
		pnlOrganistas.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 740, 139);
		pnlOrganistas.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		datmOrganistas = new OrganistaTableModel(listaOrganistasParte);

		table_1.setModel(datmOrganistas);
		ajustaColunaTabelaOrganistas(table_1);

		if (datmOrganistas.getRowCount() > 0) {
			table_1.setRowSelectionInterval(0, 0);
			table_1.grabFocus();
		}
		JButton btnConfirma = new JButton("Escolhe Organista");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(datmOrganistas.get(table_1.getSelectedRow()).getNome_pessoa());
				atualizaQtdesPartesOrganistas(lblId, datmOrganistas.get(table_1.getSelectedRow()), dia);
				pnlRelatorioRodizioGerado.remove(pnlOrganistas);
				repaint();
			}
		});
		btnConfirma.setBounds(269, 145, 152, 28);
		pnlOrganistas.add(btnConfirma);
		repaint();
	}

	private void atualizaQtdesPartesOrganistas(JLabel lblId, ModOrganistas organista, ModDiaRodizio dia) {
		listaOrganistasDia.remove(organista);
		for (ModOrganistas org : listaOrganistas) {
			if (org.getId_pessoa().compareTo(organista.getId_pessoa()) == 0) {
				switch (Integer.parseInt(lblId.getName())) {
					case 1:
						if (ordemRodizio < listaOrganistas.size()) {
							ordemRodizio++;
						}
						lblId.setText(organista.getId_pessoa());
						dia.setIdOrganistaMH(organista.getId_pessoa());
						org.setQtdeMeiaHora(1);
						org.setOrdemRodizio(ordemRodizio);
						break;
					case 2:
						if (ordemRodizio < listaOrganistas.size()) {
							ordemRodizio++;
						}
						lblId.setText(organista.getId_pessoa());
						dia.setIdOrganistaPrimParte(organista.getId_pessoa());
						org.setQtdePrimParte(1);
						org.setOrdemRodizio(ordemRodizio);
						break;
					case 3:
						if (ordemRodizio < listaOrganistas.size()) {
							ordemRodizio++;
						}
						lblId.setText(organista.getId_pessoa());
						dia.setIdOrganistaSegParte(organista.getId_pessoa());
						org.setQtdeSegParte(1);
						org.setOrdemRodizio(ordemRodizio);
						break;
					case 4:
						if (ordemRodizio < listaOrganistas.size()) {
							ordemRodizio++;
						}
						lblId.setText(organista.getId_pessoa());
						dia.setIdOrganistaPrimParteRJM(organista.getId_pessoa());
						org.setQtdePrimParteRJM(1);
						org.setOrdemRodizio(ordemRodizio);
						break;
					case 5:
						if (ordemRodizio < listaOrganistas.size()) {
							ordemRodizio++;
						}
						lblId.setText(organista.getId_pessoa());
						dia.setIdOrganistaSegParteRJM(organista.getId_pessoa());
						org.setQtdeSegParteRJM(1);
						org.setOrdemRodizio(ordemRodizio);
						break;
				}
			}
		}
	}

	private void insereTableOrganistasParte() {

		pnlDiaRodizio = new JPanel();
		tabbedPane.addTab("Relação Organistas no Rodizio", null, pnlDiaRodizio, null);
		tabbedPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlDiaRodizio.setBounds(15, 85, 733, 60);
		pnlDiaRodizio.setLayout(null);

		pnlRelatorioRodizioGerado = new JPanel();
		pnlRelatorioRodizioGerado.setBounds(0, 0, 764, 458);
		pnlRelatorioRodizioGerado.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlDiaRodizio.add(pnlRelatorioRodizioGerado);
		pnlRelatorioRodizioGerado.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 752, 195);
		pnlRelatorioRodizioGerado.add(scrollPane);

		JTable table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		Collections.sort(listaDiasRodizio);
		datmDiasDoRodizio = new DiaRodizioTableModel(listaDiasRodizio);

		table_2.setModel(datmDiasDoRodizio);

		JButton btnVoltar_1 = new JButton("Voltar");
		btnVoltar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeTab(2);
			}
		});
		btnVoltar_1.setBounds(668, 424, 90, 28);
		pnlRelatorioRodizioGerado.add(btnVoltar_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 201, 752, 211);
		pnlRelatorioRodizioGerado.add(scrollPane_2);

		JTable tableRelatorioRodizio = new JTable();
		scrollPane_2.setViewportView(table_3);
		Collections.sort(listaOrganistas);
		DiaRodizioQtdePartesTableModel datmDiasQtde = new DiaRodizioQtdePartesTableModel(listaOrganistas);
		table_3.setModel(datmDiasQtde);

		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insereRelatorioRodizio();
			}
		});
		btnGerarPdf.setBounds(566, 424, 90, 28);
		pnlRelatorioRodizioGerado.add(btnGerarPdf);

		tabbedPane.setSelectedIndex(2);

		repaint();
	}

	void insereRelatorioRodizio() {

		JasperReport report = null;
		CreateFolder.create(System.getProperty("user.home") + System.getProperty("file.separator") + "SistemaRodizioCCB"
				+ System.getProperty("file.separator") + "Relatorios" + System.getProperty("file.separator") + "codigoFonte"
				+ System.getProperty("file.separator"));
		try {
			report = JasperCompileManager.compileReport(System.getProperty("user.home") + System.getProperty("file.separator")
					+ "SistemaRodizioCCB" + System.getProperty("file.separator") + "Relatorios"
					+ System.getProperty("file.separator") + "codigoFonte/report8.jrxml");
		} catch (JRException e) {
			e.printStackTrace();
		}

		JRTableModelDataSource src = new JRTableModelDataSource(datmDiasDoRodizio);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(report, null, src);
		} catch (JRException e) {
			e.printStackTrace();
		}
		CreateFolder.create(System.getProperty("user.home") + System.getProperty("file.separator") + "SistemaRodizioCCB"
				+ System.getProperty("file.separator") + "Relatorios" + System.getProperty("file.separator"));

		try {

			JasperExportManager.exportReportToPdfFile(jasperPrint,
					System.getProperty("user.home") + System.getProperty("file.separator") + "SistemaRodizioCCB"
							+ System.getProperty("file.separator") + "Relatorios" + System.getProperty("file.separator")
							+ "RelatorioRodizio.pdf");
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}

	protected void removeTab() {
		if (tabbedPane.getTabCount() > 1) {
			tabbedPane.removeTabAt(1);
			tabbedPane.setSelectedIndex(0);
		}
	}

	private void removeTab(int i) {
		if (tabbedPane.getTabCount() > 2) {
			tabbedPane.removeTabAt(i);
			tabbedPane.setSelectedIndex(1);
		}

	}

	protected void inicializaComponentes() {

		preencheListasCheckBox();
		// insereTabLocalizaIgreja();
		// insereTabCalendario();
		// insereTabCalendario(diasRodizio);
		// insereTabDiaDoRodizio(dataFinal.getDate());
		// insereTableOrganistasParte();
		// insereRelatorioRodizio();

	}
}
