package dc3_2.tetris;

import dc3_2.tetris.clazz.Tetrimino;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class TetrisService {
	public void handleTetorimino(final KeyEvent e, final Tetrimino tetrimino, final Color[][] field) {
		if (e.getEventType() == KeyEvent.KEY_RELEASED
				&& (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.KP_DOWN)) {
			tetrimino.downFallingSpeed();
			return;
		} else if (e.getEventType() == KeyEvent.KEY_RELEASED) {
			return;
		}

		switch (e.getCode()) {
		case LEFT:
		case KP_LEFT:
			tetrimino.moveLeft();
			if (isOutsideX(field, tetrimino)) {
				tetrimino.moveRight();
			}
			break;
		case RIGHT:
		case KP_RIGHT:
			tetrimino.moveRight();
			if (isOutsideX(field, tetrimino)) {
				tetrimino.moveLeft();
			}
			break;
		case DOWN:
		case KP_DOWN:
			tetrimino.upFallingSpeed();
			break;
		case SHIFT:
			tetrimino.rotate();
			while (isOutsideX(field, tetrimino)) {
				if (tetrimino.getX() < field[0].length / 2) {
					tetrimino.moveRight();
				} else if (tetrimino.getX() >= field[0].length / 2) {
					tetrimino.moveLeft();
				}
			}
			break;
		default:
			break;
		}
	}

	public boolean isOutsideX(final Color[][] field, final Tetrimino tetrimino) {
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		final int fieldWidth = field[0].length;
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[r][i][j] != 0
						&& (j + x < 0 || j + x > fieldWidth - 1 || field[i + y][j + x] != null)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isBottom(final Color[][] field, final Tetrimino tetrimino) {
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		final int fieldHeight = field.length;
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[r][i][j] != 0 && (i + y > fieldHeight - 1 || field[i + y][j + x] != null)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isOverlap(final Color[][] field, final Tetrimino tetrimino) {
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[r][i][j] == 1 && field[i + y][j + x] != null) {
					return true;
				}
			}
		}
		return false;
	}

	public void clearLine(final Color[][] field) {
		boolean isLine = true;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == null) {
					isLine = false;
					break;
				}
			}
			if (isLine) {
				for (int j = 0; j < field[i].length; j++) {
					field[i][j] = null;
				}
			}
			isLine = true;
		}

		//
		boolean isNullLine = true;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] != null) {
					isNullLine = false;
					break;
				}
			}
			if (isNullLine) {
				for (int j = i; j > 0; j--) {
					for (int k = 0; k < field[j].length; k++) {
						field[j][k] = field[j - 1][k];
					}
				}
			}
			isNullLine = true;
		}
	}
}
