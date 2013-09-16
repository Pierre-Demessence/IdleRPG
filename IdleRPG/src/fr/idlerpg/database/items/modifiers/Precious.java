package fr.idlerpg.database.items.modifiers;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.EquipmentModifier;

/**
 * The Class Precious.
 */
public class Precious extends EquipmentModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getModifierValue()
	 */
	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.MAGICAL;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getValueBonus()
	 */
	@Override
	public int getValueBonus() {
		return 500;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getName()
	 */
	@Override
	protected String getName() {
		return "Precious";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
