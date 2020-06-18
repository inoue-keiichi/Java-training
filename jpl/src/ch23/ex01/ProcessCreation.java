package ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessCreation {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process process = userProg("pwd");
		//process.waitFor();
		//process.exitValue();
	}

	public static Process userProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	private static void plugTogether(InputStream in, OutputStream out) {
		final Runnable runnable = createRunnable(in, out);
		final Thread thread = new Thread(runnable);
		thread.start();
	}

	private static void plugTogether(OutputStream out, InputStream in) {
		final Runnable runnable = createRunnable(in, out);
		final Thread thread = new Thread(runnable);
		thread.start();
	}

	private static Runnable createRunnable(InputStream in, OutputStream out) {
		return new Runnable() {
			int c;

			@Override
			public void run() {
				try {
					while ((c = in.read()) != -1) {
						out.write(c);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
}
