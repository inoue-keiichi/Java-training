package ch09.ex09;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class LabeledPointTest {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void test_normal_equals() {
		LabeledPoint labeledPoint = new LabeledPoint("hoge", 2, 2);
		LabeledPoint other1 = new LabeledPoint("hoge", 2, 2);
		LabeledPoint other2 = new LabeledPoint("hoge", 3, 2);
		LabeledPoint other3 = new LabeledPoint("hoge", 2, -2);
		LabeledPoint other4 = new LabeledPoint("fuga", 2, 2);

		assertTrue(labeledPoint.equals(labeledPoint));
		assertTrue(labeledPoint.equals(other1));
		assertTrue(!labeledPoint.equals(other2));
		assertTrue(!labeledPoint.equals(other3));
		assertTrue(!labeledPoint.equals(other4));
		assertTrue(!labeledPoint.equals("hoge"));
	}

	@Test
	public void test_normal_hashCode() {
		LabeledPoint labeledPoint = new LabeledPoint("hoge", 2, 2);
		int actual = labeledPoint.hashCode();
		int actual2 = labeledPoint.hashCode();
		int expected = new LabeledPoint("hoge", 2, 2).hashCode();

		assertEquals(expected, actual);
		assertEquals(actual2, actual);

		Random ran = new Random(System.nanoTime());
		int[] hashs = new int[1000000];
		for (int i = 0; i < hashs.length; i++) {
			hashs[i] = ran.nextInt();
		}
		long count = Arrays.stream(hashs).filter(e -> e == expected).count();
		assertTrue(count < 5);
	}
}
