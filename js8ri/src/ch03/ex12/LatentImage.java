package ch03.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
	private final Image in;
	private final List<ColorTransFormer> pendingOperations;

	private LatentImage(final Image in) {
		this.in = in;
		this.pendingOperations = new ArrayList<>();
	}

	public LatentImage transform(final UnaryOperator<Color> f) {
		pendingOperations.add(ColorTransFormer.convert(f));
		return this;
	}

	public LatentImage transform(final ColorTransFormer ctf) {
		pendingOperations.add(ctf);
		return this;
	}

	public static LatentImage from(final Image image) {
		return new LatentImage(image);
	}

	public Image toImage() {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (final ColorTransFormer ctf : pendingOperations) {
					c = ctf.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}
