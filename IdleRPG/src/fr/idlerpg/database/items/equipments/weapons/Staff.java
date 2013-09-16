package fr.idlerpg.database.items.equipments.weapons;

import fr.idlerpg.database.GlobalFormula;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Weapon;
import fr.idlerpg.util.Formula;

public abstract class Staff extends Weapon {

	@Override
	public Formula getFormula() {
		return GlobalFormula.DAMAGE_STAFF.getFormula();
	}

	@Override
	public ItemType getType() {
		return ItemType.STAFF;
	}

}
