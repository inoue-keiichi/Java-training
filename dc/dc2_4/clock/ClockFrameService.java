package dc2_4.clock;

import java.awt.Color;

import main.service.Service;

public class ClockFrameService implements Service {
	private Color backgroundColor = Color.black;

	public Color getBackGroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(final Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
