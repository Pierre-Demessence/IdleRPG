/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package character;

// TODO: Auto-generated Javadoc
/**
 * The Enum DelayType.
 */
enum DelayType {

	/** The dungeon. */
	DUNGEON(2500),

	/** The exploration. */
	EXPLORATION(5000),

	/** The fighting. */
	FIGHTING(1000),

	/** The resurrection. */
	RESURRECTION(10000),

	/** The shop buying. */
	SHOP_BUYING(1000),

	/** The shop selling. */
	SHOP_SELLING(1000);

	/** The delay. */
	private long	delay;

	/**
	 * Instantiates a new delay type.
	 * 
	 * @param ms
	 *            the ms
	 */
	DelayType(final long ms) {
		this.delay = ms;
	}

	/**
	 * Gets the delay.
	 * 
	 * @return the delay
	 */
	public long getDelay() {
		return this.delay;
	}
}