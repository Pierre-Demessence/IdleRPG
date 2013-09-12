/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
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

	/* (non-Javadoc)
	 * @see item.Equipment#getArmorBonus()
	 */
	@Override
	public int getArmorBonus() {
		return 0;
	}

	/**
	 * Gets the dammages.
	 * 
	 * @param c
	 *            the c
	 * @return the dammages
	 */
	public int getDammages(final Character c) {
		return this.getFormula().calculate(c) + this.getDammagesBonus();
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

	/* (non-Javadoc)
	 * @see item.Equipment#getValue()
	 */
	@Override
	public int getValue() {
		return super.getValue() + 100 + 50 * this.getDammagesBonus();
	}

	/**
	 * Gets the dammages bonus.
	 * 
	 * @return the dammages bonus
	 */
	public int getDammagesBonus() {
		return 0;
	}

}
