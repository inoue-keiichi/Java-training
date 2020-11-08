package ch03.ex14;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class LatentImage {
	private final Image in;
	private final List<EnhancedColorTransFormer> pendingOperations;
	private int y;

	private LatentImage(final Image in) {
		this.in = in;
		this.pendingOperations = new ArrayList<EnhancedColorTransFormer>();
	}

	public static LatentImage from(final Image image) {
		return new LatentImage(image);
	}

//    public LatentImage transform(UnaryOperator<Color> f) {
//        pendingOperations.add(ColorTransFormer.convert(f));
//        return this;
//    }

	public LatentImage transform(final ColorTransFormer ctf) {
		final int level = pendingOperations.size();
		PixelReader preReader;
		if (level < 1) {
			preReader = in.getPixelReader();
		} else {
			preReader = pendingOperations.get(level - 1).getWritableImageAdapter().getPixelReader();
		}
		pendingOperations.add(new EnhancedColorTransFormer(ctf, (int) in.getWidth(), (int) in.getHeight(), preReader));
		return this;
	}

	public Image toImage() {
		if (pendingOperations.size() < 1) {
			return in;
		}
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int i = 0; i < pendingOperations.size(); i++) {
					final ColorTransFormer ctf = pendingOperations.get(i).getColorTransFormer();
					final PixelReader reader = getPixelReader(i);
					pendingOperations.get(i).getWritableImageAdapter().getPixelWriter().setColor(x, y,
							ctf.apply(x, y, reader));
				}
			}
		}
		// return pendingOperations.get(0).getWritableImageAdapter().createImage();
		return pendingOperations.get(pendingOperations.size() - 1).getWritableImageAdapter().createImage();
	}

	private PixelReader getPixelReader(final int i) {
		if (i == 0) {
			return in.getPixelReader();
		}
		return pendingOperations.get(i - 1).getWritableImageAdapter().getPixelReader();
	}

//    class EnhancedColorTransFormer {
//        private ColorTransFormer ctf;
//        private WritableImageAdapter in;
//
//        EnhancedColorTransFormer(ColorTransFormer ctf, int width, int height, PixelReader prePixelReader) {
//            this.ctf = ctf;
//            this.in = new WritableImageAdapter(width, height, prePixelReader);
//        }
//
//        public ColorTransFormer getColorTransFormer() {
//            return ctf;
//        }
//
//        public WritableImageAdapter getWritableImageAdapter() {
//            return in;
//        }
//    }
//
//    class WritableImageAdapter {
//        private WritableImage writableImage;
//        private PixelReader pixelReaderAdapter;
//        private PixelWriter pixelWriterAdapter;
//        private Color[][] cash;
//
//        public WritableImageAdapter(int width, int height, PixelReader prePixelReader) {
//
//            this.cash = new Color[400][400];
//            this.writableImage = new WritableImage(width, height);
//            this.pixelWriterAdapter = new PixelWriterAdapter(this.writableImage.getPixelWriter(), this.cash);
//            this.pixelReaderAdapter = new PixelReaderAdapter(prePixelReader, this.cash);
//        }
//
//        public PixelReader getPixelReader() {
//            return this.pixelReaderAdapter;
//        }
//
//        public PixelWriter getPixelWriter() {
//            return this.pixelWriterAdapter;
//        }
//
//        private Image createImage() {
//            for (int i = 0; i < cash[0].length; i++) {
//                for (int j = 0; j < cash.length; j++) {
//                    if (cash[i][j] != null) {
//                        writableImage.getPixelWriter().setColor(i, j, cash[i][j]);
//                    }
//                }
//            }
//            return writableImage;
//        }
//    }
//
//    class PixelWriterAdapter implements PixelWriter {
//        private final PixelWriter pixelWriter;
//        private Color[][] cash;
//
//        public PixelWriterAdapter(PixelWriter pixelWriter, Color[][] cash) {
//            this.pixelWriter = pixelWriter;
//            this.cash = cash;
//        }
//
//        @Override
//        public void setColor(final int x, final int y, final Color c) {
//            cash[x][y] = c;
//        }
//
//        @Override
//        public PixelFormat getPixelFormat() {
//            return null;
//        }
//
//        @Override
//        public void setArgb(final int x, final int y, final int argb) {
//
//        }
//
//        @Override
//        public <T extends Buffer> void setPixels(final int x, final int y, final int w, final int h, final PixelFormat<T> pixelformat, final T buffer, final int scanlineStride) {
//
//        }
//
//        @Override
//        public void setPixels(final int x, final int y, final int w, final int h, final PixelFormat<ByteBuffer> pixelformat, final byte[] buffer, final int offset, final int scanlineStride) {
//
//        }
//
//        @Override
//        public void setPixels(final int x, final int y, final int w, final int h, final PixelFormat<IntBuffer> pixelformat, final int[] buffer, final int offset, final int scanlineStride) {
//
//        }
//
//        @Override
//        public void setPixels(final int dstx, final int dsty, final int w, final int h, final PixelReader reader, final int srcx, final int srcy) {
//
//        }
//    }
//
//    class PixelReaderAdapter implements PixelReader {
//        private final PixelReader pixelReader;
//        private Color[][] cash;
//
//        public PixelReaderAdapter(PixelReader pixelReader, Color[][] cash) {
//            this.pixelReader = pixelReader;
//            this.cash = cash;
//        }
//
//        @Override
//        public Color getColor(final int x, final int y) {
//            if (cash[x][y] != null) {
//                return cash[x][y];
//            }
//            return pixelReader.getColor(x, y);
//        }
//
//        @Override
//        public PixelFormat getPixelFormat() {
//            return null;
//        }
//
//        @Override
//        public int getArgb(final int x, final int y) {
//            return 0;
//        }
//
//        @Override
//        public <T extends Buffer> void getPixels(final int x, final int y, final int w, final int h, final WritablePixelFormat<T> pixelformat, final T buffer, final int scanlineStride) {
//
//        }
//
//        @Override
//        public void getPixels(final int x, final int y, final int w, final int h, final WritablePixelFormat<ByteBuffer> pixelformat, final byte[] buffer, final int offset, final int scanlineStride) {
//
//        }
//
//        @Override
//        public void getPixels(final int x, final int y, final int w, final int h, final WritablePixelFormat<IntBuffer> pixelformat, final int[] buffer, final int offset, final int scanlineStride) {
//
//        }
//    }
}
