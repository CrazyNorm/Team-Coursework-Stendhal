package games.stendhal.server.entity.mapstuff.cart;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.apache.log4j.Logger;

import games.stendhal.common.Direction;
import games.stendhal.common.Rand;
import games.stendhal.common.constants.SoundLayer;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.events.MovementListener;
import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.ZoneEnterExitListener;
import games.stendhal.server.entity.ActiveEntity;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.mapstuff.chest.Chest;
import games.stendhal.server.events.SoundEvent;
import marauroa.common.game.RPObject;

public class HandCart extends Chest implements ZoneEnterExitListener,
		MovementListener, TurnListener{
	private static final Logger logger = Logger.getLogger(HandCart.class);

	private int startX;
	private int startY;
	private boolean multi = true;

	private List<String> sounds;

	public void push(Player p, Direction d) {
		if (!this.mayBePushed(d)) {
			return;
		}
		int x = getXAfterPush(d);
		int y = getYAfterPush(d);
		this.setPosition(x, y);
		this.sendSound();
		this.notifyWorldAboutChanges();
		if (logger.isDebugEnabled()) {
			logger.debug("HandCart [" + this.getID().toString() + "] pushed to (" + this.getX() + "," + this.getY() + ").");
		}
	}

	private void sendSound() {
		if (this.sounds != null && !this.sounds.isEmpty()) {
			SoundEvent e = new SoundEvent(Rand.rand(sounds), SoundLayer.AMBIENT_SOUND);
			this.addEvent(e);
			this.notifyWorldAboutChanges();
		}
	}

	public int getYAfterPush(Direction d) {
		return this.getY() + d.getdy();
	}

	public int getXAfterPush(Direction d) {
		return this.getX() + d.getdx();
	}

	private boolean wasPushed() {
		boolean xChanged = this.getInt("x") != this.startX;
		boolean yChanged = this.getInt("y") != this.startY;
		return xChanged || yChanged;
	}

	private boolean mayBePushed(Direction d) {
		boolean pushed = wasPushed();
		int newX = this.getXAfterPush(d);
		int newY = this.getYAfterPush(d);

		if (!multi && pushed) {
			return false;
		}

		// additional checks: new position must be free
		boolean collision = this.getZone().collides(this, newX, newY);

		return !collision;
	}

	@Override
	public void onEntered(ActiveEntity entity, StendhalRPZone zone, int newX, int newY) {
		// do nothing
	}

	@Override
	public void onExited(ActiveEntity entity, StendhalRPZone zone, int oldX, int oldY) {
		// doNothing
	}

	@Override
	public void onMoved(ActiveEntity entity, StendhalRPZone zone, int oldX,
			int oldY, int newX, int newY) {
		// do nothing on move
	}

	@Override
	public void onEntered(RPObject object, StendhalRPZone zone) {
		// do nothing
	}

	@Override
	public void onExited(RPObject object, StendhalRPZone zone) {
		// doNothing
	}
	@Override
	public boolean isObstacle(Entity entity) {
		if (entity instanceof RPEntity) {
			return true;
		}

		return super.isObstacle(entity);
	}

	@Override
	public void beforeMove(ActiveEntity entity, StendhalRPZone zone, int oldX,
			int oldY, int newX, int newY) {
		if (entity instanceof Player) {
			Rectangle2D oldA = new Rectangle2D.Double(oldX, oldY, entity.getWidth(), entity.getHeight());
			Rectangle2D newA = new Rectangle2D.Double(newX, newY, entity.getWidth(), entity.getHeight());
			Direction d = Direction.getAreaDirectionTowardsArea(oldA, newA);
			this.push((Player) entity, d);
		}
	}

	@Override
	public void onTurnReached(int currentTurn) {
		// do nothing
	}

	@Override
	public void onAdded(StendhalRPZone zone) {
		super.onAdded(zone);
		this.startX = getX();
		this.startY = getY();
		zone.addMovementListener(this);
		zone.addZoneEnterExitListener(this);
	}

	@Override
	public void onRemoved(StendhalRPZone zone) {
		super.onRemoved(zone);
		zone.removeMovementListener(this);
		zone.removeZoneEnterExitListener(this);
	}

}
