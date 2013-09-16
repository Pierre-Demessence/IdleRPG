package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class LowBoots extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 2;
	}

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.FEET;
	}

	@Override
	protected String getBaseName() {
		return "Low Boots";
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

}
