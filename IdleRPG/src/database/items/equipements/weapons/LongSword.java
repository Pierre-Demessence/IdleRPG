package database.items.equipements.weapons;

import item.Weapon;

import java.util.EnumMap;

import util.Formula;
import character.Attribute;
import database.GlobalFormula;
import database.items.Type;

/**
 * The Class LongSword.
 */
public class LongSword extends Weapon {

	/* (non-Javadoc)
	 * @see item.Equipment#getAttributesBonus()
	 */
	@Override
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = super.getAttributesBonus();
		attributesBonus.put(Attribute.CONSTITUTION, 2);
		return attributesBonus;
	}

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
		return "Long Sword";
	}

	/* (non-Javadoc)
	 * @see item.Item#getType()
	 */
	@Override
	public Type getType() {
		return Type.SWORD;
	}

}
