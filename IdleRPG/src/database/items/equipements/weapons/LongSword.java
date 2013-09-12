package database.items.equipements.weapons;

import item.Weapon;

import java.util.EnumMap;

import util.Formula;
import character.Attribute;
import database.GlobalFormula;
import database.items.Type;

public class LongSword extends Weapon {

	@Override
	public Formula getFormula() {
		return GlobalFormula.COMBAT_SWORD.getFormula();
	}

	@Override
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		EnumMap<Attribute, Integer> attributesBonus = super.getAttributesBonus();
		attributesBonus.put(Attribute.CONSTITUTION, 2);
		return attributesBonus;
	}

	@Override
	public String getName() {
		return "Long Sword";
	}

	@Override
	public Type getType() {
		return Type.SWORD;
	}

}
