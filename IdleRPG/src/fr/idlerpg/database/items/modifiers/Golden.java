package fr.idlerpg.database.items.modifiers;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.EquipmentModifier;

/**
 * The Class Golden.
 */
public class Golden extends EquipmentModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getModifierValue()
	 */
	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.EPIC;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getValueBonus()
	 */
	@Override
	public int getValueBonus() {
		return 1000;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getName()
	 */
	@Override
	protected String getName() {
		return "Golden";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
