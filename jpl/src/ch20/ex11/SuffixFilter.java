package ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFilter implements FilenameFilter {
	private final String suffix;

	public SuffixFilter(final String suffix) {
		this.suffix = suffix;
	}

	@Override
	public boolean accept(File dir, String name) {
		final File file = new File(dir, name);
		return file.getName().matches(".*\\." + suffix);
	}
}
