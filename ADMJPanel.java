import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;

import java.lang.NumberFormatException;
import java.lang.NullPointerException;

public class ADMJPanel extends JPanel implements ActionListener {
	
	protected JFrame controlling;
	
	protected JButton bNovaConta, bVisualizar, bRendimentos, bJuros, bVisualizarTodas, bLogout;
	
	public ADMJPanel(JFrame ctrl) {
		controlling = ctrl;
		
		JLabel bemVindo = new JLabel("Bem vindo(a) Sr(a). " + Banco.getADM().getNome());
		bemVindo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 1)); //Infinitas linhas, uma coluna
		
		JPanel buttons = new JPanel(new GridLayout(0, 1, 0, 10)); //Infinitas linhas, 1 coluna, espacamento 0x10
		//buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
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
		//add(bemVindo);
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if ("adm_nova_conta".equals(action)) {
			//Banco.getADM().criarNovaConta();
			ADMNovaContaJPanel screen = new ADMNovaContaJPanel(controlling, Banco.getADM());
			Banco.reconfigContentPane(screen);
		} else if ("adm_visualizar_todas".equals(action)) {
			String[] contas_strings = Banco.getADM().getContasString();
			ADMVisualizarJPanel screen = new ADMVisualizarJPanel(controlling, contas_strings, Banco.getNumero_contas());
			Banco.reconfigContentPane(screen);
		} else if ("adm_visualizar_conta".equals(action)) {
			//try {
				String input = JOptionPane.showInputDialog("Digite o codigo para consulta", "0000");
				try {
					int id = Integer.parseInt(input);
					Conta c = Banco.findByID(id);
					JOptionPane.showMessageDialog(null, c.to_string(), "Informacoes", JOptionPane.INFORMATION_MESSAGE);
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "Conta nao encontrada...", "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException ie) {
					JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
				}
			//}
		} else if ("adm_logout".equals(action)) {
			Banco.getADM().logOut();
			MainJPanel screen = new MainJPanel(controlling);
			Banco.reconfigContentPane(screen);
		}
	}
}
