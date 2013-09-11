/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:21
 */
package database.dungeons;

import location.Dungeon;
import database.monsters.Gobelin;
import database.monsters.Orc;

// TODO: Auto-generated Javadoc
/**
 * The Class Cave.
 */
public class Cave extends Dungeon {

	/**
	 * Instantiates a new cave.
	 */
	public Cave() {
		super("Cave");
		this.monsters.add(new Gobelin());
		this.monsters.add(new Orc());
	}

}
