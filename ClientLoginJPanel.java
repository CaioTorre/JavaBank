import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.lang.NumberFormatException;

public class ClientLoginJPanel extends JPanel implements ActionListener {
	
	protected JFrame controlling;
	
	protected JButton login, cancelar;
	protected JTextField userField;
	protected JPasswordField passField;
	
	public ClientLoginJPanel(JFrame ctrl) {
		controlling = ctrl;
		//super("Sistema bancario");
		//setLayout( new FlowLayout() );
		
		JPanel container = new JPanel(new GridLayout(3, 2, 5, 15));
		//JPanel userPanel = new JPanel();
		//JPanel passPanel = new JPanel();
		//JPanel buttonsPanel = new JPanel();
		
		JLabel userLabel = new JLabel("Usuario:");
		JLabel passLabel = new JLabel("Senha:");
		
		userField = new JTextField(10);
		userField.setHorizontalAlignment(JTextField.CENTER);
		
		passField = new JPasswordField(10);
		passField.setHorizontalAlignment(JTextField.CENTER);

		login = new JButton("Login");
		login.setActionCommand("tentar_login");
		login.setMnemonic(KeyEvent.VK_ACCEPT);
		login.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.setActionCommand("cancelar_login");
		cancelar.setMnemonic(KeyEvent.VK_G);
		cancelar.addActionListener(this);
		
		//userPanel.add(userLabel);
		//userPanel.add(userField);
		//passPanel.add(passLabel);
		//passPanel.add(passField);
		//buttonsPanel.add(login);
		//buttonsPanel.add(cancelar);
		
		//add(userPanel);
		//add(passPanel);
		//add(buttonsPanel);
		
		container.add(userLabel);
		container.add(userField);
		container.add(passLabel);
		container.add(passField);
		container.add(login);
		container.add(cancelar);
		
		//container.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		add(container);
		
		//userField.grabFocus();
		userField.requestFocusInWindow();
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("tentar_login".equals(e.getActionCommand())) {
			int user = 0;
			try {
				user = Integer.parseInt(userField.getText());
				Conta c = Banco.tentarLogin(user, String.valueOf(passField.getPassword()));
				if (c != null) {
					ClientJPanel screen = new ClientJPanel(controlling, c);
					Banco.reconfigContentPane(screen);
				} else {
					JOptionPane.showMessageDialog(null, "Combinacao nao reconhecida...", "Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NumberFormatException ie) {
				JOptionPane.showMessageDialog(null, "Favor digitar um numero!", "Erro", JOptionPane.ERROR_MESSAGE); 
			}
		} else if ("cancelar_login".equals(e.getActionCommand())) {
			MainJPanel screen = new MainJPanel(controlling);
			Banco.reconfigContentPane(screen);
		}
	}
}
