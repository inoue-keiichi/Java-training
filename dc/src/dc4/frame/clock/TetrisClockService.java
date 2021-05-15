package dc4.frame.clock;

import java.util.Calendar;
import java.util.List;

import dc4.frame.game.tetris.TetrisService;
import dc4.frame.game.tetris.clazz.DigitTetrimino;
import dc4.frame.game.tetris.clazz.DigitTetrisField;
import dc4.frame.game.tetris.clazz.Tetrimino;
import dc4.frame.game.tetris.clazz.TetrisField;
import dc4.frame.game.tetris.scenario.Action;
import dc4.frame.game.tetris.scenario.NumberScenarioFactory;
import dc4.utils.CanvasUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TetrisClockService {
	public enum Digit {
		HOURS_LEFT, HOURS_RIGHT, MINUTES_LEFT, MINUTES_RIGHT
	}

	private final TetrisService tetrisService = new TetrisService();
	private final ClockService clockService = ClockService.getInstance();

	Integer oldHours1 = null;
	Integer oldHours2 = null;
	Integer oldMinutes1 = null;
	Integer oldMinutes2 = null;

	public boolean isChangeHourLeftDigit() {
		final int newHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		final boolean result = oldHours1 == null || oldHours1 / 10 != newHours / 10;
		if (result) {
			oldHours1 = newHours;
		}
		return result;
	}

	public boolean isChangeHourRightDigit() {
		final int newHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		final boolean result = oldHours2 == null || oldHours2 % 10 != newHours % 10;
		if (result) {
			oldHours2 = newHours;
		}
		return result;
	}

	public boolean isChangeMinutesLeftDigit() {
		final int newMinutes = Calendar.getInstance().get(Calendar.MINUTE);
		final boolean result = oldMinutes1 == null || oldMinutes1 / 10 != newMinutes / 10;
		if (result) {
			oldMinutes1 = newMinutes;
		}
		return result;
	}

	public boolean isChangeMinutesRightDigit() {
		final int newMinutes = Calendar.getInstance().get(Calendar.MINUTE);
		final boolean result = oldMinutes2 == null || oldMinutes2 % 10 != newMinutes % 10;
		if (result) {
			oldMinutes2 = newMinutes;
		}
		return result;
	}

	public void setClockDigit(DigitTetrisField field, int num) {
		setDigitNumber(field.getGraphicsContext(), field.getWidth(), field.getHeight(),
				field.getTetrisField(), NumberScenarioFactory.create(num));
	}

	public void drawDot(final GraphicsContext gc, final double width, final double height) {
		tetrisService.initGc(gc, width, height);
		gc.setFill(Color.WHITE);
		gc.fillRect(TetrisField.BLOCK_SIZE * 1, TetrisField.BLOCK_SIZE * 7, TetrisField.BLOCK_SIZE,
				TetrisField.BLOCK_SIZE);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(TetrisField.BLOCK_SIZE * 1, TetrisField.BLOCK_SIZE * 7, TetrisField.BLOCK_SIZE,
				TetrisField.BLOCK_SIZE);

		gc.setFill(Color.WHITE);
		gc.fillRect(TetrisField.BLOCK_SIZE * 1, TetrisField.BLOCK_SIZE * 10, TetrisField.BLOCK_SIZE,
				TetrisField.BLOCK_SIZE);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(TetrisField.BLOCK_SIZE * 1, TetrisField.BLOCK_SIZE * 10, TetrisField.BLOCK_SIZE,
				TetrisField.BLOCK_SIZE);
	}

	private void setDigitNumber(final GraphicsContext gc, final double width,
			final double height,
			final TetrisField field, final List<DigitTetrimino> scenarios) {
		// init
		synchronized (this) {
			CanvasUtil.initGc(gc, width, height);
		}
		field.clear();
		for (DigitTetrimino scenario : scenarios) {
			for (Action action : scenario.getActions()) {
				synchronized (this) {
					CanvasUtil.initGc(gc, width, height);
					tetrisService.drawField(gc, field.getColors());
					move(scenario.getTetrimino(), action);
					tetrisService.drawFallingTetrimino(gc, scenario.getTetrimino());
				}
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			scenario.getTetrimino().moveDown();
			field.fixTetrimino(scenario.getTetrimino());
		}
	}

	private void move(Tetrimino mino, Action action) {
		switch (action) {
		case RIGHT:
			mino.moveRight();
			break;
		case LEFT:
			mino.moveLeft();
			break;
		case DOWN:
			mino.moveDown();
			break;
		case ROTATE:
			mino.rotate();
			break;
		}
	}
}
