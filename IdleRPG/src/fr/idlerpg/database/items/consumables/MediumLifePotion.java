package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

/**
 * The Class MediumLifePotion.
 */
public class MediumLifePotion extends Consumable {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Consumable#getLifeGain()
	 */
	@Override
	public int getLifeGain() {
		return 50;
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
		return "Medium Life Potion";
	}

}
