package ch20.ex04;

import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LineFilterReader extends FilterReader {
	final BufferedReader bis;

	public LineFilterReader(Reader in) {
		super(in);
		this.bis = new BufferedReader(in);
	}

	public String readLine() throws IOException {
		return bis.readLine();
	}

	public void close() throws IOException {
		bis.close();
	}
}
