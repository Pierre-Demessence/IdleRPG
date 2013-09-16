package fr.idlerpg.item;

import fr.idlerpg.database.factories.ItemFactory;
import fr.idlerpg.database.items.ItemType;

public abstract class Useless extends Item {

	@Override
	public ItemType getType() {
		return ItemType.USELESS;
	}

	@Override
	protected Object clone() {
		return ItemFactory.getUseless(this.getClass().getSimpleName());
	}

}