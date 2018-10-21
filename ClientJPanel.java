import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;

public class ClientJPanel extends JPanel implements ActionListener {
	
	protected JFrame controlling;
	
	protected JButton bSaque, bDeposito, bInfo, bSenha, bLogout;
	private Conta session_atual;
	
	public void setSession(Conta c) { session_atual = c; }
	public Conta getSession() { return session_atual; }
	
	public ClientJPanel(JFrame ctrl, Conta c) {
		controlling = ctrl;
		setSession(c);
		
		JLabel bemVindo = new JLabel("Bem vindo(a) Sr(a). " + c.getNome());
		//super("Sistema bancario");
		//setLayout( new FlowLayout() );
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		
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
		
		bInfo = new JButton("Visualizar informacoes da conta");
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
		
		buttons.add(bSaque);
		buttons.add(bDeposito);
		buttons.add(bInfo);
		buttons.add(bSenha);
		buttons.add(bLogout);
		
		add(bemVindo);
		add(buttons);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
