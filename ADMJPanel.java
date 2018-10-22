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
	private Conta session_atual;
	
	public void setSession(Conta c) { session_atual = c; }
	public Conta getSession() { return session_atual; }
	
	public ADMJPanel(JFrame ctrl) {
		controlling = ctrl;
		
		JLabel bemVindo = new JLabel("Bem vindo(a) Sr(a). " + Banco.master_adm.getNome());
		bemVindo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 1)); //Infinitas linhas, uma coluna
		
		JPanel buttons = new JPanel(new GridLayout(3, 2, 0, 10)); //3 linhas, 2 colunas, espacamento 0x10
		//buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		buttons.setOpaque(true);
		
		bNovaConta = new JButton("Criar nova conta");
		bNovaConta.setActionCommand("adm_nova_conta");
		bNovaConta.setMnemonic(KeyEvent.VK_S);
		bNovaConta.addActionListener(this);
		bNovaConta.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bVisualizar = new JButton("Visualizar conta");
		bVisualizar.setActionCommand("adm_visualizar_conta");
		bVisualizar.setMnemonic(KeyEvent.VK_D);
		bVisualizar.addActionListener(this);
		bVisualizar.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bRendimentos = new JButton("Incrementar rendimentos");
		bRendimentos.setActionCommand("adm_incr_rendimentos");
		bRendimentos.setMnemonic(KeyEvent.VK_I);
		bRendimentos.addActionListener(this);
		bRendimentos.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bJuros = new JButton("Cobrar juros");
		bJuros.setActionCommand("adm_cobrar_juros");
		bJuros.setMnemonic(KeyEvent.VK_A);
		bJuros.addActionListener(this);
		bJuros.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		bVisualizarTodas = new JButton("Visualizar todas as contas");
		bVisualizarTodas.setActionCommand("adm_visualizar_todas");
		bVisualizarTodas.setMnemonic();
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
		buttons.add(bLogout);
		container.add(buttons);
		//add(bemVindo);
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if ("adm_nova_conta".equals(action)) {
			Banco.master_adm.criarNovaConta();
		} else if ("adm_visualizar_conta".equals(action)) {
			try {
				String input = JOptionPane.showInputDialog("Digite o valor para deposito", "0.00");
				int id = Integer.parseInt(input);
				
			}
			Conta c = Banco.findByID(id);
		}
	}
}
