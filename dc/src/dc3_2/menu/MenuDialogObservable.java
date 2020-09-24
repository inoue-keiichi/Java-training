package dc3_2.menu;

import java.util.Observable;

public class MenuDialogObservable extends Observable {
	private static MenuDialogObservable menuDialogObservable = new MenuDialogObservable();

	public static MenuDialogObservable getInstance() {
		return menuDialogObservable;
	}

	private MenuDialogObservable() {

	}

	public static void execute() {
		menuDialogObservable.setChanged();
		menuDialogObservable.notifyObservers();
	}

}
