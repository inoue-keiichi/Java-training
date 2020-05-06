package ch20.ex04;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

import org.junit.Test;

public class LineFilterReaderTest {

	@Test
	public void normalTest_¥n() {
		StringReader sr = new StringReader("hogehoge\nfoofoo\n");
		LineFilterReader lfr = null;
		int c;
		final String[] actualResults = new String[2];
		try {
			lfr = new LineFilterReader(sr);
			for (int i = 0; i < actualResults.length; i++) {
				actualResults[i] = lfr.readLine();
			}
			assertEquals("hogehoge", actualResults[0]);
			assertEquals("foofoo", actualResults[1]);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (Objects.isNull(lfr)) {
					return;
				}
				lfr.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Test
	public void normalTest_¥r() {
		StringReader sr = new StringReader("hogehoge\rfoofoo\r");
		LineFilterReader lfr = null;
		int c;
		final String[] actualResults = new String[2];
		try {
			lfr = new LineFilterReader(sr);
			for (int i = 0; i < actualResults.length; i++) {
				actualResults[i] = lfr.readLine();
			}
			assertEquals("hogehoge", actualResults[0]);
			assertEquals("foofoo", actualResults[1]);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (Objects.isNull(lfr)) {
					return;
				}
				lfr.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Test
	public void normalTest_¥n¥r() {
		StringReader sr = new StringReader("hogehoge\r\nfoofoo\r\n");
		LineFilterReader lfr = null;
		int c;
		final String[] actualResults = new String[2];
		try {
			lfr = new LineFilterReader(sr);
			for (int i = 0; i < actualResults.length; i++) {
				actualResults[i] = lfr.readLine();
			}
			assertEquals("hogehoge", actualResults[0]);
			assertEquals("foofoo", actualResults[1]);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (Objects.isNull(lfr)) {
					return;
				}
				lfr.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
