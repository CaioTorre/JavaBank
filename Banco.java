import java.util.Scanner;
public class Banco {
	private final char loggedOut = 'o';
	private Conta contas[] = new Conta[30];
	private Adm a = new Adm();
	private int numero_contas = 0;
	
	protected final String spacer = "----------------------------------------------------";
	
	public Banco() {}
	
	public void main(String[] args) {
		Scanner inp = new Scanner( System.in );
		char mode = loggedOut;
		int opcode;
		int i;
		
		Conta session = null;
		int session_conta;
		String session_senha;
		
		while (true) {
			if (mode == loggedOut) {
				System.out.print("Logar como cliente (C) ou gerente (G)? ");
				mode = inp.nextLine().charAt(0);
			}
			
			if (mode == 'c' || mode == 'C') {
				if (session == null) {
					System.out.println("Insira os dados a seguir");
					
					System.out.print("Numero..: ");
					session_conta = inp.nextInt();
					inp.nextLine();
					
					System.out.print("Senha...: ");
					session_senha = inp.nextLine();
					
					//int i;
					boolean found = false;
					for (i = 0; i < numero_contas && !found; i++) {
						if (contas[i].getNumero() == session_conta && contas[i].comparaSenha(session_senha)) {
							found = true;
							session = contas[i];
						}
					}
				}
				if (session == null) {
					System.out.println("Numero ou senha nao reconhecido(s), tente novamente");
					mode = loggedOut;
				} else {
					System.out.println("Digite sua opcao:");
					System.out.print("1. Realizar saque\n2. Realizar deposito\n3. Visualizar informacoes da conta\n4. Alterar senha\n5. Desconectar\n");
					System.out.print("\n>");
					
					opcode = inp.nextInt();
					inp.nextLine();

					switch (opcode) {
						case 1:
							System.out.print("Digite o valor do saque: ");
							double valorS = inp.nextDouble();
							inp.nextLine();
				
							session.sacar(valorS);
							break;
						case 2:
							System.out.print("Digite o valor para deposito: ");
							double valorD = inp.nextDouble();
							inp.nextLine();

							session.depositar(valorD);
							break;
						case 3:
							session.exibir();
							break;
						case 4:
							session.alterarSenha();
							break;
						case 5: 
							mode = loggedOut;
							session = null;
							break;
						default:
							System.out.println("Opcao nao reconhecida, tente novamente...");
							break;	
					}
					System.out.println(spacer);
				}
				
			} else if (mode == 'g' || mode == 'G') {
				System.out.println("Digite sua opcao:");
				System.out.print("1. Criar nova conta\n");
				System.out.print("2. Visualizar conta\n");
				System.out.print("3. Incrementar rendimentos\n");
				System.out.print("4. Cobrar juros\n");
				System.out.print("5. Visualizar todas as contas\n");
				System.out.print("6. Desconectar\n");
				System.out.print("\n>");
				
				opcode = inp.nextInt();
				inp.nextLine();
				
				switch (opcode) {
					case 1: 
						this.contas[this.numero_contas] = this.a.criarNovaConta();
						this.numero_contas = this.numero_contas + 1;
						break;
					case 2:
						session = findByID();
						if (session != null) {
							session.exibir();
						} else {
							System.out.println("Conta nao encontrada...");
						}
						session = null;
						break;
					case 3:
						for (i = 0; i < numero_contas; i++) { contas[i].incrementarRendimentos(); }
						break;
					case 4:
						System.out.print("Qual o valor para o calculo de juros? ");
						double juros = inp.nextDouble(); inp.nextLine();
						for (i = 0; i < numero_contas; i++) { contas[i].cobrarJuros(juros); }
						break;
					case 5:
						for (i = 0; i < numero_contas; i++) { contas[i].exibir(); }
						break;
					case 6:
						session = null;
						mode = loggedOut;
						break;
					default:
						System.out.println("Opcao nao reconhecida, tente novamente...");
						break;	
				}
				System.out.println(spacer);
			} else {
				System.out.println("Modo nao reconhecido, tente novamente");
				System.out.println(spacer);
			}
		}
	}
	
	public Conta findByID() {
		Scanner inp = new Scanner(System.in);
		System.out.print("Digite o numero da conta: ");
		int numero = inp.nextInt();
		inp.nextLine();
		int i;
		boolean found = false;
		Conta session = null;
		for (i = 0; i < numero_contas && !found; i++) {
			if (contas[i].getNumero() == numero) {
				found = true;
				session = contas[i];
			}
		}
		
		return session;
	}
}