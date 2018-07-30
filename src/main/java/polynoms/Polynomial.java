package polynoms;

import static polynoms.Operation.BASE;
import static polynoms.Operation.MINUS;
import static polynoms.Operation.MULTIPLY;
import static polynoms.Operation.PLUS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Polynomial {
	private int[] coef;  // coefficients
	private int deg;     // degree of polynomial (0 for the zero polynomial)

	// a * x^b
	public Polynomial(int a, int b) {
		coef = new int[b+1];
		coef[b] = a;
		deg = degree();
	}

	// return the degree of this polynomial (0 for the zero polynomial)
	public int degree() {
		int d = 0;
		for (int i = 0; i < coef.length; i++)
			if (coef[i] != 0) d = i;
		return d;
	}

	// return c = a + b
	public Polynomial plus(Polynomial b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
		for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
		for (int i = 0; i <= b.deg; i++) c.coef[i] += b.coef[i];
		c.deg = c.degree();
		return c;
	}

	// return (a - b)
	public Polynomial minus(Polynomial b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
		for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
		for (int i = 0; i <= b.deg; i++) c.coef[i] -= b.coef[i];
		c.deg = c.degree();
		return c;
	}

	// return (a * b)
	public Polynomial times(Polynomial b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(0, a.deg + b.deg);
		for (int i = 0; i <= a.deg; i++)
			for (int j = 0; j <= b.deg; j++)
				c.coef[i+j] += (a.coef[i] * b.coef[j]);
		c.deg = c.degree();
		return c;
	}

	// convert String to Polynomial
	public static Polynomial compose(String b) {
		boolean startsWithMin = b.startsWith(MINUS.getOperation());
		if (b.contains(MINUS.getOperation())) {
			b = b.replace(MINUS.getOperation(), PLUS.getOperation() + MINUS.getOperation());
		}
		if (startsWithMin) {
			b = b.substring(1);
		}
		return process(b, null);
	}

	public static Polynomial compose(String b, Map<String, Polynomial> polynomialMap) {
		boolean startsWithMin = b.startsWith(MINUS.getOperation());
		if (b.contains(MINUS.getOperation())) {
			b = b.replace(MINUS.getOperation(), PLUS.getOperation() + MINUS.getOperation());
		}
		if (startsWithMin) {
			b = b.substring(1);
		}
		return process(b, polynomialMap);
	}

	public static Polynomial negate(Polynomial in) {
		Polynomial zero = new Polynomial(0, 0);
		return zero.minus(in);
	}

	private static Polynomial process(String b, Map<String, Polynomial> polynomialMap) {
		if (b.contains(PLUS.getOperation())) {
			String[] parsedValues = b.split(PLUS.getPattern());
			List<Polynomial> polynomials = new LinkedList<>();
			for (String parsedValue: parsedValues) {
				polynomials.add(process(parsedValue, polynomialMap));
			}
			Iterator<Polynomial> iterator = polynomials.iterator();
			Polynomial res = iterator.next();
			while(iterator.hasNext()) {
				Polynomial polynomial = iterator.next();
				res = res.plus(polynomial);
			}
			return res;
		}
		if (b.contains(MINUS.getOperation()) && !b.startsWith(MINUS.getOperation())) {
			String[] parsedValues = b.split(MINUS.getPattern());
			List<Polynomial> polynomials = new LinkedList<>();
			for (String parsedValue: parsedValues) {
				polynomials.add(process(parsedValue, polynomialMap));
			}
			Iterator<Polynomial> iterator = polynomials.iterator();
			Polynomial res = iterator.next();
			while(iterator.hasNext()) {
				Polynomial polynomial = iterator.next();
				res = res.minus(polynomial);
			}
			return res;
		}
		if (b.contains(MULTIPLY.getOperation())) {
			String[] parsedValues = b.split(MULTIPLY.getPattern());
			List<Polynomial> polynomials = new LinkedList<>();
			for (String parsedValue: parsedValues) {
				polynomials.add(process(parsedValue, polynomialMap));
			}
			Iterator<Polynomial> iterator = polynomials.iterator();
			Polynomial res = iterator.next();
			while(iterator.hasNext()) {
				Polynomial polynomial = iterator.next();
				res = res.times(polynomial);
			}
			return res;
		}
		if (b.contains(BASE.getOperation())) {
			int base = Integer.parseInt(b.split(BASE.getPattern())[1]);
			return new Polynomial(1,base);
		}
		if (b.contains("x")) {
			return b.contains(MINUS.getOperation()) ? new Polynomial(-1,1) : new Polynomial(1,1);
		}
		if (b.contains("inner")) {
			return b.contains(MINUS.getOperation()) ? negate(polynomialMap.get(b.replace("-", ""))) : polynomialMap.get(b);
		}
		return new Polynomial(Integer.parseInt(b), 0);
	}

	// use Horner's method to compute and return the polynomial evaluated at x
	public int evaluate(int x) {
		int p = 0;
		for (int i = deg; i >= 0; i--)
			p = coef[i] + (x * p);
		return p;
	}

	// convert to string representation
	public String toString() {
		if (deg ==  0) return "" + coef[0];
		if (deg ==  1) return coef[1] + "*x+" + coef[0];
		String s = coef[deg] + "*x^" + deg;
		for (int i = deg-1; i >= 0; i--) {
			if      (coef[i] == 0) continue;
			else if (coef[i]  > 0) s = s + "+" + ( coef[i]);
			else if (coef[i]  < 0) s = s + "-" + (-coef[i]);
			if      (i == 1) s = s + "*x";
			else if (i >  1) s = s + "*x^" + i;
		}
		return s;
	}

	public static void main(String[] args) {
		Polynomial zero = new Polynomial(0, 0);

		Polynomial p1   = new Polynomial(4, 3);
		Polynomial p2   = new Polynomial(3, 2);
		Polynomial p3   = new Polynomial(1, 0);
		Polynomial p4   = new Polynomial(2, 1);
		Polynomial p    = p1.plus(p2).plus(p3).plus(p4);   // 4x^3 + 3x^2 + 1

		Polynomial q1   = new Polynomial(3, 2);
		Polynomial q2   = new Polynomial(5, 0);
		Polynomial q    = q1.plus(q2);                     // 3x^2 + 5


		Polynomial r    = p.plus(q);
		Polynomial s    = p.times(q);

		Polynomial parsed = Polynomial.compose("-1+2*x^4-3*x-4*x^3+2*x^4+2*x^5+8*7*x^3+10*x");
//		Polynomial parsed = Polynomial.compose("1+x^2-x");

//		System.out.println("zero(x) =     " + zero);
//		System.out.println("p(x) =        " + p);
//		System.out.println("q(x) =        " + q);
//		System.out.println("p(x) + q(x) = " + r);
//		System.out.println("p(x) * q(x) = " + s);
//		System.out.println("0 - p(x)    = " + zero.minus(p));
//		System.out.println("p(3)        = " + p.evaluate(3));
		System.out.println(parsed);
	}

}