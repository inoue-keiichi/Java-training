package ch07.ex02;

public class BasicData {
	public static boolean bol;
	public static char chr;
	public static byte byt;
	public static short sho;
	public static int in;
	public static long lon;
	public static float flt;
	public static double dbl;

	public static void main(String[] args) {
		// boolean型 エラー：型の不一致。
		// chr = true;
		System.out.println("char<-boolean: " + "エラー：型の不一致。");
		// byte型
		chr = 0x4a;
		System.out.println("char<-byte: " + chr);
		// short型
		chr = 0x1a2b;
		System.out.println("char<-short: " + chr);
		// int型 エラー:型の不一致。
		// chr = 0xffffaaaa;
		System.out.println("char<-int: " + "エラー：型の不一致。");
		// long型 エラー：型の不一致。範囲外。
		// chr = 0x11112222ffffffffL;
		System.out.println("char<-long: " + "エラー：型の不一致。範囲外。");
		// float型：エラー：型の不一致。
		// chr = 1f;
		System.out.println("char<-float: " + "エラー：型の不一致。");
		// double型：エラー：型の不一致。
		// chr = 1d;
		System.out.println("char<-double: " + "エラー：型の不一致。");

		// boolean型 エラー：型の不一致。
		// byt = true;
		System.out.println("byte<-boolean: " + "エラー：型の不一致。");
		// char型
		byt = 'c';
		System.out.println("byte<-char: " + byt);
		// short型
		// byt = 0x1a2b;
		System.out.println("byte<-short: " + "エラー：型の不一致。");
		// int型 エラー:型の不一致。
		// byt = 0xffffaaaa;
		System.out.println("byte<-int: " + "エラー：型の不一致。");
		// long型 エラー：型の不一致。範囲外。
		// byt = 0x11112222ffffffffL;
		System.out.println("byte<-long: " + "エラー：型の不一致。");
		// float型：エラー：型の不一致。
		// byt = 1f;
		System.out.println("byte<-float: " + "エラー：型の不一致。");
		// double型：エラー：型の不一致。
		// byt = 1d;
		System.out.println("byte<-double: " + "エラー：型の不一致。");

		// boolean型 エラー：型の不一致。
		// flt = true;
		System.out.println("float<-boolean: " + "エラー：型の不一致。");
		// char型
		flt = 'c';
		System.out.println("float<-char: " + flt);
		// byte型
		flt = 0x4a;
		System.out.println("float<-byte: " + flt);
		// short型
		flt = 0x1a2b;
		System.out.println("float<-short: " + flt);
		// int型 エラー:型の不一致。
		flt = 0xffffaaaa;
		System.out.println("float<-int: " + flt);
		// long型 エラー：型の不一致。範囲外。
		flt = 0x11112222ffffffffL;
		System.out.println("float<-long: " + flt);
		// double型：エラー：型の不一致。
		// flt = 1d;
		System.out.println("float<-double: " + "エラー：型の不一致。");
		
		final int[] ia = new int[4];
		System.out.println(ia[1]);
		System.out.println(ia.length);
		ia[1] = 2;
		System.out.println(ia[1]);
	}
}
