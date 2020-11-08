package ch03.ex08;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransFormer {

	public Color apply(int x, int y, Color color);

	public static ColorTransFormer create(final Image image, final int width, final int height, final Color color) {

		return (x, y, c) -> {
			if (x <= width || y <= height || image.getWidth() - x <= width || image.getHeight() - y <= height) {
				return color;
			}
			return c;
		};
	}

}