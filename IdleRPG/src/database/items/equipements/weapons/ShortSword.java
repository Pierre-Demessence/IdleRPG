/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package database.items.equipements.weapons;

import item.Weapon;
import util.Formula;
import database.GlobalFormula;
import database.items.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class ShortSword.
 */
public class ShortSword extends Weapon {

	/* (non-Javadoc)
	 * @see item.Weapon#getFormula()
	 */
	@Override
	public Formula getFormula() {
		return GlobalFormula.COMBAT_SWORD.getFormula();
	}

	/* (non-Javadoc)
	 * @see item.Item#getName()
	 */
	@Override
	public String getName() {
		return "Short Sword";
	}

	/* (non-Javadoc)
	 * @see item.Item#getType()
	 */
	@Override
	public Type getType() {
		return Type.SWORD;
	}

}
