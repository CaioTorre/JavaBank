import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.lang.NumberFormatException;

public class ClientLoginJPanel extends JPanel implements ActionListener {
	
	protected JFrame controlling;
	
	protected JButton login, cancelar;
	protected JTextField userField;
	protected JPasswordField passField;
	
	public ClientLoginJPanel(JFrame ctrl) {
		controlling = ctrl;
		
		Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		//super("Sistema bancario");
		//setLayout( new FlowLayout() );
		JPanel bigContainer = new JPanel();
		bigContainer.setLayout(new BoxLayout(bigContainer, BoxLayout.Y_AXIS));
		
		JLabel cred = new JLabel("Insira suas credenciais");
		cred.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel container = new JPanel(new GridLayout(3, 2, 5, 15));
		container.setBorder(borda);
		//JPanel userPanel = new JPanel();
		//JPanel passPanel = new JPanel();
		//JPanel buttonsPanel = new JPanel();
		
		JLabel userLabel = new JLabel("Numero da conta:");
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
		
		container.add(userLabel);
		container.add(userField);
		container.add(passLabel);
		container.add(passField);
		container.add(login);
		container.add(cancelar);
		
		container.setPreferredSize(new Dimension(350, 200));
		
		Dimension minSize = new Dimension(1, 50);
		Dimension prefSize = new Dimension(1, 100);
		Dimension maxSize = new Dimension(1, 150);
		
		bigContainer.add(new Box.Filler(minSize, minSize, minSize));
		bigContainer.add(cred);
		bigContainer.add(new Box.Filler(minSize, prefSize, maxSize));
		bigContainer.add(container);
		
		add(bigContainer);
		//add(container);
		
		Banco.getMainFrame().getRootPane().setDefaultButton(login);
		EventQueue.invokeLater(new Runnable() {
		   @Override
			 public void run() {
				 userField.grabFocus();
				 userField.requestFocus();
			 }
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("tentar_login".equals(e.getActionCommand())) {
			int user = 0;
			try {
				user = Integer.parseInt(userField.getText());
				String pass = String.valueOf(passField.getPassword());
				if (pass.equals("")) { throw new StringVaziaException(); }
				Conta c = Banco.tentarLogin(user, pass);
				if (c != null) {
					ClientJPanel screen = new ClientJPanel(controlling, c);
					Banco.reconfigContentPane(screen);
				} else {
					JOptionPane.showMessageDialog(null, "Combinacao nao reconhecida...", "Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NumberFormatException ie) {
				JOptionPane.showMessageDialog(null, "Favor digitar um numero de conta!", "Erro", JOptionPane.ERROR_MESSAGE); 
			} catch (StringVaziaException sve) {
				JOptionPane.showMessageDialog(null, "Campo Senha nao pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE); 
			}
		} else if ("cancelar_login".equals(e.getActionCommand())) {
			MainJPanel screen = new MainJPanel(controlling);
			Banco.reconfigContentPane(screen);
		}
	}
}
