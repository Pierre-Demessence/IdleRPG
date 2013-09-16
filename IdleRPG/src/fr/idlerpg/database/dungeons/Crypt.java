package fr.idlerpg.database.dungeons;

import fr.idlerpg.database.characters.monsters.Skeleton;
import fr.idlerpg.database.characters.monsters.Zombie;
import fr.idlerpg.location.Dungeon;

/**
 * The Class Crypt.
 */
public class Crypt extends Dungeon {

	/**
	 * Instantiates a new crypt.
	 */
	public Crypt() {
		super("Crypt");
		this.monsters.add(new Zombie());
		this.monsters.add(new Skeleton());
	}

}
