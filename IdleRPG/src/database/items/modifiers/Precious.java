package database.items.modifiers;

import item.EquipmentModifier;
import database.items.ModifierQuality;

public class Precious extends EquipmentModifier {

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.MAGICAL;
	}

	@Override
	protected String getName() {
		return "Precious";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

	@Override
	public int getValueBonus() {
		return 500;
	}

}
