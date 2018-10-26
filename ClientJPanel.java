import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.lang.NumberFormatException;
import java.lang.NullPointerException;

public class ClientJPanel extends Painel implements ActionListener {
	
	//protected JFrame controlling;
	protected JButton bSaque, bDeposito, bInfo, bSenha, bLogout;
	protected JLabel bemVindo;
	
	//private Banco b;
	//public void setBancoInstance() { b = Banco.getInstance(); }
	
	//public void setSession(Conta c) { session_atual = c; }
	//public Conta getSession() { return session_atual; }
	
	public ClientJPanel() {
		//controlling = ctrl;
		//setSession(c);
		
		if (Banco.testInstance()) { bemVindo = new JLabel("Bem vindo(a) Sr(a). " + b.client_getNome()); } else { bemVindo = new JLabel("---"); }
		bemVindo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 1)); //Infinitas linhas, uma coluna
		
		JPanel buttons = new JPanel(new GridLayout(5, 1, 0, 10)); //5 linhas, uma coluna, espacamento 0x10
		buttons.setOpaque(true);
		
		bSaque = new JButton("Realizar Saque");
		bSaque.setActionCommand("sacar");
		bSaque.setMnemonic(KeyEvent.VK_S);
		bSaque.addActionListener(this);
		bSaque.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bDeposito = new JButton("Realizar Deposito");
		bDeposito.setActionCommand("depositar");
		bDeposito.setMnemonic(KeyEvent.VK_D);
		bDeposito.addActionListener(this);
		bDeposito.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bInfo = new JButton("Visualizar Informacoes");
		bInfo.setActionCommand("info");
		bInfo.setMnemonic(KeyEvent.VK_I);
		bInfo.addActionListener(this);
		bInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bSenha = new JButton("Alterar Senha");
		bSenha.setActionCommand("senha");
		bSenha.setMnemonic(KeyEvent.VK_A);
		bSenha.addActionListener(this);
		bSenha.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bLogout = new JButton("Desconectar");
		bLogout.setActionCommand("logout");
		bLogout.setMnemonic(KeyEvent.VK_D);
		bLogout.addActionListener(this);
		bLogout.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		container.add(bemVindo);
		buttons.add(bSaque);
		buttons.add(bDeposito);
		buttons.add(bInfo);
		buttons.add(bSenha);
		buttons.add(bLogout);
		container.add(buttons);
		
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		b = Banco.getInstance();
		if ("sacar".equals(e.getActionCommand())) {
			String input = JOptionPane.showInputDialog("Digite o valor para saque", "0.00");
			double val;
			try {
				val = Double.parseDouble(input);
				//session_atual.sacar(val);
				b.client_sacar(val);
				JOptionPane.showMessageDialog(null, "Saque realizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch ( NumberFormatException ne ) {
				JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
			} catch (SaldoInvalidoException se) {
				JOptionPane.showMessageDialog(null, se.to_string(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (ValorInvalidoException ve) {
				JOptionPane.showMessageDialog(null, ve.to_string(), "Erro", JOptionPane.WARNING_MESSAGE);
			} catch (NoActiveSessionException ns) {
				JOptionPane.showMessageDialog(null, ns.to_string(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch ( NullPointerException npe ) { }
			
		} else if ("depositar".equals(e.getActionCommand())) {
			String input = JOptionPane.showInputDialog("Digite o valor para deposito", "0.00");
			double val;
			try {
				val = Double.parseDouble(input);
				//session_atual.depositar(val);
				b.client_depositar(val);
				JOptionPane.showMessageDialog(null, "Deposito realizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch ( NumberFormatException ne ) {
				JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
			} catch (NoActiveSessionException ns) {
				JOptionPane.showMessageDialog(null, ns.to_string(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch ( NullPointerException npe ) { }
			
		} else if ("info".equals(e.getActionCommand())) {
			try {
				JOptionPane.showMessageDialog(null, b.client_to_string(), "Informacoes", JOptionPane.INFORMATION_MESSAGE);
			} catch (NoActiveSessionException ns) {
				JOptionPane.showMessageDialog(null, ns.to_string(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} else if ("senha".equals(e.getActionCommand())) {
			String senhaAntiga = JOptionPane.showInputDialog("Digite a senha antiga", "senha antiga");
			try {
				//if (session_atual.comparaSenha(senhaAntiga)) {
				if (b.client_comparaSenha(senhaAntiga)) {
					String senhaNova = JOptionPane.showInputDialog("Digite a nova senha", "senha nova");
					//session_atual.setSenha(senhaNova);
					b.client_alterarSenha(senhaAntiga, senhaNova);
					JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Senha atual diferente!", "Erro", JOptionPane.ERROR_MESSAGE); 
				}
			} catch (NoActiveSessionException ns) {
				JOptionPane.showMessageDialog(null, ns.to_string(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException npe) { }
			
		} else if ("logout".equals(e.getActionCommand())) {
			//setSession(null);
			b.client_logOut();
			b.reconfigContentPane(Banco.MAIN);
			//MainJPanel screen = new MainJPanel(controlling);
			//Banco.reconfigContentPane(screen);
		}
	}
	
	public void on_update() { 
		b = Banco.getInstance();
		bemVindo.setText("Bem vindo(a) Sr(a). " + b.client_getNome());
	}
}
