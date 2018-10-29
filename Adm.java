import java.util.Scanner;

public class Adm {
	private String user;
	private String pass;
	private String nome;
	private boolean loginState = false;
	
	private Banco b;
	
	public Adm() {
		user = "root";
		//pass = "toor";
		Hashing h = Hashing.getInstance();
		pass = h.hash("toor");
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
	public boolean comparaSenha(String tentativa) { 
		Hashing h = Hashing.getInstance();
		return (h.hash(tentativa).equals(this.pass)); 
	}
	
	public String[] getContasString() throws NoADMSessionException {
		b = Banco.getInstance();
		if (this.loginState) {
			int i;
			String[] out = new String[b.get_n_contas()];
			for (i = 0; i < b.get_n_contas(); i++) { out[i] = b.get_contas()[i].to_string(); }
			return out;
		} else { throw new NoADMSessionException(); }
		//return null;
	}
	
	public void incrementarRendimentos() throws NoADMSessionException {
		b = Banco.getInstance();
		if (this.loginState) {
			int i;
			for (i = 0; i < b.get_n_contas(); i++) {
				b.get_contas()[i].incrementarRendimentos();
			}
		} else {
			throw new NoADMSessionException();
		}
	}
	
	public int cobrarJuros(double juros) throws NoADMSessionException {
		b = Banco.getInstance();
		int cobrados = 0;
		if (this.loginState) {
			int i;
			for (i = 0; i < b.get_n_contas(); i++) { 
				if (b.get_contas()[i].cobrarJuros(juros)) { 
					cobrados = cobrados + 1;
				}
			}
		} else {
			throw new NoADMSessionException();
		}
		return cobrados;
	}
}
