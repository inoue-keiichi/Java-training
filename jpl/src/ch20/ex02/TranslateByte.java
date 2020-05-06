package ch20.ex02;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TranslateByte extends FilterInputStream {
	final Map<Character, Character> translateMap;

	protected TranslateByte(InputStream in, Map<Character, Character> translateMap) {
		super(in);
		this.translateMap = translateMap;
	}

	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : translateByte((byte) c, translateMap));
	}

	private int translateByte(final byte b, final Map<Character, Character> translateMap) {
		int i = b;
		for (Map.Entry<Character, Character> entry : translateMap.entrySet()) {
			byte from = (byte) entry.getKey().charValue();
			byte to = (byte) entry.getValue().charValue();
			if (b == from) {
				i = to;
			}
		}
		return i;
	}

	public static void main(String[] args) {
		Map<Character, Character> map = new HashMap<>();
		map.put('n', 'N');
		map.put('w', 'W');
		String str = "niwaniwaniwaniwatorigairu";

		final InputStream in;
		FilterInputStream fis = null;
		int c;
		try {
			in = new ByteArrayInputStream(str.getBytes("utf-8"));
			fis = new TranslateByte(in, map);
			while ((c = fis.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
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