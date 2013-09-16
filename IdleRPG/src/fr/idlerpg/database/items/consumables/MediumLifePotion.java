package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

public class MediumLifePotion extends Consumable {

	@Override
	public int getLifeGain() {
		return 50;
	}

	@Override
	public int getManaGain() {
		return 0;
	}

	@Override
	protected String getBaseName() {
		return "Medium Life Potion";
	}

}
