package dc3_3;

import java.net.URL;

import dc3_3.time.TimeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static int DEFAULT_WIDTH = 300;
	public static int DEFAULT_HEIGHT = 150;

	private static MainService mainService;

	private double xOffset = 0;
	private double yOffset = 0;

	public static void main(final String[] args) {
		mainService = MainService.getInstance();
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final URL location = getClass().getResource("./time/TimeView.fxml");
		final FXMLLoader timeLoader = new FXMLLoader(location);
		final Pane root = (Pane) timeLoader.load();
		final Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);

		// setup timeView to bind
		final TimeController timeController = timeLoader.getController();
		stage.show();
		timeController.initView(stage);

		// setup mainStage to move
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
		});

		// setup mainStage to exit
		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});

		mainService.setX(stage.getX());
		mainService.setY(stage.getY());
	}
}
