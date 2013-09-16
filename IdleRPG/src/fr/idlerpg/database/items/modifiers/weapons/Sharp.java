package fr.idlerpg.database.items.modifiers.weapons;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.WeaponModifier;

public class Sharp extends WeaponModifier {

	@Override
	public int getDamagesBonus() {
		return 1;
	}

	@Override
	protected String getName() {
		return "Sharp";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.UNCOMMON;
	}

}
