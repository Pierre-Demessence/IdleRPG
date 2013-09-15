package database.items.modifiers;

import item.EquipmentModifier;
import database.items.ModifierQuality;

public class Golden extends EquipmentModifier {

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.EPIC;
	}

	@Override
	protected String getName() {
		return "Golden";
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

	@Override
	public int getValueBonus() {
		return 1000;
	}

}
