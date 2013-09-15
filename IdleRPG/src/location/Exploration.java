package location;

import java.util.ArrayList;
import java.util.Random;

import database.dungeons.Cave;

/**
 * The Class Exploration.
 */
public class Exploration implements Location {

	/** The instance. */
	private static Exploration	INSTANCE;

	/**
	 * Gets the single instance of Exploration.
	 * 
	 * @return single instance of Exploration
	 */
	public static final Exploration getInstance() {
		if( INSTANCE == null )
			Exploration.init();
		return Exploration.INSTANCE;
	}

	/**
	 * Inits the.
	 */
	private static final void init() {
		Exploration.INSTANCE = new Exploration();
		Exploration.INSTANCE.dungeons.add(new Cave());

	}

	/** The dungeons. */
	private final ArrayList<Dungeon>	dungeons;

	/**
	 * Instantiates a new exploration.
	 */
	private Exploration() {
		this.dungeons = new ArrayList<>();
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
