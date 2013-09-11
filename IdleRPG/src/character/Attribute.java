/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:20
 */
package character;

// TODO: Auto-generated Javadoc
/**
 * The Enum Attribute.
 */
public enum Attribute {

	/** The charisma. */
	CHARISMA("CHA"),

	/** The constitution. */
	CONSTITUTION("CON"),

	/** The dexterity. */
	DEXTERITY("DEX"),

	/** The intelligence. */
	INTELLIGENCE("INT"),

	/** The strengh. */
	STRENGH("STR"),

	/** The wisdom. */
	WISDOM("WIS");

	/** The abreviation. */
	private String	abreviation;

	/**
	 * Instantiates a new attribute.
	 * 
	 * @param abreviation
	 *            the abreviation
	 */
	private Attribute(final String abreviation) {
		this.abreviation = abreviation;
	}

	/**
	 * Gets the abreviation.
	 * 
	 * @return the abreviation
	 */
	public String getAbreviation() {
		return this.abreviation;
	}
}