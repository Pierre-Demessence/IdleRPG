package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Necklace extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 0;
	}

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.NECK;
	}

	@Override
	public ItemType getType() {
		return ItemType.ACCESSORY;
	}

	@Override
	protected String getBaseName() {
		return "Necklace";
	}

}
