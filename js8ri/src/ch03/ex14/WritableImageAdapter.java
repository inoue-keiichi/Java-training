package ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

class WritableImageAdapter {
	private final WritableImage writableImage;
	private PixelReader pixelReaderAdapter;
	private final PixelWriter pixelWriterAdapter;
	private final Color[][] cash;

	public WritableImageAdapter(final int width, final int height, final PixelReader prePixelReader) {

		this.cash = new Color[400][400];
		this.writableImage = new WritableImage(width, height);
		this.pixelWriterAdapter = new PixelWriterAdapter(this.writableImage.getPixelWriter(), this.cash);
		if (prePixelReader instanceof PixelReaderAdapter) {
			this.pixelReaderAdapter = prePixelReader;
		} else {
			this.pixelReaderAdapter = new PixelReaderAdapter(prePixelReader, this.cash);
		}
	}

	public PixelReader getPixelReader() {
		return this.pixelReaderAdapter;
	}

	public PixelWriter getPixelWriter() {
		return this.pixelWriterAdapter;
	}

	public Image createImage() {
		for (int i = 0; i < cash[0].length; i++) {
			for (int j = 0; j < cash.length; j++) {
				if (cash[i][j] != null) {
					writableImage.getPixelWriter().setColor(i, j, cash[i][j]);
				}
			}
		}
		return writableImage;
	}

	class PixelWriterAdapter implements PixelWriter {
		private final PixelWriter pixelWriter;
		private final Color[][] cash;

		public PixelWriterAdapter(final PixelWriter pixelWriter, final Color[][] cash) {
			this.pixelWriter = pixelWriter;
			this.cash = cash;
		}

		@Override
		public void setColor(final int x, final int y, final Color c) {
			cash[x][y] = c;
		}

		@Override
		public PixelFormat getPixelFormat() {
			return null;
		}

		@Override
		public void setArgb(final int x, final int y, final int argb) {

		}

		@Override
		public <T extends Buffer> void setPixels(final int x, final int y, final int w, final int h,
				final PixelFormat<T> pixelformat, final T buffer, final int scanlineStride) {

		}

		@Override
		public void setPixels(final int x, final int y, final int w, final int h,
				final PixelFormat<ByteBuffer> pixelformat, final byte[] buffer, final int offset,
				final int scanlineStride) {

		}

		@Override
		public void setPixels(final int x, final int y, final int w, final int h,
				final PixelFormat<IntBuffer> pixelformat, final int[] buffer, final int offset,
				final int scanlineStride) {

		}

		@Override
		public void setPixels(final int dstx, final int dsty, final int w, final int h, final PixelReader reader,
				final int srcx, final int srcy) {

		}
	}

	class PixelReaderAdapter implements PixelReader {
		private final PixelReader pixelReader;
		private final Color[][] cash;

		public PixelReaderAdapter(final PixelReader pixelReader, final Color[][] cash) {
			this.pixelReader = pixelReader;
			this.cash = cash;
		}

		@Override
		public Color getColor(final int x, final int y) {
			if (cash[x][y] != null) {
				return cash[x][y];
			}
			return pixelReader.getColor(x, y);
		}

		@Override
		public PixelFormat getPixelFormat() {
			return null;
		}

		@Override
		public int getArgb(final int x, final int y) {
			return 0;
		}

		@Override
		public <T extends Buffer> void getPixels(final int x, final int y, final int w, final int h,
				final WritablePixelFormat<T> pixelformat, final T buffer, final int scanlineStride) {

		}

		@Override
		public void getPixels(final int x, final int y, final int w, final int h,
				final WritablePixelFormat<ByteBuffer> pixelformat, final byte[] buffer, final int offset,
				final int scanlineStride) {

		}

		@Override
		public void getPixels(final int x, final int y, final int w, final int h,
				final WritablePixelFormat<IntBuffer> pixelformat, final int[] buffer, final int offset,
				final int scanlineStride) {

		}
	}
}
