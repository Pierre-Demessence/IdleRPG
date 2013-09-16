package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

/**
 * The Class BigLifePotion.
 */
public class BigLifePotion extends Consumable {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Consumable#getLifeGain()
	 */
	@Override
	public int getLifeGain() {
		return 100;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Consumable#getManaGain()
	 */
	@Override
	public int getManaGain() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Big Life Potion";
	}

}
