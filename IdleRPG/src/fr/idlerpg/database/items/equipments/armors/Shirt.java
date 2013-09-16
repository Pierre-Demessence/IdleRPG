package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;

public class Shirt extends fr.idlerpg.item.Armor {

	@Override
	public EquipmentSlot getSlot() {
		// TODO Auto-generated method stub
		return EquipmentSlot.CHEST;
	}

	@Override
	protected String getBaseName() {
		return "Shirt";
	}

	@Override
	protected int getBaseArmorBonus() {
		return 1;
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

}
