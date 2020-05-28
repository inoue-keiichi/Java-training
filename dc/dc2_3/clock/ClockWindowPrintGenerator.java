package dc2_3.clock;

import dc2_3.abstracts.PrintGenerator;
import dc2_3.di.DIService;

public class ClockWindowPrintGenerator extends PrintGenerator {

	public ClockWindowPrintGenerator(DIService service) {
		super(service);
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}

}
