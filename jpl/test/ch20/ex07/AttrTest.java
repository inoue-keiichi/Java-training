package ch20.ex07;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class AttrTest {
	@Test
	public void normalTest() {
		final int expectInt = 2;
		final short expectShort = (short) 2;
		final byte expectByte = (byte) 2;
		final long expectLong = 2L;
		final float expectFloat = (float) 2;
		final double expectDouble = 2d;
		final char expectChar = 'a';
		final boolean expectBoolean = true;
		final String expectString = "str";

		Attr attrI = new Attr("name", expectInt);
		Attr attrS = new Attr("name", expectShort);
		Attr attrByte = new Attr("name", expectByte);
		Attr attrL = new Attr("name", expectLong);
		Attr attrF = new Attr("name", expectFloat);
		Attr attrD = new Attr("name", expectDouble);
		Attr attrC = new Attr("name", expectChar);
		Attr attrB = new Attr("name", expectBoolean);
		Attr attrStr = new Attr("name", expectString);
		try {
			Attr newAttr;
			attrI.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectInt, newAttr.getValue());
			attrS.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectShort, newAttr.getValue());
			attrByte.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectByte, newAttr.getValue());
			attrL.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectLong, newAttr.getValue());
			attrF.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectFloat, newAttr.getValue());
			attrD.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectDouble, newAttr.getValue());
			attrC.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectChar, newAttr.getValue());
			attrB.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectBoolean, newAttr.getValue());
			attrStr.writeData("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			newAttr = new Attr("/Users/inoue-keiichi/git/Java-training/jpl/src/ch20/ex05/attr.txt");
			assertEquals(expectString, newAttr.getValue());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
