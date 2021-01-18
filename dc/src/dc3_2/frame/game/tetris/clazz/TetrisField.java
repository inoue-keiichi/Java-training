package dc3_2.frame.game.tetris.clazz;

import javafx.scene.paint.Color;

public class TetrisField {
	public static int HEIGHT = 20;
	public static int WIDTH = 10;
	public static int BLOCK_SIZE = 20;

	private Color[][] colors;

	public TetrisField() {
		colors = new Color[HEIGHT][WIDTH];
	}

	public TetrisField(int height, int width) {
		colors = new Color[height][width];
	}

	public boolean fixTetrimino(final Tetrimino tetrimino) {
		boolean all = true;
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				// フィールドからはみ出た部分は描画しない
				if (tetrimino.getMino()[r][i][j] == 0) {
					continue;
				}
				if (y - 1 + i > -1 && x + j > -1) {
					colors[y - 1 + i][x + j] = tetrimino.getColor();
				} else {
					all = false;
				}
			}
		}
		return all;
	}

	public Color[][] getColors() {
		return colors;
	}

	public void setColors(Color[][] colors) {
		if (colors.length != WIDTH || colors[0].length != HEIGHT) {
			throw new IllegalArgumentException();
		}
		this.colors = colors;
	}

	public void clear() {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				colors[i][j] = null;
			}
		}
	}
}
