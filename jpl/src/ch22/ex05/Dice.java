package ch22.ex05;

import java.util.Random;

public class Dice {
	public static void main(String[] args) {
		final int trialNum = 10000;
		int actualResult;
		System.out.println("trialNum: " + trialNum);
		//2~12の目がでる
		int[] diceResults = new int[11];
		for (int i = 0; i < trialNum; i++) {
			actualResult = Dice.sumDiceNum(2);
			diceResults[actualResult - 2]++;
		}
		System.out.println("the case of nextInt()");
		for (int i = 0; i < diceResults.length; i++) {
			System.out.print(String.format("%3s", i + 2));
			System.out.print(" | ");
			System.out.println(diceResults[i]);
		}
		System.out.println();

		//2~12の目がでる
		diceResults = new int[11];
		for (int i = 0; i < trialNum; i++) {
			actualResult = Dice.sumDiceNumLong(2);
			diceResults[actualResult - 2]++;
		}
		System.out.println("the case of nextLong()");
		for (int i = 0; i < diceResults.length; i++) {
			System.out.print(String.format("%3s", i + 2));
			System.out.print(" | ");
			System.out.println(diceResults[i]);
		}
		System.out.println();

		//2~12の目がでる
		diceResults = new int[11];
		for (int i = 0; i < trialNum; i++) {
			actualResult = Dice.sumDiceNumFloat(2);
			diceResults[actualResult - 2]++;
		}
		System.out.println("the case of nextFloat()");
		for (int i = 0; i < diceResults.length; i++) {
			System.out.print(String.format("%3s", i + 2));
			System.out.print(" | ");
			System.out.println(diceResults[i]);
		}
		System.out.println();

		//2~12の目がでる
		diceResults = new int[11];
		for (int i = 0; i < trialNum; i++) {
			actualResult = Dice.sumDiceNumDouble(2);
			diceResults[actualResult - 2]++;
		}
		System.out.println("the case of nextDuble()");
		for (int i = 0; i < diceResults.length; i++) {
			System.out.print(String.format("%3s", i + 2));
			System.out.print(" | ");
			System.out.println(diceResults[i]);
		}
		System.out.println();
	}

	public static int sumDiceNum(final int dices) {
		final Random random = new Random();
		int sum = 0;
		for (int i = 0; i < dices; i++) {
			sum += random.nextInt(6) + 1;
		}
		return sum;
	}

	public static int sumDiceNumLong(final int dices) {
		final Random random = new Random();
		int sum = 0;
		for (int i = 0; i < dices; i++) {
			sum += Math.abs(random.nextLong() % 6) + 1;
		}
		return sum;
	}

	public static int sumDiceNumFloat(final int dices) {
		final Random random = new Random();
		int sum = 0;
		float ran;
		for (int i = 0; i < dices; i++) {
			do {
				ran = random.nextFloat() * 6.0f;
			} while (ran == 0f); // 0の目はないので振り直す
			sum += Math.ceil(ran);
		}
		return sum;
	}

	public static int sumDiceNumDouble(final int dices) {
		final Random random = new Random();
		int sum = 0;
		double ran;
		for (int i = 0; i < dices; i++) {
			do {
				ran = random.nextDouble() * 6.0;
			} while (ran == 0); // 0の目はないので振り直す
			sum += Math.ceil(ran);
		}
		return sum;
	}
}
