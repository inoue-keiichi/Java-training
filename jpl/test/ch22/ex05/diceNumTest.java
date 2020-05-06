package ch22.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

public class diceNumTest {

	@Test
	public void normalTest() {
		int actualResult;
		//2~12の目がでる
		int[] diceResults = new int[11];
		for (int i = 0; i < 10000; i++) {
			actualResult = Dice.sumDiceNum(2);
			assertTrue(actualResult > 1 && actualResult < 13);
			diceResults[actualResult - 2]++;
		}
		for (int diceNum : diceResults) {
			assertTrue(diceNum > -1 && diceNum < 10000);
		}
	}

	@Test
	public void sumDiceNumLongTest() {
		int actualResult;
		//2~12の目がでる
		int[] diceResults = new int[11];
		for (int i = 0; i < 10000; i++) {
			actualResult = Dice.sumDiceNumLong(2);
			assertTrue(actualResult > 1 && actualResult < 13);
			diceResults[actualResult - 2]++;
		}
		for (int diceNum : diceResults) {
			assertTrue(diceNum > -1 && diceNum < 10000);
		}
	}

	@Test
	public void sumDiceNumFloatTest() {
		int actualResult;
		//2~12の目がでる
		int[] diceResults = new int[11];
		for (int i = 0; i < 10000; i++) {
			actualResult = Dice.sumDiceNumFloat(2);
			assertTrue(actualResult > 1 && actualResult < 13);
			diceResults[actualResult - 2]++;
		}
		for (int diceNum : diceResults) {
			assertTrue(diceNum > -1 && diceNum < 10000);
		}
	}

	@Test
	public void sumDiceNumDoubleTest() {
		int actualResult;
		//2~12の目がでる
		int[] diceResults = new int[11];
		for (int i = 0; i < 10000; i++) {
			actualResult = Dice.sumDiceNumDouble(2);
			assertTrue(actualResult > 1 && actualResult < 13);
			diceResults[actualResult - 2]++;
		}
		for (int diceNum : diceResults) {
			assertTrue(diceNum > -1 && diceNum < 10000);
		}
	}
}
