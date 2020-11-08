package ch03.ex14;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final Image image = new Image(new File("./src/resource/image/sample.png").toURI().toString());
//        int width = (int) image.getWidth();
//        int height = (int) image.getHeight();
		final int width = 2;
		final int height = 2;

		final ColorTransFormer shadeOff = (x, y, reader) -> {
			double aveR = 0;
			double aveG = 0;
			double aveB = 0;
			final double opacity = reader.getColor(x, y).getOpacity();
			int count = 0;
			Color c;
			for (int i = -1; i < 2; i++) {
				if (x + i < 0 || x + i >= width) {
					continue;
				}
				for (int j = -1; j < 2; j++) {
					if (y + j < 0 || y + j >= height) {
						continue;
					}
					c = reader.getColor(x + i, y + j);
					aveR += c.getRed();
					aveG += c.getGreen();
					aveB += c.getBlue();
					count++;
				}
			}
			return new Color(aveR / count, aveG / count, aveB / count, opacity);
		};

		final Image result = LatentImage.from(image)
				.transform((x, y, reader) -> reader.getColor((int) image.getWidth() - (x + 1), y)).transform(shadeOff)
				.toImage();
		// final Image result = LatentImage.from(image).transform((x, y, reader) ->
		// reader.getColor((int) image.getWidth() - (x + 1),
		// y)).transform(shadeOff).toImage();
		// final Image result = LatentImage.from(image).transform((x, y, r) ->
		// r.getColor(x, y).brighter()).transform((x, y, r) -> r.getColor(x,
		// y).brighter()).toImage();

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
