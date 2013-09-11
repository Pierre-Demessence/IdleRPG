/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:21
 */
package item;

import java.util.EnumMap;

import character.Attribute;
import database.items.Slot;

// TODO: Auto-generated Javadoc
/**
 * The Class Equipment.
 */
public abstract class Equipment extends Item {

	/**
	 * Gets the slot.
	 * 
	 * @return the slot
	 */
	public abstract Slot getSlot();

	/**
	 * Gets the armor bonus.
	 * 
	 * @return the armor bonus
	 */
	public abstract int getArmorBonus();

	/**
	 * Gets the attributes bonus.
	 * 
	 * @return the attributes bonus
	 */
	public abstract EnumMap<Attribute, Integer> getAttributesBonus();

}
