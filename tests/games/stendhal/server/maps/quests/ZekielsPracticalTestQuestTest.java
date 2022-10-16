package games.stendhal.server.maps.quests;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.config.ZonesXMLLoader;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.mapstuff.portal.Portal;
import utilities.PlayerTestHelper;
import utilities.ZoneAndPlayerTestImpl;

public class ZekielsPracticalTestQuestTest extends ZoneAndPlayerTestImpl {
	
	private final String QUEST_SLOT = "zekiels_practical_test";
	
	public ZekielsPracticalTestQuestTest() {
		super("temp");
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// sets up a temporary initial zone
		setupZone("temp");		
		// load zones from semos.xml config file
		ZonesXMLLoader loader = new ZonesXMLLoader(new URI("/data/conf/zones/semos.xml"));
		loader.load();
	}
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		// gives the player a candle to unlock the portal
		PlayerTestHelper.equipWithItem(player, "candle");
	}

	@Test
	public void testFloor1QuestionAnswered() {
		//Arrange
		// - sets the quest as if the 1st question was answered
		player.setQuest(QUEST_SLOT, "second_step");
		// - puts the player in the correct zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_1");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_second_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_2", player.getZone().getName());
	}
	
	@Test
	public void testFloor1QuestionSkipped() {
		//Arrange
		// - sets the quest as if the 1st question wasn't answered
		player.setQuest(QUEST_SLOT, "first_step");
		// - puts the player in the zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_1");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_second_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_1", player.getZone().getName());
	}
	
}
