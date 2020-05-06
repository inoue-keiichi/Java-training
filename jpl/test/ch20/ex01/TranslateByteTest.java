package ch20.ex01;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TranslateByteTest {

	@Test
	public void test() {
		Map<Character, Character> map = new HashMap<>();
		map.put('n', 'N');
		map.put('w', 'W');
		String str = "niwaniwaniwaniwatorigairu";
		InputStream in = new ByteArrayInputStream(str.getBytes());
		OutputStream out = new ByteArrayOutputStream();
		TranslateByte.translateByte(map, in, out);
		assertEquals("NiWaNiWaNiWaNiWatorigairu", out.toString());
	}
}
