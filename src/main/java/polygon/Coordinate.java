package polygon;

/**
 * @author Dmytro Legeza
 */
public class Coordinate {
	private int x;

	private int y;

	public Coordinate(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	boolean isEqual(final Coordinate other) {
		return other != null && x == other.getX() && y == other.getY();
	}
}
