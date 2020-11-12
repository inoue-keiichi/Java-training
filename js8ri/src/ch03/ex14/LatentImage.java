package ch03.ex14;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class LatentImage {
	private final Image in;
	private final List<ColorTransFormer> pendingOperations;

	private LatentImage(final Image in) {
		this.in = in;
		this.pendingOperations = new ArrayList<ColorTransFormer>();
	}

	public static LatentImage from(final Image image) {
		return new LatentImage(image);
	}

	public LatentImage transform(final ColorTransFormer ctf) {
		pendingOperations.add(ctf);
		return this;
	}

	public Image toImage() {
		if (pendingOperations.size() < 1) {
			return in;
		}
		final List<WritableImageAdapter> images = createWritableImages(pendingOperations.size(), in.getPixelReader());
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int i = 0; i < pendingOperations.size(); i++) {
					final ColorTransFormer ctf = pendingOperations.get(i);
					Color c;
					if (i == 0) {
						c = ctf.apply(x, y, in.getPixelReader());
					} else {
						c = ctf.apply(x, y, images.get(i).getPixelReader());
					}
					images.get(i).getPixelWriter().setColor(x, y, c);
				}
			}
		}
		return images.get(images.size() - 1).createImage();
	}

	private List<WritableImageAdapter> createWritableImages(final int level, final PixelReader reader) {
		final List<WritableImageAdapter> list = new ArrayList<>(level);
		WritableImageAdapter element = new WritableImageAdapter((int) in.getWidth(), (int) in.getHeight(), reader,
				pendingOperations.get(0));
		list.add(element);

		for (int i = 1; i < level; i++) {
			element = new WritableImageAdapter((int) in.getWidth(), (int) in.getHeight(),
					list.get(i - 1).getPixelReader(), pendingOperations.get(i));
			list.add(element);
		}
		return list;
	}
}
