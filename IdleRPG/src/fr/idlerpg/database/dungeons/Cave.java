/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package fr.idlerpg.database.dungeons;

import fr.idlerpg.database.characters.monsters.Gobelin;
import fr.idlerpg.database.characters.monsters.Kobold;
import fr.idlerpg.database.characters.monsters.Orc;
import fr.idlerpg.location.Dungeon;

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
		this.monsters.add(new Kobold());
	}

}
