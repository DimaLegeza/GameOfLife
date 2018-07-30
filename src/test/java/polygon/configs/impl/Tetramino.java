package polygon.configs.impl;

import polygon.Template;
import polygon.Coordinate;
import polygon.Polygon;

/**
 * @author Dmytro Legeza
 */
public class Tetramino implements Template {
	private static final int X_SIZE = 10;
	private static final int Y_SIZE = 10;
	public Polygon getField() {
		Polygon field = Polygon.getInstance(X_SIZE, Y_SIZE);
		field.alive(new Coordinate(5,1));
		field.alive(new Coordinate(5,2));
		field.alive(new Coordinate(5,3));


		field.alive(new Coordinate(1,5));
		field.alive(new Coordinate(2,5));
		field.alive(new Coordinate(3,5));

		field.alive(new Coordinate(5,7));
		field.alive(new Coordinate(5,8));
		field.alive(new Coordinate(5,9));

		field.alive(new Coordinate(7,5));
		field.alive(new Coordinate(8,5));
		field.alive(new Coordinate(9,5));
		return field;
	}
}
