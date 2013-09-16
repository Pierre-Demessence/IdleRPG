package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Helm extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 2;
	}

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.HEAD;
	}

	@Override
	protected String getBaseName() {
		return "Helm";
	}

	@Override
	public ItemType getType() {
		return ItemType.MEDIUM_ARMOR;
	}

}
