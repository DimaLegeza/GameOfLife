package polygon.configs.impl;

import polygon.Template;
import polygon.Coordinate;
import polygon.Polygon;

/**
 * @author Dmytro Legeza
 */
public class ProsperGun implements Template {
	private static final int X_SIZE = 40;
	private static final int Y_SIZE = 20;

	public Polygon getField() {
		Polygon field = Polygon.getInstance(X_SIZE, Y_SIZE);
		field.alive(new Coordinate(2,7));
		field.alive(new Coordinate(2,8));
		field.alive(new Coordinate(3,7));
		field.alive(new Coordinate(3,8));


		field.alive(new Coordinate(15,4));
		field.alive(new Coordinate(14,5));
		field.alive(new Coordinate(16,5));
		field.alive(new Coordinate(13,6));
		field.alive(new Coordinate(13,7));
		field.alive(new Coordinate(13,8));

		field.alive(new Coordinate(17,6));
		field.alive(new Coordinate(17,7));
		field.alive(new Coordinate(17,8));

		field.alive(new Coordinate(18,6));
		field.alive(new Coordinate(18,7));
		field.alive(new Coordinate(18,8));

		field.alive(new Coordinate(16,9));
		field.alive(new Coordinate(14,9));
		field.alive(new Coordinate(15,10));


		field.alive(new Coordinate(27,2));
		field.alive(new Coordinate(27,3));
		field.alive(new Coordinate(26,3));
		field.alive(new Coordinate(25,3));
		field.alive(new Coordinate(24,3));
		field.alive(new Coordinate(26,4));
		field.alive(new Coordinate(25,4));
		field.alive(new Coordinate(24,4));
		field.alive(new Coordinate(23,4));

		field.alive(new Coordinate(26,5));
		field.alive(new Coordinate(23,5));

		field.alive(new Coordinate(26,6));
		field.alive(new Coordinate(25,6));
		field.alive(new Coordinate(24,6));
		field.alive(new Coordinate(23,6));

		field.alive(new Coordinate(27,7));
		field.alive(new Coordinate(26,7));
		field.alive(new Coordinate(25,7));
		field.alive(new Coordinate(24,7));

		field.alive(new Coordinate(27,8));


		field.alive(new Coordinate(32,3));
		field.alive(new Coordinate(32,4));

		field.alive(new Coordinate(36,5));
		field.alive(new Coordinate(36,6));
		field.alive(new Coordinate(37,5));
		field.alive(new Coordinate(37,6));

		return field;
	}
}
