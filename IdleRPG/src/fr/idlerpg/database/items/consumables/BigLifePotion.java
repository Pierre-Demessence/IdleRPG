package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

public class BigLifePotion extends Consumable {

	@Override
	public int getLifeGain() {
		return 100;
	}

	@Override
	public int getManaGain() {
		return 0;
	}

	@Override
	protected String getBaseName() {
		return "Big Life Potion";
	}

}
