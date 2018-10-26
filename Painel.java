import javax.swing.*;
import java.awt.event.ActionListener;
abstract class Painel extends JPanel implements ActionListener {
	protected Banco b;
	abstract void on_update();
	public void setBancoInstance() { b = Banco.getInstance(); }
}
