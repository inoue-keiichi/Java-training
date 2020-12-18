package dc3_1;

import java.net.URL;
import java.util.ResourceBundle;

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

public class TimeController implements Initializable {
	private Thread thread;
	private TimeService timeService;
	private GraphicsContext gc;

	private double x;
	private double y;

	@FXML
	private Canvas timeCanvas;

	private class Timer implements Runnable {
		@Override
		public void run() {
			while (true) {
				timeService.updateTime();
				draw();
				try {
					Thread.sleep(1000); // スリープ１秒
				} catch (final InterruptedException e) {
				}
			}
		}
	}

	public void initView(final Stage stage) {
		final VBox pane = (VBox) stage.getScene().getRoot();
		pane.widthProperty().addListener(evt -> {
			setFontSize(stage);
		});
		pane.heightProperty().addListener(evt -> {
			setFontSize(stage);
		});
		stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue,
					final Boolean isFullScreen) {
				setFontSize(stage);
			}
		});

		timeCanvas.widthProperty().bind(pane.widthProperty());
		timeCanvas.heightProperty().bind(pane.heightProperty());

		final Text text = new Text(timeService.getTime());
		text.setFont(timeService.getFont());

		draw(stage);

		this.thread.start();
	}

	public void setFontSize(final Stage stage) {
		final Text text = new Text(timeService.getTime());
		while (true) {
			text.setFont(timeService.getFont());
			if (stage.getWidth() - text.getLayoutBounds().getWidth() > stage.getWidth() * 0.3) {
				timeService.setFont(Font.font(timeService.getFont().getSize() * 1.0001));
			} else if (stage.getWidth() - text.getLayoutBounds().getWidth() < stage.getWidth() * 0.25) {
				timeService.setFont(Font.font(timeService.getFont().getSize() * 0.9999));
			} else {
				draw(stage);
				break;
			}
		}
	}

	private void draw() {
		gc.clearRect(0, 0, timeCanvas.getWidth(), timeCanvas.getHeight());

		gc.setFont(timeService.getFont());
		gc.fillText(timeService.getTime(), x, y);
	}

	private void draw(final Stage stage) {
		final Text text = new Text(timeService.getTime());
		text.setFont(timeService.getFont());
		x = (stage.getWidth() - text.getLayoutBounds().getWidth()) / 2;
		y = stage.getHeight() / 2;
		draw();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		timeService = new TimeService();
		gc = timeCanvas.getGraphicsContext2D();
		thread = new Thread(new Timer());
	}
}
