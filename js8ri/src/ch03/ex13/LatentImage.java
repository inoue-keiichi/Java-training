package ch03.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
	private Image in;
	private final List<ColorTransFormer> pendingOperations;

	private LatentImage(final Image in) {
		this.in = in;
		this.pendingOperations = new ArrayList<>();
	}

	public static LatentImage from(final Image image) {
		return new LatentImage(image);
	}

	public LatentImage transform(final UnaryOperator<Color> f) {
		pendingOperations.add(ColorTransFormer.convert(f));
		return this;
	}

	public LatentImage transform(final ColorTransFormer ctf) {
		pendingOperations.add(ctf);
		return this;
	}

	public LatentImage enhanceEdge() {
		in = toImage();
		pendingOperations.clear();

		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Color c = enhanceEdge(x, y);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		in = out;
		return this;
	}

	public LatentImage shadeOffColor() {
		in = toImage();
		pendingOperations.clear();

		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Color c = averageColor(x, y);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		in = out;
		return this;
	}

	private Color enhanceEdge(final int x, final int y) {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final ColorValue c = new ColorValue(in.getPixelReader().getColor(x, y));
		final List<ColorValue> list = new ArrayList<>();
		final ColorValue n = y - 1 >= 0 ? new ColorValue(in.getPixelReader().getColor(x, y - 1)) : null;
		list.add(n);
		final ColorValue e = x + 1 < width ? new ColorValue(in.getPixelReader().getColor(x + 1, y)) : null;
		list.add(e);
		final ColorValue s = y + 1 < height ? new ColorValue(in.getPixelReader().getColor(x, y + 1)) : null;
		list.add(s);
		final ColorValue w = x - 1 >= 0 ? new ColorValue(in.getPixelReader().getColor(x - 1, y)) : null;
		list.add(w);
		return c.calculateEdge(list);
	}

	private Color averageColor(final int x, final int y) {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		double aveR = 0;
		double aveG = 0;
		double aveB = 0;
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
				c = in.getPixelReader().getColor(x + i, y + j);
				aveR += c.getRed();
				aveG += c.getGreen();
				aveB += c.getBlue();
				count++;
			}
		}
		return new Color(aveR / count, aveG / count, aveB / count, in.getPixelReader().getColor(x, y).getOpacity());
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

	class ColorValue {
		public double red = 0;
		public double green = 0;
		public double blue = 0;
		public final double opacity;

		ColorValue(final Color color) {
			this.red = color.getRed();
			this.green = color.getGreen();
			this.blue = color.getBlue();
			this.opacity = color.getOpacity();
		}

		private ColorValue multiple(final int arg) {
			red = red * arg;
			green = green * arg;
			blue = blue * arg;
			return this;
		}

		private ColorValue subtract(final ColorValue arg) {
			if (arg == null) {
				return this;
			}
			red = red - arg.red;
			green = green - arg.green;
			blue = blue - arg.blue;
			return this;
		}

		public Color calculateEdge(final List<ColorValue> colorValues) {
			this.multiple(colorValues.size());
			for (final ColorValue colorValue : colorValues) {
				this.subtract(colorValue);
			}
			cutDownValue();
			return new Color(red, green, blue, opacity);
		}

		private void cutDownValue() {
			if (red < 0.0) {
				red = 0.0;
			} else if (red > 1.0) {
				red = 1;
			}
			if (green < 0.0) {
				green = 0.0;
			} else if (green > 1.0) {
				green = 1.0;
			}
			if (blue < 0.0) {
				blue = 0.0;
			} else if (blue > 1) {
				blue = 1.0;
			}
		}
	}
}
