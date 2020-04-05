package main.view;

import main.generator.PrintGenerator;

public interface Observer {
	public abstract void update(final PrintGenerator printGenerator);
}
