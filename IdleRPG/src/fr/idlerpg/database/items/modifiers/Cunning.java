package fr.idlerpg.database.items.modifiers;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.EquipmentModifier;

public class Cunning extends EquipmentModifier {

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.MAGICAL;
	}

	@Override
	protected String getName() {
		return "of Cunning";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.RIGHT;
	}

	@Override
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		EnumMap<Attribute, Integer> attributesBonus = super.getAttributesBonus();
		attributesBonus.put(Attribute.INTELLIGENCE, 1);
		return attributesBonus;
	}

}
