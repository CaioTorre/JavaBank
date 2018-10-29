import java.math.BigInteger;
import java.util.Arrays;

public class Hashing {
	private int[] r = new int[64];
	private int[] k = new int[64];
	private int h0;
	private int h1;
	private int h2;
	private int h3;
	private byte[][] words = new byte[16][4];
	
	private static Hashing self;
	
	private Hashing() { super(); }
	
	public static Hashing getInstance() {
		if (self == null) { self = new Hashing(); }
		return self;
	}
	
	private byte[] catArray(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	private byte[] intToByteArray( final int i ) {
		BigInteger bigInt = BigInteger.valueOf(i);   
		return bigInt.toByteArray();
	}
	private void init() {
		int i;
		for (i = 0; i < 64; i++) { k[i] = (int)Math.floor(Math.abs(Math.sin(i + 1)) * Math.pow(2, 32)); }
		h0 = 0x67452301;
		h1 = 0xEFCDAB89;
		h2 = 0x98BADCFE;
		h3 = 0x10325476;
		int j, l;
		
		int t1[] = {7, 12, 17, 22};
		int t2[] = {5, 9, 14, 20};
		int t3[] = {4, 11, 16, 23};
		int t4[] = {6, 10, 15, 21};
		
		for (j = 0; j < 4; j++) {
			for (i = 0; i < 4; i++) {
				for (l = 0; l < 4; l++) {
					if (i == 0) { k[j * 16 + l] = t1[l]; }
					if (i == 1) { k[j * 16 + l] = t2[l]; }
					if (i == 2) { k[j * 16 + l] = t3[l]; }
					if (i == 3) { k[j * 16 + l] = t4[l]; }
				}
			}
		}
	}
	
	private int getVal(byte[] arr) {
		return arr[3] + 256 * arr[2] + 256 * 256 * arr[1] + 256 * 256 * 256 * arr[0];
	}
	
	private int leftrotate(int arr, int k) { return (arr << k) | (arr >> (32 - k)); }
	
	public String hash(String in) {
		init();
		byte[] msg = in.getBytes();
		while ((msg.length * 8) % 512 > 0) { msg = catArray(msg, new byte[] {0x00}); }
		//System.out.print("Msg = ");
		//System.out.println(Arrays.toString(msg));
		//msg = catArray(msg, intToByteArray(in.length() * 16));
		
		int a = h0;
		int b = h1;
		int c = h2;
		int d = h3;
		int f, g;
		int i, j;
		int temp;
		byte[] current = new byte[64];
		while (msg.length > 0) {
			current = Arrays.copyOfRange(msg, 0, 64);
			msg = Arrays.copyOfRange(msg, 64, msg.length);
			for (j = 0; j < 16; j++) { words[j] = Arrays.copyOfRange(current, 4*j, 4*j + 4); }
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
				b = leftrotate((a + f + k[i] + getVal(words[g])), r[i]) + b;
				a = temp;
			}
			h0 = h0 + a;
			h1 = h1 + b;
			h2 = h2 + c;
			h3 = h3 + d;
		}
		
		return intArrToHex(new int[] {h0, h1, h2, h3});
	}
	
	private String intArrToHex(int[] arr) {
		StringBuilder builder = new StringBuilder(arr.length * 8);
		for (int b : arr) {
			builder.append(byteToUnsignedHex(b));
		}
		return builder.toString();
	}
	
	private String byteToUnsignedHex(int i) {
    String hex = Integer.toHexString(i);
    while(hex.length() < 8){
      hex = "0" + hex; 
    }
    return hex;
  }
}