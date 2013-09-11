/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:24
 */
package database.items.consumables;

import item.Consumable;

// TODO: Auto-generated Javadoc
/**
 * The Class SmallLifePotion.
 */
public class SmallLifePotion extends Consumable {

	/* (non-Javadoc)
	 * @see item.Consumable#getLifeGain()
	 */
	@Override
	public int getLifeGain() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see item.Consumable#getManaGain()
	 */
	@Override
	public int getManaGain() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see item.Item#getName()
	 */
	@Override
	public String getName() {
		return "Small Life Potion";
	}

	/* (non-Javadoc)
	 * @see item.Item#getValue()
	 */
	@Override
	public int getValue() {
		return 10;
	}

}
