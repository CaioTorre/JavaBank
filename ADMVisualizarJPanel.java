import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ADMVisualizarJPanel extends JPanel implements ActionListener {
	
	protected JScrollPane campo;
	protected JButton bRetorno;
	protected JFrame controlling;
	
	public ADMVisualizarJPanel (JFrame ctrl, String[] contas_as_strings, int n_contas) {
		controlling = ctrl;
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel contas_campo = new JPanel();
		contas_campo.setSize(400,400);
		contas_campo.setLayout(new GridLayout(0, 1, 0, 5));
		contas_campo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JLabel conta_atual;
		int i;
		for (i = 0; i < n_contas; i++) {
			//Usando HTML para inserir quebras de linha dentro do texto
			//Substitui as quebras de linha tradicionais (\n) por quebras de linha em HTML (<br>) usando RegExp
			conta_atual = new JLabel("<html><div style='text-align: center;'>" + contas_as_strings[i].replaceAll("\n", "<br>") + "</div></html>");
			conta_atual.setHorizontalAlignment(JLabel.CENTER);
			conta_atual.setBorder(borda);
			contas_campo.add(conta_atual);
		}
		
		campo = new JScrollPane(contas_campo);
		campo.setPreferredSize(new Dimension(350, 350));
		
		bRetorno = new JButton("Voltar");
		bRetorno.addActionListener(this);
		bRetorno.setMnemonic(KeyEvent.VK_V);
		bRetorno.setActionCommand("voltar");
		bRetorno.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		container.add(campo, BorderLayout.CENTER);
		container.add(bRetorno);
		
		add(container);
		Banco.getMainFrame().getRootPane().setDefaultButton(bRetorno);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("voltar".equals(e.getActionCommand())) {
			ADMJPanel screen = new ADMJPanel(controlling);
			Banco.reconfigContentPane(screen);
		}
	}
}
