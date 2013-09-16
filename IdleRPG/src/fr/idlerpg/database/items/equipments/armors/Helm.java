package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

/**
 * The Class Helm.
 */
public class Helm extends Armor {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getSlot()
	 */
	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.HEAD;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.MEDIUM_ARMOR;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Armor#getBaseArmorBonus()
	 */
	@Override
	protected int getBaseArmorBonus() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Helm";
	}

}
