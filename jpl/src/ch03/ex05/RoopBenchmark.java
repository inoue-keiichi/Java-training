package ch03.ex05;

public class RoopBenchmark extends Benchmark {
	private final int roopNum;
	
	RoopBenchmark(int roopNum){
		this.roopNum = roopNum;
	}
	
	@Override
	void benchmark() {
		for (int i = 0; i < roopNum; i++) {
			
		}
	}
	
	public static void main(String args[]) {
		int count = Integer.parseInt(args[0]);
		int roopNum = Integer.parseInt(args[1]);
		long time = new RoopBenchmark(roopNum).repeat(count);
		System.out.println(count + "methods in " + time + " nanoseconds");
	}

}
