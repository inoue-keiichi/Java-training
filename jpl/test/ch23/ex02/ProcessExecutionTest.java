package ch23.ex02;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ProcessExecutionTest {
	@Test
	public void normalTest() {
		ProcessExecution processExecution = new ProcessExecution();
		try {
			final String[] cmd = { "ls" };
			final List<String> list = processExecution.executeCommand(cmd);
			int i = 1;
			for (String str : list) {
				assertTrue(str.matches(i + ": .*"));
				i++;
			}

			final String[] cmd2 = { "java", "-version" };
			final List<String> list2 = processExecution.executeCommand(cmd2);
			i = 1;
			for (String str : list2) {
				assertTrue(str.matches(i + ": .*"));
				i++;
			}

			final String[] cmd3 = { "less", "error.txt" };
			final List<String> list3 = processExecution.executeCommand(cmd3);
			i = 1;
			for (String str : list3) {
				assertTrue(str.matches(i + ": .*"));
				i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
