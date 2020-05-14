package ch22.ex12;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import ch11.ex03.Attr;
import ch11.ex03.Attributed;

public class ScanTest {

	@Test
	public void normalTest() {
		FileReader fr;
		try {
			fr = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/resources/attr.txt");
			Attributed<Object> actualResult = Scan.readAttrs(fr);
			assertEquals(10.0, actualResult.find("Canada").getValue());
			assertEquals(20.0, actualResult.find("Japan").getValue());
			Iterator<Attr<Object>> attrs = actualResult.attrs();
			int size = 0;
			while (attrs.hasNext()) {
				attrs.next();
				size++;
			}
			assertEquals(2, size);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
