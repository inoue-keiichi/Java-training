package dc2_2.di;

import dc2_2.clock.ClockFramePrintGenerator;

public class DIGenerator {
	public final ClockFramePrintGenerator clockFramePrintGenerator;

	public DIGenerator(final DIService service) {
		this.clockFramePrintGenerator = new ClockFramePrintGenerator(service);
	}
}
