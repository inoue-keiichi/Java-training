package dc3_4.frame.game.tetris.clazz;

import dc3_4.frame.game.tetris.TetriminoFactory;
import dc3_4.frame.game.tetris.TetriminoFactory.TetriminoForm;
import dc3_4.frame.game.tetris.scenario.Action;

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
