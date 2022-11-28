package games.stendhal.server.entity.creature.impl.attack;

import games.stendhal.server.entity.creature.Creature;

public class Charmed extends HandToHand {
	
	// canAttackNow() always returns false
	// all other behaviour inherited from HandToHand
	// i.e. this strategy makes creatures follow their target but stops them ever attacking
	
	@Override
	public boolean canAttackNow(final Creature creature) {
		return false;
	}
}
