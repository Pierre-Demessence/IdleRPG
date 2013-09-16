package fr.idlerpg.database.items.consumables;

import fr.idlerpg.item.Consumable;

public class Fruit extends Consumable {

	@Override
	public int getLifeGain() {
		return 5;
	}

	@Override
	public int getManaGain() {
		return 0;
	}

	@Override
	protected String getBaseName() {
		return "Fruit";
	}

}
