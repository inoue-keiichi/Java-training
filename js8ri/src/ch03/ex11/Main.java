package ch03.ex11;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	public static void main(final String[] args) {
		launch(args);
	}

	public static Image transform(final Image in, final ColorTransFormer f) {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Color color;
				color = f.apply(x, y, in.getPixelReader().getColor(x, y));
				out.getPixelWriter().setColor(x, y, color);
			}
		}
		return out;
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final Image image = new Image(new File("./src/resource/image/sample.png").toURI().toString());
		final ColorTransFormer cft = ColorTransFormer.compose(ColorTransFormer.convert(Color::brighter),
				(x, y, color) -> {
					if (x <= 10 || y <= 10 || image.getWidth() - x <= 10 || image.getHeight() - y <= 10) {
						return Color.GRAY;
					}
					return color;
				});
		final Image result = transform(image, cft);

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
