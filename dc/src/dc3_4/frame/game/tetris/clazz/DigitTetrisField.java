package dc3_4.frame.game.tetris.clazz;

import dc3_4.frame.clock.TetrisClockService.Digit;
import javafx.scene.canvas.GraphicsContext;

public class DigitTetrisField {
	private Digit digit;
	private TetrisField tetrisField;
	private GraphicsContext gc;
	private double width;
	private double height;

	public DigitTetrisField(final Digit digit, final GraphicsContext gc, final double width, final double height) {
		this.digit = digit;
		this.tetrisField = new TetrisField();
		this.gc = gc;
		this.width = width;
		this.height = height;
	}

	public Digit getDigit() {
		return digit;
	}

	public TetrisField getTetrisField() {
		return tetrisField;
	}

	public GraphicsContext getGraphicsContext() {
		return gc;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
