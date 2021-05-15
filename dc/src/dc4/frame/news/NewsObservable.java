package dc4.frame.news;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewsObservable {
	private static final NewsObservable newsObservable = new NewsObservable();

	private static final PropertyChangeSupport pcs = new PropertyChangeSupport(newsObservable);

	public static NewsObservable getInstance() {
		return newsObservable;
	}

	private NewsObservable() {

	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public static void execute(String propertyName, Object oldValue, Object newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}
}
