package ch09.ex05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {

	public static void main(final String[] args) throws IOException {
		final byte[] bytes = Files.readAllBytes(Path.of("./src/resource/sample2.txt"));
		final byte[] reverseBytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			reverseBytes[i] = bytes[bytes.length - 1 - i];
		}

		Files.write(Path.of("./src/resource/out.txt"), reverseBytes);

		final Iterable<CharSequence> iteratable = new Iterable<>() {
			int i = bytes.length - 1;
			CharSequence next = null;

			@Override
			public Iterator<CharSequence> iterator() {
				return new Iterator<>() {

					@Override
					public boolean hasNext() {
						byte[] tmp = new byte[1];
						if (i < 0 && i > bytes.length - 1) {
							return false;
						}
						tmp[0] = bytes[i];
						if (i < bytes.length - 1) {
							i++;
						}
						String tmpStr = null;
						try {
							tmpStr = new String(tmp, "UTF-8");
						} catch (final UnsupportedEncodingException e1) {
							// TODO 自動生成された catch ブロック
							e1.printStackTrace();
						}

						if (Arrays.equals(tmp, tmpStr.getBytes())) {
							next = tmpStr;
							return true;
						}

						do {
							//文字コードがなかったら新しい文字を生成
							if (i < 0 && i > bytes.length - 1) {
								return false;
							}
							final byte[] tmp2 = new byte[tmp.length + 1];
							for (int j = 0; j < tmp.length; j++) {
								tmp2[j] = tmp[j];
							}
							tmp2[tmp2.length - 1] = bytes[i];
							if (i < bytes.length - 1) {
								i++;
							}
							tmp = tmp2;
							try {
								tmpStr = new String(tmp, "UTF-8");
							} catch (final UnsupportedEncodingException e) {
								// TODO 自動生成された catch ブロック
								e.printStackTrace();
							}
						} while (Arrays.equals(tmp, tmpStr.getBytes()));
						return true;
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

		Files.write(Path.of("./src/resource/out.txt"), iteratable, StandardCharsets.UTF_8);
	}

}
