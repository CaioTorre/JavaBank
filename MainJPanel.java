import java.awt.*;
import javax.swing.*;
//import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainJPanel extends Painel implements ActionListener {
	
	protected JButton bCliente, bGerente, bSair;
	
	public MainJPanel() {
		Font f = new Font("SansSerif", Font.PLAIN, 28);
		//Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel inside = new JPanel();
		inside.setLayout(new GridLayout(0, 1, 0, 10));
		
		bCliente = new JButton("Login Cliente");
		bCliente.setActionCommand("log_cliente");
		bCliente.setMnemonic(KeyEvent.VK_C);
		bCliente.addActionListener(this);
		bCliente.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		bCliente.setFont(f);
		
		bGerente = new JButton("Login Gerente");
		bGerente.setActionCommand("log_gerente");
		bGerente.setMnemonic(KeyEvent.VK_G);
		bGerente.addActionListener(this);
		bGerente.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		bGerente.setFont(f);
		
		bSair = new JButton("Sair");
		bSair.setActionCommand("exit_prog");
		bSair.setMnemonic(KeyEvent.VK_S);
		bSair.addActionListener(this);
		bSair.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		bSair.setFont(f);
		
		inside.add(bCliente);
		inside.add(bGerente);
		inside.add(bSair);
		
		Dimension minSize = new Dimension(1, 50);
		Dimension prefSize = new Dimension(1, 100);
		Dimension maxSize = new Dimension(1, 150);
		
		JLabel select = new JLabel("Selecione uma opcao:");
		select.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		container.add(new Box.Filler(minSize, minSize, minSize));
		container.add(select);
		container.add(new Box.Filler(minSize, prefSize, maxSize));
		container.add(inside);
		
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		b = Banco.getInstance();
		if ("log_cliente".equals(e.getActionCommand())) {
			b.reconfigContentPane(Banco.CLIENT_LOGIN);
		} else if ("log_gerente".equals(e.getActionCommand())) {
			b.reconfigContentPane(Banco.ADM_LOGIN);
		} else if ("exit_prog".equals(e.getActionCommand())) {
			System.exit(0);
		}
	}
	
	public void on_update() {}
}
