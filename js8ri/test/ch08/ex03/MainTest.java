package ch08.ex03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class MainTest {

	@Test
	public void test_operand_normal() {
		final int actual1 = Main.gcdOperand(780, 300);
		assertEquals(60, actual1);

		final int actual2 = Main.gcdOperand(300, 780);
		assertEquals(60, actual2);
	}

	@Test
	public void test_operand_normal_zero() {
		final int actual1 = Main.gcdOperand(100, 0);
		assertEquals(100, actual1);

		final int actual2 = Main.gcdOperand(0, 100);
		assertEquals(100, actual2);
	}

	@Test
	public void test_operand_normal_negative() {
		final int actual1 = Main.gcdOperand(-100, -75);
		assertEquals(25, actual1);

		final int actual2 = Main.gcdOperand(-75, -100);
		assertEquals(25, actual2);

		final int actual3 = Main.gcdOperand(4, -3);
		assertEquals(1, actual3);
	}

	@Test
	public void test_floorMod_normal() {
		final int actual1 = Main.gcdFloorMod(780, 300);
		assertEquals(60, actual1);

		final int actual2 = Main.gcdFloorMod(300, 780);
		assertEquals(60, actual2);
	}

	@Test
	public void test_floorMod_normal_zero() {
		final int actual1 = Main.gcdFloorMod(100, 0);
		assertEquals(100, actual1);

		final int actual2 = Main.gcdFloorMod(0, 100);
		assertEquals(100, actual2);
	}

	@Test
	public void test_floorMod_normal_negative() {
		final int actual1 = Main.gcdFloorMod(-100, -75);
		assertEquals(25, actual1);

		final int actual2 = Main.gcdFloorMod(-75, -100);
		assertEquals(25, actual2);
	}

	@Test
	public void test_rem_normal() {
		final int actual1 = Main.gcdRem(780, 300);
		assertEquals(60, actual1);

		final int actual2 = Main.gcdRem(300, 780);
		assertEquals(60, actual2);
	}

	@Test
	public void test_rem_normal_zero() {
		final int actual1 = Main.gcdRem(100, 0);
		assertEquals(100, actual1);

		final int actual2 = Main.gcdRem(0, 100);
		assertEquals(100, actual2);
	}

	@Test
	public void test_rem_normal_negative() {
		final int actual1 = Main.gcdRem(-100, -75);
		assertEquals(25, actual1);

		final int actual2 = Main.gcdRem(-75, -100);
		assertEquals(25, actual2);
	}
}
