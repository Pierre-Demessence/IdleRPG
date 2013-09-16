package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Plategreaves extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 3;
	}

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.LEGS;
	}

	@Override
	protected String getBaseName() {
		return "Plategreaves";
	}

	@Override
	public ItemType getType() {
		return ItemType.HEAVY_ARMOR;
	}

}
