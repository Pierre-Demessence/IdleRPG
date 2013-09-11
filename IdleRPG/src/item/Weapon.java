/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:22
 */
package item;

import util.Formula;
import character.Character;
import database.items.Slot;

// TODO: Auto-generated Javadoc
/**
 * The Class Weapon.
 */
public abstract class Weapon extends Equipment {

	/**
	 * Gets the dammages.
	 * 
	 * @param c
	 *            the c
	 * @return the dammages
	 */
	public int getDammages(final Character c) {
		return this.getFormula().calculate(c);
	}

	/**
	 * Gets the formula.
	 * 
	 * @return the formula
	 */
	public abstract Formula getFormula();

	/* (non-Javadoc)
	 * @see item.Equipment#getSlot()
	 */
	@Override
	public Slot getSlot() {
		return Slot.MAINHAND;
	}

	@Override
	public int getArmorBonus() {
		return 0;
	}

}
