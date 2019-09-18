package src.ch01.ex12;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		
		String[] fibos = new String[9];
		String fibo = new String();
		fibo = "1: "+ lo;
		fibos[0] = fibo;
		for (int i = 1; i < MAX_INDEX; i++) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			fibo = i + ": " + hi + mark;
			fibos[i] = fibo;
			hi = lo + hi;
			lo = hi - lo;
		}
		
		for(int i = 0; i < MAX_INDEX; i++) {
			System.out.println(fibos[i]);
		}		
	}
}
