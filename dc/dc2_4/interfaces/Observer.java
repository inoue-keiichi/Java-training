package dc2_4.interfaces;

import dc2_4.abstracts.PrintGenerator;

public interface Observer {
	public abstract void update(final PrintGenerator printGenerator);
}
