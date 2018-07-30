package polygon.configs.impl;

import polygon.Template;
import polygon.Coordinate;
import polygon.Polygon;

/**
 * @author Dmytro Legeza
 */
public class Planer implements Template {
	private static final int X_SIZE = 20;
	private static final int Y_SIZE = 20;

	public Polygon getField() {
		Polygon field = Polygon.getInstance(X_SIZE, Y_SIZE);
		field.alive(new Coordinate(2,1));
		field.alive(new Coordinate(3,2));
		field.alive(new Coordinate(3,3));
		field.alive(new Coordinate(2,3));
		field.alive(new Coordinate(1,3));
		return field;
	}

}
