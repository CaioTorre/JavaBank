public class NoActiveSessionException extends RuntimeException {
	public NoActiveSessionException() { super(); }
	public String to_string() {
		return "Nao ha nenhuma conta em sessao";
	}
}
