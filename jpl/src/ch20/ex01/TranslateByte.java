package ch20.ex01;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TranslateByte {
	public static void translateByte(final Map<Character, Character> map, final InputStream in,
			final OutputStream out) {
		int b;
		try {
			while ((b = in.read()) != -1) {
				for (Map.Entry<Character, Character> entry : map.entrySet()) {
					byte from = (byte) entry.getKey().charValue();
					byte to = (byte) entry.getValue().charValue();
					b = b == from ? to : b;
				}
				out.write(b);
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Map<Character, Character> map = new HashMap<>();
		map.put('n', 'N');
		map.put('w', 'W');
		String str = "niwaniwaniwaniwatorigairu";
		OutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			translateByte(map, new ByteArrayInputStream(str.getBytes("utf-8")), out);
			System.out.println(out);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (Objects.isNull(out)) {
				return;
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
