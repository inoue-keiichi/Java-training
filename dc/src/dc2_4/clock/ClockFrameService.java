package dc2_4.clock;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.prefs.Preferences;

import dc2_4.interfaces.Service;

public class ClockFrameService implements Service {
	public enum FrameKey {
		FRAME_X("frameX"), FRAME_Y("frameY"), HEIGHT("frameHeight"), WIDTH("frameWidth");

		private String name;

		FrameKey(final String name) {
			this.name = name;
		}
	}

	private final Preferences prefs;
	private int spaceX;
	private int spaceY;
	private Color backgroundColor = Color.black;
	private int frameX;
	private int frameY;
	private Dimension size;

	public ClockFrameService() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		try {
			frameX = load(FrameKey.FRAME_X);
			frameY = load(FrameKey.FRAME_Y);
		} catch (final IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public Color getBackGroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(final Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setFrameX(final int frameX) {
		this.save(FrameKey.FRAME_X, frameX);
		this.frameX = frameX;
	}

	public void setFrameY(final int frameY) {
		this.save(FrameKey.FRAME_Y, frameY);
		this.frameY = frameY;
	}

	public void setSpaceX(final int spaceX) {
		this.spaceX = spaceX;
	}

	public void setSpaceY(final int spaceY) {
		this.spaceY = spaceY;
	}

	public void setSize(final Dimension size) {
		this.size = size;
	}

	public int getFrameX() {
		return frameX;
	}

	public int getFrameY() {
		return frameY;
	}

	public int getSpaceX() {
		return spaceX;
	}

	public int getSpaceY() {
		return spaceY;
	}

	public Dimension getSize() {
		return size;
	}

	public void save(final FrameKey key, final int value) {
		switch (key) {
		case FRAME_X:
			prefs.putInt(key.toString(), value);
			break;

		case FRAME_Y:
			prefs.putInt(key.toString(), value);
			break;

		case HEIGHT:
			prefs.putInt(key.toString(), value);
			break;

		case WIDTH:
			prefs.putInt(key.toString(), value);
			break;

		default:
			return;
		}
	}

	public int load(final FrameKey key) throws IOException {
		switch (key) {
		case FRAME_X:
			return prefs.getInt(key.toString(), 0);

		case FRAME_Y:
			return prefs.getInt(key.toString(), 0);

		case HEIGHT:
			return prefs.getInt(key.toString(), 200);

		case WIDTH:
			return prefs.getInt(key.toString(), 400);

		default:
			throw new IOException();
		}
	}
}
