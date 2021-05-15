package dc4.frame.news.bar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewsBarObservable {
	private static final NewsBarObservable newsObservable = new NewsBarObservable();

	private static final PropertyChangeSupport pcs = new PropertyChangeSupport(newsObservable);

	public static NewsBarObservable getInstance() {
		return newsObservable;
	}

	private NewsBarObservable() {

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
