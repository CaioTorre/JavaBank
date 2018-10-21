import java.util.Scanner;

public abstract class Conta {
	private int numero;
	private String nome;
	private double saldo;
	private String senha;
	
	protected final String spacer = "-------------------------------------------";
	
	public Conta(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
		this.saldo = 0.0;
		this.senha = "0000";
	}
	
	public Conta() {
		this.saldo = 0.0;
		this.senha = "0000";
	}
	
	public String getSenha() { return this.senha; }
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNumero() {
		return numero;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	public void sacar(double valor) {
		if (valor <= saldo) {
			saldo -= valor;
			System.out.printf("Saque de R$%.2f realizado com sucesso!\n", valor);
			System.out.printf("Novo saldo: R$%.2f\n", saldo);
		} else {
			System.out.printf("Sr(a). %s (%d) nao pode sacar R$%.2f\n", nome, numero, valor);
		}
		
		System.out.println(spacer);
	}
	
	public void depositar(double valor) {
		saldo += valor;
		System.out.printf("Deposito de R$%.2f realizado com sucesso!\n", valor);
		System.out.printf("Novo saldo: R$%.2f\n", saldo);
		
		System.out.println(spacer);
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void alterarSenha() {
		Scanner inp = new Scanner( System.in );
		System.out.print("Digite a senha antiga: ");
		String senhaAntiga = inp.nextLine();
		if (comparaSenha(senhaAntiga)) {
			System.out.print("Digite a nova senha: ");
			this.senha = inp.nextLine();
			System.out.println("Senha atualizada com sucesso!");
		} else {
			System.out.println("Senha antiga difere!");
		}
		System.out.println(spacer);
	}
	
	public boolean comparaSenha(String attempt) {
		if (senha.compareTo(attempt) == 0) {
			//System.out.println("Correct!");
			return true;
		} else {
			//System.out.println("Incorrect!");
			return false;
		}
	}
	
	public void exibir() {
		System.out.printf("\t%d - %s (R$%.2f)\n", numero, nome, saldo);
	}
	
	//---------TEMPLATES---------
	public void incrementarRendimentos() {}
	public void cobrarJuros(double juros) {}//{System.out.printf("Using base function for acc %d\n", this.numero);}
}