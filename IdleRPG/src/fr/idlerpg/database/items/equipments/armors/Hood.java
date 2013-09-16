package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Hood extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 1;
	}

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.HEAD;
	}

	@Override
	protected String getBaseName() {
		return "Hood";
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

}
