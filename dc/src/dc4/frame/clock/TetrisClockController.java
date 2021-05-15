package dc4.frame.clock;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dc4.frame.clock.TetrisClockService.Digit;
import dc4.frame.game.tetris.clazz.DigitTetrisField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class TetrisClockController implements Initializable {
	@FXML
	Canvas hoursLeftSide;
	@FXML
	Canvas hoursRightSide;
	@FXML
	Canvas minutesLeftSide;
	@FXML
	Canvas minutesRightSide;
	@FXML
	Canvas clockDot;

	private TetrisClockService tetrisClockService;

	private DigitTetrisField leftHoursTetrisField;
	private DigitTetrisField rightHoursTetrisField;
	private DigitTetrisField leftMinutesTetrisField;
	private DigitTetrisField rightMinutesTetrisField;

	private ExecutorService es;

	private ExecutorService eshl;
	private ExecutorService eshr;
	private ExecutorService esml;
	private ExecutorService esmr;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tetrisClockService = new TetrisClockService();
		this.leftHoursTetrisField = new DigitTetrisField(Digit.HOURS_LEFT, hoursLeftSide.getGraphicsContext2D(),
				hoursLeftSide.getWidth(), hoursLeftSide.getHeight());
		this.rightHoursTetrisField = new DigitTetrisField(Digit.HOURS_RIGHT, hoursRightSide.getGraphicsContext2D(),
				hoursRightSide.getWidth(), hoursRightSide.getHeight());
		this.leftMinutesTetrisField = new DigitTetrisField(Digit.MINUTES_LEFT, minutesLeftSide.getGraphicsContext2D(),
				minutesLeftSide.getWidth(), minutesLeftSide.getHeight());
		this.rightMinutesTetrisField = new DigitTetrisField(Digit.MINUTES_RIGHT,
				minutesRightSide.getGraphicsContext2D(), minutesRightSide.getWidth(), minutesRightSide.getHeight());

		tetrisClockService.drawDot(clockDot.getGraphicsContext2D(), clockDot.getWidth(), clockDot.getHeight());
		this.eshl = Executors.newSingleThreadExecutor();
		this.eshl.execute(() -> {
			while (true) {
				if (tetrisClockService.isChangeHourLeftDigit()) {
					final int num = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) / 10;
					drawDigit(leftHoursTetrisField, num);
				}
				try {
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		this.eshr = Executors.newSingleThreadExecutor();
		this.eshr.execute(() -> {
			while (true) {
				if (tetrisClockService.isChangeHourRightDigit()) {
					final int num = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) % 10;
					drawDigit(rightHoursTetrisField, num);
				}
				try {
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		this.esml = Executors.newSingleThreadExecutor();
		this.esml.execute(() -> {
			while (true) {
				if (tetrisClockService.isChangeMinutesLeftDigit()) {
					final int num = Calendar.getInstance().get(Calendar.MINUTE) / 10;
					drawDigit(leftMinutesTetrisField, num);
				}
				try {
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		this.esmr = Executors.newSingleThreadExecutor();
		this.esmr.execute(() -> {
			while (true) {
				if (tetrisClockService.isChangeMinutesRightDigit()) {
					final int num = Calendar.getInstance().get(Calendar.MINUTE) % 10;
					drawDigit(rightMinutesTetrisField, num);
				}
				try {
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		//		this.es = Executors.newSingleThreadExecutor();
		//		this.es.execute(() -> {
		//			while (true) {
		//				synchronized (this) {
		//					if (tetrisClockService.ischangedMinutes()) {
		//						draw();
		//					}
		//				}
		//				try {
		//					Thread.sleep(1000);
		//				} catch (final InterruptedException e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		});
	}

	public void initView(Stage stage) {
		stage.setWidth(1600);
		stage.setHeight(400);
	}

	//	private void draw() {
	//		tetrisClockService.drawDot(clockDot.getGraphicsContext2D(), clockDot.getWidth(), clockDot.getHeight());
	//		tetrisClockService.setClock(leftHoursTetrisField, rightHoursTetrisField, leftMinutesTetrisField,
	//				rightMinutesTetrisField);
	//	}

	private void drawDigit(DigitTetrisField field, int num) {
		//tetrisClockService.drawDot(clockDot.getGraphicsContext2D(), clockDot.getWidth(), clockDot.getHeight());
		tetrisClockService.setClockDigit(field, num);
	}

	//	private void draw(ClockDigit clockDigit) {
	//		tetrisClockService.drawDot(clockDot.getGraphicsContext2D(), clockDot.getWidth(), clockDot.getHeight());
	//		switch (clockDigit) {
	//		case HOUR_LEFT:
	//			tetrisClockService.setHourLeftDigit();
	//		case HOUR_RIGHT:
	//			tetrisClockService.setHourRightDigit();
	//		case MINUTES_LEFT:
	//			tetrisClockService.setMinutesLeftDigit();
	//		case MINUTES_RIGHT:
	//			tetrisClockService.setMinutesLeftDigit();
	//		default:
	//			throw new IllegalArgumentException();
	//		}
	//	}
}
