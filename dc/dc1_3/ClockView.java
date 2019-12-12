package dc1_3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;

public class ClockView extends Window implements Runnable, MouseListener, MouseMotionListener {
	private static final ClockView clockView = new ClockView();

	private final ClockService clockService = ClockService.getInstance();
	private final PopupMenu popupMenu = PopupMenuView.getInstance().getPopupMenu();
	static final Thread thread = new Thread(clockView);

	private int mouseX;
	private int mouseY;
	private int preMouseX = this.getBounds().x;
	private int preMouseY = this.getBounds().y;
	private int dx;
	private int dy;

	private ClockView() {
		super(new Frame());
		addWindowListener(new ClockWindowAdapter());
		add(popupMenu);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2) {
			return;
		}
		this.toFront();
		popupMenu.show(this, e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dx = 0;
		dy = 0;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	public void mouseDragged(MouseEvent e) {
		Point point = e.getPoint();
		mouseX = point.x;
		mouseY = point.y;
		dx = mouseX - preMouseX;
		dy = mouseY - preMouseY;
		this.setBounds(this.getBounds().x + dx, this.getBounds().y + dy, clockService.getWidth(),
				clockService.getHeight());
		System.out.println(dx + ", " + dy);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point point = e.getPoint();
		preMouseX = point.x;
		preMouseY = point.y;
	}

	public void paint(Graphics g) {
		// フレームの大きさによって文字の大きさを変える
		final Dimension size = getSize();

		// ダブルバッファリング
		final Image back = createImage(size.width, size.height);
		final Graphics buffer = back.getGraphics();
		buffer.setColor(clockService.getFontColor());
		buffer.setFont(clockService.getFontMetrics().getFont());
		buffer.drawString(clockService.getTime(),
				(int) (size.width - clockService.getFontMetrics().stringWidth(clockService.getTime())) / 2,
				(int) (size.height + clockService.getFontMetrics().getAscent()) / 2);
		g.drawImage(back, 0, 0, this);
		g.drawImage(back, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			clockService.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			clockService.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
			clockService.setSecond(Calendar.getInstance().get(Calendar.SECOND));
			setBackground(clockService.getBackgroundColor());
			repaint();
			try {
				dx = 0;
				dy = 0;
				Thread.sleep(1000); // スリープ１秒
			} catch (InterruptedException e) {
			}
		}
	}

	public static final ClockView getInstance() {
		return clockView;
	}
}
