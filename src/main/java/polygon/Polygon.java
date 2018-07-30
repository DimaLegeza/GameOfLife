package polygon;

/**
 * @author Dmytro Legeza
 */
public class Polygon {

	private final boolean[][] field;
	private final int xSize;
	private final int ySize;

	private Polygon(final int xSize, final int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.field = new boolean[xSize][ySize];
		for (int j = 0; j < ySize; j++){
			for (int i = 0; i < xSize; i++) {
				field[i][j] = false;
			}
		}
	}

	public static Polygon getInstance(final int xSize, final int ySize) {
		return new Polygon(xSize, ySize);
	}

	@Override
	public Polygon clone() {
		return new Polygon(xSize, ySize);
	}

	// takes in values in human format - starting with 1
	public void alive(final Coordinate coord) {
		field[coord.getX()-1][coord.getY()-1] = true;
	}

	void putAlive(final Coordinate coord) {
		field[coord.getX()][coord.getY()] = true;
	}

	boolean isAlive(final Coordinate coord) {
		if ((coord.getX() >= 0 && coord.getX() < xSize) && (coord.getY() >= 0 && coord.getY() < ySize)) {
			return field[coord.getX()][coord.getY()];
		}
		return false;
	}

	int getFieldXSize() {
		return this.xSize;
	}

	int getFieldYSize() {
		return this.ySize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++){
				boolean value = field[j][i];
				sb.append(value ? "@" : " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}


	boolean equals(Polygon p) {
		if (p == null || p.getFieldXSize() != xSize || p.getFieldYSize() != ySize) {
			return false;
		}
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++){
				Coordinate coord = new Coordinate(j,i);
				if (field[j][i] != p.isAlive(coord)) {
					return false;
				}
			}
		}
		return true;
	}
}
