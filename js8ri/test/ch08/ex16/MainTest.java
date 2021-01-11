package ch08.ex16;

import static ch08.ex16.Main.Adress.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.Test;

import ch08.ex16.Main.Adress;

public class MainTest {

	@Test
	public void test_normal_postalCodeLength_5() {
		final Map<Adress, String> actual = Main.getAdress("hoge,  NN, 01234");
		assertAll(() -> assertEquals("hoge", actual.get(CITY)), () -> assertEquals("NN", actual.get(STATE)),
				() -> assertEquals("01234", actual.get(POSTAL_CODE)));
	}

	@Test
	public void test_normal_postalCodeLength_9() {
		final Map<Adress, String> actual = Main.getAdress("hoge,  NN, 123456789");
		assertAll(() -> assertEquals("hoge", actual.get(CITY)), () -> assertEquals("NN", actual.get(STATE)),
				() -> assertEquals("123456789", actual.get(POSTAL_CODE)));
	}
}
