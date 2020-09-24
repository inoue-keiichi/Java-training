package dc2_4.clock;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ClockWindowAdapter extends WindowAdapter {
	private final ClockFrameService clockFrameService;
	private final ClockFrameView clockFrameView;

	public ClockWindowAdapter(final ClockFrameView clockFrameView, final ClockFrameService clockFrameService) {
		this.clockFrameService = clockFrameService;
		this.clockFrameView = clockFrameView;
	}

	public void windowClosing(WindowEvent e) {
		clockFrameService.setFrameX(clockFrameView.view.getBounds().x);
		clockFrameService.setFrameY(clockFrameView.view.getBounds().y);
		System.exit(0);
	}
}
