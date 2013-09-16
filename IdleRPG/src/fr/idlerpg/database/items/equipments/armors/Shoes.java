package fr.idlerpg.database.items.equipments.armors;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Shoes extends Armor {

	@Override
	protected int getBaseArmorBonus() {
		return 1;
	}

	@Override
	public EquipmentSlot getSlot() {
		// TODO Auto-generated method stub
		return EquipmentSlot.FEET;
	}

	@Override
	protected String getBaseName() {
		return "Shoes";
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

}
