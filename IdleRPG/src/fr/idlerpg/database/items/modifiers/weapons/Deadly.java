package fr.idlerpg.database.items.modifiers.weapons;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.WeaponModifier;

public class Deadly extends WeaponModifier {

	@Override
	public int getDamagesBonus() {
		return 3;
	}

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.EPIC;
	}

	@Override
	protected String getName() {
		return "Deadly";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
