package ch08.ex01;

// 符号付きの場合、一番左のBitが符号として利用されている。
// 符号なしの場合、全てのBitを数値として利用している。
// オペランドでの除算、compareメソッドでの比較では符号付きで計算される。
// そのため、符号なしで除算、比較する場合はdivideUnsigned, compareUnsignedを利用する必要がある。
public class Main {
	public static void main(final String[] args) {
		final int addition = 0xffffffff + 0xffffffff;
		System.out.println(addition);
		final int subtraction = 0xffffffff - 0x00000001;
		System.out.println(subtraction);
		final int divition = Integer.divideUnsigned(0xfffffff0, 0x00000002);
		System.out.println(divition);
		System.out.println(0xfffffff0 / 0x00000002);
		final int comparation = Integer.compareUnsigned(0xfffffff0, 0x00000002);
		System.out.println(comparation);
	}
}
