package dc3_3.menu;

import java.util.Observable;

public class MenuDialogObservable extends Observable {
	private static MenuDialogObservable menuDialogObservable = new MenuDialogObservable();

	public static MenuDialogObservable getInstance() {
		return menuDialogObservable;
	}

	private MenuDialogObservable() {

	}

	public void execute() {
		menuDialogObservable.setChanged();
		menuDialogObservable.notifyObservers();
	}

}
