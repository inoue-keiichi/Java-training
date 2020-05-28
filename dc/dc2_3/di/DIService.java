package dc2_3.di;

import dc2_3.clock.ClockWindowService;
import dc2_3.clock.time.TimeService;

public class DIService {
	public final TimeService timeService;
	public final ClockWindowService clockFrameService;

	public DIService() {
		timeService = new TimeService();
		clockFrameService = new ClockWindowService();
	}
}
