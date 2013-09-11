/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:22
 */
package gui;

// TODO: Auto-generated Javadoc
/**
 * The Enum ScreenState.
 */
public enum ScreenState {

	/** The heroes list. */
	HEROES_LIST(0),
	
	/** The shop status. */
	SHOP_STATUS(1);

	/** The id. */
	private int	ID;

	/**
	 * Instantiates a new screen state.
	 * 
	 * @param ID
	 *            the id
	 */
	ScreenState(final int ID) {
		this.ID = ID;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getID() {
		return this.ID;
	}
}
