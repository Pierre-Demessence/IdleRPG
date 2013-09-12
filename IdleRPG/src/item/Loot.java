/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package item;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Loot.
 */
public class Loot {

	/** The chance. */
	private final float	chance;

	/** The item. */
	private final Item	item;

	/** The quantity. */
	private final int	quantity;

	/**
	 * Instantiates a new loot.
	 * 
	 * @param item
	 *            the item
	 * @param chance
	 *            the chance
	 * @param quantity
	 *            the quantity
	 */
	public Loot(final Item item, final float chance, final int quantity) {
		this.item = item;
		this.chance = chance;
		this.quantity = quantity;
	}

	/**
	 * Gets the item.
	 * 
	 * @return the item
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Gets the quantity.
	 * 
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Test.
	 * 
	 * @return true, if successful
	 */
	public boolean test() {
		final Random r = new Random();
		return ( r.nextFloat() <= this.chance );
	}

}
