package dc4.frame.game.tetris;

import java.util.Random;

import dc4.frame.game.tetris.clazz.Tetrimino;
import javafx.scene.paint.Color;

public class TetriminoFactory {
	public enum TetriminoForm {
		I(0), J(1), L(2), O(3), S(4), T(5), Z(6);

		private final int id;

		private TetriminoForm(final int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	public static TetriminoForm getForm(final int id) {
		final TetriminoForm[] types = TetriminoForm.values();
		for (final TetriminoForm type : types) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}

	private final static Random random = new Random(System.currentTimeMillis());

	private final static int[][][] minoI = { { { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } };

	private static final int[][][] minoJ = { { { 0, 1, 0, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } };

	private static final int[][][] minoL = { { { 0, 1, 1, 1 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } };

	private static final int[][][] minoO = { { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, } };

	private static final int[][][] minoS = { { { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, } };

	private static final int[][][] minoT = { { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, } };

	private static final int[][][] minoZ = { { { 0, 1, 1, 0 }, { 0, 0, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, },
			{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, } };

	public static Tetrimino createRandomTetrimino() {
		final int num = random.nextInt(TetriminoForm.values().length);
		return createTetrimino(getForm(num));
	}

	public static Tetrimino createTetrimino(final TetriminoForm form) {
		switch (form) {
		case I:
			return new Tetrimino(Color.CYAN, minoI);

		case J:
			return new Tetrimino(Color.BLUE, minoJ);

		case L:
			return new Tetrimino(Color.ORANGE, minoL);

		case O:
			return new Tetrimino(Color.YELLOW, minoO);

		case S:
			return new Tetrimino(Color.GREENYELLOW, minoS);

		case T:
			return new Tetrimino(Color.BLUEVIOLET, minoT);

		case Z:
			return new Tetrimino(Color.RED, minoZ);
		default:
			throw new IllegalArgumentException();
		}
	}
}
