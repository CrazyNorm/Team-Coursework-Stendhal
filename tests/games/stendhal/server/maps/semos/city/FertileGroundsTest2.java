/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.semos.city;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.item.Shovel;
import games.stendhal.server.entity.mapstuff.area.AreaEntity;
import games.stendhal.server.entity.mapstuff.area.FertileGround;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import marauroa.common.game.RPClass;
import utilities.PlayerTestHelper;
import utilities.RPClass.EntityTestHelper;
import games.stendhal.server.core.rule.EntityManager;

public class FertileGroundsTest2 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		Log4J.init();
		EntityTestHelper.generateRPClasses();
		if (!RPClass.hasRPClass("area")) {
			AreaEntity.generateRPClass();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests for configureZoneNullNull.
	 */
	@Test
	public void testConfigureZoneNullNull() {
		final FertileGrounds fg = new FertileGrounds();
		fg.configureZone(null, null);
	}

	/**
	 * Tests for configureZoneNullvalues.
	 */
	@Test
	public void testConfigureZoneNullvalues() {
		final FertileGrounds fg = new FertileGrounds();
		final StendhalRPZone zone = new StendhalRPZone("zone");
		
		final Map<String, String> attribs = new HashMap<String, String>();
		attribs.put("x", null);
		attribs.put("y", null);
		attribs.put("width", null);
		attribs.put("height", null);

		fg.configureZone(zone, attribs);
	}
	/**
	 * Tests for configureZone.
	 */
	@Test
	public void testConfigureZone() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final EntityManager entityMan = SingletonRepository.getEntityManager();
		Player player = PlayerTestHelper.createPlayer("test_player");
		zone.add(player);
		player.setPosition(1,1);
		final Item shovel = entityMan.getItem("shovel");
		player.equip("bag", shovel);
		final Shovel shovel_get = (Shovel) player.getFirstEquipped("shovel");
		shovel_get.onUsed(player);
		assertFalse(0 + ":" + 0,
				zone.getEntityAt(0, 0) instanceof FertileGround);
		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < 4; y++) {
				if (!(x==1&&y==1)) {
					assertTrue(x + ":" + y,
							zone.getEntityAt(x, y) instanceof FertileGround);
				}
			}
		}

	}
	@Test
	public void testConfigureZone1() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final EntityManager entityMan = SingletonRepository.getEntityManager();
		Player player = PlayerTestHelper.createPlayer("test_player");
		zone.add(player);
		player.setPosition(1,1);
		final Item shovel = entityMan.getItem("shovel");
		player.equip("bag", shovel);
		final Shovel shovel_get = (Shovel) player.getFirstEquipped("shovel");
		shovel_get.onUsed(player);
		assertFalse(0 + ":" + 0,
				zone.getEntityAt(0, 0) instanceof FertileGround);
		assertFalse(1 + ":" + 1, zone.getEntityAt(1, 1) instanceof FertileGround);
	}
	
	@Test
	public void testConfigureZone2() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final EntityManager entityMan = SingletonRepository.getEntityManager();
		Player player = PlayerTestHelper.createPlayer("test_player");
		zone.add(player);
		player.setPosition(2,2);
		final Item shovel = entityMan.getItem("shovel");
		player.equip("bag", shovel);
		final Shovel shovel_get = (Shovel) player.getFirstEquipped("shovel");
		shovel_get.onUsed(player);
		assertFalse(0 + ":" + 0, zone.getEntityAt(0, 0) instanceof FertileGround);
		for (int x = 2; x < 5; x++) {
			for (int y = 2; y < 2; y++) {
				if (!(x==2&&y==2)) {
					assertTrue(x + ":" + y,
							zone.getEntityAt(x, y) instanceof FertileGround);
				}
			}
		}
	}
	
	@Test
	public void testConfigureZone3() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final EntityManager entityMan = SingletonRepository.getEntityManager();
		Player player = PlayerTestHelper.createPlayer("test_player");
		zone.add(player);
		player.setPosition(3,3);
		final Item shovel = entityMan.getItem("shovel");
		player.equip("bag", shovel);
		final Shovel shovel_get = (Shovel) player.getFirstEquipped("shovel");
		shovel_get.onUsed(player);
		player.setPosition(10,10);
		assertFalse(0 + ":" + 0, zone.getEntityAt(0, 0) instanceof FertileGround);
		for (int x = 3; x < 6; x++) {
			for (int y = 3; y < 6; y++) {
					assertTrue(x + ":" + y,
							zone.getEntityAt(x, y) instanceof FertileGround);
			}
		}
	}
}
