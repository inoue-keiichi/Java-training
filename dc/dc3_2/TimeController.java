package dc3_2;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import dc3_2.menu.MenuDialogController;
import dc3_2.menu.MenuDialogObservable;
import dc3_2.utils.ColorUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TimeController implements Observer, Initializable {
	private static final String DEFAULT_TIMER_TEXT = "00:00:00";

	private Thread thread;
	private TimeService timeService;
	private GraphicsContext gc;
	private MenuDialogObservable menuDialogObservable;
	private Stage timeStage;
	private MainService mainService;

	private Text timeText;
	private double x;
	private double y;

	private double preWidth;
	private double preHeight;

	@FXML
	private Canvas timeCanvas;
	@FXML
	private MenuBar timeMenuBar;
	@FXML
	private MenuItem property;

	@FXML
	private void onOpenDialog() throws IOException {
		final URL location = getClass().getResource("./menu/MenuDialogView.fxml");
		final FXMLLoader menuLoader = new FXMLLoader(location);
		final Pane root = (Pane) menuLoader.load();
		final Scene scene = new Scene(root, 300, 200);
		final Stage stage = new Stage();
		stage.setScene(scene);

		// setup menuDialog to bind
		final MenuDialogController menuDialogController = menuLoader.getController();
		menuDialogController.initView(stage);

		stage.setX(mainService.getX() - scene.getWidth());
		stage.setY(mainService.getY());
		stage.setResizable(false);

		timeMenuBar.setDisable(true);
		stage.showAndWait();
		timeMenuBar.setDisable(false);
	}

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
		// init
		this.timeStage = stage;
		this.preWidth = stage.getWidth();
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
		pane.setStyle("-fx-background-color:" + timeService.getBackgroundColor());

		// show timer
		draw(stage);
		resizeStage(stage, this.timeText);
		this.thread.start();
	}

	public void setFontSize(final Stage stage) {
		if (stage.getWidth() == preWidth) {
			return;
		}
		final String family = timeService.getFont().getFamily();
		timeText.setFont(timeService.getFont());
		if (stage.getWidth() > preWidth) {
			timeService.setFont(Font.font(family, plusFontSize(timeText.getFont(), stage.getWidth())));
		} else {
			timeService.setFont(Font.font(family, minusFontSize(timeText.getFont(), stage.getWidth())));
		}
		preWidth = stage.getWidth();
		draw(stage);
	}

	private double plusFontSize(final Font font, final double width) {
		final String family = font.getFamily();
		double size = font.getSize();
		final Text preText = new Text();
		final Text text = new Text();
		text.setText(DEFAULT_TIMER_TEXT);
		preText.setText(DEFAULT_TIMER_TEXT);
		while (true) {
			text.setFont(Font.font(family, size));
			preText.setFont(Font.font(family, size + 1));
			if (width * 4 / 5 - text.getLayoutBounds().getWidth() < 0) {
				break;
			}
			size++;
		}
		return size;
	}

	private double minusFontSize(final Font font, final double width) {
		final String family = font.getFamily();
		double size = font.getSize();
		final Text preText = new Text();
		final Text text = new Text();
		text.setText(DEFAULT_TIMER_TEXT);
		preText.setText(DEFAULT_TIMER_TEXT);
		while (true) {
			text.setFont(Font.font(family, size));
			preText.setFont(Font.font(family, size - 1));
			if (width * 4 / 5 - text.getLayoutBounds().getWidth() > 0) {
				break;
			}
			size--;
		}
		return size;
	}

	private void draw() {
		// Init
		gc.clearRect(0, 0, timeCanvas.getWidth(), timeCanvas.getHeight());
		// Set backgroundColor
		gc.setFill(ColorUtils.get(timeService.getBackgroundColor()));
		gc.fillRect(0, 0, timeCanvas.getWidth(), timeCanvas.getHeight());
		// write text
		gc.setFill(ColorUtils.get(timeService.getFontColor()));
		gc.setFont(timeService.getFont());
		gc.fillText(timeService.getTime(), x, y);
	}

	private void draw(final Stage stage) {
		timeText.setFont(timeService.getFont());
		x = (stage.getWidth() - timeText.getLayoutBounds().getWidth()) / 2;
		y = stage.getHeight() / 2;

		System.out.println(stage.getWidth() + ", " + timeText.getLayoutBounds().getWidth() + ", " + x);

		draw();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		timeService = TimeService.getInstance();
		mainService = MainService.getInstance();
		timeText = new Text();
		timeText.setText(DEFAULT_TIMER_TEXT);
		gc = timeCanvas.getGraphicsContext2D();
		thread = new Thread(new Timer());

		// Add observer
		menuDialogObservable = MenuDialogObservable.getInstance();
		menuDialogObservable.addObserver(this);
	}

	@Override
	public void update(final Observable o, final Object arg) {
		this.timeText.setFont(timeService.getFont());
		resizeStage(this.timeStage, this.timeText);
		this.gc.setStroke(ColorUtils.get(timeService.getFontColor()));
		this.timeStage.getScene().setFill(ColorUtils.get(timeService.getBackgroundColor()));
		final VBox pane = (VBox) this.timeStage.getScene().getRoot();
		pane.setStyle("-fx-background-color:" + timeService.getBackgroundColor());
	}

	private void resizeStage(final Stage stage, final Text timeText) {
		final double textWidth = timeText.getLayoutBounds().getWidth();
		final double textHeight = timeText.getLayoutBounds().getHeight();

		preWidth = textWidth * 1.25;
		preHeight = timeMenuBar.getHeight() + textHeight * 2.0;

		stage.setWidth(preWidth);
		stage.setHeight(preHeight);

		draw(stage);
	}
}
