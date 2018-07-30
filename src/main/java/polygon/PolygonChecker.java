package polygon;

/**
 * @author Dmytro Legeza
 */
class PolygonChecker {

	int neibourElementCount(final Coordinate coord, final Polygon field) {
		int count = 0;
		for (int j = coord.getY() - 1; j <= coord.getY() + 1; j++) {
			for (int i = coord.getX() - 1; i <= coord.getX() + 1; i++) {
				final Coordinate temp = new Coordinate(i, j);
				if (!coord.isEqual(temp) && field.isAlive(temp)) {
					++count;
				}
			}
		}
		return count;
	}
}
