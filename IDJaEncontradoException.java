public class IDJaEncontradoException extends RuntimeException {
	public IDJaEncontradoException() { super(); }
	public String to_string() {
		return "O ID selecionado ja esta sendo utilizado em outra conta";
	}
}
