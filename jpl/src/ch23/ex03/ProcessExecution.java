package ch23.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessExecution {
	public static void main(String[] args) {
		ProcessExecution processExecution = new ProcessExecution();
		try {
			List<String> list = processExecution.executeCommand(args);
			for (String str : list) {
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public List<String> executeCommand(String[] cmd) throws IOException {
		Process child = new ProcessBuilder(cmd).redirectErrorStream(true).start();
		return getOutputLine(child, child.getInputStream(), "dc");
	}

	private List<String> getOutputLine(Process process, InputStream in, String str) throws IOException {
		final List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
			String line;
			int i = 1;
			while (Objects.nonNull(line = br.readLine())) {
				line = i + ": " + line;
				list.add(line);
				if (line.matches(".*" + str + ".*")) {
					break;
				}
				i++;
			}
		}
		return list;
	}
}