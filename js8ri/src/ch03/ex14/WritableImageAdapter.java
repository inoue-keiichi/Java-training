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
	private final PixelReader pixelReader;
	private final PixelWriter pixelWriter;
	private final Color[][] cash;

	public WritableImageAdapter(final int width, final int height, final PixelReader prePixelReader,
			final ColorTransFormer ctf) {
		this.cash = new Color[width][height];
		this.writableImage = new WritableImage(width, height);
		this.pixelWriter = new PixelWriterImpl(this.cash);
		this.pixelReader = new PixelReaderImpl(prePixelReader, this.cash, ctf);
	}

	public PixelReader getPixelReader() {
		return this.pixelReader;
	}

	public PixelWriter getPixelWriter() {
		return this.pixelWriter;
	}

	public Color[][] getCash() {
		return cash;
	}

	public Image createImage() {
		for (int i = 0; i < cash[0].length; i++) {
			for (int j = 0; j < cash.length; j++) {
				if (cash[j][i] != null) {
					writableImage.getPixelWriter().setColor(j, i, cash[j][i]);
				}
			}
		}
		return writableImage;
	}

	class PixelWriterImpl implements PixelWriter {
		private final Color[][] cash;

		public PixelWriterImpl(final Color[][] cash) {
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

	class PixelReaderImpl implements PixelReader {
		private final PixelReader pixelReader;
		private final Color[][] cash;
		private final ColorTransFormer ctf;

		public PixelReaderImpl(final PixelReader pixelReader, final Color[][] cash, final ColorTransFormer ctf) {
			this.pixelReader = pixelReader;
			this.cash = cash;
			this.ctf = ctf;
		}

		@Override
		public Color getColor(final int x, final int y) {
			if (cash[x][y] != null) {
				return cash[x][y];
			}

			return ctf.apply(x, y, pixelReader);
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
