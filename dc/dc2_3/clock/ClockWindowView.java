package dc2_3.clock;

import java.awt.Dimension;

import javax.swing.JWindow;

import dc2_3.abstracts.PrintGenerator;
import dc2_3.clock.popup.ClickPopupListener;
import dc2_3.clock.popup.ClockPopup;
import dc2_3.clock.time.TimePanelView;
import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.interfaces.Observer;
import dc2_3.view.FrameView;

public class ClockWindowView extends FrameView implements Observer {
	private final TimePanelView timePanel;
	private final ClockPopup popup;
	private final DragWindowListener dagWindowListener;

	public ClockWindowView(final DIGenerator generator, final DIService service) {
		super(new JWindow(), generator, service);
		this.timePanel = new TimePanelView(this.generator, this.service);
		this.dagWindowListener = new DragWindowListener();
		this.view.add(this.timePanel.view);
		this.view.setSize(
				this.service.timeService.getFontMetrics().stringWidth(this.service.timeService.getTime()) + 10,
				this.service.timeService.getFontMetrics().getHeight() + 10);
		this.view.addWindowListener(new WindowListener());
		this.view.setBackground(this.service.timeService.getBackgroundColor());
		this.view.setVisible(true);
		this.view.setMinimumSize(new Dimension(100, 100));
		this.popup = new ClockPopup(generator, service);
		this.view.addMouseListener(new ClickPopupListener(this.popup));

		this.view.addMouseMotionListener(dagWindowListener);
		this.view.addMouseListener(dagWindowListener);

		// DI
		this.generator.clockFramePrintGenerator.addObserver(this);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.view.setBackground(this.service.timeService.getBackgroundColor());
		System.out.println(
				"sizeX: " + this.service.timeService.getFontMetrics().stringWidth(this.service.timeService.getTime()));
		System.out.println("sizeY: " + this.service.timeService.getFontMetrics().getHeight());

		ClockWindowService.SPACE_X = this.service.timeService.getFontMetrics()
				.stringWidth(this.service.timeService.getTime()) / 4;
		ClockWindowService.SPACE_Y = this.service.timeService.getFontMetrics().getHeight() / 4;

		this.view.setSize(
				ClockWindowService.SPACE_X
						+ this.service.timeService.getFontMetrics().stringWidth(this.service.timeService.getTime()),
				ClockWindowService.SPACE_Y + this.service.timeService.getFontMetrics().getHeight());
	}
}
