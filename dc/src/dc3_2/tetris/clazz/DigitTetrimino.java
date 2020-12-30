package dc3_2.tetris.clazz;

import dc3_2.tetris.TetriminoFactory;
import dc3_2.tetris.TetriminoFactory.TetriminoForm;
import dc3_2.tetris.scenario.Action;

public class DigitTetrimino {
	private Tetrimino tetrimino;
	private Action[] actions;

	public DigitTetrimino(TetriminoForm form, Action[] actions) {
		this.tetrimino = TetriminoFactory.createTetrimino(form);
		this.actions = actions;
	}

	public Tetrimino getTetrimino() {
		return tetrimino;
	}

	public Action[] getActions() {
		return actions;
	}
}
