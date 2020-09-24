package dc3_2.tetris.clazz;

import javafx.scene.paint.Color;

public class TetrisField {
	public static int HEIGHT = 20;
	public static int WIDTH = 10;
	public static int BLOCK_SIZE = 20;

	private final Color[][] colors = new Color[HEIGHT][WIDTH];

	public void fixTetrimino(final Tetrimino tetrimino) {
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[r][i][j] != 0) {
					colors[y - 1 + i][x + j] = tetrimino.getColor();
				}
			}
		}
	}

	public Color[][] getColors() {
		return colors;
	}
}
