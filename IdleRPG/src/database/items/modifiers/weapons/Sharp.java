package database.items.modifiers.weapons;

import item.WeaponModifier;
import database.items.ModifierQuality;

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
