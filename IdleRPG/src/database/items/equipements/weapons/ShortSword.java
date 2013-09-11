package database.items.equipements.weapons;

import item.Type;
import item.Weapon;

import java.util.EnumMap;

import util.Formula;
import character.Attribute;
import database.GlobalFormula;

public class ShortSword extends Weapon {

	@Override
	public Formula getFormula() {
		return GlobalFormula.COMBAT_SWORD.getFormula();
	}

	@Override
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		return null;
	}

	@Override
	public String getName() {
		return "Short Sword";
	}

	@Override
	public Type getType() {
		return Type.SWORD;
	}

	@Override
	public int getValue() {
		return 20;
	}

}
