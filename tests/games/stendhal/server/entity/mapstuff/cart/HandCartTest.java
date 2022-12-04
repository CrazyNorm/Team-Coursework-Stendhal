package games.stendhal.server.entity.mapstuff.cart;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.PassiveEntity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.item.Corpse;
import games.stendhal.server.entity.mapstuff.block.Block;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPClass;
import marauroa.common.game.SlotIsFullException;
import utilities.PlayerTestHelper;

public class HandCartTest {
	
	@BeforeClass
	public static void beforeClass() {
		PlayerTestHelper.generatePlayerRPClasses();
		MockStendlRPWorld.get();
	}
	
	@Before
	public void setUp() throws Exception {
		if (!RPClass.hasRPClass("entity")) {
			Entity.generateRPClass();
		}

		if (!RPClass.hasRPClass("handcart")) {
			HandCart.generateRPClass();
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests for size.
	 */
	@Test(expected = SlotIsFullException.class)
	public final void testSize() {
		final HandCart hc = new HandCart();
		assertEquals(0, hc.size());
		for (int i = 0; i < 30; i++) {
			hc.add(new PassiveEntity() {
			});
		}
		assertEquals(30, hc.size());
		hc.add(new PassiveEntity() {
		});
	}

	/**
	 * Tests for open.
	 */
	@Test
	public final void testOpen() {
		final HandCart hc = new HandCart();
		assertFalse(hc.isOpen());
		hc.open();

		assertTrue(hc.isOpen());
		hc.close();
		assertFalse(hc.isOpen());
	}

	/**
	 * Tests for onUsed.
	 */
	@Test
	public final void testOnUsed() {
		final HandCart hc = new HandCart();
		assertFalse(hc.isOpen());
		hc.onUsed(new RPEntity() {

			@Override
			protected void dropItemsOn(final Corpse corpse) {
			}

			@Override
			public void logic() {

			}
		});

		assertTrue(hc.isOpen());
		hc.onUsed(new RPEntity() {

			@Override
			protected void dropItemsOn(final Corpse corpse) {
			}

			@Override
			public void logic() {

			}
		});
		assertFalse(hc.isOpen());
	}
	
	@Test
	public void testPush() {
		HandCart b = new HandCart();
		b.setPosition(0, 0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		Player p = PlayerTestHelper.createPlayer("pusher");
		z.add(b);
		assertThat(Integer.valueOf(b.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(b.getY()), is(Integer.valueOf(0)));

		b.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(b.getX()), is(Integer.valueOf(1)));
		assertThat(Integer.valueOf(b.getY()), is(Integer.valueOf(0)));

		b.push(p, Direction.LEFT);
		assertThat(Integer.valueOf(b.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(b.getY()), is(Integer.valueOf(0)));

		b.push(p, Direction.DOWN);
		assertThat(Integer.valueOf(b.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(b.getY()), is(Integer.valueOf(1)));

		b.push(p, Direction.UP);
		assertThat(Integer.valueOf(b.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(b.getY()), is(Integer.valueOf(0)));
	}
	
	@Test
	public void testCoordinatesAfterPush() {
		HandCart b = new HandCart();
		b.setPosition(0, 0);
		assertThat(Integer.valueOf(b.getXAfterPush(Direction.UP)), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(b.getYAfterPush(Direction.UP)), is(Integer.valueOf(-1)));

		assertThat(Integer.valueOf(b.getXAfterPush(Direction.DOWN)), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(b.getYAfterPush(Direction.DOWN)), is(Integer.valueOf(1)));

		assertThat(Integer.valueOf(b.getXAfterPush(Direction.LEFT)), is(Integer.valueOf(-1)));
		assertThat(Integer.valueOf(b.getYAfterPush(Direction.LEFT)), is(Integer.valueOf(0)));

		assertThat(Integer.valueOf(b.getXAfterPush(Direction.RIGHT)), is(Integer.valueOf(1)));
		assertThat(Integer.valueOf(b.getYAfterPush(Direction.RIGHT)), is(Integer.valueOf(0)));
	}
	
	@Test
	public void testCollisionOnPush() throws Exception {
		HandCart b1 = new HandCart();
		b1.setPosition(0, 0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		Player p = PlayerTestHelper.createPlayer("pusher");
		z.add(b1, false);

		// one successful push
		b1.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(b1.getX()), is(Integer.valueOf(1)));

		// now we add an obstacle right of b1
		Block b2 = new Block(true);
		b2.setPosition(02, 0);
		z.add(b2, false);

		// push should not be executed now and stay at the former place
		b1.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(b1.getX()), is(Integer.valueOf(1)));
	}
	
	
}
