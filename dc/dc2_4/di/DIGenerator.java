package dc2_4.di;

import dc2_4.clock.ClockFramePrintGenerator;

public class DIGenerator {
	public final ClockFramePrintGenerator clockFramePrintGenerator;

	public DIGenerator(final DIService service) {
		this.clockFramePrintGenerator = new ClockFramePrintGenerator(service);
	}
}
