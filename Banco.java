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
		//screen_1.setOpaque(true); //content panes must be opaque
        frame.setContentPane(screen);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        pushNovaContaSimples(1234, "Asduhfee");
        pushNovaContaEspecial(3456, "Especial", 20.0);
        pushNovaContaPoupanca(6789, "Poupanca", 0.01);
        pushNovaContaSimples(1234, "Asduhfee");
        pushNovaContaEspecial(3456, "Especial", 20.0);
        pushNovaContaPoupanca(6789, "Poupanca", 0.01);
        pushNovaContaSimples(1234, "Asduhfee");
        pushNovaContaEspecial(3456, "Especial", 20.0);
        pushNovaContaPoupanca(6789, "Poupanca", 0.01);
        pushNovaContaSimples(1234, "Asduhfee");
        pushNovaContaEspecial(3456, "Especial", 20.0);
        pushNovaContaPoupanca(6789, "Poupanca", 0.01);
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
		for (i = 0; i < numero_contas; i++) {
			if (contas[i].getNumero() == user && contas[i].comparaSenha(pass)) {
				return contas[i];
			}
		}
		return null;
	}
	
	public static boolean tentarLoginADM(String user, String pass) {
		return (master_adm.tentaLogin(user, pass));
	}
	
	public static void reconfigContentPane(JPanel jp) {
		System.out.println("Called reconfig");
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
	
	public void main(String[] args) {
		//Scanner inp = new Scanner( System.in );
		//char mode = loggedOut;
		//int opcode;
		//int i;
		
		//Conta session = null;
		//int session_conta;
		//String session_senha;
		
		//while (true) {
			//if (mode == loggedOut) {
				//System.out.print("Logar como cliente (C) ou gerente (G)? ");
				//mode = inp.nextLine().charAt(0);
			//}
			
			//if (mode == 'c' || mode == 'C') {
				//if (session == null) {
					//System.out.println("Insira os dados a seguir");
					//do {
						//System.out.print("Numero..: ");
						//session_conta = inp.nextInt();
						//inp.nextLine();
						//session_conta = get_int(inp);
					//} while (session_conta < 0);
					
					//System.out.print("Senha...: ");
					//session_senha = inp.nextLine();
					
					//int i;
					//boolean found = false;
					//for (i = 0; i < numero_contas && !found; i++) {
						//if (contas[i].getNumero() == session_conta && contas[i].comparaSenha(session_senha)) {
							//found = true;
							//session = contas[i];
						//}
					//}
				//}
				//if (session == null) {
					//System.out.println("Numero ou senha nao reconhecido(s), tente novamente");
					//mode = loggedOut;
				//} else {
					//System.out.println("Digite sua opcao:");
					//System.out.print("1. Realizar saque\n2. Realizar deposito\n3. Visualizar informacoes da conta\n4. Alterar senha\n5. Desconectar\n");
					//System.out.print("\n>");
					
					//opcode = inp.nextInt();
					//inp.nextLine();
					//opcode = get_int(inp);

					//switch (opcode) {
						//case 1:
						//	System.out.print("Digite o valor do saque: ");
						//	double valorS = inp.nextDouble();
						//	inp.nextLine();
						//	
						//	try {
						//		session.sacar(valorS);
						//	} catch (SaldoInvalido e) {
						//		System.out.println(e.to_string());
						//	}
						//	break;
						//case 2:
						//	System.out.print("Digite o valor para deposito: ");
						//	double valorD = inp.nextDouble();
						//	inp.nextLine();
						//
						//	session.depositar(valorD);
						//	break;
						//case 3:
							//session.exibir();
							//break;
							
						//case 4:
							//session.alterarSenha();
							//break;
							
						//case 5: 
							//mode = loggedOut;
							//session = null;
							//break;
						//case -1:
							//break;
						//default:
							//System.out.println("Opcao nao reconhecida, tente novamente...");
							//break;	
					//}
					//System.out.println(spacer);
				//}
				
			//} else if (mode == 'g' || mode == 'G') {
				//System.out.println("Digite sua opcao:");
				//System.out.print("1. Criar nova conta\n");
				//System.out.print("2. Visualizar conta\n");
				//System.out.print("3. Incrementar rendimentos\n");
				//System.out.print("4. Cobrar juros\n");
				//System.out.print("5. Visualizar todas as contas\n");
				//System.out.print("6. Desconectar\n");
				//System.out.print("\n>");
				
				//opcode = inp.nextInt();
				//inp.nextLine();
				//opcode = get_int(inp);
				
				//switch (opcode) {
					//case 1: 
						//this.contas[this.numero_contas] = this.a.criarNovaConta();
						//this.numero_contas = this.numero_contas + 1;
						//break;
					//case 2:
						//session = findByID();
						//if (session != null) {
							//session.exibir();
						//} else {
							//System.out.println("Conta nao encontrada...");
						//}
						//session = null;
						//break;
					//case 3:
						//for (i = 0; i < numero_contas; i++) { contas[i].incrementarRendimentos(); }
//						//break;
//					case 4:
//						System.out.print("Qual o valor para o calculo de juros? ");
//						double juros = inp.nextDouble(); inp.nextLine();
//						for (i = 0; i < numero_contas; i++) { contas[i].cobrarJuros(juros); }
//						break;
//					case 5:
//						for (i = 0; i < numero_contas; i++) { contas[i].exibir(); }
//						break;
//					case 6:
//						session = null;
//						mode = loggedOut;
//						break;
//					default:
//						System.out.println("Opcao nao reconhecida, tente novamente...");
//						break;	
//				}
//				System.out.println(spacer);
//			} else {
//				System.out.println("Modo nao reconhecido, tente novamente");
//				System.out.println(spacer);
//			}
//		}
	}
	
	public static Conta findByID(int id) {
		//Scanner inp = new Scanner(System.in);
		//int numero;
		//do {
			//System.out.print("Digite o numero da conta: ");
		//int numero = inp.nextInt();
		//inp.nextLine();
		
			//numero = get_int(inp);
		//} while (numero != -1);
		
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
