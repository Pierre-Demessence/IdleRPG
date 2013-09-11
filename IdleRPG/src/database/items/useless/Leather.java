/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:20
 */
package database.items.useless;

import database.items.Type;
import item.Item;

// TODO: Auto-generated Javadoc
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
