package fr.idlerpg.database.items.equipments.armors;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.item.Armor;

/**
 * The Class Shield.
 */
public class Shield extends Armor {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getSlot()
	 */
	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.OFFHAND;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.HEAVY_SHIELD;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Armor#getBaseArmorBonus()
	 */
	@Override
	protected int getBaseArmorBonus() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getBaseAttributesBonus()
	 */
	@Override
	protected EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		final EnumMap<Attribute, Integer> baseAttributesBonus = super.getBaseAttributesBonus();
		baseAttributesBonus.put(Attribute.DEXTERITY, -1);
		return baseAttributesBonus;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Shield";
	}

}
