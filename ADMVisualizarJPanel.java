import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ADMVisualizarJPanel extends Painel implements ActionListener {
	
	protected JScrollPane campo;
	protected JButton bRetorno;
	protected JPanel container;
	
	public void build_contas_view() {//String[] contas_as_strings, int n_contas) {
		b = Banco.getInstance();
		
		container.remove(campo);
		revalidate();
		//repaint();
		
		String[] contas_as_strings = b.adm_get_contas_as_strings();
		//int n_contas = b.get_n_contas();
		
		JPanel contas_campo = new JPanel();
		contas_campo.setSize(400, 400);
		contas_campo.setLayout(new GridLayout(0, 1, 0, 5));
		contas_campo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JLabel conta_atual;
			for (String s : contas_as_strings) {
			//Usando HTML para inserir quebras de linha dentro do texto
			//Substitui as quebras de linha tradicionais (\n) por quebras de linha em HTML (<br>) usando RegExp
			conta_atual = new JLabel("<html><div style='text-align: center;'>" + s.replaceAll("\n", "<br>") + "</div></html>");
			conta_atual.setHorizontalAlignment(JLabel.CENTER);
			conta_atual.setBorder(borda);
			contas_campo.add(conta_atual);
		}
		
		campo = new JScrollPane(contas_campo);
		campo.setPreferredSize(new Dimension(350, 350));
		container.add(campo);
		container.revalidate();
		revalidate();
		repaint();
		
	}
	
	public ADMVisualizarJPanel () {
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		if (Banco.testInstance()) { build_contas_view(); } else { campo = new JScrollPane(); }
		
		campo.setPreferredSize(new Dimension(350, 350));
		
		bRetorno = new JButton("Voltar");
		bRetorno.addActionListener(this);
		bRetorno.setMnemonic(KeyEvent.VK_V);
		bRetorno.setActionCommand("voltar");
		bRetorno.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		container.add(campo, BorderLayout.CENTER);
		container.add(bRetorno);
		
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		b = Banco.getInstance();
		if ("voltar".equals(e.getActionCommand())) {
			b.reconfigContentPane(Banco.ADM);
		}
	}
	
	public void on_update() { build_contas_view(); }
}
