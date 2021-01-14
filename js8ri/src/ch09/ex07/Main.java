package ch09.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

	public static void main(String[] args) throws IOException {
		URL url = new URL(
				"https://www.google.com/?hl=ja");
		Files.copy(url.openStream(), Path.of("./src/resource/out.txt"), StandardCopyOption.REPLACE_EXISTING);
	}

}
