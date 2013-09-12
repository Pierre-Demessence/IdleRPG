/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:18
 */
package location;

import java.util.ArrayList;
import java.util.Random;

import database.dungeons.Cave;

// TODO: Auto-generated Javadoc
/**
 * The Class Exploration.
 */
public class Exploration implements Location {

	/** The instance. */
	private static Exploration	instance;

	/** The is initialised. */
	private static boolean		isInitialised;

	/**
	 * Gets the single instance of Exploration.
	 * 
	 * @return single instance of Exploration
	 */
	public static final Exploration getInstance() {
		if( !Exploration.isInitialised )
			Exploration.init();
		return Exploration.instance;
	}

	/**
	 * Inits the.
	 */
	private static final void init() {

		Exploration.instance = new Exploration();
		Exploration.instance.dungeons.add(new Cave());

		Exploration.isInitialised = true;
	}

	/** The dungeons. */
	private final ArrayList<Dungeon>	dungeons;

	/**
	 * Instantiates a new exploration.
	 */
	private Exploration() {
		this.dungeons = new ArrayList<>();
		this.dungeons.add(new Cave());
	}

	/**
	 * Gets the dungeon.
	 * 
	 * @return the dungeon
	 */
	public Dungeon getDungeon() {
		final Random r = new Random();
		final int i = r.nextInt(this.dungeons.size());
		return this.dungeons.get(i);
	}
}
