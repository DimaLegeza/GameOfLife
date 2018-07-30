package polygon;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import polygon.configs.impl.Planer;
import polygon.configs.impl.ProsperGun;
import polygon.configs.impl.Tetramino;

/**
 * @author Dmytro Legeza
 */
public class GameOfLifeTest {

	private Mutator mutator;

	@Before
	public void setUp() {
		mutator = Mutator.getInstance();
	}

	@Ignore
	@Test
	public void testPlaner() {
		mutator.applyMutation(new Planer());
	}

	@Test
	public void testProsperGun() {
		mutator.applyMutation(new ProsperGun());
	}

	@Ignore
	@Test
	public void testTetramino() {
		mutator.applyMutation(new Tetramino());
	}


}
