package dc4.frame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import dc4.frame.clock.ClockService;
import dc4.frame.clock.ClockType;
import dc4.frame.menu.MenuDialogObservable;
import dc4.frame.news.NewsObservable;
import dc4.utils.ColorUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
	private dc4.frame.news.menu.MenuDialogObservable newsDialogObservable;
	private NewsObservable newsObservable;
	private Stage timeStage;
	private FrameService frameService;

	private Text timeText;

	private double preWidth;
	private double preHeight;

	@FXML
	private MenuBar menuBar;

	@FXML
	private void onOpenDialog() throws IOException {
		final Stage stage = frameService.createDialog("menu/MenuDialogView.fxml");
		menuBar.setDisable(true);
		stage.showAndWait();
		menuBar.setDisable(false);
	}

	@FXML
	private void onOpenTetris() {
		try {
			initMainPane(ScreenMode.TETRIS);
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		this.timeStage.setWidth(300);
		this.timeStage.setHeight(500);
		this.timeStage.show();
	}

	@FXML
	private void onOpenNewsDialog() throws IOException {
		final Stage stage = frameService.createDialog("news/menu/MenuDialogView.fxml");
		menuBar.setDisable(true);
		stage.showAndWait();
		menuBar.setDisable(false);
	}

	@FXML
	private void backClock() {
		try {
			initMainPane(frameService.getScreenMode());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void deletePanel() {
		VBox pane = (VBox) this.timeStage.getScene().getRoot();
		frameService.deleteElements(pane);
	}

	private void changeMainPane(ScreenMode mode) throws IOException {
		setClockPane(mode);
		resizeStage(this.timeStage, clockService.getClockType(), this.timeText);
		frameService.setScreenMode(mode);
	}

	private void changeNewsPane() {
		final VBox pane = (VBox) this.timeStage.getScene().getRoot();
		final Node node = createNewsPane();
		pane.getChildren().set(MAIN_PANE_NUM, node);
	}

	private Node createNewsPane() {
		final FXMLLoader loader = frameService.createNewsLoader();
		Node node = null;
		try {
			node = (Node) loader.load();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return node;
	}

	private void setClockPane(ScreenMode mode) throws IOException {
		Node node = createClockPane(mode);
		VBox pane = (VBox) this.timeStage.getScene().getRoot();
		pane.getChildren().add(node);
	}

	private Node createClockPane(ScreenMode mode) throws IOException {
		final FXMLLoader loader = frameService.createClockLoader(mode);
		Node node = null;
		node = (Node) loader.load();
		frameService.bindClockController(timeStage, mode, loader);
		return node;
	}

	private void setNewsBarPane() {
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
		newsDialogObservable = dc4.frame.news.menu.MenuDialogObservable.getInstance();
		newsDialogObservable.addPropertyChangeListener(this);
		newsObservable = NewsObservable.getInstance();
		newsObservable.addPropertyChangeListener(this);
	}

	public void initView(final Stage stage) throws IOException {
		this.timeStage = stage;
		stage.setX(frameService.getX());
		stage.setY(frameService.getY());
		stage.show();
		initMainPane(frameService.getScreenMode());
	}

	private void initMainPane(ScreenMode mode) throws IOException {
		deletePanel();
		if (frameService.getNewsBarVisible()) {
			setNewsBarPane();
		}
		changeMainPane(mode);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (Objects.equals(evt.getPropertyName(), "news")) {
			changeNewsPane();
			return;
		}
		if (Objects.equals(evt.getPropertyName(), "newsBar")) {
			try {
				initMainPane(frameService.getScreenMode());
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return;
		}

		final ScreenMode newVal = (ScreenMode) evt.getNewValue();
		if (!Objects.equals(frameService.getScreenMode(), newVal)) {
			try {
				initMainPane(newVal);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		this.timeText.setFont(clockService.getFont());
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
