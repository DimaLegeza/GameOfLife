package polynoms;

/**
 * @author Dmytro Legeza
 */
public enum Operation {
	PLUS("+"), MINUS("-"), MULTIPLY("*"), BASE("^");

	private String operation;

	Operation(final String operation) {
		this.operation = operation;
	}

	String getOperation() {
		return operation;
	}

	String getPattern() {
		return "\\" + operation;
	}
}
