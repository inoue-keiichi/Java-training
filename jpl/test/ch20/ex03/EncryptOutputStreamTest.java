package ch20.ex03;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import org.junit.Test;

public class EncryptOutputStreamTest {

	@Test
	public void normalTest() {
		final OutputStream baos = new ByteArrayOutputStream();
		final Byte[] expectedResult = { 107, 122, 98, 98, 118 };
		OutputStream dos = null;
		try {
			dos = new EncryptOutputStream(baos);
			for (byte b : "dummy".getBytes()) {
				dos.write(b);
			}
			byte[] actualResult = ((ByteArrayOutputStream) baos).toByteArray();

			for (int i = 0; i < expectedResult.length; i++) {
				assertEquals(expectedResult[i].byteValue(), actualResult[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Objects.isNull(dos)) {
				return;
			}
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
