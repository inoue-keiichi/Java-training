package ch03.ex08;

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
		final Image actual = Main.transform(image, ColorTransFormer.create(image, 20, 20, Color.RED));

		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();
		Color actualColor;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				actualColor = actual.getPixelReader().getColor(x, y);
				if (x <= 20 || y <= 20 || width - x <= 20 || height - y <= 20)
					assertEquals(Color.RED, actualColor);
				else
					assertNotEquals(Color.RED, actualColor);
			}
		}
	}
}
