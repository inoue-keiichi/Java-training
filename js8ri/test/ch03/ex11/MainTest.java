package ch03.ex11;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import fxjunit.JavaFxJUnit5ClassRunner;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@RunWith(JavaFxJUnit5ClassRunner.class)
public class MainTest {

	@Test
	public void test() {
		final Image image = new Image(new File("./test/resource/image/sample.png").toURI().toString());
		final ColorTransFormer cft = ColorTransFormer.compose(ColorTransFormer.convert(color -> Color.RED),
				(x, y, color) -> {
					if (x <= 10 || y <= 10 || image.getWidth() - x <= 10 || image.getHeight() - y <= 10) {
						return Color.GRAY;
					}
					return color;
				});
		final Image actual = Main.transform(image, cft);

		final int width = (int) actual.getWidth();
		final int height = (int) actual.getHeight();

		Color actualColorInner;
		Color actualColorOuter;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x <= 10 || y <= 10 || image.getWidth() - x <= 10 || image.getHeight() - y <= 10) {
					actualColorOuter = actual.getPixelReader().getColor(x, y);
					assertEquals(Color.GRAY, actualColorOuter);
					continue;
				}
				actualColorInner = actual.getPixelReader().getColor(x, y);
				assertEquals(Color.RED, actualColorInner);
			}
		}
	}
}
