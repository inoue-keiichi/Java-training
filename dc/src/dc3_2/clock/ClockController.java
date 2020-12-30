package dc3_2.clock;

import java.net.URL;
import java.util.ResourceBundle;

import dc3_2.MainService;
import dc3_2.menu.MenuDialogObservable;
import dc3_2.clock.ClockService.ClockType;
import dc3_2.frame.FrameService;
import dc3_2.frame.FrameService.ScreenMode;
import dc3_2.utils.ColorUtils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClockController implements Initializable {
	private static final String DEFAULT_TIMER_TEXT = "00:00:00";

	private Thread thread;
	private ClockService clockService;
	private GraphicsContext gc;
	private MenuDialogObservable menuDialogObservable;
	private Stage timeStage;
	private MainService mainService;
	private AnalogClockService analogClockService;
	private TetrisClockService tetrisClockService;

	private Text timeText;
	private double x;
	private double y;

	private double preWidth;

	@FXML
	private Canvas clockCanvas;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		clockService = ClockService.getInstance();
		mainService = MainService.getInstance();
		analogClockService = new AnalogClockService();
		tetrisClockService = new TetrisClockService();
		timeText = new Text();
		timeText.setText(DEFAULT_TIMER_TEXT);
		gc = clockCanvas.getGraphicsContext2D();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					clockService.updateTime();
					draw();
					try {
						Thread.sleep(clockService.getUpdateSpeed());
					} catch (final InterruptedException e) {
					}
				}
			}
		});
	}

	public void initView(final Stage stage) {
		// init
		this.timeStage = stage;
		this.preWidth = stage.getWidth();
		final VBox pane = (VBox) stage.getScene().getRoot();

		// setup eventHandler
		pane.widthProperty().addListener(evt -> {
			if (clockService.getClockType() == ClockType.DEGITAL) {
				resizeFont(stage);
			}
		});
		pane.heightProperty().addListener(evt -> {
			draw(stage);
		});
		stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue,
					final Boolean isFullScreen) {
				if (clockService.getClockType() == ClockType.DEGITAL) {
					resizeFont(stage);
				}
			}
		});

		// setup bind
		clockCanvas.widthProperty().bind(pane.widthProperty());
		clockCanvas.heightProperty().bind(pane.heightProperty());

		// show timer
		draw(stage);

		this.thread.start();
	}

	private void resizeFont(final Stage stage) {
		if (stage.getWidth() == preWidth) {
			return;
		}
		final String family = clockService.getFont().getFamily();
		timeText.setFont(clockService.getFont());
		if (stage.getWidth() > preWidth) {
			clockService.setFont(Font.font(family, clockService.plusFontSize(timeText.getFont(), stage.getWidth())));
		} else {
			clockService.setFont(Font.font(family, clockService.minusFontSize(timeText.getFont(), stage.getWidth())));
		}
		preWidth = stage.getWidth();
		draw(stage);
	}

	private void draw(final Stage stage) {
		timeText.setFont(clockService.getFont());
		x = (stage.getWidth() - timeText.getLayoutBounds().getWidth()) / 2;
		y = clockCanvas.getHeight() / 2;
		// テトリスでdraw()を呼ぶとバグが起きる。同時にCanvasを描画しようとするため。
		if (clockService.getClockType() == ClockType.TETRIS) {
			return;
		}
		draw();
	}

	private void draw() {
		// Init
		gc.clearRect(0, 0, clockCanvas.getWidth(), clockCanvas.getHeight());
		// Set backgroundColor
		gc.setFill(ColorUtils.get(clockService.getBackgroundColor()));
		gc.fillRect(0, 0, clockCanvas.getWidth(), clockCanvas.getHeight());

		switch (clockService.getClockType()) {
		case ANALOG:
			// clockService.changeUpdateSpeed(Mode.CLOCK);
			analogClockService.setClock(gc, clockCanvas.getWidth(), clockCanvas.getHeight());
			break;
//		case TETRIS:
//			clockService.changeUpdateSpeed(ScreenMode.TETRIS);
//			tetrisClockService.setClock(gc, clockCanvas.getWidth(), clockCanvas.getHeight());
//			break;
		case DEGITAL:
		default:
			// clockService.changeUpdateSpeed(Mode.CLOCK);
			setDegitalClock();
		}
	}

	private void setDegitalClock() {
		gc.setFill(ColorUtils.get(clockService.getFontColor()));
		gc.setFont(clockService.getFont());
		gc.fillText(clockService.getTime(), x, y);
	}
}
