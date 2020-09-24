package dc2_4.clock.menu;

import java.awt.event.WindowEvent;

import dc2_4.abstracts.PrintGenerator;
import dc2_4.di.DIService;

public class PropertyDialogGenerator extends PrintGenerator {
	public PropertyDialogWindowAdapter window;

	public PropertyDialogGenerator(DIService service) {
		super(service);
		this.window = new PropertyDialogWindowAdapter() {
			public void windowClosing(WindowEvent e) {
				execute();
			}
		};
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}
}
