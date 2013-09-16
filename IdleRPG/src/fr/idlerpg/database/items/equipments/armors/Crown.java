package fr.idlerpg.database.items.equipments.armors;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Crown extends Armor {

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
		return "Crown";
	}

	@Override
	public ItemType getType() {
		return ItemType.LIGHT_ARMOR;
	}

	@Override
	protected EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		EnumMap<Attribute, Integer> baseAttributesBonus = super.getBaseAttributesBonus();
		baseAttributesBonus.put(Attribute.CHARISMA, 1);
		return baseAttributesBonus;
	}

}
