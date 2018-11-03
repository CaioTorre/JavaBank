import java.lang.NullPointerException;

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
		this.senha = Hashing.hash("0000");
	}
	
	public Conta() {
		this.saldo = 0.0;
		this.senha = Hashing.hash("0000");
	}
	
	
	public void setNumero(int numero) { this.numero = numero; }
	public int getNumero() { return numero; }
	
	public void setNome(String nome) { this.nome = nome; }
	public String getNome() { return nome; }
	
	public void sacar(double valor) throws SaldoInvalidoException, ValorInvalidoException {
		if (valor > 0) {
			if (valor <= saldo) {
				saldo -= valor;
			} else {
				throw new SaldoInvalidoException();
			}
		} else {
			throw new ValorInvalidoException();
		}
	}
	
	public void depositar(double valor) throws ValorInvalidoException {
		if (valor > 0) {
			saldo += valor;
		} else {
			throw new ValorInvalidoException();
		}
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void alterarSenha(String senhaAntiga, String senhaNova) throws NullPointerException {
		try {
			if (comparaSenha(senhaAntiga)) {
				this.senha = Hashing.hash(senhaNova);
			}
		} catch (NullPointerException npe) {
			throw npe;
		}
	}
	
	public boolean comparaSenha(String attempt) throws NullPointerException {
		try {
			//Hashing h = Hashing.getInstance();
			//if (senha.equals(h.hash(attempt))) {
			if (senha.equals(Hashing.hash(attempt))) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException npe) {
			throw npe;
		}	
	}
	
	public void exibir() { System.out.printf("\t%d - %s (R$%.2f)\n", numero, nome, saldo); }
	public String to_string() { return String.format("Conta #%d\nSr(a). %s\nSaldo: R$%.2f", numero, nome, saldo); }
	
	//---------TEMPLATES---------
	public void incrementarRendimentos() {}
	public boolean cobrarJuros(double juros) { return false; }//{System.out.printf("Using base function for acc %d\n", this.numero);}
}
