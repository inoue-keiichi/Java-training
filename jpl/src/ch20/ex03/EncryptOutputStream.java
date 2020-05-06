package ch20.ex03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}

	public void write(final int b) throws IOException {
		final byte encryptedByte;
		if (b == -1) {
			super.write(b);
		} else {
			encryptedByte = (byte) (b ^ 0x0F);
			super.write(encryptedByte);
		}
	}
}
