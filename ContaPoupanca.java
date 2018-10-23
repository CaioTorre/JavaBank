import java.util.Scanner;
public class ContaPoupanca extends Conta {
	private double juros;
	
	public ContaPoupanca() {
		super();
	}
	
	public double getJuros() { return this.juros; }
	
	public ContaPoupanca(int numero, String nome, double juros) {
		super(numero, nome);
		this.juros = juros;
	}
	
	public void simularRendimento(int meses) {
		double simulado = getSaldo() * Math.pow(1 + juros, meses);
		System.out.printf("Apos %d meses, a poupanca (atualmente R$%.2f) sera de R$%.2f\n", meses, simulado);
	}
	
	public void exibir() {
		System.out.printf("\t%d - %s (R$%.2f) - %.2f%%a.m.\n", getNumero(), getNome(), getSaldo(), juros);
	}
	
	public String to_string() {
		return String.format("Conta poupanca #%d\nSr(a). %s\nSaldo: R$%.2f\nJuros: %.2f%%", getNumero(), getNome(), getSaldo(), getJuros());
	}
	
	public void incrementarRendimentos() {
		double newSaldo = this.getSaldo() * (1 + this.juros);
		this.setSaldo(newSaldo);
	}
}
