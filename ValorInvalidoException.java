public class ValorInvalidoException extends RuntimeException {
	public ValorInvalidoException() { super(); }
	public String to_string() {
		return "O valor selecionado nao é valido";
	}
}
