package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

/**
 * The Class Platemail.
 */
public class Platemail extends Armor {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getSlot()
	 */
	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.CHEST;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.HEAVY_ARMOR;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Armor#getBaseArmorBonus()
	 */
	@Override
	protected int getBaseArmorBonus() {
		return 4;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Platemail";
	}

}
