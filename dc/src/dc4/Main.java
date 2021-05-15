package dc4;

import java.net.URL;

import dc4.frame.FrameController;
import dc4.frame.FrameService;
import dc4.frame.clock.ClockService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static int DEFAULT_WIDTH = 300;
	public static int DEFAULT_HEIGHT = 150;

	private static FrameService frameService;
	private static ClockService clockService;

	public static void main(final String[] args) {
		frameService = FrameService.getInstance();
		clockService = ClockService.getInstance();
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final URL location = getClass().getResource("frame/FrameView.fxml");
		final FXMLLoader timeLoader = new FXMLLoader(location);
		final Pane root = (Pane) timeLoader.load();
		final Scene scene = new Scene(root, frameService.getWidth(), frameService.getHeight());
		stage.setScene(scene);

		// setup timeView to bind
		final FrameController timeController = timeLoader.getController();
		timeController.initView(stage);

		stage.setOnCloseRequest((final WindowEvent event) -> {
			frameService.saveX(stage.getX());
			frameService.saveY(stage.getY());
			frameService.saveHeight(stage.getScene().getHeight());
			frameService.saveWidth(stage.getScene().getWidth());
			frameService.saveScreenMode();
			System.exit(0);
		});
	}
}
