package com.rodizio.www.visao;

import com.rodizio.www.controle.ControleIgrejaDiasCulto;
import com.rodizio.www.controle.ControleOrganista;
import com.rodizio.www.modelo.*;
import com.rodizio.www.util.ferramentas.Formata;
import com.rodizio.www.util.ferramentas.Preenche;
import com.rodizio.www.visao.controle.TrataEnter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroOrganistas extends CadastroMinisterio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7241411796024247748L;
	@SuppressWarnings("unused")
	private JPanel pnlDisponibilidade;
	private JCheckBox chkDomingo;
	private JCheckBox chkMHDom;
	private JCheckBox chkSegunda;
	private JCheckBox chkPrimParteDom;
	private JCheckBox chkSegParteDom;
	private JCheckBox chkTerca;
	private JCheckBox chkQuarta;
	private JCheckBox chkQuinta;
	private JCheckBox chkSexta;
	private JCheckBox chkSabado;
	private JCheckBox chkMHSegunda;
	private JCheckBox chkPrimParteSegunda;
	private JCheckBox chkSegParteSegunda;
	private JCheckBox chkMHTerca;
	private JCheckBox chkPrimParteTerca;
	private JCheckBox chkSegParteTerca;
	private JCheckBox chkMHQuarta;
	private JCheckBox chkPrimParteQuarta;
	private JCheckBox chkSegParteQuarta;
	private JCheckBox chkMHQuinta;
	private JCheckBox chkPrimParteQuinta;
	private JCheckBox chkSegParteQuinta;
	private JCheckBox chkMHSexta;
	private JCheckBox chkPrimParteSexta;
	private JCheckBox chkSegParteSexta;
	private JCheckBox chkMHSabado;
	private JCheckBox chkPrimParteSabado;
	private JCheckBox chkSegParteSabado;
	private JCheckBox chkPrimParteDomRJM;
	private JCheckBox chkSegParteDomRJM;
	private JCheckBox chkCultoEspecial;
	private JCheckBox chkMHEspecial;
	private JCheckBox chkPrimParteEspecial;
	private JCheckBox chkSegParteEspecial;

	private List<JCheckBox> listaDiasCheckBox = new ArrayList<>();
	private Map<JCheckBox, List<JCheckBox>> mapChexkbox = new HashMap<JCheckBox, List<JCheckBox>>();

	private ModOrganistas pessoa;
	private ControleOrganista controleOrganistas;
	private List<JCheckBox> listaPartesDomingo = new ArrayList<>();
	private List<JCheckBox> listaPartesSegunda = new ArrayList<>();
	private List<JCheckBox> listaPartesTerca = new ArrayList<>();
	private List<JCheckBox> listaPartesQuarta = new ArrayList<>();
	private List<JCheckBox> listaPartesQuinta = new ArrayList<>();
	private List<JCheckBox> listaPartesSexta = new ArrayList<>();
	private List<JCheckBox> listaPartesSabado = new ArrayList<>();
	private List<JCheckBox> listaPartesEspecial = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroOrganistas frmCadOrganistas = new CadastroOrganistas();
					frmCadOrganistas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public CadastroOrganistas() {
		txtNomePessoa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!txtIdIgreja.getText().isEmpty()) {
					inicializaCheckBox(txtIdIgreja.getText());
				}
			}
		});

		txtNomeDaComum.setName("Nome da Comum");
		cmbTipoMinisterio.setSize(0, 0);
		lblSelecioneUmTipo.setSize(0, 0);
		lblSelecioneUmTipo.setVisible(false);
		cmbTipoMinisterio.setVisible(false);
		label.setLocation(6, 30);
		rdButtonEnviaEmail.setLocation(357, 305);
		lblSelecioneUmTipo.setLocation(0, 0);
		cmbTipoMinisterio.setLocation(0, 0);
		txtNomePessoa.setLocation(87, 98);
		lblNomeDaoIrmaoOuIrma.setLocation(87, 75);
		pnlEmails.setLocation(new Point(340, 190));
		lblTelefones.setBounds(6, 6, 299, 24);
		lblEmails.setBounds(6, 6, 408, 25);
		pnlEmails.setBounds(347, 145, 421, 160);
		pnlTelefones.setBounds(22, 145, 313, 160);
		txtTelCelular.setLocation(85, 108);
		lblCelular.setLocation(6, 108);
		txtEmail3.setBounds(65, 110, 350, 39);
		lblEmail_2.setBounds(6, 110, 60, 38);
		lblEmail_1.setSize(60, 33);
		lblEmail.setSize(59, 39);
		txtEmail1.setSize(350, 39);
		txtEmail2.setSize(350, 39);
		txtEmail2.setLocation(65, 70);
		lblEmail_1.setLocation(6, 75);
		txtTelComercial.setLocation(85, 68);
		lblComercial.setLocation(6, 68);
		txtEmail1.setLocation(65, 30);
		lblEmail.setLocation(5, 32);
		txtTelResidencial.setLocation(85, 30);
		lblEmail.setInheritsPopupMenu(false);
		lblNomeDaoIrmaoOuIrma.setText("Nome da Organista");

		cmbTipoMinisterio.removeAllItems();
		Preenche.comboTipoMinisterio(cmbTipoMinisterio, dcbmTipoMinisterio, 7);
		cmbTipoMinisterio.setSelectedIndex(6);
		cmbTipoMinisterio.setEnabled(false);
		IdTipoPessoa = "7";
		camposTrataEnter.remove(cmbTipoMinisterio);

		JPanel panel = new JPanel();
		panel.setBounds(22, 325, 746, 138);
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabCadastro.add(panel);
		panel.setLayout(null);

		chkDomingo = new JCheckBox("Domingo");
		chkDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		chkDomingo.setName("1");
		chkDomingo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				trataChkBox(chkDomingo);
			}
		});
		chkDomingo.setBounds(17, 25, 172, 18);
		panel.add(chkDomingo);

		chkSegunda = new JCheckBox("Segunda");
		chkSegunda.setName("2");
		chkSegunda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				trataChkBox(chkSegunda);
			}
		});
		chkSegunda.setBounds(212, 25, 84, 18);
		panel.add(chkSegunda);

		chkTerca = new JCheckBox("Ter\u00E7a");
		chkTerca.setName("3");
		chkTerca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				trataChkBox(chkTerca);
			}
		});
		chkTerca.setBounds(300, 25, 84, 18);
		panel.add(chkTerca);

		chkQuarta = new JCheckBox("Quarta");
		chkQuarta.setName("4");
		chkQuarta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				trataChkBox(chkQuarta);
			}
		});
		chkQuarta.setBounds(386, 25, 84, 18);
		panel.add(chkQuarta);

		chkQuinta = new JCheckBox("Quinta");
		chkQuinta.setName("5");
		chkQuinta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				trataChkBox(chkQuinta);
			}
		});
		chkQuinta.setBounds(482, 25, 84, 18);
		panel.add(chkQuinta);

		chkSexta = new JCheckBox("Sexta");
		chkSexta.setName("6");
		chkSexta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				trataChkBox(chkSexta);
			}
		});
		chkSexta.setBounds(567, 25, 84, 18);
		panel.add(chkSexta);

		chkSabado = new JCheckBox("S\u00E1bado");
		chkSabado.setName("7");
		chkSabado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				trataChkBox(chkSabado);
			}
		});
		chkSabado.setBounds(655, 25, 84, 18);
		panel.add(chkSabado);

		chkMHDom = new JCheckBox("Meia Hora");
		chkMHDom.setName("1");
		chkMHDom.setBounds(17, 49, 89, 18);
		panel.add(chkMHDom);

		chkPrimParteDom = new JCheckBox("1\u00AA Parte");
		chkPrimParteDom.setName("2");
		chkPrimParteDom.setBounds(17, 72, 84, 18);
		panel.add(chkPrimParteDom);

		chkSegParteDom = new JCheckBox("2\u00AA Parte");
		chkSegParteDom.setName("3");
		chkSegParteDom.setBounds(17, 93, 84, 18);
		panel.add(chkSegParteDom);

		chkMHSegunda = new JCheckBox("Meia Hora");
		chkMHSegunda.setName("1");
		chkMHSegunda.setBounds(212, 49, 89, 18);
		panel.add(chkMHSegunda);

		chkPrimParteSegunda = new JCheckBox("1\u00AA Parte");
		chkPrimParteSegunda.setName("2");
		chkPrimParteSegunda.setBounds(212, 72, 89, 18);
		panel.add(chkPrimParteSegunda);

		chkSegParteSegunda = new JCheckBox("2\u00AA Parte");
		chkSegParteSegunda.setName("3");
		chkSegParteSegunda.setBounds(212, 93, 89, 18);
		panel.add(chkSegParteSegunda);

		chkMHTerca = new JCheckBox("Meia Hora");
		chkMHTerca.setName("1");
		chkMHTerca.setBounds(300, 49, 89, 18);
		panel.add(chkMHTerca);

		chkPrimParteTerca = new JCheckBox("1\u00AA Parte");
		chkPrimParteTerca.setName("2");
		chkPrimParteTerca.setBounds(300, 72, 89, 18);
		panel.add(chkPrimParteTerca);

		chkSegParteTerca = new JCheckBox("2\u00AA Parte");
		chkSegParteTerca.setName("3");
		chkSegParteTerca.setBounds(300, 93, 89, 18);
		panel.add(chkSegParteTerca);

		chkMHQuarta = new JCheckBox("Meia Hora");
		chkMHQuarta.setName("1");
		chkMHQuarta.setBounds(386, 49, 89, 18);
		panel.add(chkMHQuarta);

		chkPrimParteQuarta = new JCheckBox("1\u00AA Parte");
		chkPrimParteQuarta.setName("2");
		chkPrimParteQuarta.setBounds(386, 72, 89, 18);
		panel.add(chkPrimParteQuarta);

		chkSegParteQuarta = new JCheckBox("2\u00AA Parte");
		chkSegParteQuarta.setName("3");
		chkSegParteQuarta.setBounds(386, 93, 89, 18);
		panel.add(chkSegParteQuarta);

		chkMHQuinta = new JCheckBox("Meia Hora");
		chkMHQuinta.setName("1");
		chkMHQuinta.setBounds(482, 49, 89, 18);
		panel.add(chkMHQuinta);

		chkPrimParteQuinta = new JCheckBox("1\u00AA Parte");
		chkPrimParteQuinta.setName("2");
		chkPrimParteQuinta.setBounds(482, 72, 89, 18);
		panel.add(chkPrimParteQuinta);

		chkSegParteQuinta = new JCheckBox("2\u00AA Parte");
		chkSegParteQuinta.setName("3");
		chkSegParteQuinta.setBounds(482, 93, 89, 18);
		panel.add(chkSegParteQuinta);

		chkMHSexta = new JCheckBox("Meia Hora");
		chkMHSexta.setName("1");
		chkMHSexta.setBounds(567, 49, 89, 18);
		panel.add(chkMHSexta);

		chkPrimParteSexta = new JCheckBox("1\u00AA Parte");
		chkPrimParteSexta.setName("2");
		chkPrimParteSexta.setBounds(567, 72, 89, 18);
		panel.add(chkPrimParteSexta);

		chkSegParteSexta = new JCheckBox("2\u00AA Parte");
		chkSegParteSexta.setName("3");
		chkSegParteSexta.setBounds(567, 93, 89, 18);
		panel.add(chkSegParteSexta);

		chkMHSabado = new JCheckBox("Meia Hora");
		chkMHSabado.setName("1");
		chkMHSabado.setBounds(655, 49, 89, 18);
		panel.add(chkMHSabado);

		chkPrimParteSabado = new JCheckBox("1\u00AA Parte");
		chkPrimParteSabado.setName("2");
		chkPrimParteSabado.setBounds(655, 72, 89, 18);
		panel.add(chkPrimParteSabado);

		chkSegParteSabado = new JCheckBox("2\u00AA Parte");
		chkSegParteSabado.setName("3");
		chkSegParteSabado.setBounds(655, 93, 89, 18);
		panel.add(chkSegParteSabado);

		chkPrimParteDomRJM = new JCheckBox("1\u00AA Parte RJM");
		chkPrimParteDomRJM.setName("4");
		chkPrimParteDomRJM.setBounds(99, 72, 100, 18);
		panel.add(chkPrimParteDomRJM);

		chkSegParteDomRJM = new JCheckBox("2\u00AA Parte RJM");
		chkSegParteDomRJM.setName("5");
		chkSegParteDomRJM.setBounds(99, 93, 100, 18);
		panel.add(chkSegParteDomRJM);

		/**
		 * CkeckBoxes culto Especial.
		 */
		chkCultoEspecial = new JCheckBox("Culto Especial");
		chkCultoEspecial.setName("8");
		chkCultoEspecial.setBounds(310, 113, 105, 18);
		panel.add(chkCultoEspecial);

		chkMHEspecial = new JCheckBox();
		chkMHEspecial.setName("1");
		panel.add(chkMHEspecial);
		chkPrimParteEspecial = new JCheckBox();
		chkPrimParteEspecial.setName("2");
		panel.add(chkPrimParteEspecial);
		chkSegParteEspecial = new JCheckBox();
		chkSegParteEspecial.setName("3");
		panel.add(chkSegParteEspecial);

		JLabel lblNewLabel = new JLabel("Disponibilidade");
		lblNewLabel.setBounds(0, 0, 746, 24);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		preencheListasCheckBox();
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				trataBotoes(cancelarOuExcluir);
				trataCampos(disable);
				inicializaCheckBox();
				removeTab();
			}
		});
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eInsert = false;
				eDelete = true;
				salvarForm();
				trataBotoes(cancelarOuExcluir);
				trataCampos(disable);
				limpaTela();
				inicializaCheckBox();
			}
		});
		btnIncluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eInsert = true;
				trataBotoes(incluir);
				trataCampos(1);
				inicializaCheckBox();
			}
		});
		btnCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() > -1) {
					trataBotoes(carregar);
					trataCampos(disable);
					try {
						controleOrganistas = new ControleOrganista();
					} catch (Exception e) {
						e.printStackTrace();
					}
					preencheFormulario(controleOrganistas.selectID(datmPessoa.get(table.getSelectedRow()).getId_pessoa()));
					removeTab();

				} else {
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha da tabela.");
				}
			}
		});
	}

	public void preencheListasCheckBox() {

		listaDiasCheckBox.add(chkDomingo);
		listaPartesDomingo.add(chkMHDom);
		listaPartesDomingo.add(chkPrimParteDom);
		listaPartesDomingo.add(chkSegParteDom);
		listaPartesDomingo.add(chkPrimParteDomRJM);
		listaPartesDomingo.add(chkSegParteDomRJM);

		listaDiasCheckBox.add(chkSegunda);
		listaPartesSegunda.add(chkMHSegunda);
		listaPartesSegunda.add(chkPrimParteSegunda);
		listaPartesSegunda.add(chkSegParteSegunda);

		listaDiasCheckBox.add(chkTerca);
		listaPartesTerca.add(chkMHTerca);
		listaPartesTerca.add(chkPrimParteTerca);
		listaPartesTerca.add(chkSegParteTerca);

		listaDiasCheckBox.add(chkQuarta);
		listaPartesQuarta.add(chkMHQuarta);
		listaPartesQuarta.add(chkPrimParteQuarta);
		listaPartesQuarta.add(chkSegParteQuarta);

		listaDiasCheckBox.add(chkQuinta);
		listaPartesQuinta.add(chkMHQuinta);
		listaPartesQuinta.add(chkPrimParteQuinta);
		listaPartesQuinta.add(chkSegParteQuinta);

		listaDiasCheckBox.add(chkSexta);
		listaPartesSexta.add(chkMHSexta);
		listaPartesSexta.add(chkPrimParteSexta);
		listaPartesSexta.add(chkSegParteSexta);

		listaDiasCheckBox.add(chkSabado);
		listaPartesSabado.add(chkMHSabado);
		listaPartesSabado.add(chkPrimParteSabado);
		listaPartesSabado.add(chkSegParteSabado);

		listaDiasCheckBox.add(chkCultoEspecial);
		listaPartesEspecial.add(chkMHEspecial);
		listaPartesEspecial.add(chkPrimParteEspecial);
		listaPartesEspecial.add(chkSegParteEspecial);

		mapChexkbox.put(chkDomingo, listaPartesDomingo);
		mapChexkbox.put(chkSegunda, listaPartesSegunda);
		mapChexkbox.put(chkTerca, listaPartesTerca);
		mapChexkbox.put(chkQuarta, listaPartesQuarta);
		mapChexkbox.put(chkQuinta, listaPartesQuinta);
		mapChexkbox.put(chkSexta, listaPartesSexta);
		mapChexkbox.put(chkSabado, listaPartesSabado);
		mapChexkbox.put(chkCultoEspecial, listaPartesEspecial);

		inicializaCheckBox();
		trataEnterFilha();

	}

	public void trataEnterFilha() {

		for (Component campo : camposTrataEnter) {
			TrataEnter.acionaEnter(campo);
		}
	}

	@Override
	protected void salvarForm() {

		pessoa = new ModOrganistas();

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

		pessoa.setId_IgrejaComum(txtIdIgreja.getText());

		pessoa.setNome_pessoa(txtNomePessoa.getText());
		pessoa.setRecebeEmail(rdButtonEnviaEmail.isSelected());
		// E-Mails
		pessoa.setListaEmailPessoa(preencheListaEmails());
		// Telefones
		pessoa.setListaTelPessoa(preencheListaTels());
		// Disponibilidades
		pessoa.setListaDisponibilidades(preencheListaDisponibilidade());

		try {
			new ControleOrganista(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
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

	@Override
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

	private List<ModDisponibilidade> preencheListaDisponibilidade() {

		List<ModDisponibilidade> lista = new ArrayList<>();

		for (JCheckBox chkTratar : listaDiasCheckBox) {
			System.out.println(chkTratar.getName());
			if (chkTratar.isSelected()) {
				ModDisponibilidade disp = new ModDisponibilidade();
				disp.setDiaCulto(Integer.parseInt(chkTratar.getName()));
				for (JCheckBox chk : mapChexkbox.get(chkTratar)) {
					switch (Integer.parseInt(chk.getName())) {
						case 1:// Meia Hora
							disp.setMeiaHora(chk.isSelected());
							break;
						case 2:// Prim. Parte
							disp.setPrimParte(chk.isSelected());
							break;
						case 3:// Seg. Parte
							disp.setSegParte(chk.isSelected());
							break;
						case 4:// Prim Parte RJM
							disp.setPrimParteRJM(chk.isSelected());
							break;
						case 5:// Seg Parte RJM
							disp.setSegParteRJM(chk.isSelected());
							break;
					}
				}
				lista.add(disp);
			}
		}
		return lista;
	}

	private void trataChkBox(JCheckBox chkTratar) {

		if (chkTratar.isSelected()) {
			for (JCheckBox chk : mapChexkbox.get(chkTratar)) {
				chk.setEnabled(true);
			}

		} else {
			for (JCheckBox chk : mapChexkbox.get(chkTratar)) {
				chk.setEnabled(false);
			}
		}
	}

	private void inicializaCheckBox() {

		for (JCheckBox chkTratar : listaDiasCheckBox) {
			chkTratar.setSelected(false);
			chkTratar.setEnabled(false);
			for (JCheckBox chk : mapChexkbox.get(chkTratar)) {
				chk.setEnabled(false);
				chk.setSelected(false);
			}
		}
		// Como estes Checkboxes nï¿½o sï¿½o exibidos, todos estarï¿½o selecionados por
		// default.
		chkMHEspecial.setSelected(true);
		chkPrimParteEspecial.setSelected(true);
		chkSegParteEspecial.setSelected(true);

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
				// System.out.println(dia.getDiaCulto()+" - "+chkTratar.getName());
				if (dia.getDiaCulto().compareTo(chkTratar.getName()) == 0) {
					chkTratar.setSelected(false);
					chkTratar.setEnabled(true);
				}
			}
		}
		// Todas as igrejas podem ter culto especial.
		chkCultoEspecial.setEnabled(true);

		repaint();
	}

	private void preencheFormulario(ModOrganistas p) {
		limpaTela();
		inicializaCheckBox();
		txtIdPessoa.setText(p.getId_pessoa());
		txtNomePessoa.setText(p.getNome_pessoa());
		rdButtonEnviaEmail.setSelected(p.getRecebeEmail());

		preencheDadosComum(p.getId_Igreja());
		preenchePainelTelefones(p.getListaTelPessoa());
		preenchePainelEmail(p.getListaEmailPessoa());
		inicializaCheckBox(p.getId_Igreja());
		preenchePainelDisponibilidades(p.getListaDisponibilidades());

	}

	private void preenchePainelDisponibilidades(List<ModDisponibilidade> listaDispon) {

		for (ModDisponibilidade disp : listaDispon) {
			switch (disp.getDiaCulto()) {
				case 1:// domingo
					for (JCheckBox chk : mapChexkbox.get(chkDomingo)) {
						chkDomingo.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
							case 4:// Prim. Parte
								chk.setSelected(disp.isPrimParteRJM());
								break;
							case 5:// Seg. Parte
								chk.setSelected(disp.isSegParteRJM());
								break;
						}
					}
					break;
				case 2:// segunda
					for (JCheckBox chk : mapChexkbox.get(chkSegunda)) {
						chkSegunda.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
						}
					}
					break;
				case 3:// terï¿½a
					for (JCheckBox chk : mapChexkbox.get(chkTerca)) {
						chkTerca.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
						}
					}
					break;
				case 4:// quarta
					for (JCheckBox chk : mapChexkbox.get(chkQuarta)) {
						chkQuarta.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
						}
					}
					break;
				case 5:// quinta
					for (JCheckBox chk : mapChexkbox.get(chkQuinta)) {
						chkQuinta.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
						}
					}
					break;
				case 6:// sexta
					for (JCheckBox chk : mapChexkbox.get(chkSexta)) {
						chkSexta.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
						}
					}
					break;
				case 7:// sabado
					for (JCheckBox chk : mapChexkbox.get(chkSabado)) {
						chkSabado.setSelected(true);
						switch (Integer.parseInt(chk.getName())) {
							case 1:// Meia Hora
								chk.setSelected(disp.isMeiaHora());
								break;
							case 2:// Prim. Parte
								chk.setSelected(disp.isPrimParte());
								break;
							case 3:// Seg. Parte
								chk.setSelected(disp.isSegParte());
								break;
						}
					}
					break;
			}
		}

	}

	@Override
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

	@Override
	protected boolean validaFormulario() {
		for (JTextField campoTxt : listaCamposTexto) {
			if (campoTxt.getText().isEmpty() && campoTxt.getName().compareTo("ID") != 0) {
				JOptionPane.showMessageDialog(campoTxt, "O Campo: \"" + campoTxt.getName() + "\" deve ser preenchido.");
				campoTxt.grabFocus();
				return false;
			}
		}
		boolean ok = false;
		for (JCheckBox campoCheckBox : listaDiasCheckBox) {
			if (campoCheckBox.isSelected()) {
				ok = true;
			}
		}
		if (!ok) {
			JOptionPane.showMessageDialog(chkDomingo, "Ao menos um dia de Culto deve estar Selecionado.");
			return false;
		}
		return true;
	}
}
