import java.util.Scanner;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
//import java.util.Exception;
import java.util.InputMismatchException;

public class Banco {
	private final char loggedOut = 'o';
	private static Conta contas[] = new Conta[30];
	private static Adm master_adm = new Adm();
	private static int numero_contas = 0;
	
	public static JFrame frame;
	protected final String spacer = "----------------------------------------------------";
	
	/*
	private static JPanel panels[] = new JPanel[10];
	public static final int MAIN = 0;
	public static final int CLIENT_LOGIN = 1;
	public static final int ADM_LOGIN = 2;
	public static final int CLIENT = 3;
	public static final int ADM = 4;
	*/
	
	public static Adm getADM() { return master_adm; }
	public static int getNumero_contas() { return numero_contas; }
	public static Conta[] getContas() { return contas; }
	public static JFrame getMainFrame() { return frame; }
	
	public Banco() {
		frame = new JFrame("Sistema Bancario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 450));
		
		/*
		panels[MAIN] = new MainJPanel(frame);
		panels[CLIENT_LOGIN] = new ClientLoginJPanel(frame);
		panels[ADM_LOGIN] = new ADMLoginJPanel(frame);
		panels[CLIENT] = new ClientJPanel(frame, null);
		panels[ADM] = new ADMJPanel(frame);
		*/
		
		MainJPanel screen = new MainJPanel(frame);
        frame.setContentPane(screen);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        //====================== INICIALIZANDO CONTAS TESTE ======================
        pushNovaContaSimples(123, "Asduhfee");
        pushNovaContaEspecial(456, "Especial", 20.0);
        pushNovaContaPoupanca(789, "Poupanca", 0.01);
	}
	
	public static Conta pushNovaContaSimples(int numero, String nome) {
		contas[numero_contas] = new ContaSimples(numero, nome);
		numero_contas = numero_contas + 1;
		return contas[numero_contas - 1];
	}
	
	public static Conta pushNovaContaEspecial(int numero, String nome, double limite) {
		contas[numero_contas] = new ContaEspecial(numero, nome, limite);
		numero_contas = numero_contas + 1;
		return contas[numero_contas - 1];
	}
	
	public static Conta pushNovaContaPoupanca(int numero, String nome, double juros) {
		contas[numero_contas] = new ContaPoupanca(numero, nome, juros);
		numero_contas = numero_contas + 1;
		return contas[numero_contas - 1];
	}
	
	public int get_int(Scanner inp) {
		int res;
		try {
			res = inp.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Por favor digite um numero");
			return -1;
		} finally {
			inp.nextLine();
		}
		return res;
	}
	
	public static Conta tentarLogin(int user, String pass) {
		int i;
		for (i = 0; i < Banco.numero_contas; i++) {
			if (Banco.contas[i].getNumero() == user && Banco.contas[i].comparaSenha(pass)) {
				return contas[i];
			}
		}
		return null;
	}
	
	public static boolean checkForID(int id) {
		int i;
		for (i = 0; i < Banco.numero_contas; i++) {
			if (Banco.contas[i].getNumero() == id) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean tentarLoginADM(String user, String pass) { return (master_adm.tentaLogin(user, pass)); }
	
	public static void reconfigContentPane(JPanel jp) {
		frame.setContentPane(jp);
		frame.pack();
		frame.setVisible(true);
	}
	
	/*
	public static void reconfigContentPane(int panel_code) {
		frame.setContentPane(Banco.panels[panel_code]);
		frame.pack();
		frame.setVisible(true);
	}
	*/
	
	//public void main(String[] args) { }
	
	public static Conta findByID(int id) {
		int i;
		boolean found = false;
		Conta session = null;
		for (i = 0; i < numero_contas && !found; i++) {
			if (contas[i].getNumero() == id) {
				found = true;
				session = contas[i];
			}
		}
		
		return session;
	}
}
