package character;

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

	/** The shop buying. */
	SHOP_BUYING(1000),

	/** The shop selling. */
	SHOP_SELLING(1000),
	RESURRECTION(10000);

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