import java.util.Scanner;
public class ContaEspecial extends Conta {
	private double limite;
	private double limiteMax;
	
	public ContaEspecial() {
		super();
	}
	
	public ContaEspecial(int numero, String nome, double limite) {
		super(numero, nome);
		this.limite = limite;
		this.limiteMax = limite;
	}
	
	public void sacar(double valor) throws SaldoInvalidoException {
		if (valor <= getSaldo()) {
			setSaldo(getSaldo() - valor);
			//System.out.printf("Saque de R$%.2f realizado com sucesso!\n", valor);
			//System.out.printf("Novo saldo: R$%.2f (limite: R$%.2f)\n", getSaldo(), limite);
		} else {
			if (valor <= getSaldo() + limite) {
				limite = limite + getSaldo() - valor;
				setSaldo(0.0);
				//System.out.printf("Sacando R$%.2f do limite...\n", valor);
				//System.out.printf("Novo saldo: R$%.2f (limite: R$%.2f)\n", getSaldo(), limite);
			} else {
				throw new SaldoInvalidoException();
				//System.out.printf("Sr(a). %s (%d) nao pode sacar R$%.2f\n", getNome(), getNumero(), valor);
			}
		}
		
		//System.out.println(spacer);
	}
	
	public void depositar(double valor) {
		if (limite < limiteMax) {
			limite += valor;
			if (limite > limiteMax) {
				setSaldo(getSaldo() + limite - limiteMax);
				limite = limiteMax;
			}
		} else {
			setSaldo(getSaldo() + valor);
		}
		//System.out.printf("Novo saldo: R$%.2f (limite: R$%.2f)\n", getSaldo(), limite);
		
		//System.out.println(spacer);
	}
	
	public void exibir() {
		System.out.printf("\t%d - %s (R$%.2f/R$%.2f)\n", getNumero(), getNome(), getSaldo(), limite);
	}
	
	public String to_string() {
		return String.format("Conta especial #%d\nSr(a). %s\nSaldo: R$%.2f\nLimite: R$%.2f", getNumero(), getNome(), getSaldo(), getLimite());
	}
	
	public double getLimite() { return this.limite; }

	public void cobrarJuros(double juros) {
		if (limite < limiteMax) {
			double devendo = (limiteMax - limite) * (juros / 100.0);
			limite = limite - devendo;
		}
	}
}
