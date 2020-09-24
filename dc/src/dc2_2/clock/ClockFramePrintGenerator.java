package dc2_2.clock;

import dc2_2.abstracts.PrintGenerator;
import dc2_2.di.DIService;

public class ClockFramePrintGenerator extends PrintGenerator {

	public ClockFramePrintGenerator(DIService service) {
		super(service);
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}

}
