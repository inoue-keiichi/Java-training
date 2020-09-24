package dc2_3.interfaces;

import dc2_3.abstracts.PrintGenerator;

public interface Observer {
	public abstract void update(final PrintGenerator printGenerator);
}
