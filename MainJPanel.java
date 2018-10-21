import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainJPanel extends JPanel implements ActionListener {
	
	protected JButton bCliente, bGerente;
	protected JFrame controlling;
	
	public MainJPanel(JFrame ctrl) {
		System.out.println("Creating main panel");
		controlling = ctrl;
		//super("Sistema bancario");
		//setLayout( new FlowLayout() );
		
		bCliente = new JButton("Cliente");
		bCliente.setActionCommand("log_cliente");
		bCliente.setMnemonic(KeyEvent.VK_C);
		bCliente.addActionListener(this);
		
		bGerente = new JButton("Gerente");
		bGerente.setActionCommand("log_gerente");
		bGerente.setMnemonic(KeyEvent.VK_G);
		bGerente.addActionListener(this);
		
		add(bCliente);
		add(bGerente);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("log_cliente".equals(e.getActionCommand())) {
			ClientLoginJPanel temp = new ClientLoginJPanel(controlling);
			Banco.reconfigContentPane(temp);
		} else {
			System.out.println(e.getActionCommand());
		}
	}
}
