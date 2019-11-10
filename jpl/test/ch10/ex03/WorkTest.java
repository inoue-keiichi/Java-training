package ch10.ex03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch06.ex01.Week;

public class WorkTest {
	@Test
	void test() throws Exception {
		assertEquals(false, Work.isWorkDay(Week.SUNDAY));
		assertEquals(true, Work.isWorkDay(Week.MONDAY));
		assertEquals(true, Work.isWorkDay(Week.TUESDAY));
		assertEquals(true, Work.isWorkDay(Week.WEDNESDAY));
		assertEquals(true, Work.isWorkDay(Week.THUSDAY));
		assertEquals(true, Work.isWorkDay(Week.FRIDAY));
		assertEquals(false, Work.isWorkDay(Week.SATUDAY));
	}
}
