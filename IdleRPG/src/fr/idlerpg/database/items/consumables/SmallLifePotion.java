/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

/**
 * The Class SmallLifePotion.
 */
public class SmallLifePotion extends Consumable {

	/* (non-Javadoc)
	 * @see item.Item#getName()
	 */
	@Override
	public String getBaseName() {
		return "Small Life Potion";
	}

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

}
