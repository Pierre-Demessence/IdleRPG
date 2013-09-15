package database.items.modifiers;

import item.EquipmentModifier;
import database.items.ModifierQuality;

public class Shiny extends EquipmentModifier {

	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.UNCOMMON;
	}

	@Override
	protected String getName() {
		return "Shiny";
	}

	@Override
	public int getValueBonus() {
		return 100;
	}

	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
