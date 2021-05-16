package dc4.frame.news.menu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuDialogObservable {
	private static final MenuDialogObservable menuDialogObservable = new MenuDialogObservable();

	private static final PropertyChangeSupport pcs = new PropertyChangeSupport(menuDialogObservable);

	public static MenuDialogObservable getInstance() {
		return menuDialogObservable;
	}

	private MenuDialogObservable() {

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
