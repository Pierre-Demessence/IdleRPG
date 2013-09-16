package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Trousers extends Armor {

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.LEGS;
	}

	@Override
	protected String getBaseName() {
		return "Trousers";
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

	@Override
	protected int getBaseArmorBonus() {
		return 1;
	}

}
