package ch02.ex07;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

// isFinite()を作るのはよくない考え。理由は、終端操作を終えているかどうかを確認するために
// 終端動作を実行すると、これ以降引数に指定したStreamを利用できなくなるから。
public class MainTest {

	@Test
	public void test_true() {
		final Integer[] array = { 1, 4, 9, 16 };
		final Stream<Integer> stream = Stream.of(array);
		stream.forEach(e -> {
		});
		assertEquals(true, Main.isFinite(stream));
	}

	@Test
	public void test_false() {
		final Integer[] array = { 1, 4, 9, 16 };
		final Stream<Integer> stream = Stream.of(array);
		assertEquals(false, Main.isFinite(stream));
	}
}
