import java.awt.FlowLayout;
import java.awt.*;
/*import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
*/
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;

import java.lang.NumberFormatException;
import java.lang.NullPointerException;

public class ADMNovaContaJPanel extends JPanel implements ActionListener {
	
	protected JFrame controlling;
	
	protected JLabel lNumero, lNome, lLimite, lJuros;
	protected JTextField tNumero, tNome, tLimite, tJuros;
	protected JButton bConfirma, bCancela;
	protected JRadioButton sSimples, sEspecial, sPoupanca;
	protected ButtonGroup tipo_conta_group = new ButtonGroup();
	
	private int config_tipo = 0;
	
	private Adm a;
	
	public ADMNovaContaJPanel(JFrame ctrl, Adm adm) {
		this.a = adm;
		controlling = ctrl;
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//=================== SELECAO TIPO DE CONTA ===================
		JPanel seletor = new JPanel();
		seletor.setLayout(new BoxLayout(seletor, BoxLayout.X_AXIS));
		
		sSimples = new JRadioButton("Conta Simples");
        sSimples.setMnemonic(KeyEvent.VK_S);
        sSimples.setActionCommand("config_conta_simples");
		sSimples.addActionListener(this);
		
		sEspecial = new JRadioButton("Conta Especial");
        sEspecial.setMnemonic(KeyEvent.VK_E);
        sEspecial.setActionCommand("config_conta_especial");
		sEspecial.addActionListener(this);
		
		sPoupanca = new JRadioButton("Conta Poupanca");
        sPoupanca.setMnemonic(KeyEvent.VK_P);
        sPoupanca.setActionCommand("config_conta_poupanca");
		sPoupanca.addActionListener(this);
		
		tipo_conta_group.add(sSimples);
		tipo_conta_group.add(sEspecial);
		tipo_conta_group.add(sPoupanca);
		
		sSimples.setSelected(true);
		
		seletor.add(sSimples);
		seletor.add(sEspecial);
		seletor.add(sPoupanca);
		
		container.add(seletor);
		
		TitledBorder borda;
		
		//=================== CONTA SIMPLES ===================
		JPanel panelSimples = new JPanel(new GridLayout(2, 2));
		
		lNumero = new JLabel("Numero:");
		tNumero = new JTextField(10);
		tNumero.setHorizontalAlignment(JTextField.CENTER);
		
		lNome = new JLabel("Nome:");
		tNome = new JTextField(10);
		tNome.setHorizontalAlignment(JTextField.CENTER);
		
		panelSimples.add(lNumero);
		panelSimples.add(tNumero);
		panelSimples.add(lNome);
		panelSimples.add(tNome);
		
		borda = BorderFactory.createTitledBorder("Informacoes basicas");
		panelSimples.setBorder(borda);
		
		container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(panelSimples);
		
		//=================== CONTA ESPECIAL ===================
		JPanel panelEspecial = new JPanel(new GridLayout(1, 2));
		
		lLimite = new JLabel("Limite:");
		tLimite = new JTextField(10);
		tLimite.setHorizontalAlignment(JTextField.CENTER);
		tLimite.setEditable(false);
		
		panelEspecial.add(lLimite);
		panelEspecial.add(tLimite);
		
		borda = BorderFactory.createTitledBorder("Conta especial");
		panelEspecial.setBorder(borda);
		
		container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(panelEspecial);
		
		//=================== CONTA POUPANCA ===================
		JPanel panelPoupanca = new JPanel(new GridLayout(1, 2));
		
		lJuros = new JLabel("Juros:");
		tJuros = new JTextField(10);
		tJuros.setHorizontalAlignment(JTextField.CENTER);
		tJuros.setEditable(false);
		
		panelPoupanca.add(lJuros);
		panelPoupanca.add(tJuros);
		
		borda = BorderFactory.createTitledBorder("Conta poupanca");
		panelPoupanca.setBorder(borda);
		
		container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(panelPoupanca);
		
		//=================== CONFIRMA/CANCELA ===================
		JPanel panelBotoes = new JPanel(new GridLayout(1, 2));
		
		bConfirma = new JButton("Confirma");
        bConfirma.setMnemonic(KeyEvent.VK_C);
        bConfirma.setActionCommand("config_confirma");
		bConfirma.addActionListener(this);
		
		bCancela = new JButton("Cancela");
        bCancela.setMnemonic(KeyEvent.VK_X);
        bCancela.setActionCommand("config_cancela");
		bCancela.addActionListener(this);
		
		panelBotoes.add(bConfirma);
		panelBotoes.add(bCancela);
		
		container.add(panelBotoes);
		//ADD
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("config_conta_simples")) {
			config_tipo = 0;
			tLimite.setEditable(false);
			tJuros.setEditable(false);
			System.out.println("Disabling all");
		} else if (action.equals("config_conta_especial")) {
			config_tipo = 1;
			tLimite.setEditable(true);
			tJuros.setEditable(false);
			System.out.println("Disabling 1");
		} else if (action.equals("config_conta_poupanca")) {
			config_tipo = 2;
			tLimite.setEditable(false);
			tJuros.setEditable(true);
			System.out.println("Disabling 2");
			
		} else if (action.equals("config_confirma")) {
			int numero;
			String nome;
			double limite;
			double juros;
			if (config_tipo == 0) {
				try {
					nome = tNome.getText();
					numero = Integer.parseInt(tNumero.getText());
					Conta t = a.criarNovaContaSimples(numero, nome);
					if (t != null) {
						JOptionPane.showMessageDialog(null, "Sucesso ao criar a conta", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						ADMJPanel screen = new ADMJPanel(controlling);
						Banco.reconfigContentPane(screen);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao criar a conta", "Erro", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException ie) {
					JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} else if (action.equals("config_cancela")) {
			ADMJPanel screen = new ADMJPanel(controlling);
			Banco.reconfigContentPane(screen);
		}
	}
}
