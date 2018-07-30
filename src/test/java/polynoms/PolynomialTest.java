package polynoms;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Dmytro Legeza
 */
public class PolynomialTest {

	private Process process;

	@Before
	public void setUp() {
		process = new Process();
	}

	@Test
	public void test1() {
		String polynom = "84-x+x*37*80+78-98+x-(x)+(23)*x+x-x+x+78-(x*32+61+x-10*x*x)-(59*x)-(x)-x*49+x+23*72+0+(x+x)+(x*(x))";
		process.simplify(polynom, 2);
	}

	@Test
	public void test2() {
		String polynom = "3*(x*(x+1)*(x+2)+x*(2*x*x+1-2*x+x)*x+x*(x+1)+x+1)*x+8";
		process.simplify(polynom, 2);
	}

	@Test
	public void test3() {
		String polynom = "-20*5+x^2-(x)";
		process.simplify(polynom, 2);
	}

	@Test
	public void test4() {
		String polynom = "((1+x)*x+2+24)*(((x+4)+1)+x^3+6)";
		process.simplify(polynom, 2);
	}

}