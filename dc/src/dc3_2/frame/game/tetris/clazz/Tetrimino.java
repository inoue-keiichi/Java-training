package dc3_2.frame.game.tetris.clazz;

import javafx.scene.paint.Color;

public class Tetrimino {
	private static int X_INIT = 3;
	private static int Y_INIT = 0;
	private static int R_INIT = 0;
	private static long FAST_FALLING_SPEED = 100;
	private static long FALLING_SPEED = 500;

	private int[][][] forms;
	private Color color;
	private int x;
	private int y;
	private int r;
	private long speed;

	public Tetrimino(final Color color, final int[][][] forms) {
		x = X_INIT;
		y = Y_INIT;
		r = R_INIT;
		this.color = color;
		this.forms = forms;
		this.speed = FALLING_SPEED;
	}

	public int[][][] getMino() {
		return forms;
	}

	public void setMino(final int[][][] forms) {
		this.forms = forms;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
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

	public void moveDown() {
		y++;
	}

	public void moveUp() {
		y--;
	}

	public int getRotationPosition() {
		return r;
	}

	public void rotate() {
		r = (r + 1) % forms.length;
	}

	public void reverseRotate() {
		if (r == 0) {
			r = forms.length - 1;
			return;
		}
		r = (r - 1) % forms.length;
	}

	public long getSpeed() {
		return this.speed;
	}

	public void upFallingSpeed() {
		speed = FAST_FALLING_SPEED;
	}

	public void downFallingSpeed() {
		speed = FALLING_SPEED;
	}
}
