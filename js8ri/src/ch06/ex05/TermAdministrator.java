package ch06.ex05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TermAdministrator {
	private final ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

	public void readFiles(final List<File> files) {
		files.forEach(file -> {
			try (final FileReader reader = new FileReader(file)) {
				final StreamTokenizer tokenizer = new StreamTokenizer(reader);
				int token;
				while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
					if (token != StreamTokenizer.TT_WORD) {
						continue;
					}
					putTermAndFile(tokenizer.sval, file);
				}
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	private void putTermAndFile(final String term, final File file) {
		final Set<File> initSet = new HashSet<>();
		initSet.add(file);
		map.merge(term, initSet, (additionSet, newSet) -> {
			additionSet.forEach(element -> newSet.add(element));
			return newSet;
		});
	}

	public Set<File> getTermFiles(final String term) {
		return map.get(term);
	}

	public Set<Entry<String, Set<File>>> getEntrySet() {
		return map.entrySet();
	}
}
