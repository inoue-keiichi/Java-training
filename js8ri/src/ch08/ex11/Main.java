package ch08.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Main {

	public static void main(final String[] args) throws IOException {
		final URL url = new URL(
				"https://id.hulu.jp/account/login");
		final String username = "";
		final String password = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getWebPage(url, username, password)))) {
			reader.lines().forEach(System.out::println);
		}
	}

	private static InputStream getWebPage(final URL url, final String username, final String password)
			throws IOException {
		final URLConnection connection = url.openConnection();
		connection.setRequestProperty("Authorization", "Basic " + encodePass(username, password));
		return connection.getInputStream();
	}

	private static String encodePass(final String username, final String password) {
		final Encoder encoder = Base64.getEncoder();
		final String original = new StringBuilder().append(username).append(":").append(password).toString();
		return encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
	}
}
