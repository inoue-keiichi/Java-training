package ch22.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class WhichCharsTest {

	@Test
	public void normalTest() {
		final WhichChars whichChars = new WhichChars("Testing 1 2 3");
		int num_braFirst = 0;
		int num_braEnd = 0;
		int num_T = 0;
		int num_e = 0;
		int num_s = 0;
		int num_t = 0;
		int num_i = 0;
		int num_n = 0;
		int num_g = 0;
		int num_1 = 0;
		int num_2 = 0;
		int num_3 = 0;
		int num_space = 0;
		int num_other = 0;

		for (int i = 0; i < whichChars.toString().length(); i++) {
			switch (whichChars.toString().charAt(i)) {
			case '[':
				num_braFirst++;
				break;
			case 'T':
				num_T++;
				break;
			case 'e':
				num_e++;
				break;
			case 's':
				num_s++;
				break;
			case 't':
				num_t++;
				break;
			case 'i':
				num_i++;
				break;
			case 'n':
				num_n++;
				break;
			case 'g':
				num_g++;
				break;
			case '1':
				num_1++;
				break;
			case '2':
				num_2++;
				break;
			case '3':
				num_3++;
				break;
			case ' ':
				num_space++;
				break;
			case ']':
				num_braEnd++;
				break;
			default:
				num_other++;
				break;
			}
		}

		assertEquals(1, num_braFirst);
		assertEquals(1, num_T);
		assertEquals(1, num_e);
		assertEquals(1, num_s);
		assertEquals(1, num_t);
		assertEquals(1, num_i);
		assertEquals(1, num_n);
		assertEquals(1, num_g);
		assertEquals(1, num_1);
		assertEquals(1, num_2);
		assertEquals(1, num_3);
		assertEquals(1, num_space);
		assertEquals(1, num_braEnd);
		assertEquals(0, num_other);
	}
}
