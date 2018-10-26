import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.lang.NumberFormatException;
import java.lang.NullPointerException;

public class ADMJPanel extends Painel implements ActionListener {
	
	//protected JFrame controlling;
	
	protected JButton bNovaConta, bVisualizar, bRendimentos, bJuros, bVisualizarTodas, bLogout;
	protected JLabel bemVindo;
	
	//private Banco b;
	//public void setBancoInstance() { b = Banco.getInstance(); }
	
	public ADMJPanel() {
		//controlling = ctrl;
		
		if (Banco.testInstance()) { bemVindo = new JLabel("Bem vindo(a) Sr(a). " + b.adm_getNome()); } else { bemVindo = new JLabel("---"); }
		bemVindo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 1)); //Infinitas linhas, uma coluna
		
		JPanel buttons = new JPanel(new GridLayout(0, 1, 0, 10)); //Infinitas linhas, 1 coluna, espacamento 0x10
		buttons.setOpaque(true);
		
		bNovaConta = new JButton("Criar nova conta");
		bNovaConta.setActionCommand("adm_nova_conta");
		bNovaConta.setMnemonic(KeyEvent.VK_N);
		bNovaConta.addActionListener(this);
		bNovaConta.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bVisualizar = new JButton("Visualizar conta");
		bVisualizar.setActionCommand("adm_visualizar_conta");
		bVisualizar.setMnemonic(KeyEvent.VK_V);
		bVisualizar.addActionListener(this);
		bVisualizar.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bRendimentos = new JButton("Incrementar rendimentos");
		bRendimentos.setActionCommand("adm_incr_rendimentos");
		bRendimentos.setMnemonic(KeyEvent.VK_R);
		bRendimentos.addActionListener(this);
		bRendimentos.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bJuros = new JButton("Cobrar juros");
		bJuros.setActionCommand("adm_cobrar_juros");
		bJuros.setMnemonic(KeyEvent.VK_J);
		bJuros.addActionListener(this);
		bJuros.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bVisualizarTodas = new JButton("Visualizar todas as contas");
		bVisualizarTodas.setActionCommand("adm_visualizar_todas");
		bVisualizarTodas.setMnemonic(KeyEvent.VK_T);
		bVisualizarTodas.addActionListener(this);
		bVisualizarTodas.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bLogout = new JButton("Desconectar");
		bLogout.setActionCommand("adm_logout");
		bLogout.setMnemonic(KeyEvent.VK_D);
		bLogout.addActionListener(this);
		bLogout.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		container.add(bemVindo);
		buttons.add(bNovaConta);
		buttons.add(bVisualizar);
		buttons.add(bRendimentos);
		buttons.add(bJuros);
		buttons.add(bVisualizarTodas);
		buttons.add(bLogout);
		container.add(buttons);
		
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		b = Banco.getInstance();
		String action = e.getActionCommand();
		if ("adm_nova_conta".equals(action)) {
			//ADMNovaContaJPanel screen = new ADMNovaContaJPanel(controlling, Banco.getADM());
			//Banco.reconfigContentPane(screen);
			b.reconfigContentPane(Banco.ADM_NOVA_CONTA);
			
		} else if ("adm_visualizar_todas".equals(action)) {
			//String[] contas_strings = Banco.getADM().getContasString();
			//ADMVisualizarJPanel screen = new ADMVisualizarJPanel(controlling, contas_strings, Banco.getNumero_contas());
			//Banco.reconfigContentPane(screen);
			b.reconfigContentPane(Banco.ADM_VISUALIZAR);
			
			
		} else if ("adm_visualizar_conta".equals(action)) {
			try {
				String input = JOptionPane.showInputDialog("Digite o codigo para consulta", "0000");
				int id = Integer.parseInt(input);
				//Conta c = Banco.findByID(id);
				JOptionPane.showMessageDialog(null, b.adm_client_to_string(id), "Informacoes", JOptionPane.INFORMATION_MESSAGE);
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(null, "Conta nao encontrada...", "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException ie) {
				JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
			}
			
		} else if ("adm_incr_rendimentos".equals(action)) {
			//Banco.getADM().incrementarRendimentos();
			b.adm_incrementarRendimentos();
			JOptionPane.showMessageDialog(null, "Rendimentos incrementados com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
		} else if ("adm_cobrar_juros".equals(action)) {
			try {
				String input = JOptionPane.showInputDialog("Digite a porcentagem para cobranca de juros", "0.0");
				double j = Double.parseDouble(input);
				
				if (j <= 0.0) { throw new ValorInvalidoException(); }
				
				//int cobrados = Banco.getADM().cobrarJuros(j);
				int cobrados = b.adm_cobrarJuros(j);
				if (cobrados == 0) {
					JOptionPane.showMessageDialog(null, "Nenhuma conta estava em divida", "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					char plural = (cobrados == 1 ? '\0' : 's');
					JOptionPane.showMessageDialog(null, String.format("Juros cobrados com sucesso (%d conta%c debitada%c)", cobrados, plural, plural), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (NumberFormatException ie) {
				JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
			} catch (ValorInvalidoException vie) {
				JOptionPane.showMessageDialog(null, "Favor digitar um valor valido (positivo)!", "Erro", JOptionPane.ERROR_MESSAGE); 
			}
			
		} else if ("adm_logout".equals(action)) {
			//Banco.getADM().logOut();
			b.adm_logOut();
			//MainJPanel screen = new MainJPanel(controlling);
			//Banco.reconfigContentPane(screen);
			b.reconfigContentPane(Banco.MAIN);
		}
	}
	
	public void on_update() { 
		b = Banco.getInstance();
		bemVindo.setText("Bem vindo(a) Sr(a). " + b.adm_getNome());
	}
}
