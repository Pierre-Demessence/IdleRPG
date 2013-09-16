package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Greaves extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 2;
	}

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.LEGS;
	}

	@Override
	protected String getBaseName() {
		return "Greaves";
	}

	@Override
	public ItemType getType() {
		return ItemType.MEDIUM_ARMOR;
	}

}
