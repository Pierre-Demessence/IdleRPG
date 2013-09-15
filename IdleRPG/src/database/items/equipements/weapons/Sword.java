package database.items.equipements.weapons;

import item.Weapon;
import util.Formula;
import database.GlobalFormula;
import database.items.ItemType;

public abstract class Sword extends Weapon {

	@Override
	public Formula getFormula() {
		return GlobalFormula.DAMAGE_SWORD.getFormula();
	}

	@Override
	public ItemType getType() {
		return ItemType.SWORD;
	}

}