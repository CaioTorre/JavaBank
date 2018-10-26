import java.util.Scanner;

public class Adm {
	private String user;
	private String pass;
	private String nome;
	private boolean loginState = false;
	
	private Banco b;
	
	public Adm() {
		user = "root";
		pass = "toor";
		nome = "Gerente";
	}
	
	public boolean tentaLogin(String user, String pass) {
		if (user.equals(this.user) && comparaSenha(pass)) {
			this.loginState = true;
			return true;
		}
		return false;
	}
	
	public boolean getLoginState() { return this.loginState; }
	
	public void logOut() { this.loginState = false; }
	
	public String getNome() { return this.nome; }
	
	public boolean comparaSenha(String tentativa) { return (tentativa.equals(this.pass)); }
	
	/*
	public Conta criarNovaContaSimples(int numero, String nome) throws IDJaEncontradoException {
		if (this.loginState) {
			if (b.checkForID(numero)) {
				throw new IDJaEncontradoException();
			} else {
				return b.pushNovaContaSimples(numero, nome);
			}
		}
		return null;
	}
	
	public Conta criarNovaContaEspecial(int numero, String nome, double limite) throws IDJaEncontradoException {
		if (this.loginState) {
			if (b.checkForID(numero)) {
				throw new IDJaEncontradoException();
			} else {
				return b.pushNovaContaEspecial(numero, nome, limite);
			}
		}
		return null;
	}
	
	public Conta criarNovaContaPoupanca(int numero, String nome, double juros) throws IDJaEncontradoException {
		if (this.loginState) {
			if (b.checkForID(numero)) {
				throw new IDJaEncontradoException();
			} else {
				return b.pushNovaContaPoupanca(numero, nome, juros);
			}
		}
		return null;
	}
	*/
	
	public String[] getContasString() {
		b = Banco.getInstance();
		if (this.loginState) {
			int i;
			String[] out = new String[b.get_n_contas()];
			for (i = 0; i < b.get_n_contas(); i++) { out[i] = b.get_contas()[i].to_string(); }
			return out;
		}
		return null;
	}
	
	public void incrementarRendimentos() {
		b = Banco.getInstance();
		if (this.loginState) {
			int i;
			for (i = 0; i < b.get_n_contas(); i++) {
				b.get_contas()[i].incrementarRendimentos();
			}
		}
	}
	
	public int cobrarJuros(double juros) {
		b = Banco.getInstance();
		int cobrados = 0;
		if (this.loginState) {
			int i;
			for (i = 0; i < b.get_n_contas(); i++) { 
				if (b.get_contas()[i].cobrarJuros(juros)) { 
					cobrados = cobrados + 1;
				}
			}
		}
		return cobrados;
	}
}
