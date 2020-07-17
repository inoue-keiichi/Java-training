package dc2_4.abstracts;

import java.util.ArrayList;
import java.util.List;

import dc2_4.di.DIService;
import dc2_4.interfaces.Observer;

public abstract class PrintGenerator {
	private List<Observer> observers = new ArrayList<>();
	private Object item;
	protected DIService service;
	private boolean isChanged = true;

	public PrintGenerator(final DIService service) {
		this.service = service;
	}

	public void addObserver(final Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers() {
		if (!isChanged) {
			return;
		}
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public abstract void execute();

	public void setChanged(final boolean isChanged) {
		this.isChanged = isChanged;
	}
}
