package dc3_3.time;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import dc3_3.MainService;
import dc3_3.menu.MenuDialogObservable;
import dc3_3.menu.MenuPopup;
import dc3_3.utils.ColorUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	private MenuPopup menuPopup;

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

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		timeService = TimeService.getInstance();
		mainService = MainService.getInstance();
		menuPopup = new MenuPopup();
		timeText = new Text();
		timeText.setText(DEFAULT_TIMER_TEXT);
		gc = timeCanvas.getGraphicsContext2D();
		thread = new Thread(new Runnable() {
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
		});

		// Add observer
		menuDialogObservable = MenuDialogObservable.getInstance();
		menuDialogObservable.addObserver(this);
	}

	public void initView(final Stage stage) {
		// init
		this.timeStage = stage;
		this.preWidth = stage.getWidth();
		final VBox pane = (VBox) stage.getScene().getRoot();
		pane.setStyle("-fx-background-color:" + timeService.getBackgroundColor());

		// setup listener
		pane.widthProperty().addListener(evt -> {
			resizeFont(stage);
		});
		pane.heightProperty().addListener(evt -> {
			resizeFont(stage);
		});
		stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue,
					final Boolean isFullScreen) {
				resizeFont(stage);
			}
		});
		pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getButton() != MouseButton.SECONDARY) {
				this.menuPopup.getContextMenu().hide();
				return;
			}
			this.menuPopup.getContextMenu().show(pane, event.getScreenX(), event.getScreenY());
		});

		// setup bind
		timeCanvas.widthProperty().bind(pane.widthProperty());
		timeCanvas.heightProperty().bind(pane.heightProperty());

		// show timer
		draw(stage);
		resizeStage(stage, this.timeText);
		this.thread.start();
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

	private void resizeFont(final Stage stage) {
		if (stage.getWidth() == preWidth) {
			return;
		}
		final String family = timeService.getFont().getFamily();
		timeText.setFont(timeService.getFont());
		if (stage.getWidth() > preWidth) {
			timeService.setFont(Font.font(family, timeService.plusFontSize(timeText.getFont(), stage.getWidth())));
		} else {
			timeService.setFont(Font.font(family, timeService.minusFontSize(timeText.getFont(), stage.getWidth())));
		}
		preWidth = stage.getWidth();
		draw(stage);
	}

	private void resizeStage(final Stage stage, final Text timeText) {
		final double textWidth = timeText.getLayoutBounds().getWidth();
		final double textHeight = timeText.getLayoutBounds().getHeight();

		preWidth = textWidth * 1.25;
		preHeight = textHeight * 1.5;

		stage.setWidth(preWidth);
		stage.setHeight(preHeight);

		draw(stage);
	}

	private void draw(final Stage stage) {
		timeText.setFont(timeService.getFont());
		x = (stage.getWidth() - timeText.getLayoutBounds().getWidth()) / 2;
		y = (stage.getHeight() + timeText.getBaselineOffset()) / 2;

		draw();
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
}
