package dc2_2;

import dc2_2.clock.ClockFrameView;
import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;

public class Main {
	public static void main(String[] args) {
		DIService service = new DIService();
		DIGenerator generator = new DIGenerator(service);
		new ClockFrameView(generator, service);
	}
}
