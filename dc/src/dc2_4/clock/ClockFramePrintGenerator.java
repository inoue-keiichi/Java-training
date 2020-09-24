package dc2_4.clock;

import dc2_4.abstracts.PrintGenerator;
import dc2_4.di.DIService;

public class ClockFramePrintGenerator extends PrintGenerator {

	public ClockFramePrintGenerator(DIService service) {
		super(service);
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}

}
