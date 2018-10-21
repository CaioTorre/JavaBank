public class SaldoInvalidoException extends RuntimeException {
	public SaldoInvalidoException() { super(); }
	public String to_string() {
		return "A conta selecionada nao possui saldo suficiente para completar a operacao";
	}
}
