package com.rodizio.www.visao;

import com.rodizio.www.controle.ControleCidade;
import com.rodizio.www.controle.ControleIgreja;
import com.rodizio.www.controle.ControleUF;
import com.rodizio.www.modelo.ModCadIgreja;
import com.rodizio.www.modelo.ModCidade;
import com.rodizio.www.modelo.ModDiasCulto;
import com.rodizio.www.modelo.ModUF;
import com.rodizio.www.util.ferramentas.Formata;
import com.rodizio.www.visao.abstractTableModel.IgrejaTableModel;
import com.rodizio.www.visao.controle.TrataEnter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CadastroIgreja extends JInternalFrame{

    //Variaveis Gerais.
    private static final long serialVersionUID = 1L;
    private Boolean eInsert=false;
    private Boolean eDelete=false;
    private int incluir = 1, alterar = 2, salvar = 3, cancelarOuExcluir = 4, localizar = 5, carregar = 6, enable = 1, disable = 2;
    private List<Component> camposTrataEnter = new ArrayList<Component>();
    private List<JTextField> listaCamposTexto = new ArrayList<JTextField>();
    private List<JCheckBox> listaCamposCheckBox = new ArrayList<JCheckBox>();
    private List<JComboBox<String>> listaComboBox = new ArrayList<JComboBox<String>>();

    //Variaveis de paineis.
    private JPanel pnlGeralCadComum;
    private JTabbedPane tabbedPane;

    //Variaveis de Botoes
    private JButton btnLocalizar;
    private JButton btnSalvar;
    private JButton btnAlterar;
    private JButton btnCancelar;
    private JButton btnExcluir;
    private JButton btnIncluir;
    private JButton btnCarregar;

    //Variaveis de TextField
    private JTextField txtIdCidade;
    private JTextField txtIdComum;
    private JTextField txtNomeDaComum;
    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtComplem;
    private JTextField txtBairro;
    private JTextField txtCEP;

    //Variaveis CheckBox

    private JCheckBox chkDomingo;
    private JCheckBox chkSegunda;
    private JCheckBox chkTerca;
    private JCheckBox chkQuarta;
    private JCheckBox chkQuinta;
    private JCheckBox chkSexta;
    private JCheckBox chkSabado;
    private JCheckBox chkRJM;

    @SuppressWarnings("rawtypes")
    private JComboBox cmbCidade;
    private DefaultComboBoxModel<String> dcbmCidades = new DefaultComboBoxModel<>();
    @SuppressWarnings("rawtypes")
    private JComboBox cmbUF;
    private DefaultComboBoxModel<String> dcbmUF = new DefaultComboBoxModel<>();

    //Cadastro de Igreja
    private ModCadIgreja cadIgreja;
    private ControleIgreja controleIgreja;
    private IgrejaTableModel datmIgreja;
    private Map<Integer,JCheckBox> mapChexkbox = new HashMap<Integer,JCheckBox>();

    private JInternalFrame frmCadastroIgreja;
    private JTable table;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    CadastroIgreja window = new CadastroIgreja();

		    window.frmCadastroIgreja.setVisible(true);

		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    @SuppressWarnings("rawtypes")
    public CadastroIgreja() {
	setRootPaneCheckingEnabled(false);
	setFont(new Font("Arial Black", Font.BOLD, 18));
	setTitle("Cadastro de Comum Congrega\u00E7\u00E3o");
	setName("frmCadastroIgreja");
	getContentPane().setPreferredSize(new Dimension(800, 600));
	setPreferredSize(new Dimension(800, 600));
	setBounds(50, 50, 800, 600);
	getContentPane().setLayout(null);



	pnlGeralCadComum = new JPanel();
	pnlGeralCadComum.setPreferredSize(new Dimension(800, 600));
	pnlGeralCadComum.setBounds(0, 0, 784, 569);
	getContentPane().add(pnlGeralCadComum);
	pnlGeralCadComum.setLayout(null);

	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
	tabbedPane.setBounds(6, 6, 778, 503);
	pnlGeralCadComum.add(tabbedPane);


	JPanel tabCadastro = new JPanel();
	tabCadastro.setBorder(UIManager.getBorder("TitledBorder.border"));
	tabbedPane.addTab("Cadastro", null, tabCadastro, null);
	tabCadastro.setLayout(null);

	JLabel lblCdigoDaComum = new JLabel("C\u00F3digo da Comum:");
	lblCdigoDaComum.setVisible(false);
	lblCdigoDaComum.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCdigoDaComum.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblCdigoDaComum.setBounds(0, 0, 0, 0);
	tabCadastro.add(lblCdigoDaComum);

	txtIdComum = new JTextField();
	txtIdComum.setName("ID");
	txtIdComum.setVisible(false);
	txtIdComum.setBounds(0, 0, 0, 0);
	tabCadastro.add(txtIdComum);
	txtIdComum.setColumns(10);


	txtIdCidade = new JTextField();
	txtIdCidade.setName("ID");
	txtIdCidade.setVisible(false);
	txtIdCidade.setBounds(0, 0, 0, 0);
	tabCadastro.add(txtIdCidade);

	JLabel lblNomeDaComum = new JLabel("Nome da Comum:");
	lblNomeDaComum.setHorizontalAlignment(SwingConstants.LEFT);
	lblNomeDaComum.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblNomeDaComum.setBounds(26, 0, 143, 30);
	tabCadastro.add(lblNomeDaComum);

	txtNomeDaComum = new JTextField();
	txtNomeDaComum.setName("Nome da Comum");
	txtNomeDaComum.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent arg0) {
		maiuscula(txtNomeDaComum);
	    }
	});
	txtNomeDaComum.setFont(new Font("SansSerif", Font.BOLD, 16));
	txtNomeDaComum.setBounds(16, 30, 751, 40);
	tabCadastro.add(txtNomeDaComum);
	txtNomeDaComum.setColumns(10);

	JLabel lblEndereo = new JLabel("Endere\u00E7o");
	lblEndereo.setHorizontalAlignment(SwingConstants.LEFT);
	lblEndereo.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblEndereo.setBounds(26, 71, 87, 30);
	tabCadastro.add(lblEndereo);

	JSeparator separator = new JSeparator();
	separator.setBounds(6, 99, 766, 2);
	tabCadastro.add(separator);

	JLabel lblLogradouro = new JLabel("Logradouro:");
	lblLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
	lblLogradouro.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblLogradouro.setBounds(26, 99, 100, 30);
	tabCadastro.add(lblLogradouro);

	txtLogradouro = new JTextField();
	txtLogradouro.setName("Logradouro (Av, rua e etc.)");
	txtLogradouro.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent arg0) {
		maiuscula(txtLogradouro);
	    }
	});
	txtLogradouro.setFont(new Font("SansSerif", Font.BOLD, 15));
	txtLogradouro.setBounds(16, 127, 555, 40);
	tabCadastro.add(txtLogradouro);
	txtLogradouro.setColumns(10);

	JLabel lblNumero = new JLabel("N\u00BA:");
	lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNumero.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblNumero.setBounds(647, 99, 27, 30);
	tabCadastro.add(lblNumero);

	txtNumero = new JTextField();
	txtNumero.setName("Numero Predial");
	txtNumero.setFont(new Font("SansSerif", Font.BOLD, 15));
	txtNumero.setBounds(647, 127, 120, 40);
	tabCadastro.add(txtNumero);
	txtNumero.setColumns(10);

	JLabel lblComplemento = new JLabel("Complemento:");
	lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
	lblComplemento.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblComplemento.setBounds(26, 163, 101, 30);
	tabCadastro.add(lblComplemento);

	txtComplem = new JTextField();
	txtComplem.setName("Complemento do Endere\u00E7o");
	txtComplem.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent arg0) {
		maiuscula(txtComplem);
	    }
	});
	txtComplem.setFont(new Font("SansSerif", Font.BOLD, 15));
	txtComplem.setBounds(16, 192, 419, 40);
	tabCadastro.add(txtComplem);
	txtComplem.setColumns(10);

	txtBairro = new JTextField();
	txtBairro.setName("Bairro");
	txtBairro.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent arg0) {
		maiuscula(txtBairro);
	    }
	});
	txtBairro.setFont(new Font("SansSerif", Font.BOLD, 15));
	txtBairro.setColumns(10);
	txtBairro.setBounds(16, 255, 200, 40);
	tabCadastro.add(txtBairro);

	JLabel lblBairro = new JLabel("Bairro:");
	lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
	lblBairro.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblBairro.setBounds(26, 229, 55, 30);
	tabCadastro.add(lblBairro);

	JLabel lblCidade = new JLabel("Cidade:");
	lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCidade.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblCidade.setBounds(491, 229, 55, 30);
	tabCadastro.add(lblCidade);

	cmbCidade = new JComboBox();
	cmbCidade.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusGained(FocusEvent e) {
		if(cmbUF.getSelectedIndex()<1){
		    JOptionPane.showMessageDialog(rootPane, "Voc� Dever� Selecionar um Estado Primeiro.");
		    cmbUF.grabFocus();
		}
	    }
	    @Override
	    public void focusLost(FocusEvent e) {
		if(cmbCidade.getSelectedIndex()<1){
		    JOptionPane.showMessageDialog(rootPane, "Voc� Dever� Selecionar uma Cidade.");
		    cmbCidade.grabFocus();
		}
	    }
	});
	cmbCidade.setName("Cidade");
	cmbCidade.setFont(new Font("SansSerif", Font.BOLD, 15));
	cmbCidade.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent arg0) {
		if(cmbCidade.getSelectedIndex()>0){
		    txtIdCidade.setText(Formata.soNumeros(""+cmbCidade.getItemAt(cmbCidade.getSelectedIndex())));
		}
	    }
	});

	cmbCidade.setBounds(491, 255, 276, 40);
	tabCadastro.add(cmbCidade);

	JLabel lblUf = new JLabel("UF:");
	lblUf.setHorizontalAlignment(SwingConstants.RIGHT);
	lblUf.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblUf.setBounds(243, 229, 27, 30);
	tabCadastro.add(lblUf);

	cmbUF = new JComboBox();
	cmbUF.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
		    if(cmbUF.getSelectedIndex()<1){
			JOptionPane.showMessageDialog(rootPane, "Voc� Dever� Selecionar um Estado Primeiro.");
			arg0.consume();
		    }else{
			cmbCidade.grabFocus();
		    }
		}
	    }
	});
	cmbUF.setFont(new Font("SansSerif", Font.BOLD, 15));
	cmbUF.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent arg0) {
		if(cmbUF.getSelectedIndex()>0){
		    preencheComboCidade(dcbmUF.getElementAt(cmbUF.getSelectedIndex()));
		}
	    }
	});
	cmbUF.setBounds(243, 255, 236, 40);
	tabCadastro.add(cmbUF);

	JLabel lblCep = new JLabel("CEP:");
	lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCep.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblCep.setBounds(547, 163, 42, 30);
	tabCadastro.add(lblCep);

	txtCEP = new JTextField();
	txtCEP.setName("CEP");
	txtCEP.setFont(new Font("SansSerif", Font.BOLD, 15));
	txtCEP.setColumns(10);
	txtCEP.setBounds(547, 192, 192, 40);
	tabCadastro.add(txtCEP);

	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(-16, 304, 819, 1);
	tabCadastro.add(separator_1);

	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(6, 327, 766, 2);
	tabCadastro.add(separator_2);

	JLabel lblDiasDeCulto = new JLabel("Dias de Culto");
	lblDiasDeCulto.setHorizontalAlignment(SwingConstants.LEFT);
	lblDiasDeCulto.setFont(new Font("SansSerif", Font.BOLD, 14));
	lblDiasDeCulto.setBounds(26, 299, 100, 30);
	tabCadastro.add(lblDiasDeCulto);

	chkDomingo = new JCheckBox("Domingo");//id=1
	chkDomingo.setName("1");
	chkDomingo.setBounds(new Rectangle(0, 190, 0, 0));
	chkDomingo.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkDomingo.setBounds(16, 342, 100, 45);
	tabCadastro.add(chkDomingo);

	chkSegunda = new JCheckBox("Segunda-Feira");//id=2
	chkSegunda.setName("2");
	chkSegunda.setBounds(new Rectangle(0, 190, 0, 0));
	chkSegunda.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkSegunda.setBounds(243, 342, 170, 45);
	tabCadastro.add(chkSegunda);

	chkTerca = new JCheckBox("Ter\u00E7a-Feira");//id=3
	chkTerca.setName("3");
	chkTerca.setBounds(new Rectangle(0, 190, 0, 0));
	chkTerca.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkTerca.setBounds(425, 342, 170, 45);
	tabCadastro.add(chkTerca);

	chkQuarta = new JCheckBox("Quarta-Feira");//id=4
	chkQuarta.setName("4");
	chkQuarta.setBounds(new Rectangle(0, 190, 120, 30));
	chkQuarta.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkQuarta.setBounds(597, 342, 170, 45);
	tabCadastro.add(chkQuarta);

	chkQuinta = new JCheckBox("Quinta-Feira");//id=5
	chkQuinta.setName("5");
	chkQuinta.setBounds(new Rectangle(0, 190, 120, 30));
	chkQuinta.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkQuinta.setBounds(16, 399, 170, 45);
	tabCadastro.add(chkQuinta);

	chkSexta = new JCheckBox("Sexta-Feira");//id=6
	chkSexta.setName("6");
	chkSexta.setBounds(new Rectangle(0, 190, 120, 30));
	chkSexta.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkSexta.setBounds(243, 399, 170, 45);
	tabCadastro.add(chkSexta);

	chkSabado = new JCheckBox("S\u00E1bado");//id=7
	chkSabado.setName("7");
	chkSabado.setBounds(new Rectangle(0, 190, 120, 30));
	chkSabado.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkSabado.setBounds(425, 399, 170, 45);
	tabCadastro.add(chkSabado);

	chkRJM = new JCheckBox("RJM");//id=8
	chkRJM.setName("8");
	chkRJM.setFont(new Font("SansSerif", Font.BOLD, 16));
	chkRJM.setBounds(new Rectangle(0, 190, 0, 0));
	chkRJM.setBounds(117, 341, 120, 45);
	tabCadastro.add(chkRJM);

	/**
	 * Lista de Campos para tratar enter no Lugar do TAB.
	 * e Limpar a Tela.
	 */
	preparaArrayDeComponentes();//Insere os Componetes para Limpar ou travar a Edi��o
	inicializaComponentes();//Seta Propriedades dos Componentes.
	//insereTabLocalizaIgreja();
    }

    void trataBotoes(int opcao) {
	switch (opcao) {
	case 1: //Incluir
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

	    eInsert=true;

	    break;
	case 2: //Alterar
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
	case 3: //Salvar
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
	case 4: //Cancelar ou Excluir

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
		//preencheFormulario();
		trataBotoes(6);
	    }
	    break;
	case 5: //Localizar
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
	case 6: //Carregar
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

    void trataCampos(int opcao) {
	switch (opcao) {
	case 1://Enable
	    cmbUF.setEnabled(true);
	    for (Component componente : camposTrataEnter) {
		if (componente != null) {
		    componente.setEnabled(true);
		}
	    }

	    break;
	case 2://Disable
	    cmbUF.setEnabled(false);
	    for (Component componente : camposTrataEnter) {
		if (componente != null) {
		    componente.setEnabled(false);
		}
	    }
	    break;
	}
    }

    void limpaTela() {

	for (JTextField jTextField : listaCamposTexto) {
	    if (jTextField != null) {
		jTextField.setText("");
	    }
	}
	for (JCheckBox jChkBox : listaCamposCheckBox) {
	    if (jChkBox != null) {
		jChkBox.setSelected(false);
	    }
	}
    }

    void preencheFormulario(ModCadIgreja igreja) {
	txtIdComum.setText(igreja.getId_igreja());
	txtNomeDaComum.setText(igreja.getNome_comum());
	txtLogradouro.setText(igreja.getLogradouro_igreja());
	txtComplem.setText(igreja.getComplem_end_igreja());
	txtNumero.setText(igreja.getNum_end_igreja());
	txtCEP.setText(igreja.getCep_igreja());
	txtBairro.setText(igreja.getBairro_igreja());
	txtIdCidade.setText(igreja.getId_cidade());

	preencheComboBoxUFeCidade(igreja.getId_cidade());
	preencheCheckBoxDiasCulto(igreja.getListaDiasCulto());

    }

    private void preencheCheckBoxDiasCulto(List<ModDiasCulto> diasCulto) {
	for(ModDiasCulto dia:diasCulto){
	    mapChexkbox.get(Integer.parseInt(dia.getDiaCulto())).setSelected(true);
	}
    }

    private void preencheComboBoxUFeCidade(String id_cidade) {
	ModCidade cidade = new ModCidade();
	cidade = ControleCidade.cidadeID(id_cidade);
	cmbUF.setSelectedIndex(Integer.parseInt(cidade.getCodEstado()));
	//cmbCidade.setSelectedIndex(dcbmCidades.getIndexOf(id_cidade));;
    }

    void insereBotoes() {
	/**
	 * Botao Alterar
	 * Ocupa a Mesma Posicao do Botao Salvar
	 */
	btnAlterar = new JButton("Alterar");
	btnAlterar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		eInsert=false;
		trataBotoes(alterar);
		trataCampos(enable);
	    }
	});
	btnAlterar.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/BotaoAlterar.png")));
	btnAlterar.setBounds(190, 510, 130, 58);
	btnAlterar.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnAlterar);
	btnAlterar.setVisible(true);

	/**
	 * Botao Cancelar
	 * Ocupa a Mesma Posi�ao do Botao Incluir
	 */
	btnCancelar = new JButton("Cancelar");
	btnCancelar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		trataBotoes(cancelarOuExcluir);
		trataCampos(disable);
		removeTab();
	    }
	});
	btnCancelar.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/Button Cancel15x15.png")));
	btnCancelar.setBounds(48, 510, 130, 58);
	btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnCancelar);
	btnCancelar.setVisible(false);

	/**
	 * Botao Carregar
	 */
	btnCarregar = new JButton("Carregar");
	btnCarregar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		if(table.getSelectedRow()>-1){
		    trataBotoes(carregar);
		    trataCampos(disable);
		    try {
			controleIgreja = new ControleIgreja();

			preencheFormulario(controleIgreja.selectId(datmIgreja.get(table.getSelectedRow()).getId_igreja()));

		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    removeTab();
		}else{
		    JOptionPane.showMessageDialog(rootPane, "Selecione uma linha e ent�o Clique em Carregar.");
		}				
	    }
	});
	btnCarregar.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/Button Reload15x15.png")));
	btnCarregar.setBounds(332, 510, 130, 58);
	btnCarregar.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnCarregar);

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
	btnExcluir.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/Button Delete15x15.png")));
	btnExcluir.setBounds(332, 510, 130, 58);
	btnExcluir.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnExcluir);

	/**
	 * Botao Fechar.
	 */
	JButton btnFechar = new JButton("Fechar");
	btnFechar.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/Button Close15x15.png")));
	btnFechar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		dispose();
	    }
	});
	btnFechar.setBounds(616, 510, 130, 58);
	btnFechar.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnFechar);

	/**
	 * Botao Incluir.
	 * Ocupa a Mesma Posi�ao do Botao Cancelar
	 */
	btnIncluir = new JButton("Incluir");
	btnIncluir.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		eInsert=true;
		eDelete=false;
		trataBotoes(incluir);
		trataCampos(enable);
		limpaTela();
	    }
	});
	btnIncluir.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/Button Add15x15.png")));
	btnIncluir.setBounds(48, 510, 130, 58);
	btnIncluir.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnIncluir);

	/**
	 * Botao Localizar
	 */
	btnLocalizar = new JButton("Localizar");
	btnLocalizar.addActionListener(new ActionListener() {


	    public void actionPerformed(ActionEvent arg0) {
		trataBotoes(localizar);
		insereTabLocalizaIgreja();

	    }
	});
	btnLocalizar.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/BotaoLocalizar.png")));
	btnLocalizar.setBounds(474, 510, 130, 58);
	btnLocalizar.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnLocalizar);

	/**
	 * Botao Salvar.
	 * Ocupa a Mesma Posi�ao do Botao Alterar
	 */
	btnSalvar = new JButton("Salvar");
	btnSalvar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		if(validaFormulario()){
		    salvarForm();
		    trataBotoes(salvar);
		    trataCampos(disable);
		}
	    }
	});
	btnSalvar.setIcon(new ImageIcon(CadastroIgreja.class.getResource("/com/rodizio/www/visao/imagens/BotaoSalvar.png")));
	btnSalvar.setBounds(190, 510, 130, 58);
	btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
	pnlGeralCadComum.add(btnSalvar);
    }

    void trataEnter() {

	for (Component campo : camposTrataEnter) {
	    TrataEnter.acionaEnter(campo);
	}
	//System.out.println("Terminou");
    }

    void inicializaComponentes() {

	insereBotoes();
	trataBotoes(4);
	trataEnter();
	//trataCampos(enable);
	trataCampos(disable);
	preencheComboUF();


    }

    @SuppressWarnings("unchecked")
    void preparaArrayDeComponentes(){
	listaCamposTexto.add(txtIdComum);
	listaCamposTexto.add(txtNomeDaComum);
	listaCamposTexto.add(txtLogradouro);
	listaCamposTexto.add(txtNumero);
	listaCamposTexto.add(txtComplem);
	listaCamposTexto.add(txtCEP);
	listaCamposTexto.add(txtBairro);
	listaCamposTexto.add(txtIdCidade);

	listaCamposCheckBox.add(chkDomingo);
	listaCamposCheckBox.add(chkRJM);
	listaCamposCheckBox.add(chkQuarta);
	listaCamposCheckBox.add(chkQuinta);
	listaCamposCheckBox.add(chkSabado);
	listaCamposCheckBox.add(chkSegunda);
	listaCamposCheckBox.add(chkSexta);
	listaCamposCheckBox.add(chkTerca);

	listaComboBox.add(cmbCidade);
	//listaComboBox.add(cmbUF);

	camposTrataEnter.addAll(listaCamposTexto);
	camposTrataEnter.addAll(listaCamposCheckBox);
	camposTrataEnter.addAll(listaComboBox);
	mapChexkbox.put(1, chkDomingo);
	mapChexkbox.put(2, chkSegunda);
	mapChexkbox.put(3, chkTerca);
	mapChexkbox.put(4, chkQuarta);
	mapChexkbox.put(5, chkQuinta);
	mapChexkbox.put(6, chkSexta);
	mapChexkbox.put(7, chkSabado);
	mapChexkbox.put(8, chkRJM);
    }

    @SuppressWarnings("unchecked")
    void preencheComboCidade(String uf){
	cmbCidade.removeAll();
	dcbmCidades.removeAllElements();
	List<ModCidade> listaCidades = new ArrayList<>();
	listaCidades.addAll(ControleCidade.listaCidadess(uf.substring(0,2)));
	dcbmCidades.addElement("< Selecione uma Cidade >");
	for(ModCidade cidade : listaCidades){
	    dcbmCidades.addElement(cidade.getIdCidade()+" - "+cidade.getNome_Cidade());
	}
	cmbCidade.setModel(dcbmCidades);
    }

    @SuppressWarnings("unchecked")
    void preencheComboUF(){

	for(ModUF uf:ControleUF.listaEstados()){
	    dcbmUF.addElement(uf.getUf_Estado()+" - "+uf.getNome_Estado());
	}
	cmbUF.setModel(dcbmUF);
    }

    void salvarForm(){

	cadIgreja = new ModCadIgreja();

	if(eInsert){//INSERT
	    cadIgreja.setStatusCadIgreja(0);
	}else if(!eDelete){ //UPDATE
	    cadIgreja.setId_igreja(txtIdComum.getText());
	    cadIgreja.setStatusCadIgreja(1);
	}else if(eDelete){//DELETE
	    cadIgreja.setId_igreja(txtIdComum.getText());
	    cadIgreja.setStatusCadIgreja(2);
	}

	cadIgreja.setNome_comum(txtNomeDaComum.getText());
	cadIgreja.setLogradouro_igreja(txtLogradouro.getText());
	cadIgreja.setComplem_end_igreja(txtComplem.getText());
	cadIgreja.setCep_igreja(txtCEP.getText());
	cadIgreja.setBairro_igreja(txtBairro.getText());
	cadIgreja.setNum_end_igreja(txtNumero.getText());
	cadIgreja.setId_cidade(txtIdCidade.getText());

	List<ModDiasCulto> listaDiasCulto = new ArrayList<>();

	for(JCheckBox chkBox : listaCamposCheckBox){
	    if(chkBox.isSelected()){
		ModDiasCulto diaDeCulto = new ModDiasCulto();
		diaDeCulto.setDiaCulto(chkBox.getName());
		listaDiasCulto.add(diaDeCulto);
	    }
	}
	cadIgreja.setListaDiasCulto(listaDiasCulto);

	try {
	    new ControleIgreja(cadIgreja);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    void maiuscula(JTextField txt) {
	txt.setText(txt.getText().toUpperCase());
    }

    void insereTabLocalizaIgreja(){
	removeTab();
	btnCarregar.setEnabled(false);
	JPanel tabLocalizaIgreja = new JPanel();
	tabLocalizaIgreja.setBorder(UIManager.getBorder("TitledBorder.border"));
	tabbedPane.addTab("Localiza Igreja", null, tabLocalizaIgreja, null);
	tabLocalizaIgreja.setLayout(null);

	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(50, 59, 584, 283);
	tabLocalizaIgreja.add(scrollPane_1);

	table = new JTable();
	table.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
		    btnCarregar.doClick();
		    arg0.consume();
		}
	    }
	});
	scrollPane_1.setViewportView(table);

	cadIgreja = new ModCadIgreja();
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

	if(datmIgreja.getRowCount()>0){
	    btnCarregar.setEnabled(true);
	}

    }

    void removeTab(){
	if (tabbedPane.getTabCount() > 1) {
	    tabbedPane.removeTabAt(1);
	    tabbedPane.setSelectedIndex(0);
	}
    }

    void ajustaColunasTable(int colunas){

	table.getColumnModel().getColumn(0).setResizable(false);
	table.getColumnModel().getColumn(0).setPreferredWidth(100);

	switch(2){
	case 2:
	    table.getColumnModel().getColumn(1).setPreferredWidth(500);
	    break;
	case 3:
	    table.getColumnModel().getColumn(1).setPreferredWidth(300);
	    table.getColumnModel().getColumn(2).setPreferredWidth(300);
	    break;
	}
    }

    boolean validaFormulario(){
	for(JTextField campoTxt : listaCamposTexto){
	    if(campoTxt.getText().isEmpty() && campoTxt.getName().compareTo("ID")!=0){
		JOptionPane.showMessageDialog(campoTxt, "O Campo: \""+campoTxt.getName()+"\" dever� ser preenchido.");
		campoTxt.grabFocus();
		return false;
	    }
	}
	if(cmbCidade.getSelectedIndex()<1){
	    JOptionPane.showMessageDialog(rootPane, "Voc� Dever� Selecionar uma cidade.");
	    cmbCidade.grabFocus();
	    return false;
	}

	boolean ok = false;
	for(JCheckBox campoCheckBox : listaCamposCheckBox){
	    if(campoCheckBox.isSelected()){
		ok=true;
	    }
	}
	if(!ok){
	    JOptionPane.showMessageDialog(rootPane, "Ao menos um dia de Culto dever� estar Selecionado.");
	    return false;
	}

	return true;
    }
}
