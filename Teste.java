import java.util.Scanner;
public class Teste {
	public static void main(String []args) {
		Scanner inp = new Scanner( System.in );
		//Hashing h = Hashing.getInstance();
		String s;
		try {
			while (true) {
				System.out.print("Insert string> ");
				s = inp.nextLine();
				//System.out.println("\tHash of \"" + s + "\" is " + h.hash(s) + "\n");
				System.out.println("\tHash of \"" + s + "\" is " + Hashing.hash(s) + "\n");
			} 
		} catch (Exception e) {
			inp.close();
		}
	}
}