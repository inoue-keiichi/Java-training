package ch20.ex03;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.junit.Test;

public class DecryptInputStreamTest {

	@Test
	public void normalTest() {
		final InputStream in = new ByteArrayInputStream("dummy".getBytes());
		final Byte[] expectedResult = { 107, 122, 98, 98, 118 };
		FilterInputStream fis = null;
		try {
			fis = new DecryptInputStream(in);
			for (int i = 0; i < expectedResult.length; i++) {
				assertEquals(expectedResult[i].byteValue(), (byte) fis.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Objects.isNull(fis)) {
				return;
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
