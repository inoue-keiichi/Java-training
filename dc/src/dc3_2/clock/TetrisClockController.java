package dc3_2.clock;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dc3_2.clock.TetrisClockService.Digit;
import dc3_2.frame.FrameService.ScreenMode;
import dc3_2.tetris.clazz.DigitTetrisField;
import dc3_2.tetris.clazz.TetrisField;
import dc3_2.utils.CanvasUtil;
import dc3_2.utils.ColorUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

		this.es = Executors.newSingleThreadExecutor();
		this.es.execute(() -> {
			while (true) {
				synchronized (this) {
					if (tetrisClockService.ischangedMinutes()) {
						draw();
					}
				}
				try {
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
				}
			}
		});
	}

	public void initView(Stage stage) {
		stage.setWidth(1600);
		stage.setHeight(400);
	}

	private void draw() {
		tetrisClockService.drawDot(clockDot.getGraphicsContext2D(), clockDot.getWidth(), clockDot.getHeight());
		tetrisClockService.setClock(leftHoursTetrisField, rightHoursTetrisField, leftMinutesTetrisField,
				rightMinutesTetrisField);
	}
}
