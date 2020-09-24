package dc2_2.interfaces;

import dc2_2.abstracts.PrintGenerator;

public interface Observer {
	public abstract void update(final PrintGenerator printGenerator);
}
