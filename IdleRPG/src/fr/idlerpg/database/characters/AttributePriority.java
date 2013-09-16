package fr.idlerpg.database.characters;

/**
 * The Enum AttributePriority.
 */
public enum AttributePriority {

	/** The high. */
	HIGH(3),

	/** The low. */
	LOW(1),

	/** The normal. */
	NORMAL(2);

	/** The factor. */
	private int	factor;

	/**
	 * Instantiates a new attribute priority.
	 * 
	 * @param factor
	 *            the factor
	 */
	private AttributePriority(final int factor) {
		this.factor = factor;
	}

	/**
	 * Gets the factor.
	 * 
	 * @return the factor
	 */
	public int getFactor() {
		return this.factor;
	}

}
