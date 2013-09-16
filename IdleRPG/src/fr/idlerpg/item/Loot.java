/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package fr.idlerpg.item;

import java.util.Random;

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
	 *            the item to loot
	 * @param chance
	 *            the chance to get the item. Between 0.0f and 1.0f
	 * @param quantity
	 *            the quantity to loot
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
		if( this.item instanceof Equipment )
			return ( (Equipment) this.item ).clone(true);
		return (Item) this.item.clone();
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
	 * @return true, if the looting is successful
	 */
	public boolean test() {
		final Random r = new Random();
		return ( r.nextFloat() <= this.chance );
	}

}
