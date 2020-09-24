package dc3_3;

public class MainService {
	private static final MainService mainService = new MainService();

	public static MainService getInstance() {
		return mainService;
	}

	private double x;
	private double y;

	private MainService() {

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}
}
