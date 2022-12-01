package games.stendhal.server.entity.item;

import static games.stendhal.common.constants.Actions.INVISIBLE;


import java.util.Map;

import games.stendhal.server.core.engine.GameEvent;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;


public class MinotaurArmor extends Item {
	
	public MinotaurArmor(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
		
		setPersistent(false);
	}
	
	
	public MinotaurArmor(final MinotaurArmor armor) {
		super(armor);
	}
	
	@Override
	public String describe() {
		return "You see a suit of disguise armor. It disguises you from minotaurs.";
	}
	
	// disguises you from enemies in athor zone (minotaur)
	@Override
	public boolean onEquipped (RPEntity equipper, String slot) {
		if (equipper instanceof Player) {
			final Player player = (Player) equipper;
			final StendhalRPZone zone = player.getZone();
			player.setDisguise("minotaur", zone.getName());
		}
		
		return true;
	}
	
	@Override
	public boolean onUnequipped () {
		if (this.getContainerOwner() instanceof Player) {
			final Player player = (Player) this.getContainerOwner();
			player.setInvisible(false);
			new GameEvent(player.getName(), INVISIBLE, "off").raise();
		}
		return true;
	}
}
