package dc2_4;

import java.io.IOException;

import dc2_4.clock.ClockFrameView;
import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;

public class Main {
	public static void main(String[] args) throws IOException {
		DIService service = new DIService();
		DIGenerator generator = new DIGenerator(service);
		new ClockFrameView(generator, service);
		//To set offset.
		generator.timePanelPrintGenerator.execute();
	}
}
