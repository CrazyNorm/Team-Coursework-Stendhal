package games.stendhal.server.entity.item;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendlRPWorld;

public class MagicalPipeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
	}

	@Test
	public void testCreation() {
		final Item pipe = SingletonRepository.getEntityManager().getItem("magical pipe");
		assertNotNull("Generated item is not null", pipe);
		assertFalse("The item is not persistent", pipe.isPersistent());
		assertEquals("You see a Magical Pipe, it can block creatures from attacking you.", pipe.getDescription());
	}
}
