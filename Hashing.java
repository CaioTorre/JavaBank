//import java.math.BigInteger;
import java.util.Arrays;

public abstract class Hashing {	
	private static byte[] catArray(byte[] a, byte[] b) { //Joins two byte arrays into one
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	//private static byte[] intToByteArray( final int i ) { //Converts an int into a byte array
	//	BigInteger bigInt = BigInteger.valueOf(i);   
	//	return bigInt.toByteArray();
	//}
	
	private static int getVal(byte[] arr) { //Gets the integer value of four bytes (from array)
		return arr[3] + 256 * arr[2] + 256 * 256 * arr[1] + 256 * 256 * 256 * arr[0];
	}
	
	private static int leftrotate(int arr, int k) { return (arr << k) | (arr >> (32 - k)); } //Bitwise left rotation
	
	private static String intArrToHex(int[] arr) { //Converts integer array to hexadec string
		StringBuilder builder = new StringBuilder(arr.length * 8);
		for (int b : arr) {
			builder.append(byteToUnsignedHex(b));
		}
		return builder.toString();
	}
	
	private static String byteToUnsignedHex(int i) { //Converts integer to hexadec string
		String hex = Integer.toHexString(i);
		while(hex.length() < 8){
			hex = "0" + hex; 
		}
		return hex;
	}
  
	public static String hash(String in) {
		int i;
		int[] r = new int[64];
		long[] k = new long[64];
		//=================================== INIT HASHING FUNCTION VARIABLES ===================================
		for (i = 0; i < 64; i++) { k[i] = (long)Math.floor(Math.abs(Math.sin(i + 1)) * Math.pow(2, 32)); }
		
		long h0 = (long)Math.floor(0x67452301);
		long h1 = (long)Math.floor(0xEFCDAB89);
		long h2 = (long)Math.floor(0x98BADCFE);
		long h3 = (long)Math.floor(0x10325476);
		int j, l;
		
		int t1[] = {7, 12, 17, 22};
		int t2[] = {5, 9, 14, 20};
		int t3[] = {4, 11, 16, 23};
		int t4[] = {6, 10, 15, 21};
		
		for (j = 0; j < 4; j++) {
			for (i = 0; i < 4; i++) {
				for (l = 0; l < 4; l++) {
					if (j == 0) { r[j * 16 + i * 4 + l] = t1[l]; }
					if (j == 1) { r[j * 16 + i * 4 + l] = t2[l]; }
					if (j == 2) { r[j * 16 + i * 4 + l] = t3[l]; }
					if (j == 3) { r[j * 16 + i * 4 + l] = t4[l]; }
					//System.out.printf("i = %d; j = %d; l = %d\t added %d\n", i, j, l, r[j*16 + l]);
				}
			}
		}
		byte[] msg = in.getBytes();
		while ((msg.length * 8) % 512 > 0) { msg = catArray(msg, new byte[] {0x00}); } //Should be % 512 < 448, then append original size as integer
		
		long a;
		long b;
		long c;
		long d;
		long f, g;
		long temp;
		byte[] current = new byte[64];
		byte[][] words = new byte[16][4];
		
		for (i = 0; i < 8; i ++) {
			for (j = 0; j < 8; j++) {
				System.out.printf("\tT[%2d]=%13d", i * 8 + j, k[i * 8 + j]);
			}
			System.out.println("");
		}
		System.out.println("");
		
		for (i = 0; i < 8; i ++) {
			for (j = 0; j < 8; j++) {
				System.out.printf("\tr[%2d]=%13d", i * 8 + j, r[i * 8 + j]);
			}
			System.out.println("");
		}
		System.out.println("");
		
		//System.out.printf("A=%d\tB=%d\tC=%d\tD=%d\n", h0, h1, h2, h3);
		System.out.printf("A=%s\tB=%s\tC=%s\tD=%s\n", Integer.toUnsignedString((int)h0), Integer.toUnsignedString((int)h1), Integer.toUnsignedString((int)h2), Integer.toUnsignedString((int)h3));
		//=================================== BEGIN HASHING FUNCTION ===================================
		while (msg.length > 0) {
			current = Arrays.copyOfRange(msg, 0, 64);		//Pop a new block
			msg = Arrays.copyOfRange(msg, 64, msg.length);	//from message
			for (j = 0; j < 16; j++) { words[j] = Arrays.copyOfRange(current, 4*j, 4*j + 4); } //Split current block into 16 words
			a = h0;
			b = h1;
			c = h2;
			d = h3;
			for (i = 0; i < 64; i++) {
				if (i < 16) {
					f = (b & c) | ((~b) & d);
					g = i;
				} else if (i < 32) {
					f = (d & b) | ((~d) & c);
					g = (5 * i + 1) % 16;
				} else if (i < 48) {
					f = (b ^ c) ^ d;
					g = (3 * i + 5) % 16;
				} else {
					f = c ^ (b | (~d));
					g = (7 * i) % 16;
				}
				temp = d;
				d = c;
				c = b;
				b = leftrotate((int)(a + f + k[i] + getVal(words[(int)g])), r[i]) + b;
				a = temp;
				//System.out.printf("[i = %d] A=%d\tB=%d\tC=%d\tD=%d\n", i, a, b, c, d);
				System.out.printf("[i = %d] A=%s\tB=%s\tC=%s\tD=%s\n", i, Integer.toUnsignedString((int)a), Integer.toUnsignedString((int)b), Integer.toUnsignedString((int)c), Integer.toUnsignedString((int)d));
				
			}
			h0 = h0 + a;
			h1 = h1 + b;
			h2 = h2 + c;
			h3 = h3 + d;
		}
		
		return intArrToHex(new int[] {(int)h0, (int)h1, (int)h2, (int)h3}); //Join the 4 ints into a big hexadec string
	}
}