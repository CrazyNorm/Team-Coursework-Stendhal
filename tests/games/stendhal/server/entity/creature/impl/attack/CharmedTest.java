package games.stendhal.server.entity.creature.impl.attack;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.creature.Creature;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.CreatureTestHelper;

public class CharmedTest {

	@BeforeClass
	public static void setUpbeforeClass() {
		MockStendlRPWorld.get();
		CreatureTestHelper.generateRPClasses();
	}
	
	// tests that creatures can never attack their target
	@Test
	public void testCanAttackNow() {
		// create attack strategy, a creature to attack, and a player
		final Charmed c = new Charmed();
		final Creature creature = new Creature();
		final Player charmer = PlayerTestHelper.createPlayer("charmer");
		// set the creature to target the player
		charmer.put("id", 1);
		creature.setTarget(charmer);
		// check if the creature is able to attack
		assertFalse("Charmed creature can still attack", c.canAttackNow(creature));
	}

}
