package ch03.ex06;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import fxjunit.JavaFxJUnit4ClassRunner;
import javafx.scene.image.Image;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class MainTest {

	@Test
	public void test_up() throws IOException {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = Main.transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 2.0);

		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();

		double originBrightness;
		double actualBrightness;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				originBrightness = image.getPixelReader().getColor(x, y).getBrightness();
				actualBrightness = actual.getPixelReader().getColor(x, y).getBrightness();
				assertTrue(originBrightness < actualBrightness);
			}
		}
	}

	@Test
	public void test_down() throws IOException {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = Main.transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 0.01);

		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();

		double originBrightness;
		double actualBrightness;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				originBrightness = image.getPixelReader().getColor(x, y).getBrightness();
				actualBrightness = actual.getPixelReader().getColor(x, y).getBrightness();
				assertTrue(originBrightness > actualBrightness);
			}
		}
	}

	@Test
	public void test_equal() throws IOException {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = Main.transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.0);

		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();

		double originBrightness;
		double actualBrightness;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				originBrightness = image.getPixelReader().getColor(x, y).getBrightness();
				actualBrightness = actual.getPixelReader().getColor(x, y).getBrightness();
				assertTrue(originBrightness == actualBrightness);
			}
		}
	}
}
