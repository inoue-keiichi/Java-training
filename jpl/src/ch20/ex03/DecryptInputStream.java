package ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

	protected DecryptInputStream(InputStream in) {
		super(in);
	}

	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : decrypt((byte) c));
	}

	private byte decrypt(final byte b) {
		final byte decryptedByte = (byte) (b ^ 0x0F);
		return decryptedByte;
	}
}
