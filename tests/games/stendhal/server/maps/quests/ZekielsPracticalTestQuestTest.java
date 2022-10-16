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
	
	@Test
	public void testFloor2QuestionAnswered() {
		//Arrange
		// - sets the quest as if the 2nd question was answered
		player.setQuest(QUEST_SLOT, "third_step");
		// - puts the player in the correct zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_2");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_third_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_3", player.getZone().getName());
	}
	
	@Test
	public void testFloor2QuestionSkipped() {
		//Arrange
		// - sets the quest as if the 2nd question wasn't answered
		player.setQuest(QUEST_SLOT, "second_step");
		// - puts the player in the zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_2");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_third_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_2", player.getZone().getName());
	}
	
	@Test
	public void testFloor3QuestionAnswered() {
		//Arrange
		// - sets the quest as if the 3rd question was answered
		player.setQuest(QUEST_SLOT, "fourth_step");
		// - puts the player in the correct zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_3");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_fourth_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_4", player.getZone().getName());
	}
	
	@Test
	public void testFloor3QuestionSkipped() {
		//Arrange
		// - sets the quest as if the 3rd question wasn't answered
		player.setQuest(QUEST_SLOT, "third_step");
		// - puts the player in the zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_3");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_fourth_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_3", player.getZone().getName());
	}
	
	@Test
	public void testFloor4QuestionAnswered() {
		//Arrange
		// - sets the quest as if the 4th question was answered
		player.setQuest(QUEST_SLOT, "fifth_step");
		// - puts the player in the correct zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_4");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_fifth_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_5", player.getZone().getName());
	}
	
	@Test
	public void testFloor4QuestionSkipped() {
		//Arrange
		// - sets the quest as if the 4th question wasn't answered
		player.setQuest(QUEST_SLOT, "fourth_step");
		// - puts the player in the zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_4");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_fifth_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_4", player.getZone().getName());
	}
	
	@Test
	public void testFloor5QuestionAnswered() {
		//Arrange
		// - sets the quest as if the 5th question was answered
		player.setQuest(QUEST_SLOT, "sixth_step");
		// - puts the player in the correct zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_5");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_sixth_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_6", player.getZone().getName());
	}
	
	@Test
	public void testFloor5QuestionSkipped() {
		//Arrange
		// - sets the quest as if the 5th question wasn't answered
		player.setQuest(QUEST_SLOT, "fifth_step");
		// - puts the player in the zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_5");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_sixth_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_5", player.getZone().getName());
	}
	
	@Test
	public void testFloor6QuestionAnswered() {
		//Arrange
		// - sets the quest as if the 6th question was answered
		player.setQuest(QUEST_SLOT, "last_step");
		// - puts the player in the correct zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_6");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_seventh_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_7", player.getZone().getName());
	}
	
	@Test
	public void testFloor6QuestionSkipped() {
		//Arrange
		// - sets the quest as if the 6th question wasn't answered
		player.setQuest(QUEST_SLOT, "sixth_step");
		// - puts the player in the zone for the 1st question
		StendhalRPZone zone = StendhalRPWorld.get().getZone("int_semos_wizards_tower_6");
		PlayerTestHelper.registerPlayer(player, zone);
		// - gets the portal to the next question
		Portal portal = zone.getPortal("portal_to_seventh_floor");
		// - moves the player close enough to use the portal
		player.setPosition(portal.getX(), portal.getY());
		
		//Act - try use the portal
		portal.onUsed(player);
		
		//Assert - check if the player has moved to the next question
		assertEquals("int_semos_wizards_tower_6", player.getZone().getName());
	}
}
