package polynoms;

/**
 * @author Dmytro Legeza
 */
public enum Separator {
	LEFT("("), RIGHT(")");

	private String separator;

	Separator(final String separator) {
		this.separator = separator;
	}

	String getSeparator() {
		return separator;
	}

}