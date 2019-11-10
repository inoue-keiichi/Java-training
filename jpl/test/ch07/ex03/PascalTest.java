package ch07.ex03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class PascalTest {

	@Test
	void createNextPascalRowTest() throws Exception {
		Pascal pascal = new Pascal();
		Method method = Pascal.class.getDeclaredMethod("createNextPascalRow", int[].class);
		method.setAccessible(true);
		int[] args = { 1, 3, 3, 1 };
		int[] actual = (int[]) method.invoke(pascal, args);
		int[] expected = { 1, 4, 6, 4, 1 };

		assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	void createPascalMatrixTest() throws Exception {
		//正常処理のテスト
		Pascal pascal = new Pascal(4);
		Method method = Pascal.class.getDeclaredMethod("createPascalMatrix");
		method.setAccessible(true);
		int[][] actual = (int[][]) method.invoke(pascal);
		int[][] expected = new int[4][];
		expected[0] = new int[] { 1 };
		expected[1] = new int[] { 1, 1 };
		expected[2] = new int[] { 1, 2, 1 };
		expected[3] = new int[] { 1, 3, 3, 1 };
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[i].length; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
		
		//例外処理のテスト。自然数以外を引数にした場合。		
		try{
			new Pascal(-3);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(),"引数は自然数のみ有効です。");
		}
	}
}
