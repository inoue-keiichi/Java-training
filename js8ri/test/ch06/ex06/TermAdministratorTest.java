package ch06.ex06;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TermAdministratorTest {
	@Test
	public void test_getTermFiles() {
		final TermAdministrator target = new TermAdministrator();
		final List<File> files = new ArrayList<>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				{
					this.add(new File("./test/resource/text/term1.txt"));
					this.add(new File("./test/resource/text/term2.txt"));
					this.add(new File("./test/resource/text/term3.txt"));
				}
			}
		};
		target.readFiles(files);
		final Set<File> expected1 = new HashSet<>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add(new File("./test/resource/text/term2.txt"));
			}
		};
		final Set<File> expected2 = new HashSet<>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add(new File("./test/resource/text/term3.txt"));
			}
		};
		final Set<File> expected3 = new HashSet<>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add(new File("./test/resource/text/term1.txt"));
				this.add(new File("./test/resource/text/term3.txt"));
			}
		};
		assertEquals(expected1, target.getTermFiles("monkey"));
		assertEquals(expected2, target.getTermFiles("Mitsubishi"));
		assertEquals(expected3, target.getTermFiles("red"));
	}

	@Test
	public void test_getEntrySet() {
		final TermAdministrator target = new TermAdministrator();
		final List<File> files = new ArrayList<>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				{
					this.add(new File("./test/resource/text/term1.txt"));
					this.add(new File("./test/resource/text/term2.txt"));
					this.add(new File("./test/resource/text/term3.txt"));
				}
			}
		};
		target.readFiles(files);

		assertEquals(18, target.getEntrySet().size());
	}
}
