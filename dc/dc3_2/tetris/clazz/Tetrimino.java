package dc3_2.tetris.clazz;

import javafx.scene.paint.Color;

public class Tetrimino {
	private int[][][] mino;
	private Color color;

	public Tetrimino() {

	}

	public int[][][] getMino() {
		return mino;
	}

	public void setMino(final int[][][] mino) {
		this.mino = mino;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}
}
