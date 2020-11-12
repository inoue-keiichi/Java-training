package ch03.ex15;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import fxjunit.JavaFxJUnit4ClassRunner;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class MainTest {
	@Test
	public void test_executeParallel() {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = LatentImage.from(image).parallelTransform(Color::brighter)
				.parallelTransform(Color::brighter).toImage();

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
	public void test_only_toImage() {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = LatentImage.from(image).toImage();
		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();

		Color expectedColor;
		Color actualColor;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				expectedColor = image.getPixelReader().getColor(x, y);
				actualColor = actual.getPixelReader().getColor(x, y);
				assertEquals(expectedColor, actualColor);
			}
		}
	}

	@Test
	public void test_executeNormal() throws Exception {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = LatentImage.from(image).transform(Color::brighter).toImage();

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
	public void test_normalAndParallel() throws Exception {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final Image actual = LatentImage.from(image).parallelTransform(Color::brighter).transform(Color::brighter)
				.toImage();

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
}
