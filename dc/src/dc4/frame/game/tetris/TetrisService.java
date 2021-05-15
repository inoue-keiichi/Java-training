package dc4.frame.game.tetris;

import java.util.ArrayList;
import java.util.List;

import dc4.frame.game.tetris.clazz.Tetrimino;
import dc4.frame.game.tetris.clazz.TetrisField;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
				// 側面にめり込む場合は内側に移動する
				if (tetrimino.getX() < field[0].length / 2) {
					tetrimino.moveRight();
				} else if (tetrimino.getX() >= field[0].length / 2) {
					tetrimino.moveLeft();
				}
				// 他のテトリミノと被る場合は回転できない
				if (isOverlap(field, tetrimino)) {
					tetrimino.reverseRotate();
				}
			}
			break;
		default:
			break;
		}
	}

	public void initCanvas(final Canvas canvas) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		// Init
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// Set backgroundColor
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.WHITE);
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void initGc(final GraphicsContext gc, double width, double height) {
		// Init
		gc.clearRect(0, 0, width, height);
		// Set backgroundColor
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);
	}

	public void drawField(final GraphicsContext gc, final Color[][] fieldColors) {
		for (int i = 0; i < TetrisField.HEIGHT; i++) {
			for (int j = 0; j < fieldColors[i].length; j++) {
				if (fieldColors[i][j] != null) {
					gc.setFill(fieldColors[i][j]);
					gc.fillRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
							TetrisField.BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
							TetrisField.BLOCK_SIZE);
				}
			}
		}
	}

	public void drawFallingTetrimino(final GraphicsContext gc, final Tetrimino tetrimino) {
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		gc.setFill(tetrimino.getColor());
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[r][i][j] != 0) {
					gc.fillRect(TetrisField.BLOCK_SIZE * (j + x), TetrisField.BLOCK_SIZE * (i + y),
							TetrisField.BLOCK_SIZE, TetrisField.BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(TetrisField.BLOCK_SIZE * (j + x), TetrisField.BLOCK_SIZE * (i + y),
							TetrisField.BLOCK_SIZE, TetrisField.BLOCK_SIZE);
				}
			}
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

	public List<Integer> clearLines(final Color[][] field) {
		final List<Integer> result = new ArrayList<>();
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
				result.add(i);
			}
			isLine = true;
		}
		return result;
	}

	public void fillBlankLines(final Color[][] field, List<Integer> lineIndexs) {
		boolean isNullLine = true;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] != Color.WHITE) {
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

	public void clearLinesAnimation(final Color[][] field, List<Integer> lineIndexs) {
		for (int index : lineIndexs) {
			for (int j = 0; j < field[index].length; j++) {
				field[index][j] = Color.WHITE;
			}
		}
	}

	public void terminateAnimation(final Color[][] field) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] != null) {
					field[i][j] = Color.WHITE;
				}
			}
		}
	}
}
