public class StringVaziaException extends RuntimeException {
	public StringVaziaException() { super(); }
	public String to_string() {
		return "A String nao pode estar vazia";
	}
}
