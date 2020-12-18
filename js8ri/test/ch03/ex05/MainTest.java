package ch03.ex05;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import fxjunit.JavaFxJUnit5ClassRunner;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@RunWith(JavaFxJUnit5ClassRunner.class)
public class MainTest {

	@Test
	public void test() throws IOException {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = Main.transform(image, (x, y, color) -> {
			if (x <= 10 || y <= 10 || image.getWidth() - x <= 10 || image.getHeight() - y <= 10) {
				return Color.GRAY;
			}
			return color;
		});

		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();
		Color actualColor;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				actualColor = actual.getPixelReader().getColor(x, y);
				if (x <= 10 || y <= 10 || width - x <= 10 || height - y <= 10)
					assertEquals(Color.GRAY, actualColor);
				else
					assertNotEquals(Color.GRAY, actualColor);
			}
		}
	}
}
