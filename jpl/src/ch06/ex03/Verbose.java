package ch06.ex03;

interface Verbose {
	public enum Level {
		SILENT, TERSE, NORMAL, VERBOSE
	}

	void setVerbosity(Level level);
	Level getVerbosity();
}
