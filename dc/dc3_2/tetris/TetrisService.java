package dc3_2.tetris;

import java.util.Random;

import dc3_2.tetris.clazz.Tetrimino;
import dc3_2.tetris.clazz.Tetris;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class TetrisService {
	private static int X_INIT = 3;
	private static int Y_INIT = 0;
	private static int R_INIT = 0;
	private static long FAST_FALLING_SPEED = 100;
	private static long FALLING_SPEED = 500;

	private final Random random;

	private final Tetris tetris;
	private final int fieldHeight;
	private final int fieldWidth;
	private int x;
	private int y;
	private int r;
	private long fallingSpeed;

	public TetrisService() {
		final long seed = System.currentTimeMillis();
		this.random = new Random(seed);
		this.tetris = new Tetris();
		this.fieldHeight = this.tetris.getField().length;
		this.fieldWidth = this.tetris.getField()[0].length;
		this.x = X_INIT;
		this.y = Y_INIT;
		this.r = R_INIT;
		this.fallingSpeed = FALLING_SPEED;
	}

	public Tetrimino getRandomTetrimino() {
		final int index = random.nextInt(tetris.getTetriminos().size());
		return tetris.getTetriminos().get(index);
	}

	public void fixTetrimino(final int[][] field, final Color[][] fieldColors, final Tetrimino tetorimino) {
		for (int i = 0; i < tetorimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetorimino.getMino()[0][i].length; j++) {
				if (tetorimino.getMino()[r][i][j] != 0) {
					field[y - 1 + i][x + j] = tetorimino.getMino()[r][i][j];
					fieldColors[y - 1 + i][x + j] = tetorimino.getColor();
				}
			}
		}
	}

	public void handleTetorimino(final KeyEvent e, final Tetrimino tetrimino) {
		switch (e.getCode()) {
		case LEFT:
		case KP_LEFT:
			x--;
			if (isOutsideX(tetris.getField(), tetrimino)) {
				x++;
			}
			break;
		case RIGHT:
		case KP_RIGHT:
			x++;
			if (isOutsideX(tetris.getField(), tetrimino)) {
				x--;
			}
			break;
		case DOWN:
		case KP_DOWN:
			break;
		case SHIFT:
			r = (r + 1) % tetrimino.getMino().length;
			break;
		default:
			break;
		}
	}

	public boolean isOutsideX(final int[][] field, final Tetrimino tetorimino) {
		for (int i = 0; i < tetorimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetorimino.getMino()[0][i].length; j++) {
				if (tetorimino.getMino()[r][i][j] != 0
						&& (j + x < 0 || j + x > tetris.getField()[0].length - 1 || field[i + y][j + x] != 0)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isBottom(final int[][] field, final Tetrimino tetorimino) {
		for (int i = 0; i < tetorimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetorimino.getMino()[0][i].length; j++) {
				if (tetorimino.getMino()[r][i][j] != 0 && (i + y > fieldHeight - 1 || field[i + y][j + x] != 0)) {
					return true;
				}
			}
		}
		return false;
	}

	public Tetris getTetris() {
		return tetris;
	}

	public void initXYR() {
		x = X_INIT;
		y = Y_INIT;
		r = R_INIT;
	}

	public int getX() {
		return x;
	}

	public void moveRight() {
		x++;
	}

	public void moveLeft() {
		x--;
	}

	public int getY() {
		return y;
	}

	public void moveUnder() {
		y++;
	}

	public int getR() {
		return r;
	}

	public void setR(final int r) {
		this.r = r;
	}

	public int getFieldWidth() {
		return fieldWidth;
	}

	public int getFieldHeight() {
		return fieldHeight;
	}

	public long getFallingSpeed() {
		return this.fallingSpeed;
	}

	public void upFallingSpeed() {
		this.fallingSpeed = FAST_FALLING_SPEED;
	}

	public void downFallingSpeed() {
		this.fallingSpeed = FALLING_SPEED;
	}
}
