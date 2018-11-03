//import java.util.Scanner;
import java.awt.Dimension;
//import javax.swing.SwingUtilities;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
//import java.util.InputMismatchException;

public class Banco {
	private Conta contas[] = new Conta[30];
	private int numero_contas = 0;
	
	private Conta session;
	private Adm master_adm = new Adm();
	
	private JFrame frame;
	
	private static Banco self;
	
	private Painel panels[] = new Painel[10];
	public static final int MAIN = 0;
	public static final int CLIENT_LOGIN = 1;
	public static final int ADM_LOGIN = 2;
	public static final int CLIENT = 3;
	public static final int ADM = 4;
	public static final int ADM_NOVA_CONTA = 5;
	public static final int ADM_VISUALIZAR = 6;
	
	//====================== SINGLETON BANCO ======================
	public static Banco getInstance() {
		if (self == null) { self = new Banco(); }
		return self;
	}
	
	public static boolean testInstance() {
		if (self == null) return false;
		return true;
	}
	//protected Adm get_adm() { return self.master_adm; }
	
	private Banco() {
        //====================== CRIANDO JFRAME CONTROLADOR ======================
		frame = new JFrame("Sistema Bancario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 450));
		
        //====================== CRIANDO JPANELS ======================
		panels[MAIN] 			= new MainJPanel();
		panels[CLIENT_LOGIN]	= new ClientLoginJPanel();
		panels[ADM_LOGIN]		= new ADMLoginJPanel();
		panels[CLIENT]			= new ClientJPanel();
		panels[ADM]				= new ADMJPanel();
		panels[ADM_NOVA_CONTA]	= new ADMNovaContaJPanel();
		panels[ADM_VISUALIZAR]	= new ADMVisualizarJPanel();
		
		MainJPanel screen = new MainJPanel();
        frame.setContentPane(screen);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        //====================== INICIALIZANDO CONTAS TESTE ======================
        pushNovaContaSimples(123, "Asduhfee");
        pushNovaContaEspecial(456, "Especial", 20.0);
        pushNovaContaPoupanca(789, "Poupanca", 0.01);
	}
	
	public int get_n_contas() { return numero_contas; }
	public Conta[] get_contas() { return contas; }
	
	//====================== CRIADORES DE CONTAS PUBLICOS ======================
	public boolean novaContaS(int numero, String nome) throws IDJaEncontradoException, NoADMSessionException {
		if (master_adm.getLoginState()) {
			if (checkForID(numero)) { throw new IDJaEncontradoException(); }
			pushNovaContaSimples(numero, nome);
			return true;
		} else { throw new NoADMSessionException(); }
		//return false;
	}
	public boolean novaContaE(int numero, String nome, double limite) throws IDJaEncontradoException, NoADMSessionException {
		if (master_adm.getLoginState()) {
			if (checkForID(numero)) { throw new IDJaEncontradoException(); }
			pushNovaContaEspecial(numero, nome, limite);
			return true;
		} else { throw new NoADMSessionException(); }
		//return false;
	}
	public boolean novaContaP(int numero, String nome, double juros) throws IDJaEncontradoException, NoADMSessionException {
		if (master_adm.getLoginState()) {
			if (checkForID(numero)) { throw new IDJaEncontradoException(); }
			pushNovaContaPoupanca(numero, nome, juros);
			return true;
		} else { throw new NoADMSessionException(); }
		//return false;
	}
	
	//====================== CRIADORES DE CONTAS PRIVADOS E SEPARADOS ======================
	private Conta pushNovaContaSimples(int numero, String nome) {
		contas[numero_contas] = new ContaSimples(numero, nome);
		numero_contas = numero_contas + 1;
		return contas[numero_contas - 1];
	}
	private Conta pushNovaContaEspecial(int numero, String nome, double limite) {
		contas[numero_contas] = new ContaEspecial(numero, nome, limite);
		numero_contas = numero_contas + 1;
		return contas[numero_contas - 1];
	}
	private Conta pushNovaContaPoupanca(int numero, String nome, double juros) {
		contas[numero_contas] = new ContaPoupanca(numero, nome, juros);
		numero_contas = numero_contas + 1;
		return contas[numero_contas - 1];
	}
	
	public boolean tentarLoginADM(String user, String pass) { return (master_adm.tentaLogin(user, pass)); }
	
	public void reconfigContentPane(int panel_code) {
		frame.setContentPane(panels[panel_code]);
		Painel t = (Painel)frame.getContentPane();
		t.on_update();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setDefaultButtonForPane(JButton but) { frame.getRootPane().setDefaultButton(but); }
	
	private Conta findByID(int id) {
		for (Conta c : contas) { 
			if (c != null) {
				if (c.getNumero() == id) {
					return c;
				}
			}
		}
		return null;
	}
	
	public boolean checkForID(int id) {
		for (Conta c : contas) {
			if (c != null) { if (c.getNumero() == id) return true; }
		}
		return false;
	}
	
	//====================== FUNCOES FACADE CLIENTE ======================
	public boolean client_tentarLogin(int user, String pass) {
		for (Conta c : contas) {
			if (c != null) {
				if (c.getNumero() == user && c.comparaSenha(pass)) {
					session = c;
					return true;
				}
			}
		}
		return false;
	}
	
	public void client_logOut() { session = null; }
	
	public void client_sacar(double val) throws NoActiveSessionException {
		if (this.session == null) throw new NoActiveSessionException();
		session.sacar(val); 
	}
	
	public void client_depositar(double val) throws NoActiveSessionException {
		if (this.session == null) throw new NoActiveSessionException();
		session.depositar(val); 
	}
	
	public String client_to_string() throws NoActiveSessionException {
		if (this.session == null) throw new NoActiveSessionException();
		return session.to_string(); 
	}
	
	public boolean client_comparaSenha(String senha) throws NoActiveSessionException {
		if (this.session == null) throw new NoActiveSessionException();
		return session.comparaSenha(senha); 
	}
	
	public void client_alterarSenha(String senhaAntiga, String senhaNova) throws NoActiveSessionException {
		if (this.session == null) throw new NoActiveSessionException();
		session.alterarSenha(senhaAntiga, senhaNova); 
	}
	
	public String client_getNome() throws NoActiveSessionException {
		if (this.session == null) throw new NoActiveSessionException();
		return session.getNome(); 
	}
	
	//====================== FUNCOES FACADE ADM ======================
	public void adm_logOut() { master_adm.logOut(); }
	
	public void adm_incrementarRendimentos() { master_adm.incrementarRendimentos(); }
	
	public int adm_cobrarJuros(double j) { return master_adm.cobrarJuros(j); }
	
	public String adm_client_to_string(int id) throws NullPointerException, ValorInvalidoException {
		if (id < 0) { throw new ValorInvalidoException(); }
		//Conta t = findByID(id);
		//return t.to_string();
		try {
			return findByID(id).to_string();
		} catch (NullPointerException e) {
			throw e;
		}
	}
	
	public String[] adm_get_contas_as_strings() {
		return master_adm.getContasString();
	}
	
	public String adm_getNome() { return master_adm.getNome(); }
}
