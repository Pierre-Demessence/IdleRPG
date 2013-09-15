/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package database.dungeons;

import location.Dungeon;
import database.characters.monsters.Gobelin;

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
		// this.monsters.add(new Orc());
	}

}
