/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package fr.idlerpg.database.items.useless;

import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Item;

/**
 * The Class Leather.
 */
public class Leather extends Item {

	/* (non-Javadoc)
	 * @see item.Item#getName()
	 */
	@Override
	public String getBaseName() {
		return "Leather";
	}

	/* (non-Javadoc)
	 * @see item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.USELESS;
	}

	/* (non-Javadoc)
	 * @see item.Item#getValue()
	 */
	@Override
	public int getValue() {
		return 5;
	}

}
