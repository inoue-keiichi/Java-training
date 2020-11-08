package ch03.ex13;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final Image image = new Image(new File("./src/resource/image/sample.png").toURI().toString());
		final Image result = LatentImage.from(image).shadeOffColor().enhanceEdge().toImage();

		final ImageView imageView = new ImageView(result);
		final Pane pane = new Pane(imageView);
		final Scene scene = new Scene(pane, result.getWidth(), result.getHeight());
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
