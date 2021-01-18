package dc3_2.frame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dc3_2.MainService;
import dc3_2.frame.FrameService.ScreenMode;
import dc3_2.frame.clock.ClockController;
import dc3_2.frame.clock.ClockService;
import dc3_2.frame.clock.ClockService.ClockType;
import dc3_2.frame.clock.TetrisClockController;
import dc3_2.frame.game.tetris.TetrisController;
import dc3_2.frame.menu.MenuDialogController;
import dc3_2.frame.menu.MenuDialogObservable;
import dc3_2.utils.ColorUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FrameController implements PropertyChangeListener, Initializable {
	private static final String DEFAULT_TIMER_TEXT = "00:00:00";

	private ClockService clockService;
	private MenuDialogObservable menuDialogObservable;
	private Stage timeStage;
	private MainService mainService;

	private Text timeText;

	private double preWidth;
	private double preHeight;

	private ScreenMode mode;

	@FXML
	private MenuBar menuBar;

	@FXML
	private void onOpenDialog() throws IOException {
		final URL location = getClass().getResource("menu/MenuDialogView.fxml");
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

		menuBar.setDisable(true);
		stage.showAndWait();
		menuBar.setDisable(false);
	}

	@FXML
	private void onOpenTetris() {
		changeMainPane(ScreenMode.TETRIS);
		this.timeStage.setWidth(300);
		this.timeStage.setHeight(500);
		this.timeStage.show();
	}

	private void changeMainPane(ScreenMode mode) {
		URL location = null;
		switch (mode) {
		case CLOCK:
			location = getClass().getResource("clock/ClockView.fxml");
			break;
		case TETRIS:
			location = getClass().getResource("game/tetris/TetrisView.fxml");
			break;
		case TETRIS_CLOCK:
			location = getClass().getResource("clock/TetrisClockView.fxml");
			break;
		default:
		}
		final FXMLLoader loader = new FXMLLoader(location);
		Node root = null;
		try {
			root = (Node) loader.load();
			this.mode = mode;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		VBox pane = (VBox) this.timeStage.getScene().getRoot();

		// すでに画面があれば削除する。
		if (pane.getChildren().size() > 1) {
			pane.getChildren().remove(1);
		}
		pane.getChildren().add(root);

		// setup timeView to bind
		switch (mode) {
		case CLOCK:
			final ClockController clockController = loader.getController();
			clockController.initView(this.timeStage);
			break;
		case TETRIS_CLOCK:
			final TetrisClockController tetrisClockController = loader.getController();
			tetrisClockController.initView(this.timeStage);
			break;
		case TETRIS:
			final TetrisController tetrisController = loader.getController();
			tetrisController.initView(this.timeStage);
			break;
		default:
		}
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		clockService = ClockService.getInstance();
		mainService = MainService.getInstance();
		timeText = new Text();
		timeText.setText(DEFAULT_TIMER_TEXT);

		// Add observer
		menuDialogObservable = MenuDialogObservable.getInstance();
		menuDialogObservable.addPropertyChangeListener(this);
	}

	public void initView(final Stage stage) {
		this.timeStage = stage;
		changeMainPane(ScreenMode.CLOCK);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		final ScreenMode newVal = (ScreenMode) evt.getNewValue();
		if (!mode.equals(newVal)) {
			changeMainPane(newVal);
		}

		this.timeText.setFont(clockService.getFont());
		resizeStage(this.timeStage, clockService.getClockType(), this.timeText);
		this.timeStage.getScene().setFill(ColorUtils.get(clockService.getBackgroundColor()));
	}

	private void resizeStage(final Stage stage, final ClockType clockType, final Text timeText) {
		if (clockType == ClockType.ANALOG) {
			stage.setWidth(300);
			stage.setHeight(400);
			return;
		} else if (clockType == ClockType.TETRIS) {
			stage.setWidth(700);
			stage.setHeight(400);
			return;
		}

		final double textWidth = timeText.getLayoutBounds().getWidth();
		final double textHeight = timeText.getLayoutBounds().getHeight();

		preWidth = textWidth * 1.25;
		preHeight = menuBar.getHeight() + textHeight * 2.0;

		stage.setWidth(preWidth);
		stage.setHeight(preHeight);
	}
}
