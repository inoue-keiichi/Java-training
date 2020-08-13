package dc3_2;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static int DEFAULT_WIDTH = 300;
	public static int DEFAULT_HEIGHT = 150;

	private static MainService mainService;

	public static void main(final String[] args) {
		mainService = MainService.getInstance();
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final URL location = getClass().getResource("TimeView.fxml");
		final FXMLLoader timeLoader = new FXMLLoader(location);
		final Pane root = (Pane) timeLoader.load();
		final Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setScene(scene);

		// setup timeView to bind
		final TimeController timeController = timeLoader.getController();
		stage.show();
		timeController.initView(stage);

		mainService.setX(stage.getX());
		mainService.setY(stage.getY());

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
