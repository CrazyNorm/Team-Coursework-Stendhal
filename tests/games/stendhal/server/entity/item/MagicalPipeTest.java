package games.stendhal.server.entity.item;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.creature.Creature;
import games.stendhal.server.entity.creature.impl.attack.Charmed;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;

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
	
	// tests that creatures are attacking player when not holding a pipe
	@Test
	public void testNotCharmedWhenTargetted() {
		// create player and equip with pipe
		final Player charmer = PlayerTestHelper.createPlayer("charmer");
		// create a creature and make it target the player
		final Creature creature = new Creature();
		charmer.put("id", 1);
		creature.setTarget(charmer);
		// check the creature's attack strategy
		assertFalse("Creature not attacking player", creature.getAttackStrategy() instanceof Charmed);
	}
	
	// tests that creatures are charmed when they target a player holding a pipe
	@Test
	public void testCharmedWhenTargetted() {
		// create player and equip with pipe
		final Player charmer = PlayerTestHelper.createPlayer("charmer");
		final Player charmer2 = PlayerTestHelper.createPlayer("charmer2");
		PlayerTestHelper.equipWithItemToSlot(charmer, "magical pipe", "lhand");
		PlayerTestHelper.equipWithItemToSlot(charmer2, "magical pipe", "rhand");
		// create a creature and make it target the player
		final Creature creature = new Creature();
		final Creature creature2 = new Creature();
		charmer.put("id", 1);
		charmer2.put("id", 1);
		creature.setTarget(charmer);
		creature2.setTarget(charmer2);
		// check the creature's attack strategy
		assertTrue("Charmed state not applied to creature-left hand", creature.getAttackStrategy() instanceof Charmed);
		assertTrue("Charmed state not applied to creature-right hand", creature2.getAttackStrategy() instanceof Charmed);
	}
	
	// tests that creatures are charmed when the pipe is equipped
	@Test
	public void testCharmedWhenEquipped() {
		// create player and a creature and make it target the player
		final Player charmer = PlayerTestHelper.createPlayer("charmer");
		final Player charmer2 = PlayerTestHelper.createPlayer("charmer2");
		final Creature creature = new Creature();
		final Creature creature2 = new Creature();
		charmer.put("id", 1);
		charmer2.put("id", 1);
		creature.setTarget(charmer);
		creature2.setTarget(charmer2);
		// equip the players with the pipe
		PlayerTestHelper.equipWithItemToSlot(charmer, "magical pipe", "lhand");
		PlayerTestHelper.equipWithItemToSlot(charmer2, "magical pipe", "rhand");
		// check the creature's attack strategy
		assertTrue("Charmed state not applied to creature-left hand", creature.getAttackStrategy() instanceof Charmed);
		assertTrue("Charmed state not applied to creature-right hand", creature2.getAttackStrategy() instanceof Charmed);
		// unequip the pipe from the player
		charmer.drop("magical pipe");
		// check the creature's attack strategy
		assertFalse("Charmed state not removed from creature", creature.getAttackStrategy() instanceof Charmed);
	}
}
