package ch09.ex11;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	private static final String CREDIT_NUM = "\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d";

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder("grep", "-r", CREDIT_NUM,
				args[0]);
		builder.inheritIO();
		Process process = builder.start();
		process.waitFor(30, TimeUnit.SECONDS);
	}
}
