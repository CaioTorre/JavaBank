public class NoADMSessionException extends RuntimeException {
	public NoADMSessionException() { super(); }
	public String to_string() {
		return "O administrador do sistema nao esta logado";
	}
}
