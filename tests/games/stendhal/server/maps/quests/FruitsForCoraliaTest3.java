/***************************************************************************
 *                   (C) Copyright 2003-2011 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

package games.stendhal.server.maps.quests;

//import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.ados.tavern.BarMaidNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class FruitsForCoraliaTest3 extends ZonePlayerAndNPCTestImpl {

	private Player player = null;
	private SpeakerNPC npc = null;

	private Engine en = null;

	private String questSlot;
	private static final String ZONE_NAME = "int_ados_tavern_0";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	public FruitsForCoraliaTest3() {
		super(ZONE_NAME, "Coralia");
	}

	@Override
	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone(ZONE_NAME);
		new BarMaidNPC().configureZone(zone, null);

		quest = new FruitsForCoralia();
		quest.addToWorld();

		questSlot = quest.getSlotName();

		player = PlayerTestHelper.createPlayer("bob");
	}

	@Test
	public void testQuest() {
		npc = SingletonRepository.getNPCList().get("Coralia");
		en = npc.getEngine();
		


		// -----------------------------------------------


		en.step(player, "hi");

		// -----------------------------------------------

		assertEquals("Oh hello there, did I just catch you admiring my beautiful #hat?", getReply(npc));

		// -----------------------------------------------

		en.step(player, "hat");

		// -----------------------------------------------

		assertEquals("It's a shame for you to see it all withered like this, it really needs some fresh #fruits...", getReply(npc));

		// -----------------------------------------------

		en.step(player, "fruit");

		// -----------------------------------------------

		assertEquals("Would you be kind enough to find me some fresh fruits for my hat? I'd be ever so grateful!", getReply(npc));

		// -----------------------------------------------

		en.step(player, "no");

		// -----------------------------------------------

		assertEquals("These exotic hats don't keep themselves you know...", getReply(npc));

		// -----------------------------------------------

		en.step(player, "bye");

		// -----------------------------------------------

		assertEquals("Bye.", getReply(npc));

		// -----------------------------------------------


		// -----------------------------------------------

		en.step(player, "hi");

		// -----------------------------------------------

		assertEquals("Oh hello there, did I just catch you admiring my beautiful #hat?", getReply(npc));

		// -----------------------------------------------

		en.step(player, "quest");

		// -----------------------------------------------

		assertEquals("Are you willing to find me some fresh fruits for my hat yet?", getReply(npc));

		// -----------------------------------------------

		en.step(player, "yes");

		// -----------------------------------------------

		assertEquals("That's wonderful! I'd like these fresh fruits: 4 #apples, 5 #bananas, 9 #cherries, 2 #'bunches of grapes', 4 #pears, 2 #pomegranates, and a #watermelon.", getReply(npc));

		// -----------------------------------------------

		en.step(player, "apples");

		// -----------------------------------------------

		assertEquals("Glowing, radiant apples! The ones I have just now came from somewhere east of Semos.", getReply(npc));

		// -----------------------------------------------

		en.step(player, "bye");

		// -----------------------------------------------

		assertEquals("Bye.", getReply(npc));

		// -----------------------------------------------
		// -----------------------------------------------

		en.step(player, "hi");
		assertEquals("Hello again. If you've brought me some fresh fruits for my #hat, or #everything, I'll happily take them!", getReply(npc));
		en.step(player, "everything");
		assertEquals("You haven't got all the items needed. Try again when you have all the items needed in your bag.", getReply(npc));
		en.step(player, "bye");

		assertEquals("Bye.", getReply(npc));

		// -----------------------------------------------
	

		player.setQuest(questSlot, "done;0");
//		//player.setQuest(questSlot, 1, "0");	This doesn't seem to work either.
//
//
//		//TODO: correct test (or fix bug in quest ^^)
//		/*
//		assertEquals("I'm sorry to say that the fruits you brought for my hat aren't very fresh anymore.. Would you be kind enough to find me some more?", getReply(npc));
//
//		en.step(player, "yes");
//
//		assertEquals("That's wonderful! I'd like these fresh fruits: 4 #apples, 5 #bananas, 9 #cherries, 2 #'bunches of grapes', 4 #pears, 2 #pomegranates, and a #watermelon?", getReply(npc));
//
//		en.step(player, "bye");
//
//		assertEquals("Bye.", getReply(npc));
//		*/
		
		
	}
}
