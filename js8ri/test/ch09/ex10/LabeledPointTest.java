package ch09.ex10;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class LabeledPointTest {

	@Test
	public void test_normal_compare_0() {
		LabeledPoint target = new LabeledPoint("hoge", 1, 1);
		LabeledPoint other = new LabeledPoint("hoge", 1, 1);
		assertEquals(0, target.compareTo(other));
	}

	@Test
	public void test_normal_compare_1() {
		LabeledPoint target = new LabeledPoint("hoge", 1, 1);
		LabeledPoint other1 = new LabeledPoint("hoge", 0, 1);
		LabeledPoint other2 = new LabeledPoint("hoge", 1, 0);
		LabeledPoint other3 = new LabeledPoint("fuga", 1, 1);

		assertEquals(1, target.compareTo(other1));
		assertEquals(1, target.compareTo(other2));
		assertEquals(1, target.compareTo(other3));
	}

	@Test
	public void test_normal_compare_minus1() {
		LabeledPoint target = new LabeledPoint("hoge", 1, 1);
		LabeledPoint other1 = new LabeledPoint("hoge", 2, 1);
		LabeledPoint other2 = new LabeledPoint("hoge", 1, 2);

		assertEquals(-1, target.compareTo(other1));
		assertEquals(-1, target.compareTo(other2));
	}
}
