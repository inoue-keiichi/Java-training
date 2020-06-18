package ch23.ex03;

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
			String str;
			for (int i = 0; i < list.size(); i++) {
				str = list.get(i);
				if (i == list.size() - 1) {
					assertTrue(str.matches((i + 1) + ": .*dc.*"));
					break;
				}
				assertTrue(str.matches((i + 1) + ": .*"));
				assertTrue(!str.matches((i + 1) + ": .*dc.*"));
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
