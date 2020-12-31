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

	private final TetrisService tetrisService = new TetrisService();
	private final ClockService clockService = ClockService.getInstance();

	boolean hourFlag = false;
	boolean minuteFlag = false;
	boolean secondFlag = false;

	Integer oldHours = null;
	Integer oldMinutes = null;

	final Object leftHoursLock = new Object();
	final Object rightHoursLock = new Object();
	final Object leftMinutesLock = new Object();
	final Object rightMinutesLock = new Object();

	final ExecutorService hles = Executors.newSingleThreadExecutor();
	final ExecutorService hres = Executors.newSingleThreadExecutor();
	final ExecutorService mles = Executors.newSingleThreadExecutor();
	final ExecutorService mres = Executors.newSingleThreadExecutor();

	public boolean ischangedMinutes() {
		if (oldMinutes != null && oldMinutes == Calendar.getInstance().get(Calendar.MINUTE)) {
			return false;
		}
		return true;
	}

	public void setClock(final DigitTetrisField leftHours, final DigitTetrisField rightHours,
			final DigitTetrisField leftMinutes, final DigitTetrisField rightMinutes) {

		synchronized (this) {
			final int newHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			final int newMinutes = Calendar.getInstance().get(Calendar.MINUTE);

			if (oldHours == null || oldHours / 10 != newHours / 10) {
				setDigitNumber(hles, leftHours.getGraphicsContext(), leftHours.getWidth(), leftHours.getHeight(),
						leftHours.getTetrisField(), NumberScenarioFactory.create(newHours / 10), leftHoursLock);
			}
			if (oldHours == null || oldHours % 10 != newHours % 10) {
				setDigitNumber(hres, rightHours.getGraphicsContext(), rightHours.getWidth(), rightHours.getHeight(),
						rightHours.getTetrisField(), NumberScenarioFactory.create(newHours % 10), rightHoursLock);
			}
			if (oldMinutes == null || oldMinutes / 10 != newMinutes / 10) {
				setDigitNumber(mles, leftMinutes.getGraphicsContext(), leftMinutes.getWidth(), leftMinutes.getHeight(),
						leftMinutes.getTetrisField(), NumberScenarioFactory.create(newMinutes / 10), leftMinutesLock);
			}
			if (oldMinutes == null || oldMinutes % 10 != newMinutes % 10) {
				setDigitNumber(mres, rightMinutes.getGraphicsContext(), rightMinutes.getWidth(),
						rightMinutes.getHeight(),
						rightMinutes.getTetrisField(), NumberScenarioFactory.create(newMinutes % 10), rightMinutesLock);
			}

			oldMinutes = Calendar.getInstance().get(Calendar.MINUTE);
			oldHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
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

	private void setDigitNumber(final ExecutorService es, final GraphicsContext gc, final double width,
			final double height,
			final TetrisField field, final List<DigitTetrimino> scenarios, final Object lock) {

		es.execute(() -> {
			// init
			CanvasUtil.initGc(gc, width, height);
			//CanvasUtil.initGc(gc, ColorUtils.get(clockService.getBackgroundColor()), width, height);
			field.clear();

			for (DigitTetrimino scenario : scenarios) {
				// tetrisService.drawFallingTetrimino(gc, scenario.getTetrimino());
				for (Action action : scenario.getActions()) {
					synchronized (this) {
						CanvasUtil.initGc(gc, width, height);
						//CanvasUtil.initGc(gc, ColorUtils.get(clockService.getBackgroundColor()), width, height);
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
