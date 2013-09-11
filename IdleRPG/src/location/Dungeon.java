/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:22
 */
package location;

import java.util.ArrayList;
import java.util.Random;

import character.Monster;

// TODO: Auto-generated Javadoc
/**
 * The Class Dungeon.
 */
public abstract class Dungeon implements Location {

	/** The monsters. */
	protected ArrayList<Monster>	monsters	= new ArrayList<>();
	
	/** The name. */
	protected String				name;

	/**
	 * Instantiates a new dungeon.
	 * 
	 * @param name
	 *            the name
	 */
	public Dungeon(final String name) {
		this.name = name;
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
