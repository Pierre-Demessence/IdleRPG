package fr.idlerpg.location;

import java.util.ArrayList;
import java.util.Random;

import fr.idlerpg.character.Monster;

/**
 * The Class Dungeon.
 */
public abstract class Dungeon implements Location {

	/** The monsters. */
	protected ArrayList<Monster>	monsters	= new ArrayList<>();

	/** The name. */
	private String					name;

	/**
	 * Instantiates a new dungeon.
	 * 
	 * @param name
	 *            the name
	 */
	public Dungeon(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public int getLevel() {
		int level = 0;
		for( final Monster m : this.monsters )
			level += m.getLevel();
		return level / this.monsters.size();
	}

	/**
	 * Gets the monster.
	 * 
	 * @return the monster
	 */
	public Monster getMonster() {
		final Random r = new Random();
		final int i = r.nextInt(this.monsters.size());
		return (Monster) this.monsters.get(i).clone();
	}

}
