package dc2_3;

import dc2_3.clock.ClockWindowView;
import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;

public class Main {
	public static void main(String[] args) {
		DIService service = new DIService();
		DIGenerator generator = new DIGenerator(service);
		new ClockWindowView(generator, service);
		//To set offset.
		generator.timePanelPrintGenerator.execute();
	}
}
