import java.util.Scanner;

public class Adm {
	private String user;
	private String pass;
	private String nome;
	private boolean loginState = false;
	
	public Adm() {
		user = "root";
		pass = "toor";
		nome = "Gerente";
	}
	
	public boolean tentaLogin(String user, String pass) {
		System.out.printf("user = (%s) --- pass = (%s)\n", user, pass);
		if (user.equals(this.user) && comparaSenha(pass)) {
			this.loginState = true;
			return true;
		}
		return false;
	}
	
	public void logOut() {
		this.loginState = false;
	}
	
	public String getNome() { return this.nome; }
	
	public boolean comparaSenha(String tentativa) {
		return (tentativa.equals(this.pass));
	}
	
	public Conta criarNovaContaSimples(int numero, String nome) {
		if (this.loginState) {
			return Banco.pushNovaContaSimples(numero, nome);
		}
		return null;
	}
	
	public Conta criarNovaContaEspecial(int numero, String nome, double limite) {
		if (this.loginState) {
			return Banco.pushNovaContaEspecial(numero, nome, limite);
		}
		return null;
	}
	
	public Conta criarNovaContaPoupanca(int numero, String nome, double juros) {
		if (this.loginState) {
			return Banco.pushNovaContaPoupanca(numero, nome, juros);
		}
		return null;
	}
	
	public String[] getContasString() {
		if (this.loginState) {
			int i;
			String[] out = new String[Banco.getNumero_contas()];
			for (i = 0; i < Banco.getNumero_contas(); i++) { out[i] = Banco.getContas()[i].to_string(); }
			return out;
		}
		return null;
	}
	
	/*
			// Scanner inp = new Scanner( System.in );
			
			String nome;
			int numero;
			char mode;
			
			// System.out.print("Criar conta simples (S), especial (E) ou poupanca (P)? ");
			// mode = inp.nextLine().charAt(0);
			
			System.out.print("Nome da conta..: ");
			// nome = inp.nextLine();
			System.out.print("Numero da conta: ");
			// numero = inp.nextInt();
			// inp.nextLine();
			
			switch (mode) {// (mode == 'e' || mode == 'E')
				case 's':
				case 'S':
					// return null;
					// return new ContaSimples(numero, nome);
					// break;
					return Banco.pushNovaContaSimples(numero, nome);
				case 'e':
				case 'E':
					double limite;
					System.out.print("Limite da conta: ");
					// limite = inp.nextDouble();
					// inp.nextLine();
					
					// return null;
					return new ContaEspecial(numero, nome, limite);
					// break;
					
				case 'p':
				case 'P':
					double juros;
					System.out.print("Taxa de juros (rendimento): ");
					// juros = inp.nextDouble();
					// inp.nextLine();
					
					// return null;
					return new ContaPoupanca(numero, nome, juros);
					// break;
					
				default:
					System.out.println("Tipo de conta nao reconhecido");
					break;
			}
			
			return null; */
}