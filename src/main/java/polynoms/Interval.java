package polynoms;

/**
 * @author Dmytro Legeza
 */
public class Interval {
	private int start;
	private int end;

	public Interval(final int start) {
		this.start = start;
	}

	public Interval(final int start, final int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(final int end) {
		this.end = end;
	}

	public boolean contains(Interval other) {
		return start <= other.getStart() && end >= other.getEnd();
	}

	public boolean equals(Interval other) {
		return start == other.getStart() && end == other.getEnd();
	}
}
