public class SaldoInvalido extends RuntimeException {
	public SaldoInvalido() { super(); }
	public String to_string() {
		return "A conta selecionada nao possui saldo suficiente para completar a operacao";
	}
}
