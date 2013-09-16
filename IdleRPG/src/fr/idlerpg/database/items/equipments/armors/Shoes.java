package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

/**
 * The Class Shoes.
 */
public class Shoes extends Armor {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getSlot()
	 */
	@Override
	public EquipmentSlot getSlot() {
		// TODO Auto-generated method stub
		return EquipmentSlot.FEET;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Armor#getBaseArmorBonus()
	 */
	@Override
	protected int getBaseArmorBonus() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Shoes";
	}

}
