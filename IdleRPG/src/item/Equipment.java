/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package item;

import java.util.EnumMap;
import java.util.Map.Entry;

import character.Attribute;
import database.items.Slot;

// TODO: Auto-generated Javadoc
/**
 * The Class Equipment.
 */
public abstract class Equipment extends Item {

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
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		EnumMap<Attribute, Integer> attributesBonus = new EnumMap<>(Attribute.class);
		return attributesBonus;
	}

	/**
	 * The required level to equip this object.
	 * 
	 * @return the level
	 */
	public int getLevel() {
		// TODO En fonction de la valeur.
		return 1;
	}

	/**
	 * Gets the slot.
	 * 
	 * @return the slot
	 */
	public abstract Slot getSlot();

	@Override
	public int getValue() {
		int value = this.getArmorBonus() * 50;
		for( Entry<Attribute, Integer> e : this.getAttributesBonus().entrySet() )
			value += e.getValue() * 50;
		return value;
	}

}
