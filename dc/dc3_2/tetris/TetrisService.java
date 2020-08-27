package dc3_2.tetris;

import java.util.Random;

import dc3_2.tetris.clazz.Tetrimino;
import dc3_2.tetris.clazz.Tetris;

public class TetrisService {
	private final Random random;

	private final Tetris tetris;

	public TetrisService() {
		final long seed = System.currentTimeMillis();
		this.random = new Random(seed);
		this.tetris = new Tetris();
	}

	public Tetrimino getRandomTetrimino() {
		final int index = random.nextInt(tetris.getTetriminos().size());
		return tetris.getTetriminos().get(index);
	}

	public Tetris getTetris() {
		return tetris;
	}
}
