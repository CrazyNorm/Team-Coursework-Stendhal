package games.stendhal.server.entity.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.creature.Creature;

public class MagicalPipe extends Item {
	
	private RPEntity equipper; // used to identify the player in onUnequipped()
	
	public MagicalPipe(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);

		setPersistent(false);
	}
	
	
	public MagicalPipe(final MagicalPipe pipe) {
		super(pipe);
	}
	
	@Override
	public String describe() {
		return "You see a Magical Pipe, it can block creatures from attacking you.";
	}
	
	// charms all enemies currently attacking when pipe is equipped
	@Override
	public boolean onEquipped (RPEntity equipper, String slot) {
		// get a list of all creatures currently attacking the player
		List<RPEntity> attackers = equipper.getAttackingRPEntities();
		for (RPEntity attacker : attackers) {
			// check the entity is a creature and add 'charmed' to its ai profile
			if (attacker instanceof Creature) {
				Map<String, String> profiles = new HashMap<String, String>(((Creature)attacker).getAIProfiles());
				profiles.put("charmed", "null");
				((Creature) attacker).setAIProfiles(profiles);
			}	
		}
		// preserve equipper for onUnequipped
		this.equipper = equipper;
		return true;
	}
	
	@Override
	public boolean onUnequipped () {
		// get a list of all creatures currently attacking the player
		List<RPEntity> attackers = this.equipper.getAttackingRPEntities();
		for (RPEntity attacker : attackers) {
			// check the entity is a creature and remove 'charmed' from its ai profile
			if (attacker instanceof Creature) {
				Map<String, String> profiles = new HashMap<String, String>(((Creature)attacker).getAIProfiles());
				profiles.remove("charmed");
				((Creature) attacker).setAIProfiles(profiles);
			}
		}
		// clears the equipper until onEquipped() is called again
		this.equipper = null;
		return true;
	}
}
