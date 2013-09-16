package fr.idlerpg.database.items.modifiers.armors;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.ArmorModifier;

public class Padded extends ArmorModifier {

	@Override
	public int getArmorBonus() {
		return 1;
	}

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.UNCOMMON;
	}

	@Override
	protected String getName() {
		return "Padded";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
