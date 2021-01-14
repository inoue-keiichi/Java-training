package ch09.ex05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {

	public static void main(final String[] args) throws IOException {
		writeReversely(Path.of("./src/resource/sample1.txt"), Path.of("./src/resource/out.txt"));
	}

	public static void writeReversely(Path from, Path to) throws IOException {
		final byte[] bytes = Files.readAllBytes(from);
		final Iterable<CharSequence> iteratable = createReverseIterable(bytes);
		Files.write(to, iteratable);
	}

	private static Iterable<CharSequence> createReverseIterable(byte[] bytes) {
		return new Iterable<>() {
			int i = bytes.length - 1;
			CharSequence next = null;

			@Override
			public Iterator<CharSequence> iterator() {
				return new Iterator<>() {

					@Override
					public boolean hasNext() {
						final StringBuilder sb = new StringBuilder();
						while (i > -1) {
							byte[] tmp = { bytes[i--] };
							String tmpStr = convertToStr(tmp, "UTF-8");

							if (tmpStr.equals("\n") | tmpStr.equals("\r")) {
								next = sb.toString();
								return true;
							} else if (Arrays.equals(tmp, tmpStr.getBytes())) {
								sb.append(tmpStr);
								continue;
							} else if (i < 0) {
								return false;
							}

							do {
								//該当する文字コードがなかったら先頭に新しいバイトを追加して再度探す
								tmp = addFirst(tmp, bytes[i--]);
								tmpStr = convertToStr(tmp, "UTF-8");
								// i<0でwhileを抜ける場合は文字化けする
							} while (!Arrays.equals(tmp, tmpStr.getBytes()) && i > -1);
							sb.append(tmpStr);
						}

						if (sb.toString().equals("")) {
							return false;
						} else {
							next = sb.toString();
							return true;
						}
					}

					@Override
					public CharSequence next() {
						if (next == null) {
							throw new NoSuchElementException();
						}
						final CharSequence result = next;
						next = null;
						return result;
					}
				};
			}
		};
	}

	private static byte[] addFirst(byte[] bytes, byte b) {
		final byte[] result = new byte[bytes.length + 1];
		for (int j = 0; j < bytes.length; j++) {
			result[j + 1] = bytes[j];
		}
		result[0] = b;
		return result;
	}

	private static String convertToStr(byte[] bytes, String charsetName) {
		String result = null;
		try {
			result = new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return result;
	}
}
