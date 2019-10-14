package ch01.ex10;

public class ImprovedFibonacci {
	
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark="";
		boolean isEven = false;
	
		Fibo[] fibos = new Fibo[9];
		fibos[0] = new Fibo(lo, isEven);
		System.out.println("1: " + lo);
		for (int i = 2; i <= MAX_INDEX; i++) {
			isEven = hi % 2 == 0 ? true : false;
			if (isEven)
				mark = " *";
			else
				mark = "";
			fibos[i-1] = new Fibo(hi, isEven);
			System.out.println(i + ": " + hi + mark);
			hi = lo + hi;
			lo = hi - lo;			
		}
	}
}
