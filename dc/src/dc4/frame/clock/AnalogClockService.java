package dc4.frame.clock;

import java.util.Calendar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AnalogClockService {
	private static final double OUTER_MARGIN = 0;
	private static final double INTERVAL = 10;
	private static final double INNER_MARGIN = INTERVAL + OUTER_MARGIN;

	private static enum Hand {
		HOUR("hour"), MINUTE("minute"), SECOND("second");

		// private final String name;
		public final double lineWidthRatio;
		public final double lineLengthRatio;
		public final Color color;

		Hand(final String name) {
			switch (name) {
			case "hour":
				this.lineWidthRatio = 0.04;
				this.lineLengthRatio = 0.68;
				this.color = Color.RED;
				break;
			case "minute":
				this.lineWidthRatio = 0.02;
				this.lineLengthRatio = 0.84;
				this.color = Color.BLUE;
				break;
			case "second":
				this.lineWidthRatio = 0.01;
				this.lineLengthRatio = 0.92;
				this.color = Color.GREEN;
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
	}

	public void setClock(final GraphicsContext gc, final double width, final double height) {
		final double diameter = width > height ? height : width;
		final double x = (width - diameter) / 2;
		final double y = (height - diameter) / 2;

		// setup outer frame.
		createFrame(gc, diameter, x, y);

		// 太い目盛りの描画
		createLargeScale(gc, diameter, x, y);
		// 細い目盛りの描画
		createSmallScale(gc, diameter, x, y);

		// 針の描画
		createHand(gc, Hand.SECOND, diameter, x, y);
		createHand(gc, Hand.MINUTE, diameter, x, y);
		createHand(gc, Hand.HOUR, diameter, x, y);
	}

	private void createFrame(final GraphicsContext gc, final double diameter, final double x, final double y) {
		// setup outer frame.
		gc.setFill(Color.SKYBLUE); // 水色に設定
		gc.fillOval(x + OUTER_MARGIN, y + OUTER_MARGIN, diameter - OUTER_MARGIN * 2, diameter - OUTER_MARGIN * 2); // 枠を描画
		gc.setFill(Color.WHITE); // 白色に設定
		gc.fillOval(x + INNER_MARGIN, y + INNER_MARGIN, diameter - INNER_MARGIN * 2, diameter - INNER_MARGIN * 2); // 盤を描画
	}

	private void createLargeScale(final GraphicsContext gc, final double diameter, final double x, final double y) {
		final double radius = diameter / 2 - INNER_MARGIN;
		final double largeScaleLength = radius * 0.08;
		final double originX = x + diameter / 2;
		final double originY = y + diameter / 2;
		double cos, sin, r;
		gc.setLineWidth(diameter / 50); // 目盛りの幅
		gc.setStroke(Color.BLACK); // 黒色に設定
		for (int i = 0; i < 12; i++) {
			cos = Math.cos(Math.PI * i / 6);
			sin = Math.sin(Math.PI * i / 6);
			r = radius;
			gc.strokeLine(originX + r * cos, originY + r * sin, // 始点
					originX + (r - largeScaleLength) * cos, originY + (r - largeScaleLength) * sin); // 終点
		}
	}

	private void createSmallScale(final GraphicsContext gc, final double diameter, final double x, final double y) {
		final double radius = diameter / 2 - INNER_MARGIN;
		final double smallScaleLength = diameter / 50;
		final double originX = x + diameter / 2;
		final double originY = y + diameter / 2;
		double cos, sin, r;
		gc.setLineWidth(1); // 目盛りの幅
		gc.setStroke(Color.BLACK); // 黒色に設定
		for (int i = 0; i < 60; i++) {
			cos = Math.cos(Math.PI * i / 30);
			sin = Math.sin(Math.PI * i / 30);
			r = radius;
			gc.strokeLine(originX + r * cos, originY + r * sin, // 始点
					originX + (r - smallScaleLength) * cos, originY + (r - smallScaleLength) * sin); // 終点
		}
	}

	private void createHand(final GraphicsContext gc, final Hand hand, final double diameter, final double x,
			final double y) {
		final Calendar calendar = Calendar.getInstance(); // Calendarの取得
		final int hour = calendar.get(Calendar.HOUR); // 時間を取得
		final int minute = calendar.get(Calendar.MINUTE); // 分を取得
		final int second = calendar.get(Calendar.SECOND); // 秒を取得

		double theta;
		if (hand == Hand.HOUR) {
			theta = Math.PI * hour / 6 + Math.PI * minute / 360 - Math.PI / 2;
		} else if (hand == Hand.MINUTE) {
			theta = Math.PI * minute / 30 - Math.PI / 2;
		} else {
			theta = Math.PI * second / 30 - Math.PI / 2;
		}

		final double r = (diameter / 2 - INNER_MARGIN) * hand.lineLengthRatio; // 針の長さ
		final double originX = x + diameter / 2;
		final double originY = y + diameter / 2;
		gc.setLineWidth(diameter * hand.lineWidthRatio); // 針の幅
		gc.setStroke(hand.color); // 赤色に設定
		gc.strokeLine(originX, originY, originX + Math.cos(theta) * r, originY + Math.sin(theta) * r); // 秒
	}
}