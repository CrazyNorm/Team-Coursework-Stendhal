package games.stendhal.server.entity.item;

import java.util.Map;

public class MagicalPipe extends Item {
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
}
