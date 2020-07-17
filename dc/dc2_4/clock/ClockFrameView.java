package dc2_4.clock;

import java.io.IOException;

import javax.swing.JFrame;

import dc2_4.abstracts.PrintGenerator;
import dc2_4.clock.menu.ClockMenuBarView;
import dc2_4.clock.time.TimePanelView;
import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;
import dc2_4.interfaces.Observer;
import dc2_4.view.FrameView;

public class ClockFrameView extends FrameView implements Observer {
	private final TimePanelView timePanel;
	private final ClockMenuBarView menuBar;

	public ClockFrameView(final DIGenerator generator, final DIService service) throws IOException {
		super(new JFrame(), generator, service);
		this.timePanel = new TimePanelView(this.generator, this.service);
		this.menuBar = new ClockMenuBarView(generator, service, this.view);
		this.view.setJMenuBar(this.menuBar.view);
		this.view.add(this.timePanel.view);
		this.view.setSize(
				this.service.timeService.getFontMetrics().stringWidth(this.service.timeService.getTime()) + 10,
				this.service.timeService.getFontMetrics().getHeight() + 10);
		this.view.addWindowListener(new ClockWindowAdapter(this, service.clockFrameService));
		this.view.setBackground(this.service.timeService.getBackgroundColor());
		//this.view.setMinimumSize(new Dimension(100, 100));
		this.view.setLocation(this.service.clockFrameService.getFrameX(), this.service.clockFrameService.getFrameY());
		this.view.setVisible(true);

		this.service.clockFrameService.setSize(this.view.getSize());

		// DI
		this.generator.clockFramePrintGenerator.addObserver(this);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.view.setBackground(this.service.timeService.getBackgroundColor());

		this.service.clockFrameService.setSpaceX(this.service.timeService.getFontMetrics()
				.stringWidth(this.service.timeService.getTime()) / 4);
		this.service.clockFrameService.setSpaceY(this.service.timeService.getFontMetrics().getHeight() / 4);

		this.view.setSize(
				this.service.clockFrameService.getSpaceX()
						+ this.service.timeService.getFontMetrics().stringWidth(this.service.timeService.getTime()),
				this.service.clockFrameService.getSpaceY() + (int) this.menuBar.view.getSize().getHeight()
						+ this.service.timeService.getFontMetrics().getHeight());

		//		this.view.setMinimumSize(
		//				new Dimension(this.service.timeService.getFontMetrics().stringWidth(this.service.timeService.getTime()),
		//						this.service.timeService.getFontMetrics().getHeight() + ClockFrameService.SPACE_Y));
		//		System.out.println(this.view.getSize().getHeight() + ": " + this.view.getSize().getWidth());
	}
}
