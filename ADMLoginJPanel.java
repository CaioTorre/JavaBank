import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

//import java.lang.NumberFormatException;

public class ADMLoginJPanel extends Painel implements ActionListener {

	protected JButton login, cancelar;
	protected JTextField userField;
	protected JPasswordField passField;

	public ADMLoginJPanel() {
		Font f = new Font("SansSerif", Font.PLAIN, 28);
		Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

		JPanel bigContainer = new JPanel();
		bigContainer.setLayout(new BoxLayout(bigContainer, BoxLayout.Y_AXIS));
		
		JLabel cred = new JLabel("Insira suas credenciais");
		cred.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel container = new JPanel(new GridLayout(3, 2, 5, 15));
		container.setBorder(borda);
		
		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setFont(f);
		JLabel passLabel = new JLabel("Senha:");
		passLabel.setFont(f);
		
		userField = new JTextField(10);
		userField.setHorizontalAlignment(JTextField.CENTER);
		userField.setFont(f);
		
		passField = new JPasswordField(10);
		passField.setHorizontalAlignment(JTextField.CENTER);
		passField.setFont(f);

		login = new JButton("Login");
		login.setActionCommand("tentar_login");
		login.setMnemonic(KeyEvent.VK_ACCEPT);
		login.addActionListener(this);
		login.setFont(f);
		
		cancelar = new JButton("Cancelar");
		cancelar.setActionCommand("cancelar_login");
		cancelar.setMnemonic(KeyEvent.VK_ESCAPE);
		cancelar.addActionListener(this);
		cancelar.setFont(f);
		
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
	}
	
	public void actionPerformed(ActionEvent e) {
		b = Banco.getInstance();
		if ("tentar_login".equals(e.getActionCommand())) {
			//int user = 0;
			String campo_erro = "Usuario";
			try {
				String username = userField.getText();
				if (username.equals("")) { throw new StringVaziaException(); }
				campo_erro = "Senha";
				String password = String.valueOf(passField.getPassword());
				if (password.equals("")) { throw new StringVaziaException(); }
				
				if (b.tentarLoginADM(username, password)) {
					clearFields();
					b.reconfigContentPane(Banco.ADM);
				} else {
					JOptionPane.showMessageDialog(null, "Combinacao nao reconhecida...", "Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (StringVaziaException sve) {
				JOptionPane.showMessageDialog(null, "Campo " + campo_erro + " nao pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE); 
			}
		} else if ("cancelar_login".equals(e.getActionCommand())) {
			clearFields();
			b.reconfigContentPane(Banco.MAIN);
		}
	}
	
	private void clearFields() {
		userField.setText("");
		passField.setText("");
	}
	
	public void on_update() {
		Banco b = Banco.getInstance();
		b.setDefaultButtonForPane(login);
		EventQueue.invokeLater(new Runnable() {
		   @Override
			 public void run() {
				 userField.grabFocus();
				 userField.requestFocus();
			 }
		});
	}
}
