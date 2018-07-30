package polynoms;

/**
 * @author Dmytro Legeza
 */
public class Pair<V,T> {
	private V v;

	private T t;

	public Pair(final V v, final T t) {
		this.v = v;
		this.t = t;
	}

	public V getV() {
		return v;
	}

	public T getT() {
		return t;
	}
}
