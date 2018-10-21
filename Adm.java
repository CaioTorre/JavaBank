import java.util.Scanner;

public class Adm {
	private String user;
	private String pass;
	private boolean loginState = false;
	
	public Adm() {
		user = "root";
		pass = "toor";
	}
	
	public boolean comparaSenha(String tentativa) {
		return (tentativa == pass);
	}
	
	public Conta criarNovaConta() {
		Scanner inp = new Scanner( System.in );
		
		String nome;
		int numero;
		char mode;
		
		System.out.print("Criar conta simples (S), especial (E) ou poupanca (P)? ");
		mode = inp.nextLine().charAt(0);
		
		System.out.print("Nome da conta..: ");
		nome = inp.nextLine();
		System.out.print("Numero da conta: ");
		numero = inp.nextInt();
		inp.nextLine();
		
		switch (mode) {// (mode == 'e' || mode == 'E')
			case 's':
			case 'S':
				//return null;
				return new ContaSimples(numero, nome);
				//break;
				
			case 'e':
			case 'E':
				double limite;
				System.out.print("Limite da conta: ");
				limite = inp.nextDouble();
				inp.nextLine();
				
				//return null;
				return new ContaEspecial(numero, nome, limite);
				//break;
				
			case 'p':
			case 'P':
				double juros;
				System.out.print("Taxa de juros (rendimento): ");
				juros = inp.nextDouble();
				inp.nextLine();
				
				//return null;
				return new ContaPoupanca(numero, nome, juros);
				//break;
				
			default:
				System.out.println("Tipo de conta nao reconhecido");
				break;
		}
		
		return null;
	}
}