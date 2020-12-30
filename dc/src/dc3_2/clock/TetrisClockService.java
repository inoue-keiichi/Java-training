package dc3_2.clock;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dc3_2.tetris.TetrisService;
import dc3_2.tetris.clazz.DigitTetrimino;
import dc3_2.tetris.clazz.DigitTetrisField;
import dc3_2.tetris.clazz.Tetrimino;
import dc3_2.tetris.clazz.TetrisField;
import dc3_2.tetris.scenario.Action;
import dc3_2.tetris.scenario.NumberScenarioFactory;
import dc3_2.utils.CanvasUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TetrisClockService {
	public enum Digit {
		HOURS_LEFT, HOURS_RIGHT, MINUTES_LEFT, MINUTES_RIGHT
	}

	final TetrisService tetrisService = new TetrisService();

	boolean hourFlag = false;
	boolean minuteFlag = false;
	boolean secondFlag = false;

	Integer oldHours = null;
	Integer oldMinutes = null;

	final Object leftHoursLock = new Object();
	final Object rightHoursLock = new Object();
	final Object leftMinutesLock = new Object();
	final Object rightMinutesLock = new Object();

	ExecutorService es = Executors.newFixedThreadPool(4);

	public boolean ischangedMinutes() {
		if (oldMinutes != null && oldMinutes == Calendar.getInstance().get(Calendar.MINUTE)) {
			return false;
		}
		return true;
	}

	public void setClock(final DigitTetrisField leftHours, final DigitTetrisField rightHours,
			final DigitTetrisField leftMinutes, final DigitTetrisField rightMinutes) {

		synchronized (this) {
			final int newHours = Calendar.getInstance().get(Calendar.HOUR);
			final int newMinutes = Calendar.getInstance().get(Calendar.MINUTE);

			if (oldHours == null || oldHours / 10 != newHours / 10) {
				setDigitNumber(leftHours.getGraphicsContext(), leftHours.getWidth(), leftHours.getHeight(),
						leftHours.getTetrisField(), NumberScenarioFactory.create(newHours / 10), leftHoursLock);
			}
			if (oldHours == null || oldHours % 10 != newHours % 10) {
				setDigitNumber(rightHours.getGraphicsContext(), rightHours.getWidth(), rightHours.getHeight(),
						rightHours.getTetrisField(), NumberScenarioFactory.create(newHours % 10), rightHoursLock);
			}
			if (oldMinutes == null || oldMinutes / 10 != newMinutes / 10) {
				setDigitNumber(leftMinutes.getGraphicsContext(), leftMinutes.getWidth(), leftMinutes.getHeight(),
						leftMinutes.getTetrisField(), NumberScenarioFactory.create(newMinutes / 10), leftMinutesLock);
			}
			if (oldMinutes == null || oldMinutes % 10 != newMinutes % 10) {
				setDigitNumber(rightMinutes.getGraphicsContext(), rightMinutes.getWidth(), rightMinutes.getHeight(),
						rightMinutes.getTetrisField(), NumberScenarioFactory.create(newMinutes % 10), rightMinutesLock);
			}

			oldMinutes = Calendar.getInstance().get(Calendar.MINUTE);
			oldHours = Calendar.getInstance().get(Calendar.HOUR);
		}

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

	private void setDigitNumber(final GraphicsContext gc, final double width, final double height,
			final TetrisField field, final List<DigitTetrimino> scenarios, final Object ) {
			// init
			CanvasUtil.initGc(gc, width, height);
			field.clear();

		es.execute(() -> {
			synchronized (lock) {
				for (DigitTetrimino scenario : scenarios) {
					// tetrisService.drawFallingTetrimino(gc, scenario.getTetrimino());
					for (Action action : scenario.getActions()) {
						synchronized (this) {
							tetrisService.initGc(gc, width, height);
							tetrisService.drawField(gc, field.getColors());
							move(scenario.getTetrimino(), action);
							tetrisService.drawFallingTetrimino(gc, scenario.getTetrimino());
						}
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
					scenario.getTetrimino().moveDown();
					field.fixTetrimino(scenario.getTetrimino());
				}
			}
		});
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
