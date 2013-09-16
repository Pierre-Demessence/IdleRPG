package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Buckler extends Armor {

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.OFFHAND;
	}

	@Override
	protected String getBaseName() {
		return "Buckler";
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_SHIELD;
	}

	@Override
	public int getBaseArmorBonus() {
		return 1;
	}

}
