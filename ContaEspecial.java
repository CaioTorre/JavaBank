public class ContaEspecial extends Conta {
	private double limite;
	private double limiteMax;
	
	public ContaEspecial() { super(); }
	
	public ContaEspecial(int numero, String nome, double limite) {
		super(numero, nome);
		this.limite = limite;
		this.limiteMax = limite;
	}
	
	public double getLimite() { return this.limite; }
	
	public void sacar(double valor) throws SaldoInvalidoException {
		if (valor <= getSaldo()) {
			setSaldo(getSaldo() - valor);
		} else {
			if (valor <= getSaldo() + limite) {
				limite = limite + getSaldo() - valor;
				setSaldo(0.0);
			} else {
				throw new SaldoInvalidoException();
			}
		}
	}
	
	public void depositar(double valor) throws ValorInvalidoException {
		if (valor <= 0) { throw new ValorInvalidoException(); }
		if (limite < limiteMax) {
			limite += valor;
			if (limite > limiteMax) {
				setSaldo(getSaldo() + limite - limiteMax);
				limite = limiteMax;
			}
		} else {
			setSaldo(getSaldo() + valor);
		}
	}
	
	public void exibir() { System.out.printf("\t%d - %s (R$%.2f/R$%.2f)\n", getNumero(), getNome(), getSaldo(), limite); }
	public String to_string() { return String.format("Conta especial #%d\nSr(a). %s\nSaldo: R$%.2f\nLimite: R$%.2f", getNumero(), getNome(), getSaldo(), getLimite()); }

	public boolean cobrarJuros(double juros) {
		if (limite < limiteMax) {
			double devendo = (limiteMax - limite) * (juros / 100.0);
			limite = limite - devendo;
			return true;
		}
		return false;
	}
}
