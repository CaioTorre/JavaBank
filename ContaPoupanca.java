public class ContaPoupanca extends Conta {
	private double juros;
	
	public ContaPoupanca() { super(); }
	
	public ContaPoupanca(int numero, String nome, double juros) {
		super(numero, nome);
		this.juros = juros;
	}
	
	public double getJuros() { return this.juros; }
	
	public void exibir() { System.out.printf("\t%d - %s (R$%.2f) - %.2f%%a.m.\n", getNumero(), getNome(), getSaldo(), juros); }
	public String to_string() { return String.format("Conta poupanca #%d\nSr(a). %s\nSaldo: R$%.2f\nJuros: %.2f%%", getNumero(), getNome(), getSaldo(), getJuros()); }
	
	public void incrementarRendimentos() {
		double newSaldo = this.getSaldo() * (1 + this.juros / 100.0);
		this.setSaldo(newSaldo);
	}
}
