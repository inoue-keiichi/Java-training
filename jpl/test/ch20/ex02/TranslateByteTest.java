package ch20.ex02;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

public class TranslateByteTest {
	@Test
	public void translateTest() {
		final Map<Character, Character> map = new HashMap<>();
		map.put('n', 'N');
		map.put('w', 'W');
		final String str = "niwaniwaniwaniwatorigairu";
		final InputStream in = new ByteArrayInputStream(str.getBytes());
		final OutputStream out = new ByteArrayOutputStream();
		FilterInputStream fis = null;
		int c;
		try {
			fis = new TranslateByte(in, map);
			while ((c = fis.read()) != -1) {
				out.write(c);
			}
			assertEquals("NiWaNiWaNiWaNiWatorigairu", out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Objects.isNull(fis)) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Objects.isNull(out)) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void emptyMapTest() {
		final Map<Character, Character> map = new HashMap<>();
		final String str = "niwaniwaniwaniwatorigairu";
		final InputStream in = new ByteArrayInputStream(str.getBytes());
		final OutputStream out = new ByteArrayOutputStream();
		FilterInputStream fis = null;
		int c;
		try {
			fis = new TranslateByte(in, map);
			while ((c = fis.read()) != -1) {
				out.write(c);
			}
			assertEquals(str, out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Objects.isNull(fis)) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Objects.isNull(out)) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
