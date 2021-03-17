package dc3_4.frame.game.tetris.scenario;

import static dc3_4.frame.game.tetris.scenario.Action.*;

import java.util.ArrayList;
import java.util.List;

import dc3_4.frame.game.tetris.TetriminoFactory.TetriminoForm;
import dc3_4.frame.game.tetris.clazz.DigitTetrimino;

public class NumberScenarioFactory {
	public static List<DigitTetrimino> create(int num) {
		switch (num) {
		case 0:
			return createDigit0();
		case 1:
			return createDigit1();
		case 2:
			return createDigit2();
		case 3:
			return createDigit3();
		case 4:
			return createDigit4();
		case 5:
			return createDigit5();
		case 6:
			return createDigit6();
		case 7:
			return createDigit7();
		case 8:
			return createDigit8();
		case 9:
			return createDigit9();
		default:
			throw new IllegalArgumentException();
		}
	}

	private static List<DigitTetrimino> createDigit0() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions2 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions4 = { ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { ROTATE, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { ROTATE, ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { ROTATE, ROTATE, ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { ROTATE, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions10 = { RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions11 = { ROTATE, ROTATE, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.Z, actions1));
				add(new DigitTetrimino(TetriminoForm.Z, actions2));
				add(new DigitTetrimino(TetriminoForm.O, actions3));
				add(new DigitTetrimino(TetriminoForm.I, actions4));
				add(new DigitTetrimino(TetriminoForm.I, actions5));
				add(new DigitTetrimino(TetriminoForm.J, actions6));
				add(new DigitTetrimino(TetriminoForm.J, actions7));
				add(new DigitTetrimino(TetriminoForm.L, actions8));
				add(new DigitTetrimino(TetriminoForm.T, actions9));
				add(new DigitTetrimino(TetriminoForm.O, actions10));
				add(new DigitTetrimino(TetriminoForm.T, actions11));
			}
		};
	}

	private static List<DigitTetrimino> createDigit1() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { ROTATE, ROTATE, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN, DOWN };
				Action[] actions2 = { DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { ROTATE, ROTATE, ROTATE, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN };
				Action[] actions4 = { ROTATE, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { ROTATE, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { ROTATE, ROTATE, ROTATE, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.L, actions1));
				add(new DigitTetrimino(TetriminoForm.J, actions2));
				add(new DigitTetrimino(TetriminoForm.L, actions3));
				add(new DigitTetrimino(TetriminoForm.I, actions4));
				add(new DigitTetrimino(TetriminoForm.L, actions5));
				add(new DigitTetrimino(TetriminoForm.T, actions6));
			}
		};
	}

	private static List<DigitTetrimino> createDigit2() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions2 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions3 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions4 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions10 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions11 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.J, actions1));
				add(new DigitTetrimino(TetriminoForm.L, actions2));
				add(new DigitTetrimino(TetriminoForm.I, actions3));
				add(new DigitTetrimino(TetriminoForm.O, actions4));
				add(new DigitTetrimino(TetriminoForm.I, actions5));
				add(new DigitTetrimino(TetriminoForm.L, actions6));
				add(new DigitTetrimino(TetriminoForm.J, actions7));
				add(new DigitTetrimino(TetriminoForm.O, actions8));
				add(new DigitTetrimino(TetriminoForm.I, actions9));
				add(new DigitTetrimino(TetriminoForm.J, actions10));
				add(new DigitTetrimino(TetriminoForm.L, actions11));
			}
		};
	}

	private static List<DigitTetrimino> createDigit3() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions2 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { ROTATE, ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions4 = { ROTATE, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.Z, actions1));
				add(new DigitTetrimino(TetriminoForm.Z, actions2));
				add(new DigitTetrimino(TetriminoForm.J, actions3));
				add(new DigitTetrimino(TetriminoForm.L, actions4));
				add(new DigitTetrimino(TetriminoForm.L, actions5));
				add(new DigitTetrimino(TetriminoForm.L, actions6));
				add(new DigitTetrimino(TetriminoForm.O, actions7));
				add(new DigitTetrimino(TetriminoForm.S, actions8));
				add(new DigitTetrimino(TetriminoForm.S, actions9));
			}
		};
	}

	private static List<DigitTetrimino> createDigit4() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { ROTATE, ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN, DOWN };
				Action[] actions2 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions4 = { ROTATE, ROTATE, ROTATE, ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN, DOWN };
				Action[] actions5 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { ROTATE, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { RIGHT, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.J, actions1));
				add(new DigitTetrimino(TetriminoForm.J, actions2));
				add(new DigitTetrimino(TetriminoForm.I, actions3));
				add(new DigitTetrimino(TetriminoForm.L, actions4));
				add(new DigitTetrimino(TetriminoForm.J, actions5));
				add(new DigitTetrimino(TetriminoForm.I, actions6));
				add(new DigitTetrimino(TetriminoForm.I, actions7));
				add(new DigitTetrimino(TetriminoForm.O, actions8));
				add(new DigitTetrimino(TetriminoForm.O, actions9));
			}
		};
	}

	private static List<DigitTetrimino> createDigit5() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions2 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions3 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions4 = { RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions10 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions11 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.J, actions1));
				add(new DigitTetrimino(TetriminoForm.L, actions2));
				add(new DigitTetrimino(TetriminoForm.I, actions3));
				add(new DigitTetrimino(TetriminoForm.O, actions4));
				add(new DigitTetrimino(TetriminoForm.I, actions5));
				add(new DigitTetrimino(TetriminoForm.L, actions6));
				add(new DigitTetrimino(TetriminoForm.J, actions7));
				add(new DigitTetrimino(TetriminoForm.O, actions8));
				add(new DigitTetrimino(TetriminoForm.I, actions9));
				add(new DigitTetrimino(TetriminoForm.J, actions10));
				add(new DigitTetrimino(TetriminoForm.L, actions11));
			}
		};
	}

	private static List<DigitTetrimino> createDigit6() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { ROTATE, ROTATE, ROTATE, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions2 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN };
				Action[] actions4 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { ROTATE, ROTATE, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { ROTATE, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions10 = { LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions11 = { DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.J, actions1));
				add(new DigitTetrimino(TetriminoForm.S, actions2));
				add(new DigitTetrimino(TetriminoForm.O, actions3));
				add(new DigitTetrimino(TetriminoForm.L, actions4));
				add(new DigitTetrimino(TetriminoForm.O, actions5));
				add(new DigitTetrimino(TetriminoForm.Z, actions6));
				add(new DigitTetrimino(TetriminoForm.Z, actions7));
				add(new DigitTetrimino(TetriminoForm.I, actions8));
				add(new DigitTetrimino(TetriminoForm.T, actions9));
				add(new DigitTetrimino(TetriminoForm.Z, actions10));
				add(new DigitTetrimino(TetriminoForm.Z, actions11));
			}
		};
	}

	private static List<DigitTetrimino> createDigit7() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions2 = { ROTATE, ROTATE, ROTATE, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { ROTATE, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions4 = { DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { ROTATE, ROTATE, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { LEFT, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.O, actions1));
				add(new DigitTetrimino(TetriminoForm.J, actions2));
				add(new DigitTetrimino(TetriminoForm.J, actions3));
				add(new DigitTetrimino(TetriminoForm.O, actions4));
				add(new DigitTetrimino(TetriminoForm.I, actions5));
				add(new DigitTetrimino(TetriminoForm.J, actions6));
				add(new DigitTetrimino(TetriminoForm.L, actions7));
			}
		};
	}

	private static List<DigitTetrimino> createDigit8() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN };
				Action[] actions2 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN };
				Action[] actions3 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN };
				Action[] actions4 = { ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions5 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions10 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions11 = { ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions12 = { LEFT, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.I, actions1));
				add(new DigitTetrimino(TetriminoForm.J, actions2));
				add(new DigitTetrimino(TetriminoForm.L, actions3));
				add(new DigitTetrimino(TetriminoForm.S, actions4));
				add(new DigitTetrimino(TetriminoForm.Z, actions5));
				add(new DigitTetrimino(TetriminoForm.O, actions6));
				add(new DigitTetrimino(TetriminoForm.O, actions7));
				add(new DigitTetrimino(TetriminoForm.Z, actions8));
				add(new DigitTetrimino(TetriminoForm.S, actions9));
				add(new DigitTetrimino(TetriminoForm.L, actions10));
				add(new DigitTetrimino(TetriminoForm.J, actions11));
				add(new DigitTetrimino(TetriminoForm.I, actions12));
			}
		};
	}

	private static List<DigitTetrimino> createDigit9() {
		return new ArrayList<DigitTetrimino>() {
			{
				Action[] actions1 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions2 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions3 = { DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions4 = { ROTATE, ROTATE, ROTATE, RIGHT, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN,
						DOWN, DOWN };
				Action[] actions5 = { RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions6 = { LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions7 = { LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions8 = { ROTATE, LEFT, LEFT, LEFT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions9 = { ROTATE, ROTATE, ROTATE, RIGHT, DOWN, DOWN, DOWN, DOWN, DOWN };
				Action[] actions10 = { LEFT, LEFT, DOWN, DOWN, DOWN, DOWN };
				Action[] actions11 = { ROTATE, DOWN, DOWN, DOWN, DOWN };
				add(new DigitTetrimino(TetriminoForm.Z, actions1));
				add(new DigitTetrimino(TetriminoForm.Z, actions2));
				add(new DigitTetrimino(TetriminoForm.Z, actions3));
				add(new DigitTetrimino(TetriminoForm.T, actions4));
				add(new DigitTetrimino(TetriminoForm.S, actions5));
				add(new DigitTetrimino(TetriminoForm.S, actions6));
				add(new DigitTetrimino(TetriminoForm.O, actions7));
				add(new DigitTetrimino(TetriminoForm.O, actions8));
				add(new DigitTetrimino(TetriminoForm.J, actions9));
				add(new DigitTetrimino(TetriminoForm.Z, actions10));
				add(new DigitTetrimino(TetriminoForm.L, actions11));
			}
		};
	}
}
