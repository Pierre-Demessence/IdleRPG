package fr.idlerpg.database.items.modifiers.armors;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.ArmorModifier;

public class Reinforced extends ArmorModifier {

	@Override
	public int getArmorBonus() {
		return 2;
	}

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.MAGICAL;
	}

	@Override
	protected String getName() {
		return "Reinforced";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
