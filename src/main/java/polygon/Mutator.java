package polygon;

/**
 * @author Dmytro Legeza
 */
public class Mutator {

	private Polygon field;
	private final PolygonChecker checker;

	private Mutator() {
		checker = new PolygonChecker();
	}

	public static Mutator getInstance() {
		return new Mutator();
	}

	public Mutator withField(final Polygon field) {
		this.field = field;
		return this;
	}

	private Polygon mutate() {
		Polygon newState = field.clone();
		for (int j = 0; j < field.getFieldYSize(); j++) {
			for (int i = 0; i < field.getFieldXSize(); i++) {
				Coordinate coord = new Coordinate(i, j);
				boolean isAlive = field.isAlive(coord);
				int neibourCount = checker.neibourElementCount(coord, field);
				if (!isAlive && neibourCount == 3) {
					newState.putAlive(coord);
				}
				if (isAlive && (neibourCount == 3 || neibourCount == 2)) {
					newState.putAlive(coord);
				}
			}
		}
		return newState;
	}

	public void applyMutation(final Template template) {
		Polygon field = template.getField();
		System.out.println(field.toString());
		while(true) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				System.out.println("Oops :)");
			}
			Polygon newField = withField(field).mutate();
			System.out.println(newField);
			if (newField.equals(field)) {
				break;
			}
			field = newField;
		}
	}

}
