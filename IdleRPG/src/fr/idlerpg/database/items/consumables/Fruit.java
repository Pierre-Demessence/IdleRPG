package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

/**
 * The Class Fruit.
 */
public class Fruit extends Consumable {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Consumable#getLifeGain()
	 */
	@Override
	public int getLifeGain() {
		return 5;
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
		return "Fruit";
	}

}
