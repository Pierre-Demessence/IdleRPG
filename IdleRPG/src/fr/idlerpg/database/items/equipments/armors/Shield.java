package fr.idlerpg.database.items.equipments.armors;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

public class Shield extends Armor {

	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.OFFHAND;
	}

	@Override
	protected String getBaseName() {
		return "Shield";
	}

	@Override
	public ItemType getType() {
		return ItemType.HEAVY_SHIELD;
	}

	@Override
	protected int getBaseArmorBonus() {
		return 3;
	}

	@Override
	protected EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		EnumMap<Attribute, Integer> baseAttributesBonus = super.getBaseAttributesBonus();
		baseAttributesBonus.put(Attribute.DEXTERITY, -1);
		return baseAttributesBonus;
	}

}
