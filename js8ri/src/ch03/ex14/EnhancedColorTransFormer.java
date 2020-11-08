package ch03.ex14;

import javafx.scene.image.PixelReader;

public class EnhancedColorTransFormer {
	private final ColorTransFormer ctf;
	private final WritableImageAdapter in;

	EnhancedColorTransFormer(final ColorTransFormer ctf, final int width, final int height,
			final PixelReader prePixelReader) {
		this.ctf = ctf;
		this.in = new WritableImageAdapter(width, height, prePixelReader);
	}

	public ColorTransFormer getColorTransFormer() {
		return ctf;
	}

	public WritableImageAdapter getWritableImageAdapter() {
		return in;
	}
}
