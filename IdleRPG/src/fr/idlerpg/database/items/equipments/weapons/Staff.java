package fr.idlerpg.database.items.equipments.weapons;

import fr.idlerpg.database.GlobalFormula;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Weapon;
import fr.idlerpg.util.Formula;

/**
 * The Class Staff.
 */
public abstract class Staff extends Weapon {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Weapon#getFormula()
	 */
	@Override
	public Formula getFormula() {
		return GlobalFormula.DAMAGE_STAFF.getFormula();
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.STAFF;
	}

}
