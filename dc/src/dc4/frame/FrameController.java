package dc4.frame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import dc4.frame.clock.ClockService;
import dc4.frame.clock.ClockType;
import dc4.frame.menu.MenuDialogController;
import dc4.frame.menu.MenuDialogObservable;
import dc4.frame.news.NewsObservable;
import dc4.utils.ColorUtils;
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
	private static final int MAIN_PANE_NUM = 2;

	private ClockService clockService;
	private MenuDialogObservable menuDialogObservable;
	private NewsObservable newsObservable;
	private Stage timeStage;
	private static FrameService frameService;

	private Text timeText;

	private double preWidth;
	private double preHeight;

	@FXML
	private MenuBar menuBar;

	@FXML
	private void onOpenDialog() throws IOException {
		final URL location = getClass().getResource("menu/MenuDialogView.fxml");
		final FXMLLoader menuLoader = new FXMLLoader(location);
		final VBox root = (VBox) menuLoader.load();
		final Scene scene = new Scene(root, 400, 300);
		final Stage stage = new Stage();
		stage.setScene(scene);

		// setup menuDialog to bind
		final MenuDialogController menuDialogController = menuLoader.getController();
		menuDialogController.initView(stage);

		stage.setX(frameService.getX() - scene.getWidth());
		stage.setY(frameService.getY());
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
		VBox pane = (VBox) this.timeStage.getScene().getRoot();
		frameService.deleteElements(pane);

		changeNewsBarPane();
		changeClockPane(mode);

		frameService.setScreenMode(mode);
	}

	private void changeNewsPane(String url) {
		VBox pane = (VBox) this.timeStage.getScene().getRoot();
		final FXMLLoader loader = frameService.createNewsLoader();
		Node node = null;
		try {
			node = (Node) loader.load();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		pane.getChildren().set(MAIN_PANE_NUM, node);
	}

	private void changeClockPane(ScreenMode mode) {
		final FXMLLoader loader = frameService.createClockLoader(mode);
		Node node = null;
		try {
			node = (Node) loader.load();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		VBox pane = (VBox) this.timeStage.getScene().getRoot();
		pane.getChildren().add(node);
		frameService.bindClockController(timeStage, mode, loader);
	}

	private void changeNewsBarPane() {
		final FXMLLoader loader = frameService.createNewsBarLoader();
		Node node = null;
		try {
			node = (Node) loader.load();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		Pane pane = (Pane) this.timeStage.getScene().getRoot();
		pane.getChildren().add(node);
		frameService.bindNewsController(timeStage, loader);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		clockService = ClockService.getInstance();
		frameService = FrameService.getInstance();
		timeText = new Text();
		timeText.setText(DEFAULT_TIMER_TEXT);

		// Add observer
		menuDialogObservable = MenuDialogObservable.getInstance();
		menuDialogObservable.addPropertyChangeListener(this);
		newsObservable = NewsObservable.getInstance();
		newsObservable.addPropertyChangeListener(this);
	}

	public void initView(final Stage stage) {
		this.timeStage = stage;
		stage.setX(frameService.getX());
		stage.setY(frameService.getY());
		stage.show();

		changeMainPane(frameService.getScreenMode());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (Objects.equals(evt.getPropertyName(), "news")) {
			changeNewsPane((String) evt.getNewValue());
			return;
		}

		final ScreenMode newVal = (ScreenMode) evt.getNewValue();
		if (!Objects.equals(frameService.getScreenMode(), newVal)) {
			changeMainPane(newVal);
		}

		this.timeText.setFont(clockService.getFont());
		resizeStage(this.timeStage, clockService.getClockType(), this.timeText);
		this.timeStage.getScene().setFill(ColorUtils.get(clockService.getBackgroundColorName()));
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
