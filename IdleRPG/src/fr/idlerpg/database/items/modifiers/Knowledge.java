package fr.idlerpg.database.items.modifiers;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.EquipmentModifier;

/**
 * The Class Knowledge.
 */
public class Knowledge extends EquipmentModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getAttributesBonus()
	 */
	@Override
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = super.getAttributesBonus();
		attributesBonus.put(Attribute.WISDOM, 1);
		return attributesBonus;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getModifierValue()
	 */
	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.MAGICAL;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getName()
	 */
	@Override
	protected String getName() {
		return "of Knowledge";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.RIGHT;
	}

}
