package ch09.ex02;

public class Bit {
	public static int INT_BIT_MAX_LENGTH = 32;

	/**
	 * 引数で与えられた整数のビット数を返します。
	 * 
	 * @param int 整数
	 * @return int 1のビット数
	 */
	public static int countBits(int x) {
		int count = 0;
		for (int i = 0; i < INT_BIT_MAX_LENGTH; i++) {
			if ((x & 0x00000001) == 0x00000001) {
				count++;
			}
			x = x >>> 1;
		}
		return count;
	}

	// ネットで調べた、ループしないでカウントする方法。
	public static int count_bits(int n) {
		n = (n & 0x55555555) + (n >> 1 & 0x55555555);
		n = (n & 0x33333333) + (n >> 2 & 0x33333333);
		n = (n & 0x0f0f0f0f) + (n >> 4 & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + (n >> 8 & 0x00ff00ff);
		n = (n & 0x0000ffff) + (n >> 16 & 0x0000ffff);
		return n;
	}

	public static void main(String[] args) {
		int x = 0xffffffff;
		
		long startTime = System.nanoTime();
		int count = countBits(x);
		long endTime = System.nanoTime();
		System.out.println("自作メソッド");
		System.out.println("1のビット数：" + count);
		System.out.println("処理時間：" + (endTime - startTime) + "nsec");
		System.out.println("------------------------------");
		
		startTime = System.nanoTime();
		count = count_bits(x);
		endTime = System.nanoTime();
		System.out.println("公開されているアルゴリズムのメソッド");
		System.out.println("1のビット数：" + count);
		System.out.println("処理時間：" + (endTime - startTime) + "nsec");
	}
}
