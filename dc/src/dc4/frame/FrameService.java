package dc4.frame;

import java.net.URL;
import java.util.prefs.Preferences;

import dc4.frame.clock.ClockController;
import dc4.frame.clock.TetrisClockController;
import dc4.frame.game.tetris.TetrisController;
import dc4.frame.news.bar.NewsBarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FrameService {
	private static final FrameService timeService = new FrameService();
	public static double DEFAULT_X = 0d;
	public static double DEFAULT_Y = 0d;
	public static double DEFAULT_WIDTH = 300d;
	public static double DEFAULT_HEIGHT = 150d;

	private enum FrameKey {
		FRAME_X("frameX"), FRAME_Y("frameY"), HEIGHT("frameHeight"), WIDTH("frameWidth"), SCREEN_MODE("screenMode");

		private String name;

		FrameKey(final String name) {
			this.name = name;
		}
	}

	private final Preferences prefs;

	private double x;
	private double y;
	private double width;
	private double height;
	private ScreenMode screenMode;
	private boolean newsBarVisible;
	private String newsCountry;

	public static FrameService getInstance() {
		return timeService;
	}

	private FrameService() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		x = prefs.getDouble(FrameKey.FRAME_X.toString(), 0d);
		y = prefs.getDouble(FrameKey.FRAME_Y.toString(), 0d);
		height = prefs.getDouble(FrameKey.HEIGHT.toString(), DEFAULT_HEIGHT);
		width = prefs.getDouble(FrameKey.WIDTH.toString(), DEFAULT_WIDTH);
		screenMode = ScreenMode.get(prefs.get(FrameKey.SCREEN_MODE.toString(), ScreenMode.CLOCK.toString()));
	}

	public FXMLLoader createClockLoader(ScreenMode mode) {
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
		return new FXMLLoader(location);
	}

	public FXMLLoader createNewsBarLoader() {
		URL location = getClass().getResource("news/bar/NewsBarView.fxml");
		return new FXMLLoader(location);
	}

	public FXMLLoader createNewsLoader() {
		URL location = getClass().getResource("news/NewsView.fxml");
		return new FXMLLoader(location);
	}

	public void deleteElements(VBox pane) {
		// すでに画面があれば削除する。
		if (pane.getChildren().size() < 2) {
			return;
		}
		for (int i = pane.getChildren().size() - 1; i > 0; i--) {
			pane.getChildren().remove(i);
		}
	}

	public void bindClockController(Stage stage, ScreenMode mode, FXMLLoader loader) {
		switch (mode) {
		case CLOCK:
			final ClockController clockController = loader.getController();
			clockController.initView(stage);
			break;
		case TETRIS_CLOCK:
			final TetrisClockController tetrisClockController = loader.getController();
			tetrisClockController.initView(stage);
			break;
		case TETRIS:
			final TetrisController tetrisController = loader.getController();
			tetrisController.initView(stage);
			break;
		default:
		}
	}

	public void bindNewsController(Stage stage, FXMLLoader loader) {
		NewsBarController newsController = loader.getController();
		newsController.initView(stage);
	}

	public void saveX(final double value) {
		prefs.putDouble(FrameKey.FRAME_X.toString(), value);
	}

	public void saveY(final double value) {
		prefs.putDouble(FrameKey.FRAME_Y.toString(), value);
	}

	public void saveHeight(final double value) {
		prefs.putDouble(FrameKey.HEIGHT.toString(), value);
	}

	public void saveWidth(final double value) {
		prefs.putDouble(FrameKey.WIDTH.toString(), value);
	}

	public void saveScreenMode() {
		prefs.put(FrameKey.SCREEN_MODE.toString(), screenMode.toString());
	}

	//	private void save(final FrameKey key, final double value) {
	//		switch (key) {
	//		case FRAME_X:
	//			prefs.putDouble(key.toString(), value);
	//			break;
	//
	//		case FRAME_Y:
	//			prefs.putDouble(key.toString(), value);
	//			break;
	//
	//		case HEIGHT:
	//			prefs.putDouble(key.toString(), value);
	//			break;
	//
	//		case WIDTH:
	//			prefs.putDouble(key.toString(), value);
	//			break;
	//
	//		default:
	//			return;
	//		}
	//	}

	//	private double load(final FrameKey key) throws IOException {
	//		switch (key) {
	//		case FRAME_X:
	//			return prefs.getDouble(key.toString(), 0d);
	//
	//		case FRAME_Y:
	//			return prefs.getDouble(key.toString(), 0d);
	//
	//		case HEIGHT:
	//			return prefs.getDouble(key.toString(), DEFAULT_HEIGHT);
	//
	//		case WIDTH:
	//			return prefs.getDouble(key.toString(), DEFAULT_WIDTH);
	//
	//		default:
	//			throw new IllegalArgumentException();
	//		}
	//	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public ScreenMode getScreenMode() {
		return screenMode;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public void setScreenMode(ScreenMode screenMode) {
		this.screenMode = screenMode;
	}

	public boolean getNewsBarVisible() {
		return newsBarVisible;
	}

	public void setNewsBarVisible(boolean newsBarVisible) {
		this.newsBarVisible = newsBarVisible;
	}

	public String getNewsCountry() {
		return newsCountry;
	}

	public void setNewsCountry(String newsCountry) {
		this.newsCountry = newsCountry;
	}
}
