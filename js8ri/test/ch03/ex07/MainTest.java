package ch03.ex07;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MainTest {
	private final List<String> list = new ArrayList<String>() {
		{
			add("amenbo");
			add("suzumushi");
			add("Ari");
			add(" hachi");
			add("kuwagata");
			add("inago");
			add("Tonbo");
			add("Korogi");
			add("AriToKirigirisu");
		}
	};

	@Test
	public void test_normalOrder() {
		list.sort(Main.createComparator(true, true, false));

		final List<String> expected = new ArrayList<String>() {
			{
				add(" hachi");
				add("Ari");
				add("AriToKirigirisu");
				add("Korogi");
				add("Tonbo");
				add("amenbo");
				add("inago");
				add("kuwagata");
				add("suzumushi");
			}
		};
		assertEquals(expected, list);
	}

	@Test
	public void test_reverseOrder() {
		list.sort(Main.createComparator(false, true, false));

		final List<String> expected = new ArrayList<String>() {
			{
				add("suzumushi");
				add("kuwagata");
				add("inago");
				add("amenbo");
				add("Tonbo");
				add("Korogi");
				add("AriToKirigirisu");
				add("Ari");
				add(" hachi");
			}
		};
		assertEquals(expected, list);
	}

	@Test
	public void test_notDistinguishCase() {
		list.sort(Main.createComparator(true, false, false));

		final List<String> expected = new ArrayList<String>() {
			{
				add(" hachi");
				add("amenbo");
				add("Ari");
				add("AriToKirigirisu");
				add("inago");
				add("Korogi");
				add("kuwagata");
				add("suzumushi");
				add("Tonbo");
			}
		};
		assertEquals(expected, list);
	}

	@Test
	public void test_omitSpace() {
		list.sort(Main.createComparator(true, false, true));

		final List<String> expected = new ArrayList<String>() {
			{
				add("amenbo");
				add("Ari");
				add("AriToKirigirisu");
				add(" hachi");
				add("inago");
				add("Korogi");
				add("kuwagata");
				add("suzumushi");
				add("Tonbo");
			}
		};
		assertEquals(expected, list);
	}
}
