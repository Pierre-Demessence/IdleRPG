/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package database.items.useless;

import item.Item;
import database.items.Type;

/**
 * The Class Leather.
 */
public class Leather extends Item {

	/* (non-Javadoc)
	 * @see item.Item#getName()
	 */
	@Override
	public String getName() {
		return "Leather";
	}

	/* (non-Javadoc)
	 * @see item.Item#getType()
	 */
	@Override
	public Type getType() {
		return Type.USELESS;
	}

	/* (non-Javadoc)
	 * @see item.Item#getValue()
	 */
	@Override
	public int getValue() {
		return 5;
	}

}
