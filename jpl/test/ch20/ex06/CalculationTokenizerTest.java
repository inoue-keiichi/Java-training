package ch20.ex06;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;

public class CalculationTokenizerTest {

	@Test
	public void normalTest() {
		try (FileInputStream in = new FileInputStream(
				new File("/Users/inoue-keiichi/git/Java-training/jpl/test/resources/calcurationTest.txt"))) {
			Map<String, Double> expectedResult = CalculationTokenizer.cal(in);
			assertEquals(3, expectedResult.get("akira").intValue());
			assertEquals(2, expectedResult.get("bob").intValue());
			assertEquals(1, expectedResult.get("ken").intValue());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void errorTest() {
		try (InputStream in = new ByteArrayInputStream("akira % 5".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("akira * 5".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("akira 5 +".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("+ akira 5".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("5 akira +".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
			//assertEquals("/", e.getMessage());
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("+ 5 akira".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("5 + akira".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("akira + +".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("akira + akira".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}

		try (InputStream in = new ByteArrayInputStream("akira akira + 5".getBytes())) {
			CalculationTokenizer.cal(in);
			fail();
		} catch (IllegalArgumentException e) {
		} catch (IOException e) {
			fail();
		}
	}
}
